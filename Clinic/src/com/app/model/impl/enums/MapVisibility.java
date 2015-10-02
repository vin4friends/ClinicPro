/**
 * 
 */
package com.app.model.impl.enums;

/**
 * Enum for the Options of Map Visibility.
 * 
 * @author jomu
 */
public enum MapVisibility {
	/** Always show the map and no map hide/show link. */
	ALWAYS_SHOWN("entity.application.map.visibility.always_shown"),
	/** show the map on entry and show a map hide/show link. */
	SHOWN_ON_ENTRY("entity.application.map.visibility.shown_on_entry"), //
	/** hide the map on page entry and show a map hide/show link. */
	HIDDEN_ON_ENTRY("entity.application.map.visibility.hidden_on_entry"), //
	/** never show the map and no map hide/show link. */
	NEVER_SHOWN("entity.application.map.visibility.never_shown");

	private final String messageKey;

	private MapVisibility(String messageKey) {
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
