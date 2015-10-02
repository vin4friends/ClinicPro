/**
 * 
 */
package com.app.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;

/**
 * Extension of the authentication interface.
 * 
 * @author jomu
 */
public interface LfAuthentication extends Authentication {

	enum ROLES {
		/**
		 * Super adminstrator role, extends ROLE_LOCATION_ADMIN and
		 * ROLE_SUPPORT_ADMIN. allows the user addiotnally to administer
		 * applications
		 */
		ROLE_SUPER_ADMIN,
		/** Allows the user to administer locations. */
		ROLE_LOCATION_ADMIN,
		/** Allows the user to administer users. */
		ROLE_SUPPORT_ADMIN
	}

	/**
	 * Countrycodes of countries that this user is allowed do administer.
	 * 
	 * @return a List of Countrycodes.
	 */
	Collection<String> getCountrycodes();

	/**
	 * Tests if this user is a super admin.
	 * 
	 * @return {@code true} if the user has the authority
	 *         {@code ROLE_SUPER_ADMIN}
	 */
	boolean isSuperAdmin();

	/**
	 * Tests if this user is a location admin.
	 * 
	 * @return {@code true} if the user has the authority
	 *         {@code ROLE_LOCATION_ADMIN}
	 */
	boolean isLocationAdmin();
}
