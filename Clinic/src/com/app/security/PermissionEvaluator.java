/**
 * 
 */
package com.app.security;

import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.security.core.Authentication;

/**
 * Strategy used in expression evaluation to determine whether a user has a
 * permission for a given domain object. Inspired by Spring PermissionEvaluator.
 * 
 * @author jomu
 */
public interface PermissionEvaluator extends AopInfrastructureBean {
	/**
	 * @param authentication
	 *            represents the user in question. Should not be null.
	 * @param targetDomainObject
	 *            the domain object for which permissions should be checked. May
	 *            be null in which case implementations should return false, as
	 *            the null condition can be checked explicitly in the
	 *            expression.
	 * @return true if the permission is granted, false otherwise
	 */
	boolean hasPermission(Authentication authentication, Object targetDomainObject);

	/**
	 * Alternative method for evaluating a permission where only the identifier
	 * of the target object is available, rather than the target instance
	 * itself.
	 * 
	 * @param authentication
	 *            represents the user in question. Should not be null.
	 * @param countrycode
	 *            a String representing the target's country member. Not null.
	 * @return true if the permission is granted, false otherwise
	 */
	boolean hasPermission(Authentication authentication, String countrycode);
}
