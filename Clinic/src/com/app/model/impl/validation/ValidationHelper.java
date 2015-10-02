/**
 * 
 */
package com.app.model.impl.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.app.util.DateUtil;


/**
 * @author jomu
 * 
 */
public final class ValidationHelper {
	private static final Logger LOG = LoggerFactory.getLogger(ValidationHelper.class);

	/** Utility class should not have a public constructor. */
	private ValidationHelper() {
	}

	/**
	 * Creates a localized Message with arguments.
	 * 
	 * @param globalMessageSource
	 *            the ResourceBundleMessageSource
	 * @param messageKey
	 *            MessageResourceKey
	 * @param messageReplacements
	 *            List of objects which should be placed into the message
	 * @return the localized Message
	 */
	public static String getMessage(ResourceBundleMessageSource globalMessageSource, String messageKey,
			Object... messageReplacements) {
		if (messageReplacements.length > 0 && globalMessageSource != null) {
			Locale locale = LocaleContextHolder.getLocale();
			if (locale == null) {
				LOG.warn("Could not retrieve a locale for the message localization, default locale ENGLISH is used.");
				locale = Locale.ENGLISH;
			}

			if (locale != null) {
				String[] arguments = new String[messageReplacements.length];
				int index = 0;
				for (Object replacement : messageReplacements) {
					if (replacement instanceof Date) {
						Date date = (Date) replacement;
						arguments[index] = DateUtil.formatDate(date,
								globalMessageSource.getMessage(DateUtil.DATE_PATTERN_KEY, new Object[] {}, locale));
					} else if (replacement instanceof Calendar) {
						Calendar cal = (Calendar) replacement;
						String datePattern = globalMessageSource.getMessage(DateUtil.DATE_PATTERN_KEY, new Object[] {},
								locale);
						String timePattern = globalMessageSource.getMessage(DateUtil.TIME_PATTERN_KEY, new Object[] {},
								locale);
						arguments[index] = DateUtil.formatDate(cal.getTime(), datePattern + " " + timePattern);
					} else {
						arguments[index] = replacement.toString();
					}
					index++;
				}
				messageKey = globalMessageSource.getMessage(messageKey, arguments, locale);
			}
		}
		return messageKey;
	}

	/**
	 * Adds a ConstraintViolation to a field. This method is used bei
	 * FieldValidators.
	 * 
	 * @param constraintValidatorContext
	 *            the ConstraintValidatorContext of the field
	 * @param globalMessageSource
	 *            the ResourceBundleMessageSource
	 * @param messageKey
	 *            MessageResourceKey
	 * @param messageReplacements
	 *            List of objects which should be placed into the message
	 */
	public static void addConstraintViolation(ConstraintValidatorContext constraintValidatorContext,
			ResourceBundleMessageSource globalMessageSource, String messageKey, Object... messageReplacements) {
		String message = getMessage(globalMessageSource, messageKey, messageReplacements);
		constraintValidatorContext.disableDefaultConstraintViolation();
		constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}

	/**
	 * Adds a ConstraintViolation to a field. This method could be used by class
	 * validators, which would add a constraint violation to a specific field.
	 * 
	 * @param constraintValidatorContext
	 *            the ConstraintValidatorContext of the field
	 * @param fieldname
	 *            the field name to which the ConstraintViolation will be added.
	 * @param globalMessageSource
	 *            the ResourceBundleMessageSource
	 * @param messageKey
	 *            MessageResourceKey
	 * @param messageReplacements
	 *            List of objects which should be placed into the message
	 */
	public static void addConstraintViolation(ConstraintValidatorContext constraintValidatorContext, String fieldname,
			ResourceBundleMessageSource globalMessageSource, String messageKey, Object... messageReplacements) {
		String message = getMessage(globalMessageSource, messageKey, messageReplacements);
		constraintValidatorContext.disableDefaultConstraintViolation();
		constraintValidatorContext.buildConstraintViolationWithTemplate(message).addNode(fieldname)
				.addConstraintViolation();
	}
}
