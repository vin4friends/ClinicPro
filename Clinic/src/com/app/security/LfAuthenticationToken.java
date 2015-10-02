/**
 * 
 */
package com.app.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author jomu
 * 
 */
public class LfAuthenticationToken extends UsernamePasswordAuthenticationToken implements LfAuthentication {

	private static final long serialVersionUID = 1L;

	private final Collection<String> countrycodes;

	/**
	 * This constructor should only be used by
	 * <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
	 * implementations that are satisfied with producing a trusted (i.e.
	 * {@link #isAuthenticated()} = <code>true</code>) authentication token.
	 * 
	 * @param principal
	 * @param authorities
	 * @param countrycodes
	 *            Countrycodes of countries that this user can administer.
	 */
	public LfAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities,
			Collection<String> countrycodes) {
		super(principal, null, authorities);
		this.countrycodes = countrycodes;
	}

	@Override
	public Collection<String> getCountrycodes() {
		return countrycodes;
	}

	@Override
	public boolean isSuperAdmin() {
		for (GrantedAuthority authority : getAuthorities()) {
			if (authority.getAuthority().equals(ROLES.ROLE_SUPER_ADMIN.name())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isLocationAdmin() {
		for (GrantedAuthority authority : getAuthorities()) {
			if (authority.getAuthority().equals(ROLES.ROLE_LOCATION_ADMIN.name())) {
				return true;
			}
		}

		return false;
	}
}
