/**
 * 
 */
package com.app.common.fe.impl;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Abstrakte Basis-Klasse für Actions.
 * 
 * @author jomu
 */
public abstract class AbstractBaseAction extends ActionSupport implements
		ServletRequestAware, Preparable {
	private static final long serialVersionUID = 1565034569017979949L;

	private HttpServletRequest request;

	/**
	 * This Map contains the mapping between the vaildation path and the field
	 * names. StrutsHibernateValidator will use this attribute to additionally
	 * map validation messages to the correct form fields.
	 */
	private final Map<String, String> validationPathToFieldnameMap;

	public AbstractBaseAction() {
		validationPathToFieldnameMap = Collections.emptyMap();
	}

	public AbstractBaseAction(Map<String, String> validationPathToFieldnameMap) {
		this.validationPathToFieldnameMap = validationPathToFieldnameMap;
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, String> getValidationPathToFieldnameMap() {
		return validationPathToFieldnameMap;
	}

	/**
	 * Returns the name of the scheme used to make this request, for example,
	 * <code>http</code>, <code>https</code>, or <code>ftp</code>. Different
	 * schemes have different rules for constructing URLs, as noted in RFC 1738.
	 * 
	 * @return a <code>String</code> containing the name of the scheme used to
	 *         make this request
	 * 
	 */
	public String getScheme() {
		return request.getScheme();
	}

	/**
	 * 
	 * Returns the portion of the request URI that indicates the context of the
	 * request. The context path always comes first in a request URI. The path
	 * starts with a "/" character but does not end with a "/" character. For
	 * servlets in the default (root) context, this method returns "". The
	 * container does not decode this string.
	 * 
	 * <p>
	 * It is possible that a servlet container may match a context by more than
	 * one context path. In such cases this method will return the actual
	 * context path used by the request and it may differ from the path returned
	 * by the {@link javax.servlet.ServletContext#getContextPath()} method. The
	 * context path returned by
	 * {@link javax.servlet.ServletContext#getContextPath()} should be
	 * considered as the prime or preferred context path of the application.
	 * 
	 * @return a <code>String</code> specifying the portion of the request URI
	 *         that indicates the context of the request
	 * 
	 * @see javax.servlet.ServletContext#getContextPath()
	 */
	public String getContextPath() {
		return request.getContextPath();
	}
}
