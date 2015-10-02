/**
 * 
 */
package com.app.admin.bl.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.app.model.impl.Country;
import com.app.model.impl.LfUser;
import com.app.model.impl.Role;
import com.app.wrapper.PasswordWrapper;

/**
 * @author Vinoth
 * 
 */
public interface UserAdministrationFacade {
	/**
	 * Finds all Users.
	 * 
	 * @return a list of all users known to the system.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	List<LfUser> findAllUser();

	/**
	 * Find a LfUser by Id.
	 * 
	 * @param id
	 *            the Id of the user to load.
	 * @return the found user or <code>null</code>
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	LfUser findUserById(Long id);

	/**
	 * Persists a new user with the given password to the underlaying database.
	 * 
	 * @param user
	 *            user to persist
	 * @param password
	 *            password to set for user
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void createUser(LfUser user, PasswordWrapper password);

	/**
	 * Persists a new user with the given password to the underlaying database.
	 * 
	 * @param user
	 *            user to persist
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void updateUser(LfUser user);

	/**
	 * Updates an existing user to the underlaying database.
	 * 
	 * @param user
	 *            to update
	 * @param newPassword
	 *            new password to set for user
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void updateUser(LfUser user, PasswordWrapper password);

	/**
	 * Deletes a user from the underlaying database.
	 * 
	 * @param user
	 *            to delete
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void deleteUser(LfUser user);

	/**
	 * Lock a user.
	 * 
	 * @param user
	 *            the user to lock.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void lockUser(LfUser user);

	/**
	 * Unlock user.
	 * 
	 * @param user
	 *            the user to unlock.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void unlockUser(LfUser user);

	/**
	 * Get List of avaible Roles in the system.
	 * 
	 * @return list of roles.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	List<Role> getAvailableRoles();

	/**
	 * Loads all countries from the underlying database.
	 * 
	 * @return a list of all countries.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	List<Country> findAllCountries();

	/**
	 * Generates a DEFAULT_PASSWORD_LENGTH password.
	 * 
	 * @return the generated password
	 */
	String getNewPassword();

	/**
	 * Retrieves the page size for pagination.
	 * 
	 * @return the page size for pagination.
	 */
	int getPageSize();
}
