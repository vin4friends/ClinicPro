/**
 * 
 */
package com.app.security;

/**
 * @author jomu
 * 
 */
public interface PasswordGenerator {

	int DEFAULT_PASSWORD_LENGTH = 8;

	/**
	 * Generates a DEFAULT_PASSWORD_LENGTH password.
	 * 
	 * @return the generated password
	 */
	String getNewPassword();

	/**
	 * Generates a password with the specified passwordLength.
	 * 
	 * @param passwordLength
	 *            length of the password
	 * @return the generated password
	 */
	String getNewPassword(int passwordLength);
}
