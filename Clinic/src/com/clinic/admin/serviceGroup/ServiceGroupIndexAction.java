/**
 * 
 */
package com.clinic.admin.serviceGroup;

import java.util.List;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractListAction;
import com.clinic.admin.bl.serviceGroup.ServiceGroupAdministrationFacade;
import com.clinic.admin.model.impl.ServiceGroup;


/**
 * @author Vinoth
 * 
 */
public class ServiceGroupIndexAction extends AbstractListAction<ServiceGroup> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private transient ServiceGroupAdministrationFacade serviceGroupAdministrationFacade;

	@Override
	public List<ServiceGroup> getList() {
		System.err.println("in here");
		return serviceGroupAdministrationFacade.findAllServiceGroup();
	}

	@Override
	protected int getPageSizeInternal() {
		return serviceGroupAdministrationFacade.getPageSize();
	}
}
