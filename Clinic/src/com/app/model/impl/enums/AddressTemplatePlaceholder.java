/**
 * 
 */
package com.app.model.impl.enums;

/**
 * A Enumeration of possible PlaceHolder in the Address Template of a country.
 * 
 * @author jomu
 */
public enum AddressTemplatePlaceholder {

	/**
	 * Placeholder for the street attribute of a location.
	 */
	STREET("street"),
	/**
	 * Placeholder for the town attribute of a location.
	 */
	TOWN("town"),
	/**
	 * Placeholder for the postcode attribute of a location.
	 */
	POSTCODE("postcode"),
	/**
	 * Placeholder for the district attribute of a location.
	 */
	DISTRICT("district"),
	/**
	 * Placeholder for the state attribute of a location.
	 */
	STATE("state"),
	/**
	 * Placeholder for the county attribute of a location.
	 */
	COUNTY("county");

	private final String key;

	private AddressTemplatePlaceholder(String key) {
		this.key = key;
	}

	/**
	 * Get the Key which is used in the AddressTemplate.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns a AddressTemplatePlaceholder specified by the given {@code key}.
	 * 
	 * @param key
	 *            the key
	 * @return the placeholder to which the key belongs or {@code null}
	 */
	public static AddressTemplatePlaceholder getPlaceholder(String key) {
		for (AddressTemplatePlaceholder placeholder : AddressTemplatePlaceholder.values()) {
			if (placeholder.getKey().equals(key)) {
				return placeholder;
			}
		}
		return null;
	}
}
