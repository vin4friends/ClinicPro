/**
 * 
 */
package com.app.model.impl.enums;

/**
 * The possible Unit of Measurements for distance display.
 * 
 * @author jomu
 */
public enum DistanceUnitOfMeasurement {
	/** show distance in mile. */
	MILE("entity.application.uomDistance.mile", 3959),
	/** show distance in kilometer. */
	KILOMETER("entity.application.uomDistance.kilometer", 6371);

	private final String messageKey;
	private final String messageKeyShort;
	private final double earthRadius;

	private DistanceUnitOfMeasurement(String messageKey, double earthRadius) {
		this.messageKey = messageKey;
		this.messageKeyShort = messageKey + ".short";
		this.earthRadius = earthRadius;
	}

	/**
	 * Gets the messageKey. Usable to get a localized display value.
	 * 
	 * @return the messageKey of a ResourceBundleEntry
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * Gets the messageKey for the shortname of the unit.
	 * 
	 * @return the shortMessageKey
	 */
	public String getShortMessageKey() {
		return messageKeyShort;
	}

	/**
	 * The earth radius in thi sunit of measurement.
	 * 
	 * @return the earth radius
	 */
	public double getEarthRadius() {
		return earthRadius;
	}
}
