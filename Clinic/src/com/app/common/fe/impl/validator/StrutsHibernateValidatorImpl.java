/**
 * 
 */
package com.app.common.fe.impl.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.engine.ConstraintViolationImpl;
import org.hibernate.validator.engine.PathImpl;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.ResourceBundleLocator;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

import com.app.common.fe.impl.AbstractBaseAction;
import com.app.message.GlobalMessageSource;
import com.opensymphony.xwork2.ActionContext;

/**
 * Implementation of StrutsHibernateValidator.
 * 
 * @author jomu
 */
@Component("strutsHibernateValidator")
public class StrutsHibernateValidatorImpl implements StrutsHibernateValidator {

	/**
	 * Reference to the ValidatorFactory. (Spring-Bean)
	 */
	@Resource
	private transient ValidatorFactory validatorFactory;

	@Resource
	private transient GlobalMessageSource globalMessageSource;

	private transient MessageInterpolator messageInterpolator;

	@Override
	public Collection<ConstraintViolation<?>> validate(AbstractBaseAction action, Locale clientLocale,
			ClassLoader classLoader) throws IOException {
		List<ConstraintViolation<?>> invalidValuesFromRequest = new ArrayList<ConstraintViolation<?>>();

		Validator validator = validatorFactory.usingContext().messageInterpolator(getMessageInterpolator())
				.getValidator();

		Set<ConstraintViolation<AbstractBaseAction>> constraintViolations = validator.validate(action);

		List<Path> invalidFieldNames = new ArrayList<Path>();
		Map<?, ?> parameters = ActionContext.getContext().getParameters();
		for (ConstraintViolation<AbstractBaseAction> constrantViolation : constraintViolations) {
			Path fieldPath = constrantViolation.getPropertyPath();
			if (invalidFieldNames.contains(fieldPath))
				continue;

			final String fieldPathString = fieldPath.toString();
			if (parameters.containsKey(fieldPathString)) {
				invalidValuesFromRequest.add(constrantViolation);
				invalidFieldNames.add(fieldPath);
			} else if (action.getValidationPathToFieldnameMap().containsKey(fieldPathString)) {
				String mappedPath = action.getValidationPathToFieldnameMap().get(fieldPathString);

				ConstraintViolation<AbstractBaseAction> mappedViolation = new ConstraintViolationImpl<AbstractBaseAction>(
						constrantViolation.getMessageTemplate(), constrantViolation.getMessage(),
						constrantViolation.getRootBeanClass(), constrantViolation.getRootBean(),
						constrantViolation.getLeafBean(), constrantViolation.getInvalidValue(),
						PathImpl.createPathFromString(mappedPath), constrantViolation.getConstraintDescriptor(), null);
				invalidValuesFromRequest.add(mappedViolation);
				invalidFieldNames.add(fieldPath);
			}
		}
		constraintViolations.clear();
		constraintViolations = null;
		invalidFieldNames.clear();
		invalidFieldNames = null;
		validator = null;
		return invalidValuesFromRequest;
	}

	@Override
	public void addFieldErrors(AbstractBaseAction action, Collection<ConstraintViolation<?>> invalidValuesFromRequest) {
		for (ConstraintViolation<?> constraintViolation : invalidValuesFromRequest) {
			StringBuilder sbMessage = new StringBuilder(action.getText(
					constraintViolation.getPropertyPath().toString(), ""));
			if (sbMessage.length() > 0)
				sbMessage.append(" - ");
			sbMessage.append(action.getText(constraintViolation.getMessage()));
			action.addFieldError(constraintViolation.getPropertyPath().toString(), sbMessage.toString());
		}
	}

	private MessageInterpolator getMessageInterpolator() {
		if (messageInterpolator == null) {
			final ResourceBundleLocator locator = new MessageSourceResourceBundleLocator(globalMessageSource);
			final MessageInterpolator interpolator = new ResourceBundleMessageInterpolator(locator);
			messageInterpolator = new LocaleContextMessageInterpolator(interpolator);
		}

		return messageInterpolator;
	}
}
