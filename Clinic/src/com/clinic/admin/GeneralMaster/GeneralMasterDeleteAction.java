/**
 * 
 */
package com.clinic.admin.GeneralMaster;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractDeleteAction;
import com.clinic.admin.bl.generalMaster.GeneralMasterAdministrationFacade;
import com.clinic.admin.model.impl.GeneralMaster;

/**
 * @author Vineet
 * 
 */
public class GeneralMasterDeleteAction extends AbstractDeleteAction<GeneralMaster> {

	private static final long serialVersionUID = 1L;

	@Resource
	private transient GeneralMasterAdministrationFacade facade;

	public GeneralMasterDeleteAction() {
		super("GeneralMasterDelete");
	}

	@Override
	public void prepare() throws Exception {
		model = facade.findGeneralMasterById(model.getDocid());
	}

	@Override
	public String delete() {
		facade.deleteGeneralMaster(model);
		return SUCCESS;
	}

	@Override
	protected GeneralMaster createModel() {
		return new GeneralMaster();
	}
}
