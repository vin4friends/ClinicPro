/**
 * 
 */
package com.app.common.fe.impl.validator;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

import javax.validation.ConstraintViolation;

import com.app.common.fe.impl.AbstractBaseAction;

/**
 * Interface which defines a Connector between Struts2 and HibernateValidator.
 * 
 * @author jomu
 */
public interface StrutsHibernateValidator {
	Collection<ConstraintViolation<?>> validate(AbstractBaseAction action, Locale clientLocale, ClassLoader classLoader)
			throws IOException;

	void addFieldErrors(AbstractBaseAction action, Collection<ConstraintViolation<?>> invalidValuesFromRequest);
}
