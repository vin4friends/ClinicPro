/**
 * 
 */
package com.app.security;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;

/**
 * Re-Implementation of org.springframework.security.access.expression.method.
 * MethodSecurityEvaluationContext because this class is package private.
 * 
 * @author jomu
 */
public class LfMethodSecurityEvaluationContext extends StandardEvaluationContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(LfMethodSecurityEvaluationContext.class);

	private ParameterNameDiscoverer parameterNameDiscoverer;
	private boolean argumentsAdded;
	private MethodInvocation mi;

	/**
	 * Intended for testing. Don't use in practice as it creates a new parameter
	 * resolver for each instance. Use the constructor which takes the resolver,
	 * as an argument thus allowing for caching.
	 */
	public LfMethodSecurityEvaluationContext(Authentication user, MethodInvocation mi) {
		this(user, mi, new LocalVariableTableParameterNameDiscoverer());
	}

	public LfMethodSecurityEvaluationContext(Authentication user, MethodInvocation mi,
			ParameterNameDiscoverer parameterNameDiscoverer) {
		this.mi = mi;
		this.parameterNameDiscoverer = parameterNameDiscoverer;
	}

	@Override
	public Object lookupVariable(String name) {
		Object variable = super.lookupVariable(name);
		if (variable != null) {
			return variable;
		}

		if (!argumentsAdded) {
			addArgumentsAsVariables();
			argumentsAdded = true;
		}

		return super.lookupVariable(name);
	}

	public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
		this.parameterNameDiscoverer = parameterNameDiscoverer;
	}

	private void addArgumentsAsVariables() {
		Object[] args = mi.getArguments();

		if (args.length == 0) {
			return;
		}

		Object targetObject = mi.getThis();
		Method method = AopUtils.getMostSpecificMethod(mi.getMethod(), targetObject.getClass());
		String[] paramNames = parameterNameDiscoverer.getParameterNames(method);

		if (paramNames == null) {
			LOGGER.error("Unable to resolve method parameter names for method: {}. Debug symbol information"
					+ " is required if you are using parameter names in expressions.", method);
			return;
		}

		for (int i = 0; i < args.length; i++) {
			super.setVariable(paramNames[i], args[i]);
		}
	}
}
