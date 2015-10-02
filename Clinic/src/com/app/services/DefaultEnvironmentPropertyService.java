/**
 * 
 */
package com.app.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.EnvironmentProperty;
import com.app.model.impl.EnvironmentProperty_;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.model.impl.EnvironmentProperty.StringProperty;

/**
 * Default implementation of EnvironmentPropertiesService.
 * 
 * @author jomu
 */
@Service("environmentPropertiesService")
@ThreadSafe
public class DefaultEnvironmentPropertyService implements EnvironmentPropertiesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEnvironmentPropertyService.class);

	/** Cache for EnvProps. */
	@GuardedBy("propertyCache")
	private Map<String, CacheEntry<EnvironmentProperty>> propertyCache = new HashMap<String, CacheEntry<EnvironmentProperty>>();

	/** Cache lifetime for environment properties. initial 60 seconds. */
	private static final long CACHE_LIFETIME_MILLIS = TimeUnit.MILLISECONDS.convert(60L, TimeUnit.SECONDS);

	@Resource
	private GenericDao dao;

	@Override
	public String getStringProperty(StringProperty property) {
		EnvironmentProperty result = retrieveEnvironmentProperty(property.getKey());
		if (result != null && result.getTvalue() != null) {
			return result.getTvalue();
		}
		LOGGER.warn("Environment property '{}' not found, using default value '{}'",
				new Object[] { property, property.getDefaultValue() });

		return property.getDefaultValue();
	}

	@Override
	public Integer getIntegerProperty(IntegerProperty property) {
		EnvironmentProperty result = retrieveEnvironmentProperty(property.getKey());
		if (result != null && result.getTvalue() != null) {
			try {
				return Integer.valueOf(result.getTvalue());
			} catch (NumberFormatException e) {
				LOGGER.error("Environment property '{}' found, but could not be interpreted as an "
						+ "Integer, using default value '{}'.", new Object[] { property, property.getDefaultValue() });
				return property.getDefaultValue();
			}
		}
		LOGGER.warn("Environment property '{}' not found, using default value '{}'",
				new Object[] { property, property.getDefaultValue() });

		return property.getDefaultValue();
	}

	// -----------------------------------------------------------------------------------------------------------------
	// private methods
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param key
	 *            key of property to retrieve
	 * @return EnvironmentProperty
	 */
	private EnvironmentProperty retrieveEnvironmentProperty(String key) {

		LOGGER.debug("Load Environment property '{}' from Cache", key);
		CacheEntry<EnvironmentProperty> cachedResult = propertyCache.get(key);

		boolean reload = false;
		if (cachedResult != null) { // is from cache
			LOGGER.debug("Environment property '{}' = {} was found in Cache", key, unwrap(cachedResult.getValue()));

			if (canEvictFromCache(cachedResult.getTimestamp())) {
				LOGGER.debug("Environment property '{}' is to old and will be replaced", key,
						cachedResult.getTimestamp());
				reload = true;
			}
		} else {
			LOGGER.debug("Environment property '{}' was not found in Cache", key);
			reload = true;
		}

		if (reload) {
			EnvironmentProperty envProp = (EnvironmentProperty) dao.findByUniqueAttributeN(EnvironmentProperty_.key, key);				
			
			cachedResult = new CacheEntry<EnvironmentProperty>(envProp, System.currentTimeMillis());
			synchronized (propertyCache) {
				LOGGER.debug("Systemkonstante '{}' = {} im Cache gespeichert", key, unwrap(envProp));
				propertyCache.put(key, cachedResult);
			}
		}

		return cachedResult.getValue();
	}

	/**
	 * 
	 * @param timestamplfus
	 *            Zeitstempel
	 * @return true oder false
	 */
	private boolean canEvictFromCache(long timestamp) {
		return (timestamp + CACHE_LIFETIME_MILLIS) < System.currentTimeMillis();
	}

	/**
	 * Returns the value of the Envirnment Property.
	 * 
	 * @param envProp
	 *            Enviroment Proeprty
	 * @return the value of Environment Property
	 */
	private Object unwrap(EnvironmentProperty envProp) {
		return envProp == null ? null : envProp.getTvalue();
	}

	// -----------------------------------------------------------------------------------------------------------------
	// private classes
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * CacheEntry
	 * 
	 * @param <T>
	 *            Type of Value in Cache
	 */
	private static class CacheEntry<T> {
		private T value;

		private long timestamp;

		public CacheEntry(T value, long timestamp) {
			this.value = value;
			this.timestamp = timestamp;
		}

		public T getValue() {
			return value;
		}

		public long getTimestamp() {
			return timestamp;
		}
	}
}
