/**
 * 
 */
package com.app.common.fe.impl.validator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.app.common.fe.impl.AbstractBaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.AnnotationUtils;

/**
 * @author jomu
 * 
 */
public class HibernateValidatorInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Resource
	private transient StrutsHibernateValidator validator;

	protected void doBeforeInvocation(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();

		if (!(action instanceof AbstractBaseAction)) {
			return;
		}

		AbstractBaseAction actionAs = (AbstractBaseAction) action;
		Locale clientLocale = actionAs.getLocale();

		Collection<ConstraintViolation<?>> invalidValuesFromRequest = validator.validate(actionAs, clientLocale,
				getClass().getClassLoader());

		if (invalidValuesFromRequest.isEmpty()) {
			log.debug("HibernateValidatorInterceptor found no erros.");
			return;
		} else {
			log.debug("HibernateValidatorInterceptor found " + actionAs.getFieldErrors().size() + " validation Errors.");
			validator.addFieldErrors(actionAs, invalidValuesFromRequest);
		}
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		if (!skipValidation(invocation)) {
			doBeforeInvocation(invocation);
		}
		return invocation.invoke();
	}

	@SuppressWarnings("unchecked")
	protected boolean skipValidation(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (action != null) {
			Method method = getActionMethod(action.getClass(), invocation.getProxy().getMethod());
			Collection<Method> annotatedMethods = AnnotationUtils.getAnnotatedMethods(action.getClass(),
					SkipValidation.class);
			if (annotatedMethods.contains(method))
				return true;

			// check if method overwites an annotated method
			Class<?> clazz = action.getClass().getSuperclass();
			while (clazz != null) {
				annotatedMethods = AnnotationUtils.getAnnotatedMethods(clazz, SkipValidation.class);
				if (annotatedMethods != null) {
					for (Method annotatedMethod : annotatedMethods) {
						if (annotatedMethod.getName().equals(method.getName())
								&& Arrays.equals(annotatedMethod.getParameterTypes(), method.getParameterTypes())
								&& Arrays.equals(annotatedMethod.getExceptionTypes(), method.getExceptionTypes()))
							return true;
					}
				}
				clazz = clazz.getSuperclass();
			}
		}

		return false;
	}

	protected Method getActionMethod(Class<?> actionClass, String methodName) throws NoSuchMethodException {
		Method method;
		try {
			method = actionClass.getMethod(methodName, new Class[0]);
		} catch (NoSuchMethodException e) {
			// hmm -- OK, try doXxx instead
			try {
				String altMethodName = "do" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
				method = actionClass.getMethod(altMethodName, new Class[0]);
			} catch (NoSuchMethodException e1) {
				// throw the original one
				throw e;
			}
		}
		return method;
	}
}
