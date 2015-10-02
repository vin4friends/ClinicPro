package com.app.security;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.ReflectionUtils;

import com.app.model.impl.Country;
import com.app.security.LfAuthentication;
import com.app.security.PermissionEvaluator;

/**
 * @author jomu
 */
public class PermissionEvaluatorImpl implements PermissionEvaluator {

	private final HashMap<Class<?>, Method> METHOD_CACHE = new HashMap<Class<?>, Method>();

	private static final String COUNTRY_GETTER = "get" + Country.class.getSimpleName();

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject) {
		final Country country = getCountryFromDomainObject(targetDomainObject);
		if (country == null) {
			return false;
		}
		return hasPermission(authentication, country.getCountrycode());
	}

	@Override
	public boolean hasPermission(Authentication authentication, String countrycode) {
		if (authentication instanceof LfAuthentication) {
			LfAuthentication auth = (LfAuthentication) authentication;

			// test for super user, which is allowed to do everything with every
			// country
			for (GrantedAuthority authority : auth.getAuthorities()) {
				if (authority.getAuthority().equals(LfAuthentication.ROLES.ROLE_SUPER_ADMIN.name())) {
					return true;
				}
			}

			return ((LfAuthentication) authentication).getCountrycodes().contains(countrycode);
		}
		return false;
	}

	private Country getCountryFromDomainObject(Object targetDomainObject) {
		if (targetDomainObject instanceof Country) {
			return (Country) targetDomainObject;
		}

		// try to find a getter for country
		final Class<?> targetClass = targetDomainObject.getClass();
		Method resultGetter = METHOD_CACHE.get(targetClass);

		if (resultGetter == null) {
			resultGetter = ReflectionUtils.findMethod(targetClass, COUNTRY_GETTER);
			METHOD_CACHE.put(targetClass, resultGetter);
		}

		if (resultGetter == null || !resultGetter.isAccessible() || !resultGetter.getReturnType().equals(Country.class)) {
			return null;
		}

		return (Country) ReflectionUtils.invokeMethod(resultGetter, targetDomainObject);
	}
}
