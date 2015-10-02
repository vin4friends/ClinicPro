/**
 * 
 */
package com.app.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Constants for Logging.
 * <p />
 * <strong>Atention:</strong> The Attributes will also be used in
 * logback-configuration.xml. If a refactoring is necessary also update
 * logback-configuration.xml.
 * 
 * @author jomu
 * 
 */
public final class LoggingConstants {

	/**
	 * Private Constructor to prevent instantiation
	 */
	private LoggingConstants() {
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// ATTENTION: Please note the logback-configuration.xml commentar (see class
	// javadoc).
	// ///////////////////////////////////////////////////////////////////////////////////

	/**
	 * Marker name for import or export log messages.
	 */
	public static final String MARKER_NAME_IMPEX = "com.app.logging.LoggingConstants.MARKER_IMPEX";

	/**
	 * Marker for import or export log messages.
	 */
	public static final Marker MARKER_IMPEX = MarkerFactory.getMarker(MARKER_NAME_IMPEX);
}
