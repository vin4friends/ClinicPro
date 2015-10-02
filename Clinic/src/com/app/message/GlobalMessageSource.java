/**
 * 
 */
package com.app.message;

import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Supplies the message resources as spring-bean. every listen message resouce
 * file must to be in the classpath in order to work.
 * 
 * @author jomu
 */
@Component
public class GlobalMessageSource extends ResourceBundleMessageSource {
	/**
	 * The message resources of the application.
	 */
	protected static final String[] MESSAGE_SOURCES = {
			// common
			"i18n/common/commonGlobalMessages",
			// back office
			"i18n/admin/adminGlobalMessages" };

	/**
	 * Constructor. Apllies the message as message resources.
	 */
	public GlobalMessageSource() {
		setBasenames(MESSAGE_SOURCES);
		setAlwaysUseMessageFormat(true);
	}

	/**
	 * Retrieves the message associated to the {@code code}. is equivalent to
	 * {@code getMessage(code, null, locale)}.
	 * 
	 * @param code
	 *            the message key of the emssage to retrieve
	 * @param locale
	 *            the locale in which the message should be retrieved
	 * @return the localized message
	 * @throws NoSuchMessageException
	 *             if the message could not be found
	 * @see {@link org.springframework.context.MessageSource#getMessage(code, args, locale)}
	 */
	public String getMessage(String code, Locale locale) throws NoSuchMessageException {
		return getMessage(code, null, locale);
	}
}
