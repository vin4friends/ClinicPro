/**
 * 
 */
package com.app.model.impl.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.app.util.CustomStringUtil;
import com.app.validation.ServiceValidatorContext;

/**
 * Implementation of {@link ValidateByService}-Validator.
 * 
 * @author jomu
 */
public class ValidateByServiceValidator implements ConstraintValidator<ValidateByService, Object>,
		ApplicationContextAware {

	/**
	 * Reference to the EntityManager for setting the FlushMode.
	 */
	@PersistenceContext
	private transient EntityManager em;

	/**
	 * The Message-Ressource
	 */
	@Autowired(required = false)
	private transient ResourceBundleMessageSource globalMessageSource;

	/**
	 * @see ValidateByService#message()
	 */
	private String message;

	/** @see ValidateByService#messages() */
	private String[] messages;

	/**
	 * @see ValidateByService#serviceClass()
	 */
	private Class<?> serviceClass;

	/**
	 * @see ValidateByService#serviceName()
	 */
	private String serviceName;

	/**
	 * @see ValidateByService#serviceMethod()
	 */
	private String serviceMethod;

	/**
	 * @see ValidateByService#fieldname()
	 */
	private String fieldname;

	/**
	 * ApplicationContext.
	 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void initialize(ValidateByService constraintAnnotation) {
		this.message = constraintAnnotation.message();
		this.messages = constraintAnnotation.messages();
		this.serviceClass = constraintAnnotation.serviceClass();
		this.serviceName = constraintAnnotation.serviceName();
		this.serviceMethod = constraintAnnotation.serviceMethod();
		this.fieldname = constraintAnnotation.fieldname();

		if (!this.serviceMethod.startsWith("validate")) {
			throw new IllegalArgumentException("the given serviceMethod must begin with the prefix 'validate'!");
		}
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		FlushModeType type = em.getFlushMode();
		if (type != null) {
			em.setFlushMode(FlushModeType.COMMIT);
		}
		boolean isValid = true;
		try {
			List<Object> messageReplacements = new ArrayList<Object>();
			ServiceValidatorContext validatorContext = new ServiceValidatorContext();
			isValid = invokeValidation(applicationContext.getBean(serviceName, serviceClass), serviceClass,
					serviceMethod, value, messageReplacements, validatorContext);

			if (!isValid && !CustomStringUtil.isEmpty(fieldname)) {
				context.disableDefaultConstraintViolation();

				Integer msgPos = validatorContext.getMessagePosition();
				String msg = message;
				if (msgPos != null && messages.length > msgPos && CustomStringUtil.isNotBlank(messages[msgPos])) {
					msg = messages[msgPos];
				}

				message = ValidationHelper.getMessage(globalMessageSource, msg, messageReplacements.toArray());
				context.buildConstraintViolationWithTemplate(message).addNode(fieldname).addConstraintViolation();
			}

		} finally {
			if (type != null) {
				em.setFlushMode(type);
			}

		}
		return isValid;
	}

	/**
	 * Calls on a {@code serviceValidator} the method {@code method} with the
	 * arguments {@code object}. The method will be found an called by
	 * Reflection-API, so the declaring class {@code clazz} is needed.
	 * 
	 * @param serviceValidator
	 *            Validator
	 * @param clazz
	 *            ValidatorClass
	 * @param method
	 *            validate-method at the class
	 * @param object
	 *            object to validate.
	 * @param messageReplacements
	 *            an empty {@code List<Object>}, which can be filled in the
	 *            validate-method. if the list is filled after the call of the
	 *            validate-method, placeholder in the errormessage will be
	 *            replaced by the objects of the list.
	 * @param validatorContext
	 *            ein leere Context, der in validate befüllt werden kann
	 *            {@link ZvsServiceValidatorContextParam}.
	 * @return {@code true} if valid, otherwise {@code false}
	 */
	static boolean invokeValidation(Object serviceValidator, Class<?> clazz, String method, Object object,
			List<Object> messageReplacements, ServiceValidatorContext validatorContext) {

		try {
			// checks if the validation-method exists with an Object as
			// parameter, if so use it.
			Method m = BeanUtils.findMethod(clazz, method, Object.class);
			if (m != null) {
				return (Boolean) m.invoke(serviceValidator, object);
			}

			// checks if the validation-method exists with an Object and List
			// parameter.
			m = BeanUtils.findMethod(clazz, method, Object.class, List.class);
			if (m != null) {
				return (Boolean) m.invoke(serviceValidator, object, messageReplacements);
			}
			// checks if the validation-method exists with an Object, List and
			// Object parameters.
			m = BeanUtils.findMethod(clazz, method, Object.class, List.class, ServiceValidatorContext.class);
			if (m != null) {
				return (Boolean) m.invoke(serviceValidator, object, messageReplacements, validatorContext);
			}

			throw new IllegalArgumentException("The validation-method '" + method + "' of the class '" + clazz
					+ "' must have a method signature of one (Object) or two (Object, List<Object>) " + "parameter!");

		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
