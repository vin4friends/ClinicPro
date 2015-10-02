/**
 * 
 */
package com.app.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility Class for Date functions.
 * 
 * @author jomu
 */
public final class DateUtil {
	/**
	 * private Konstruktor.
	 */
	private DateUtil() {
	}

	/**
	 * SLF4J Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * The key for the date-format-pattern.
	 */
	public static final String DATE_PATTERN_KEY = "common.datePattern";

	/** The key for the time-format-pattern. */
	public static final String TIME_PATTERN_KEY = "common.timePattern";

	/** The key for a time-format-pattern with seconds. */
	public static final String DATE_TIME_SECONDS_PATTERN_KEY = "common.dateTimeSecondsPattern";

	/** The key for a date-time-format-pattern. */
	public static final String DATE_TIME_PATTERN_KEY = "common.dateTimePattern";

	/**
	 * A day in milliseconds.
	 */
	public static final long ONE_DAY = 24L * 60 * 60 * 1000;

	/**
	 * date format according to RFC 3399.
	 */
	private static final String DATE_RFC_3399 = "yyyy-MM-dd'T'HH:mm:ss";

	/** a second in millisecods for calculations. */
	public static final float EINE_FLOAT_SEKUNDE = 1000f;

	/**
	 * Parses a String and returns a Date. The possible Date and the Pattern
	 * must not be {@code null} and there length must be equal.
	 * 
	 * @param dateToParse
	 *            the string to parse for a date
	 * @param pattern
	 *            the date pattern to use
	 * @return a Date object or {@code null}
	 */
	public static Date parseDate(String dateToParse, String pattern) {
		if (dateToParse == null || pattern == null) {
			return null;
		}
		if (dateToParse.length() != pattern.length()) {
			return null;
		}
		try {
			return ThreadLocalSDF.parse(pattern, dateToParse);
		} catch (IllegalArgumentException e) {
			LOG.debug("failed to convert Date: " + dateToParse + " - invalid pattern: " + pattern);
		} catch (ParseException e) {
			LOG.debug("failed to convert Date: " + dateToParse + " no parse");
		}
		return null;
	}

	/**
	 * Parses a String and returns a Date. The possible Date and the Pattern
	 * must not be {@code null} and there length must be equal.
	 * 
	 * @param dateToParse
	 *            the string to parse for a date
	 * @param pattern
	 *            the date pattern to use
	 * @return a Date object or {@code null}
	 */
	public static Date parseDateLenient(String dateToParse, String pattern) {
		if (dateToParse == null || pattern == null) {
			return null;
		}
		if (dateToParse.length() != pattern.length()) {
			return null;
		}
		try {
			return ThreadLocalSDF.parseLenient(pattern, dateToParse);
		} catch (IllegalArgumentException e) {
			LOG.debug("failed to convert Date: " + dateToParse + " - invalid pattern: " + pattern);
		} catch (ParseException e) {
			LOG.debug("failed to convert Date: " + dateToParse + " no parse");
		}
		return null;
	}

	/**
	 * Returns a Date as formatted String.
	 * 
	 * @param date
	 *            the date must not be null
	 * @param pattern
	 *            the pattern for formatting
	 * @return the formatted date string or {@code null}
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return ThreadLocalSDF.format(pattern, date);
	}

	/**
	 * Returns a Date as RFC 3399 formatted String.
	 * 
	 * @param date
	 *            the date to serialize
	 * @return the RFC 3399 formatted String
	 */
	public static String serializeDateToString(Date date) {
		return formatDate(date, DATE_RFC_3399);
	}

	/**
	 * Creates a Date object from a RFC 3399 formatted String.
	 * 
	 * @param rfc339Date
	 *            serialized date string
	 * @return Date
	 */
	public static Date serializeDateFromString(String rfc339Date) {
		return parseDateLenient(rfc339Date, DATE_RFC_3399);
	}

	/**
	 * returns the Date with the time set to 00:00:00.
	 * 
	 * @param date
	 *            the date
	 * @return date with time set to 00:00:00
	 */
	public static Date getDateWithTimeSetToMidnight(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getCalendarWithTimeSetToMidnight(calendar).getTime();
	}

	/**
	 * returns the Calendar with the time set to 00:00:00.
	 * 
	 * @param calendar
	 *            the calendar
	 * @return calendar with time set to 00:00:00
	 */
	public static Calendar getCalendarWithTimeSetToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * returns the Date with the time set to 23:59:59.999.
	 * 
	 * @param date
	 *            the date
	 * @return date with time set to 23:59:59.999
	 */
	public static Date getDateWithTimeSetToLastTimeOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getCalendarWithTimeSetToLastTimeOfDay(calendar).getTime();
	}

	/**
	 * returns the Calendar with the time set to 23:59:59.999.
	 * 
	 * @param calendar
	 *            the calendar
	 * @return calendar with time set to 23:59:59.999
	 */
	public static Calendar getCalendarWithTimeSetToLastTimeOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return calendar;
	}

	/**
	 * Indicates whether a date falls within a period of time. Start and end
	 * date are included.
	 * 
	 * @param dateToCompare
	 *            the date that should be within the period.
	 * @param startDate
	 *            the start of the period
	 * @param endeDate
	 *            the end date of the period
	 * 
	 * @return <code>true</code> if the date falls within the period of time
	 */
	public static boolean isBetween(Date dateToCompare, Date startDate, Date endeDate) {
		if (dateToCompare == null || startDate == null || endeDate == null) {
			throw new IllegalArgumentException("date must not be null!");
		}
		return !startDate.after(dateToCompare) && !endeDate.before(dateToCompare);

	}

	/**
	 * Creates a date from the two given strings for date and time.
	 * 
	 * @param dateStr
	 *            the String for the date (Day/Month/Year).
	 * @param datePattern
	 *            the pattern for parsing the date.
	 * @param timeStr
	 *            the string for the time (hour/minute/second/millisecond).
	 * @param timePattern
	 *            the pattern for parsing the time.
	 * 
	 * @return the created date or {@code null} if an error occured while
	 *         parsing the strings.
	 */
	public static Date getDate(String dateStr, String datePattern, String timeStr, String timePattern) {
		if (CustomStringUtil.isBlank(dateStr) && CustomStringUtil.isBlank(timeStr)) {
			return null;
		}

		Calendar cal = Calendar.getInstance();

		try {
			Date date = parseDate(dateStr, datePattern);
			cal.setTime(date);

			Date time = parseDate(timeStr, timePattern);
			Calendar timeCal = Calendar.getInstance();
			timeCal.setTime(time);

			cal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, timeCal.get(Calendar.MILLISECOND));
			return cal.getTime();

		} catch (Exception e) {
			LOG.info("Error while parsing the date" + " - date:" + dateStr + " datePattern: " + datePattern
					+ " - time:" + timeStr + " timePattern: " + timePattern, e);
			return null;
		}
	}

	/**
	 * Calaculates the days between to dates.
	 * 
	 * @param from
	 *            start date
	 * @param to
	 *            end date
	 * @return days between the to dates.
	 */
	public static long getDaysBetweenTwoDates(Date from, Date to) {
		Calendar start = Calendar.getInstance();
		start.setTime(from);
		Calendar end = Calendar.getInstance();
		end.setTime(to);
		start = getCalendarWithTimeSetToMidnight(start);
		end = getCalendarWithTimeSetToMidnight(end);

		long millis = end.getTimeInMillis() - start.getTimeInMillis();
		return millis / ONE_DAY;
	}
}
