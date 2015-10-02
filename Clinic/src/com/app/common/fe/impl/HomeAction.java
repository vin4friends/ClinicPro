/**
 * 
 */
package com.app.common.fe.impl;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Bei einer noch existierenden Session wird auf die IndexAction verwiesen,
 * sonst auf das Login.
 * 
 * @author jomu
 */
public class HomeAction extends AbstractBaseAction implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Weiterleitung zum Login
	 */
	private static final String LOGIN = "login";

	/**
	 * Weiterleitung zum Index
	 */
	private static final String INDEX = "index";

	@Override
	public String execute() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return LOGIN;
		}
		return INDEX;
	}
}
