/**
 * 
 */
package com.app.common.fe.impl;

import java.util.Map;

import javax.validation.Valid;

/**
 * @author jomu
 * 
 */
public abstract class AbstractModelAction<T> extends AbstractBaseAction {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	protected T model;

	private final String actionPrefix;

	private String currentMethod;

	private String nextMethod;

	public AbstractModelAction(String actionPrefix) {
		this.actionPrefix = actionPrefix;
		model = createModel();
	}

	public AbstractModelAction(String actionPrefix, Map<String, String> validationPathToFieldnameMap) {
		super(validationPathToFieldnameMap);
		this.actionPrefix = actionPrefix;
		model = createModel();
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	protected abstract T createModel();

	public String getActionPrefix() {
		return actionPrefix;
	}

	public String getNextStep() {
		return actionPrefix + "_" + nextMethod;
	}

	public String getNextMethod() {
		return nextMethod;
	}

	public void setNextMethod(String nextMethod) {
		this.nextMethod = nextMethod;
	}

	public String getCurrentMethod() {
		return currentMethod;
	}

	public void setCurrentMethod(String currentMethod) {
		this.currentMethod = currentMethod;
	}
}
