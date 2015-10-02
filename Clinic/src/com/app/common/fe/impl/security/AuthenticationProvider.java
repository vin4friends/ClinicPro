/**
 * 
 */
package com.app.common.fe.impl.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.app.GenericDao;
import com.app.model.impl.Country;
import com.app.model.impl.LfUser;
import com.app.model.impl.LfUser_;
import com.app.model.impl.Role;
import com.app.security.LfAuthentication;
import com.app.security.LfAuthenticationToken;
import com.app.security.crypto.password.StandardPasswordEncoder;

/**
 * Adappter to Spring Security authentication. This AuthenticationProvider loads
 * users from the database and checks if the povided matches the password stored
 * in the database.
 * 
 * @author jomu
 */
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProvider.class);

	@Resource
	private transient GenericDao dao;

	private static final StandardPasswordEncoder PASSWORD_ENCODER = new StandardPasswordEncoder();

	/**
	 * To prevent blind sql injection exploits we will always use this password
	 * for comparison even if no user with the provided credentials could be
	 * found.
	 */
	private static final String DUMMY_TEST_PASSWORD = PASSWORD_ENCODER.encode("dummy_password");

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		final LfUser user = getUser(authentication.getPrincipal());

		if (user == null) {
			// found no user, but do a dummy compare
			PASSWORD_ENCODER.matches("nothing", DUMMY_TEST_PASSWORD);
			// now we can deny the authentication
			LOGGER.info("Denying login because of unknown username '{}'", authentication.getPrincipal());
			throw new BadCredentialsException("login.failure.badCredentials");
		}

		// check for password match
		if (!PASSWORD_ENCODER.matches((String) authentication.getCredentials(), user.getPassword())) {
			LOGGER.info("Denying login for user '{}' because wrong password provided.", user.getLogin());
			throw new BadCredentialsException("login.failure.badCredentials");
		}

		// check if the user is locked
		if (user.getIsLocked()) {
			LOGGER.info("Denying login for user '{}' because the user is locked.", user.getLogin());
			throw new BadCredentialsException("login.failure.userLocked");
		}

		// check for granted authorities
		if (user.getGrantedAuthorities() == null || user.getGrantedAuthorities().size() == 0) {
			LOGGER.info("Denying login for user '{}' because there are no roles associated with him.", user.getLogin());
			throw new BadCredentialsException("login.failure.badCredentials");
		}

		// All tests passed therefor authenticate the user
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(user.getGrantedAuthorities()
				.size());

		for (Role role : user.getGrantedAuthorities()) {
			grantedAuthorities.add(new GrantedAuthorityImpl(role.getAuthority()));
		}

		Set<String> countrycodes = new HashSet<String>(user.getCountries().size());
		for (Country country : user.getCountries()) {
			countrycodes.add(country.getCountrycode());
		}

		LfAuthentication succesToken = new LfAuthenticationToken(authentication.getPrincipal(), grantedAuthorities,
				countrycodes);

		return succesToken;
	}

	/**
	 * Searchs a user by login.
	 * 
	 * @param username
	 *            the username of the searched user.
	 * @return the user found or null if none or more than one user with this
	 *         username was found.
	 */
	private LfUser getUser(Object username) {
		try {
			return (LfUser) dao.findByUniqueAttributeN(LfUser_.login, username.toString());
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (Authentication.class.isAssignableFrom(authentication));
	}
}
