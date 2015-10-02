/**
 * 
 */
package com.app.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author jomu
 * 
 */
public final class NumberFormatUtil {

	private static final ThreadLocal<Map<Locale, Map<String, DecimalFormat>>> TL_DFS = new ThreadLocal<Map<Locale, Map<String, DecimalFormat>>>() {
		@Override
		protected Map<Locale, Map<String, DecimalFormat>> initialValue() {
			return new HashMap<Locale, Map<String, DecimalFormat>>();
		}
	};

	public static String format(String pattern, Double source) {
		return format(pattern, source, LocaleContextHolder.getLocale());
	}

	public static String format(String pattern, Double source, Locale locale) {
		return format(pattern, source, RoundingMode.HALF_UP, locale);
	}

	public static String format(String pattern, Double source, RoundingMode roundingMode, Locale locale) {
		DecimalFormat df = getDF(locale, pattern);
		if (roundingMode != null) {
			df.setRoundingMode(roundingMode);
		}
		String result = df.format(source);

		if (roundingMode != null) {
			df.setRoundingMode(RoundingMode.HALF_EVEN);
		}

		return result;
	}

	private static DecimalFormat getDF(Locale locale, String pattern) {
		Map<String, DecimalFormat> patternMap = TL_DFS.get().get(locale);
		if (patternMap == null) {
			patternMap = new HashMap<String, DecimalFormat>();
			TL_DFS.get().put(locale, patternMap);
		}

		DecimalFormat df = patternMap.get(pattern);
		if (df == null) {
			df = new DecimalFormat(pattern, new DecimalFormatSymbols(locale));
			patternMap.put(pattern, df);
		}

		return df;
	}

	private NumberFormatUtil() {

	}
}
