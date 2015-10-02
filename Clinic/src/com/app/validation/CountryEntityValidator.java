/**
 * 
 */
package com.app.validation;

import java.util.List;

/**
 * EntityValidator for {@code com.app.model.impl.Country}.
 * 
 * @author jomu
 */
public interface CountryEntityValidator {
	/**
	 * validates a country. checks if the defined maxLocationResult on the
	 * entity is valid. this attribute is valid if it is lower than the
	 * correspending environment property.
	 * 
	 * @param country
	 *            the country to validate
	 * @param messageArguments
	 *            filled by validate-Method to replace placehodler in error
	 *            message
	 * @return {@code true} if this attribute is valid, otherwise {@code false}
	 */
	boolean validateMaxLocationResult(Object country, List<Object> messageArguments);

	/**
	 * validates a country. checks if the defined addressTemplate contains only
	 * known placeholders.
	 * 
	 * @param country
	 *            the country to validate
	 * @param messageArguments
	 *            filled by validate-Method to replace placehodler in error
	 *            message
	 * @return{@code true} if this attribute is valid, otherwise {@code false}
	 */
	boolean validateAddressTemplate(Object country, List<Object> messageArguments);
}
