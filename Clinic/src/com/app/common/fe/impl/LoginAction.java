/**
 * 
 */
package com.app.common.fe.impl;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Login-Action for handling of Spring Security Excpetions. Those exceptions
 * will be converted to acion errors.
 * 
 * @author jomu
 */
public class LoginAction extends AbstractBaseAction implements SessionAware {
	private static final long serialVersionUID = -8653247651660590795L;

	/**
	 * the HTTP-Session as Map
	 */
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		Object springSecurityLastExceptionObject = session.get("SPRING_SECURITY_LAST_EXCEPTION");
		if (springSecurityLastExceptionObject != null) {
			BadCredentialsException badCredentialsException = (BadCredentialsException) springSecurityLastExceptionObject;
			this.addActionError(getText(badCredentialsException.getMessage()));
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * Getter-method for attribute session.
	 * 
	 * @return the attribute session
	 */
	public Map<String, Object> getSession() {
		return session;
	}
}
