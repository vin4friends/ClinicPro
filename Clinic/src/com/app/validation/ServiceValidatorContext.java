/**
 * 
 */
package com.app.validation;

/**
 * Possible Keys in the ServiceValidatorContext.
 * 
 * @author jomu
 */
public class ServiceValidatorContext {
	/**
	 * The position of the message to be used will specified in the
	 * validation-method. is this position null the default 'message' will be
	 * used.
	 */
	private Integer messagePosition;

	/** @return the position of the message to use. */
	public Integer getMessagePosition() {
		return messagePosition;
	}

	/**
	 * @param messagePosition
	 *            defines the position of the message to use.
	 */
	public void setMessagePosition(Integer messagePosition) {
		this.messagePosition = messagePosition;
	}
}
