/**
 * 
 */
package com.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates per thread as many {@link SimpleDateFormat} as needed (one for
 * each pattern).
 * 
 * <p>
 * Background: A constant recreation of @ code SimpleDateFormat} instances is
 * expansive and a ThreadLocal-approach is recommended, because of the worse
 * scaling for a synchronized approach.
 * </p>
 * 
 * <p>
 * The {@code SimpleDateFormat} is never exposed, because of its change ability.
 * </p>
 * 
 * @author jomu
 * @see http://www.thedwick.com/blog/2008/04/simpledateformat-performance-pig/
 * @see http://stackoverflow.com/questions/4107839/synchronizing-access-to-
 *      simpledateformat
 */
public final class ThreadLocalSDF {
	/**
	 * The internal {@link ThreadLocal}-Map with dynamiclly created SDF
	 * instances.
	 */
	private static final ThreadLocal<Map<String, SimpleDateFormat>> TL_SDFS = new ThreadLocal<Map<String, SimpleDateFormat>>() {
		@Override
		protected Map<String, SimpleDateFormat> initialValue() {
			return new HashMap<String, SimpleDateFormat>();
		}
	};

	/**
	 * Calls {@link SimpleDateFormat#parse(String)} on a possibly to create
	 * {@code SimpleDateFormat}. {@link SimpleDateFormat#setLenient(boolean)} is
	 * set to {@code false}.
	 * 
	 * @param pattern
	 *            the date pattern
	 * @param source
	 *            the formatted date
	 * @return the date object
	 * @throws ParseException
	 *             see {@link SimpleDateFormat#parse(String)}
	 */
	public static Date parse(String pattern, String source) throws ParseException {
		return getSDF(pattern).parse(source);
	}

	/**
	 * Calls {@link SimpleDateFormat#parse(String)} on a possibly to create
	 * {@code SimpleDateFormat}. {@link SimpleDateFormat#setLenient(boolean)} is
	 * set to {@code true}.
	 * 
	 * @param pattern
	 *            the date pattern
	 * @param source
	 *            the formatted date
	 * @return the date object
	 * @throws ParseException
	 *             see {@link SimpleDateFormat#parse(String)}
	 */
	public static Date parseLenient(String pattern, String source) throws ParseException {
		final SimpleDateFormat sdf = getSDF(pattern);
		sdf.setLenient(true);
		try {
			return sdf.parse(source);
		} finally {
			sdf.setLenient(false);
		}
	}

	/**
	 * Calls {@link SimpleDateFormat#parse(String)} on a possibly to create
	 * {@code SimpleDateFormat}.
	 * 
	 * @param pattern
	 *            the date pattern
	 * @param date
	 *            the date object
	 * @return the formatted date
	 */
	public static String format(String pattern, Date date) {
		return getSDF(pattern).format(date);
	}

	/**
	 * internally: retrieves the SDF for the given pattern. the SDF will be
	 * created if it does not exists.
	 * 
	 * @param pattern
	 *            the date pattern
	 * @return the SDF
	 */
	private static SimpleDateFormat getSDF(String pattern) {
		SimpleDateFormat sdf = TL_SDFS.get().get(pattern);
		if (sdf == null) {
			sdf = new SimpleDateFormat(pattern);
			sdf.setLenient(false);
			TL_SDFS.get().put(pattern, sdf);
		}
		return sdf;
	}

	/**
	 * private constructor.
	 */
	private ThreadLocalSDF() {
	}
}
