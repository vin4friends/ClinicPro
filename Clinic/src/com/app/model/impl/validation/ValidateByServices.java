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
 * Validation of an array of {@link ValidateByService}.
 * 
 * @author jomu
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateByServicesValidator.class)
@Documented
public @interface ValidateByServices {
	/**
	 * Message als String.
	 */
	String message() default "Uniques check failed";

	/**
	 * Validierungsgruppen.
	 */
	Class<?>[] groups() default {};

	/**
	 * Payload.
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * Array mit zu validierenden {@link ValidateByService}.
	 */
	ValidateByService[] services();
}
