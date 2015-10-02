/**
 * 
 */
package com.app.validation;

import java.util.List;

/**
 * EntityValidator for {@code com.app.model.impl.Application}.
 * 
 * @author jomu
 */
public interface ApplicationEntityValidator {

	/**
	 * validates an application. checks if the defined maxLocationResult on the
	 * entity is valid. this attribute is valid if it is lower than the
	 * correspending environment property.
	 * 
	 * @param application
	 *            the application to validate
	 * @param messageArguments
	 *            filled by validate-Method to replace placehodler in error
	 *            message
	 * @return {@code true} if this attribute is valid, otherwise {@code false}
	 */
	boolean validateMaxLocationResult(Object application, List<Object> messageArguments);

	/**
	 * validates an application. checks if the defined applicationKey is unique
	 * and if this application entity has already an id, checks whether the
	 * applicationKey does not have changed compared to that one stored in the
	 * database.
	 * 
	 * @param application
	 *            the application to validate
	 * @param messageArguments
	 *            filled by validate-Method to replace placehodler in error
	 *            message
	 * @return {@code true} if this attribute is valid, otherwise {@code false}
	 */
	boolean validateApplicationKey(Object application, List<Object> messageArguments);
}
