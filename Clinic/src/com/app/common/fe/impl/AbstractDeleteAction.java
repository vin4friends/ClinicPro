/**
 * 
 */
package com.app.common.fe.impl;

import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * @author jomu
 * 
 */
public abstract class AbstractDeleteAction<T> extends AbstractModelAction<T> {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private static final String METHOD_DELETE = "delete";

	public AbstractDeleteAction(String actionPrefix) {
		super(actionPrefix);
	}

	@SkipValidation
	@Override
	public String execute() throws Exception {
		setNextMethod(METHOD_DELETE);
		return INPUT;
	}

	public abstract String delete();
}
