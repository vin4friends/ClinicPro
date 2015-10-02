/**
 * 
 */
package com.app.security;

import org.springframework.security.core.Authentication;

/**
 * @author jomu
 * 
 */
public class SecurityExpressionRoot extends org.springframework.security.access.expression.SecurityExpressionRoot {
	private PermissionEvaluator permissionEvaluator;
	private Object filterObject;
	private Object returnObject;

	public SecurityExpressionRoot(Authentication a) {
		super(a);
	}

	public boolean hasPermission(Object target) {
		return permissionEvaluator.hasPermission(authentication, target);
	}

	public boolean hasPermission(String countrycode) {
		return permissionEvaluator.hasPermission(authentication, countrycode);
	}

	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	public Object getFilterObject() {
		return filterObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}
}
