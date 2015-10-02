/**
 * 
 */
package com.app.model.impl.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Class-Annotation for JSR-303-Validation with the help of a Spring service.
 * <p>
 * The Spring service must be declared by {@code serviceName},
 * {@link #serviceClass()} and {@link #validateMethod()} which should be called
 * for validating an object. The {@code validateMethod} will be called by
 * reflection and by errors calling the method an
 * {@code IllegalArgumentException} will be thrown.
 * </p>
 * The validation error will by default assiociated with class (the roor-element
 * in the validation tree}. By defining a {@link #fieldname()} the validation
 * error message will associated with this field.
 * 
 * @author jomu
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateByServiceValidator.class)
@Documented
public @interface ValidateByService {
	/**
	 * Message as String.
	 */
	String message() default "ValidateByService check failed";

	/**
	 * Alternative messages which can be selected in the validate-method.
	 */
	String[] messages() default {};

	/**
	 * Validation groups.
	 */
	Class<?>[] groups() default {};

	/**
	 * Payload.
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * Class of a Spring Bean (e.g. a Service).
	 */
	Class<?> serviceClass();

	/**
	 * Name of a Spring-Bean (e.g. name of the service).
	 */
	String serviceName();

	/**
	 * Name of validation method of the specified spring-bean. The method
	 * <b>must</b> begin with the prefix "validate" and except one parameter
	 * from type {@code Object} (the entity to validate), an
	 * {@code List<Object>} for messageArguments (optional) and returning a
	 * {@code boolean}.
	 */
	String serviceMethod() default "validate";

	/**
	 * Name des zu validierenden Feldes (optional).
	 */
	String fieldname() default "";
}
