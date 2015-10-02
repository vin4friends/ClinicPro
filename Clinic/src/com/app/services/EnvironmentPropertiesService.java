/**
 * 
 */
package com.app.services;

import com.app.model.impl.EnvironmentProperty;

/**
 * This Service retrieves values of environment properties. The keys for the
 * properties are specified in the entity {@code EnvironmentProperty}. For
 * Integer Enviroment properties exists the Key-Enum {@code IntegerProperty} and
 * for String Environment properties the Key-Enum {@code StringProperty}.
 * 
 * @author jomu
 */
public interface EnvironmentPropertiesService {

	/**
	 * Returns the value of the specified String environment property.
	 * 
	 * @param property
	 *            the property for which the value should be retrieved.
	 * @return the retrieved value for the property or the default value of the
	 *         property if it could not be found.
	 */
	String getStringProperty(EnvironmentProperty.StringProperty property);

	/**
	 * Returns the value of the specified Integer environment property.
	 * 
	 * @param property
	 *            the property for which the value should be retrieved.
	 * @return the retrieved value for the property or the default value of the
	 *         property if it could not be found.
	 */
	Integer getIntegerProperty(EnvironmentProperty.IntegerProperty property);
}
