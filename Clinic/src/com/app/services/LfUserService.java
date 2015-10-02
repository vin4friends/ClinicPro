/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.LfUser;
import com.app.model.impl.Role;
import com.app.wrapper.PasswordWrapper;

/**
 * Service for dealing with LfUser Entities
 * 
 * @author jomu
 */
public interface LfUserService {

	/**
	 * Find a LfUser by Id.
	 * 
	 * @param id
	 *            the Id of the user to load.
	 * @return the found user or <code>null</code>
	 */
	LfUser findById(Long id);

	/**
	 * Finds all Users.
	 * 
	 * @return a list of all users known to the system.
	 */
	List<LfUser> findAll();

	/**
	 * Persists a new user with the given password to the underlaying database.
	 * 
	 * @param user
	 *            user to persist
	 * @param password
	 *            password to set for user
	 */
	void create(LfUser user, PasswordWrapper password);

	/**
	 * Persists a new user with the given password to the underlaying database.
	 * 
	 * @param user
	 *            user to persist
	 */
	void update(LfUser user);

	/**
	 * Updates an existing user to the underlaying database.
	 * 
	 * @param user
	 *            to update
	 * @param newPassword
	 *            new password to set for user
	 */
	void update(LfUser user, PasswordWrapper password);

	/**
	 * Deletes a user from the underlaying database.
	 * 
	 * @param user
	 *            to delete
	 */
	void delete(LfUser user);

	/**
	 * Get List of avaible Roles in the system.
	 * 
	 * @return list of roles.
	 */
	List<Role> getAvailableRoles();

	/**
	 * Lock a user.
	 * 
	 * @param user
	 *            the user to lock.
	 */
	void lockUser(LfUser user);

	/**
	 * Unlock user.
	 * 
	 * @param user
	 *            the user to unlock.
	 */
	void unlockUser(LfUser user);
}
