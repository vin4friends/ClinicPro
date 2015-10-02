/**
 * 
 */
package com.app.security;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.app.security.PasswordGenerator;

/**
 * @author jomu
 * 
 */
@Component("passwordGenerator")
public class PasswordGeneratorImpl implements PasswordGenerator {

	private final SecureRandom random;

	/**
	 * Creates a secure random password generator.
	 */
	public PasswordGeneratorImpl() {
		this.random = new SecureRandom();
	}

	/**
	 * Set of characters that are valid, which are printable, memorable, and
	 * won't break HTML.
	 */
	private static final char[] CHARACTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9', '+',
			'-', '@', };

	@Override
	public String getNewPassword() {
		return getNewPassword(DEFAULT_PASSWORD_LENGTH);
	}

	@Override
	public String getNewPassword(int passwordLength) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < passwordLength; i++) {
			sb.append(CHARACTERS[random.nextInt(CHARACTERS.length)]);
		}
		return sb.toString();
	}
}
