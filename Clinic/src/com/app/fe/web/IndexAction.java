/**
 * 
 */
package com.app.fe.web;

import static com.app.security.LfAuthentication.ROLES.ROLE_LOCATION_ADMIN;
import static com.app.security.LfAuthentication.ROLES.ROLE_SUPER_ADMIN;
import static com.app.security.LfAuthentication.ROLES.ROLE_SUPPORT_ADMIN;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.app.common.fe.impl.AbstractBaseAction;
import com.app.security.LfAuthentication;

/**
 * Action which will handle the right start page for the user after a successful
 * login.
 * spring-web-3.2.0.RELEASE.jar 
 * @author jomu
 */
public class IndexAction extends AbstractBaseAction {
	private static final long serialVersionUID = -8466764594504390328L;
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexAction.class);

	private static final String LOCATION = "location";

	private static final String APPLICATION = "application";

	private static final String USER = "user";

	@Override
	public String execute() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof LfAuthentication) {
			LfAuthentication auth = (LfAuthentication) authentication;

			Set<String> roles = new HashSet<String>(auth.getAuthorities().size());

			for (GrantedAuthority authority : auth.getAuthorities()) {
				roles.add(authority.getAuthority());
			}

			// SUPER_ADMIN should start at application administration
			if (roles.contains(ROLE_SUPER_ADMIN.toString())) {
				LOGGER.debug("user seems to be a super admin, redirecting him to application administration.");
				return APPLICATION;
			}

			// LOCATION_ADMIN should start at location administration
			if (roles.contains(ROLE_LOCATION_ADMIN.toString())) {
				LOGGER.debug("user seems to be a location admin, redirecting him to location administration.");
				return LOCATION;
			}

			// SUPPORT_ADMIN should start at user administration
			if (roles.contains(ROLE_SUPPORT_ADMIN.toString())) {
				LOGGER.debug("user seems to be a support admin, redirecting him to user administration.");
				return USER;
			}
		}

		// Nothing found logout user
		LOGGER.error("unknown user authentication object found. redirect user to logout.");
		return super.execute();
	}
}
