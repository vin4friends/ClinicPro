/**
 * 
 */
package com.app.wrapper;

import java.io.Serializable;

/**
 * @author jomu
 */
public class PasswordWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String password;
	private String passwordConfirmation;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Password");
		if (password != null || passwordConfirmation != null) {
			builder.append("[");
			if (password != null) {
				builder.append("password = ********,");
			}
			if (passwordConfirmation != null) {
				builder.append("passwordConfirmation = ********");
			}
			builder.append("]");
		}
		return builder.toString();
	}
}
