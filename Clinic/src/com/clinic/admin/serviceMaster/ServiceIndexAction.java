/**
 * 
 */
package com.clinic.admin.serviceMaster;

import java.util.List;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractListAction;
import com.clinic.admin.bl.serviceMaster.ServiceAdministrationFacade;
import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.model.impl.ServiceMaster;


/**
 * @author Vineet
 * 
 */
public class ServiceIndexAction extends AbstractListAction<ServiceMaster> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private transient ServiceAdministrationFacade serviceAdministrationFacade;

	@Override
	public List<ServiceMaster> getList() {
		System.err.println("here");
		return serviceAdministrationFacade.findAllService();
	}

	@Override
	protected int getPageSizeInternal() {
		return serviceAdministrationFacade.getPageSize();
	}

}
