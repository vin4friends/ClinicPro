/**
 * 
 */
package com.app.fe.web.admin.application;

import javax.annotation.Resource;

import com.app.admin.bl.application.ApplicationAdministrationFacade;
import com.app.common.fe.impl.AbstractDeleteAction;
import com.app.model.impl.Application;

/**
 * @author jomu
 * 
 */
public class ApplicationDeleteAction extends AbstractDeleteAction<Application> {

	private static final long serialVersionUID = 1L;

	@Resource
	private transient ApplicationAdministrationFacade facade;

	public ApplicationDeleteAction() {
		super("ApplicationDelete");
	}

	@Override
	public void prepare() throws Exception {
		model = facade.findApplication(model.getId());
	}

	@Override
	public String delete() {
		facade.deleteApplication(model);
		return SUCCESS;
	}

	@Override
	protected Application createModel() {
		return new Application();
	}

}
