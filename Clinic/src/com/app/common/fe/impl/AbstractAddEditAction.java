/**
 * 
 */
package com.app.common.fe.impl;

import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * @author jomn
 * 
 */
public abstract class AbstractAddEditAction<T> extends AbstractModelAction<T> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	protected static final String METHOD_ADD = "add";

	protected static final String METHOD_EDIT = "edit";

	protected static final String METHOD_SAVE = "save";

	public AbstractAddEditAction(String actionPrefix, Map<String, String> validationPathToFieldnameMap) {
		super(actionPrefix, validationPathToFieldnameMap);
	}

	@SkipValidation
	public String create() {
		setCurrentMethod(METHOD_ADD);
		setNextMethod(METHOD_SAVE);
		return INPUT;
	}

	@SkipValidation
	public String edit() {
		setCurrentMethod(METHOD_EDIT);
		setNextMethod(METHOD_SAVE);
		return INPUT;
	}

	public abstract String save();
}
