/**
 * 
 */
package com.clinic.admin.serviceMaster;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractDeleteAction;
import com.clinic.admin.bl.serviceGroup.ServiceGroupAdministrationFacade;
import com.clinic.admin.model.impl.ServiceGroup;

/**
 * @author Vinoth
 * 
 */
public class ServiceDeleteAction extends AbstractDeleteAction<ServiceGroup> {

	private static final long serialVersionUID = 1L;

	@Resource
	private transient ServiceGroupAdministrationFacade facade;

	public ServiceDeleteAction() {
		super("ServiceGroupDelete");
	}

	@Override
	public void prepare() throws Exception {
		model = facade.findServiceGroupById(model.getId());
	}

	@Override
	public String delete() {
		facade.deleteServiceGroup(model);
		return SUCCESS;
	}

	@Override
	protected ServiceGroup createModel() {
		return new ServiceGroup();
	}
}
