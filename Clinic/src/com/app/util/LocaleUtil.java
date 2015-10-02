/**
 * 
 */
package com.app.util;

import java.util.Locale;

/**
 * Utility Class for Locale functions.
 * 
 * @author jomn
 */
public final class LocaleUtil {
	/**
	 * private Konstruktor.
	 */
	private LocaleUtil() {
	}

	// /**
	// * SLF4J Logger.
	// */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(LocaleUtil.class);

	/**
	 * The seperator char in locale string.
	 */
	public static final String LOCALE_SEPERATOR = "-";

	public static String getDBLocale(Locale locale) {
		return locale.getLanguage() + LOCALE_SEPERATOR + locale.getCountry();
	}

}
