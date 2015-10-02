/**
 * 
 */
package com.app.validation;

import java.util.List;

/**
 * EntityValidator for {@code com.app.model.impl.LfUser}.
 * 
 * @author jomu
 */
public interface LfUserEntityValidator {

	/**
	 * validates the countries attribute of a user with the role
	 * {@code ROLE_LOCATION_ADMIN}. a user with this role must have at least one
	 * country defined.
	 * 
	 * @param lfUser
	 *            the user to validate
	 * @param messageArguments
	 *            the mesageArguments for the error message. will be filled in
	 *            the validation method.
	 * @return {@code true} when at least one country is associated with the
	 *         user, otherwise {@code false}
	 */
	boolean validateLocationAdminCountries(Object lfUser, List<Object> messageArguments);

	/**
	 * validates the countries attribute of a user with the role
	 * {@code ROLE_SUPPORT_ADMIN} or {@code ROLE_SUPER_ADMIN}. a user with one
	 * of this roles must have no countries defined.
	 * 
	 * @param lfUser
	 *            the user to validate
	 * @param messageArguments
	 *            the mesageArguments for the error message. will be filled in
	 *            the validation method.
	 * @return {@code true} when no country is associated with the user,
	 *         otherwise {@code false}
	 */
	boolean validateSupportAndSuperAdminCountries(Object lfUser, List<Object> messageArguments);

	/**
	 * validates the login attribute of a user which must be unique.
	 * 
	 * @param lfUser
	 *            the user to validate
	 * @param messageArguments
	 *            the mesageArguments for the error message. will be filled in
	 *            the validation method.
	 * @return {@code true} if the login of the user is unique
	 */
	boolean validateUniqueLogin(Object lfUser, List<Object> messageArguments);
}
