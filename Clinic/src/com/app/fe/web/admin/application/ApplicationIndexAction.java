/**
 * 
 */
package com.app.fe.web.admin.application;

import java.util.List;

import javax.annotation.Resource;

import com.app.admin.bl.application.ApplicationAdministrationFacade;
import com.app.common.fe.impl.AbstractListAction;
import com.app.model.impl.Application;

/**
 * @author jomu
 */
public class ApplicationIndexAction extends AbstractListAction<Application> {
	private static final long serialVersionUID = 2208770026767183224L;

	@Resource
	private transient ApplicationAdministrationFacade facade;

	@Override
	public List<Application> getList() {
		return facade.findAll();
	}

	@Override
	protected int getPageSizeInternal() {
		return facade.getPageSize();
	}
}
