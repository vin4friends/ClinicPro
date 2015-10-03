/**
 * 
 */
package com.clinic.admin.GeneralMaster;

import java.util.List;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractListAction;
import com.clinic.admin.bl.generalMaster.GeneralMasterAdministrationFacade;
import com.clinic.admin.model.impl.GeneralMaster;


/**
 * @author Vineet
 * 
 */
public class GeneralMasterIndexAction extends AbstractListAction<GeneralMaster> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private transient GeneralMasterAdministrationFacade generalMasterAdministrationFacade;

	@Override
	public List<GeneralMaster> getList() {
		System.err.println("in here");
		return generalMasterAdministrationFacade.findAllGeneralMaster();
	}

	@Override
	protected int getPageSizeInternal() {
		return generalMasterAdministrationFacade.getPageSize();
	}
}
