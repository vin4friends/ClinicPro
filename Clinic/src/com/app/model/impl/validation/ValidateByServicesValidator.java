/**
 * 
 */
package com.app.model.impl.validation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.app.util.CustomStringUtil;
import com.app.validation.ServiceValidatorContext;

/**
 * Implementation of {@link ValidateByServices}-Validator.
 * 
 * @author jomu
 */
public class ValidateByServicesValidator implements ConstraintValidator<ValidateByServices, Object>,
		ApplicationContextAware {

	/**
	 * Reference to the EntityManager for setting the FlushMode.
	 */
	@PersistenceContext
	private transient EntityManager em;

	/**S
	 * the Message-Ressource
	 */
	@Autowired(required = false)
	private transient ResourceBundleMessageSource globalMessageSource;

	/**
	 * Array of to validate {@link ValidateByService}.
	 */
	private ValidateByService[] services;

	/**
	 * ApplicationContext.
	 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void initialize(ValidateByServices constraintAnnotation) {
		services = constraintAnnotation.services();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		FlushModeType type = em.getFlushMode();
		if (type != null) {
			em.setFlushMode(FlushModeType.COMMIT);
		}

		boolean allValid = true;
		try {
			for (ValidateByService v : services) {

				List<Object> messageReplacements = new ArrayList<Object>();
				ServiceValidatorContext validatorContext = new ServiceValidatorContext();
				boolean isValid = ValidateByServiceValidator.invokeValidation(
						applicationContext.getBean(v.serviceName(), v.serviceClass()), v.serviceClass(),
						v.serviceMethod(), value, messageReplacements, validatorContext);
				if (!isValid) {
					context.disableDefaultConstraintViolation();

					Integer msgPos = (Integer) validatorContext.getMessagePosition();
					String msg = v.message();
					if (msgPos != null && v.messages().length > msgPos && CustomStringUtil.isNotBlank(v.messages()[msgPos])) {
						msg = v.messages()[msgPos];
					}

					String message = ValidationHelper.getMessage(globalMessageSource, msg,
							messageReplacements.toArray());

					if (!CustomStringUtil.isEmpty(v.fieldname())) {
						context.buildConstraintViolationWithTemplate(message).addNode(v.fieldname())
								.addConstraintViolation();
					} else {
						context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
					}
					allValid = false;
				}
			}
		} finally {
			if (type != null) {
				em.setFlushMode(type);
			}
		}

		return allValid;
	}
}
