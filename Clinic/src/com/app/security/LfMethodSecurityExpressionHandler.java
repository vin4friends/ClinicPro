/**
 * 
 */
package com.app.security;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

/**
 * @author jomu
 * 
 */
public class LfMethodSecurityExpressionHandler implements MethodSecurityExpressionHandler {
	/**
	 * SLF4J Logger für Logging-Ausgaben.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LfMethodSecurityExpressionHandler.class);

	/**
	 * parameterNameDiscoverer
	 */
	private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

	/**
	 * permissionChecker
	 */
	private PermissionEvaluator permissionEvaluator;

	/**
	 * trustResolver
	 */
	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	/**
	 * expressionParser
	 */
	private ExpressionParser expressionParser = new SpelExpressionParser();

	/**
	 * roleHierarchy
	 */
	private RoleHierarchy roleHierarchy;

	@Override
	public ExpressionParser getExpressionParser() {
		return expressionParser;
	}

	@Override
	public EvaluationContext createEvaluationContext(Authentication authentication, MethodInvocation mi) {
		LfMethodSecurityEvaluationContext ctx = new LfMethodSecurityEvaluationContext(authentication, mi,
				parameterNameDiscoverer);
		SecurityExpressionRoot root = new SecurityExpressionRoot(authentication);
		root.setTrustResolver(trustResolver);
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setRoleHierarchy(roleHierarchy);
		ctx.setRootObject(root);

		return ctx;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object filter(Object filterTarget, Expression filterExpression, EvaluationContext ctx) {
		List<Object> retainList;

		SecurityExpressionRoot rootObject = (SecurityExpressionRoot) ctx.getRootObject().getValue();
		LOGGER.debug("Filtering with expression: {}", filterExpression.getExpressionString());

		if (filterTarget instanceof Collection) {
			Collection<Object> collection = (Collection<Object>) filterTarget;
			retainList = new ArrayList<Object>(collection.size());

			LOGGER.debug("Filtering collection with {} elements", collection.size());
			for (Object filterObject : (Collection<Object>) filterTarget) {
				rootObject.setFilterObject(filterObject);

				if (ExpressionUtils.evaluateAsBoolean(filterExpression, ctx)) {
					retainList.add(filterObject);
				}
			}

			LOGGER.debug("Retaining collection with {} elements", retainList.size());

			collection.clear();
			collection.addAll(retainList);

			return filterTarget;
		}

		if (filterTarget.getClass().isArray()) {
			Object[] array = (Object[]) filterTarget;
			retainList = new ArrayList<Object>(array.length);

			LOGGER.debug("Filtering collection with {} elements", array.length);

			for (int i = 0; i < array.length; i++) {
				rootObject.setFilterObject(array[i]);

				if (ExpressionUtils.evaluateAsBoolean(filterExpression, ctx)) {
					retainList.add(array[i]);
				}
			}

			LOGGER.debug("Retaining collection with {} elements", retainList.size());

			Object[] filtered = (Object[]) Array.newInstance(filterTarget.getClass().getComponentType(),
					retainList.size());
			for (int i = 0; i < retainList.size(); i++) {
				filtered[i] = retainList.get(i);
			}

			return filtered;
		}

		throw new IllegalArgumentException("Filter target must be a collection or array type, but was " + filterTarget);
	}

	@Override
	public void setReturnObject(Object returnObject, EvaluationContext ctx) {
		((SecurityExpressionRoot) ctx.getRootObject().getValue()).setReturnObject(returnObject);
	}

	public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
		this.parameterNameDiscoverer = parameterNameDiscoverer;
	}

	public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	private PermissionEvaluator getPermissionEvaluator() {
		if (permissionEvaluator == null) {
			throw new IllegalStateException(
					"MethodSecurityExpressionHandler not correct initialized PermissionEvaluator ist null.");
		}

		return permissionEvaluator;
	}

	public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
		this.trustResolver = trustResolver;
	}

	public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
		this.roleHierarchy = roleHierarchy;
	}
}
