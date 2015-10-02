/**
 * 
 */
package com.app;



import com.app.util.CustomStringUtil;

/**
 * Util to help handle language strings.
 * 
 * @author jomu
 */
public final class LanguageUtil {

	private static final String SEPERATOR = "-";

	/**
	 * returns the next candidate of language from a given language string.
	 * which will be substring before the last {@code "-"}. if no such sperator
	 * is found in the string or the string is blank than {@code null} will be
	 * returned.
	 * 
	 * @param language
	 *            the language string to handle
	 * @return the substring before last {@code "-"} or <code>null</code>
	 */
	public static String getNextLanguageCandidate(String language) {
		if (CustomStringUtil.isBlank(language) || !CustomStringUtil.contains(language, SEPERATOR)) {
			return null;
		}

		return CustomStringUtil.substringBeforeLast(language, "-");
	}

	/**
	 * Returns an array of language candidates extracted from the given language
	 * string. Each entry will be the prior entry substring before the last
	 * {@code "-"}. The first entry will be the provided {@code language}
	 * string.
	 * 
	 * @param language
	 *            the language string to split
	 * @return the language candiates array or {@code null} if the given
	 *         {@code language} string was blank.
	 */
	@SuppressWarnings("deprecation")
	public static String[] getLanguageCandidates(final String language) {
		if (CustomStringUtil.isBlank(language)) {
			return null;
		}

		if (!CustomStringUtil.contains(language, SEPERATOR)) {
			return new String[] { language };
		}

		String workLanguage = language;
		final int sepCount = CustomStringUtil.countMatches(language, SEPERATOR);
		final String[] result = new String[sepCount + 1];
		result[0] = language;

		for (int i = 0; i < sepCount; i++) {
			workLanguage = getNextLanguageCandidate(workLanguage);
			result[i + 1] = workLanguage;
		}

		return result;
	}

	private LanguageUtil() {

	}
}
