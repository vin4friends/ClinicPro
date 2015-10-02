/**
 * 
 */
package com.app.model.impl.enums;

/**
 * The possible types of an application.
 * 
 * @author jomu
 */
public enum ApplicationType {

	/** defines a intranet application. */
	INTRANET("entity.application.type.intranet"),
	/** defines a internet application. */
	INTERNET("entity.application.type.internet");

	private final String messageKey;

	private ApplicationType(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * Gets the messageKey. Usable to get a localized display value.
	 * 
	 * @return the messageKey of a ResourceBundleEntry
	 */
	public String getMessageKey() {
		return messageKey;
	}
}
