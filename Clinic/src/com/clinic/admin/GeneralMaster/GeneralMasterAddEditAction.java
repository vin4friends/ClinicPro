/**
 * 
 */
package com.clinic.admin.GeneralMaster;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractAddEditAction;
import com.clinic.admin.bl.generalMaster.GeneralMasterAdministrationFacade;
import com.clinic.admin.model.impl.GeneralMaster;


/**
 * @author Vineet
 * 
 */
public class GeneralMasterAddEditAction extends AbstractAddEditAction<GeneralMaster> {

	private static final long serialVersionUID = 1L;

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	@Resource
	private transient GeneralMasterAdministrationFacade facade;

	

	static {
		//VALIDATION_PATH_TO_FIELDNAME.put("model.countries", "selectedCountries");
		//VALIDATION_PATH_TO_FIELDNAME.put("model.grantedAuthorities", "selectedRole");
	}

	public GeneralMasterAddEditAction() {
		super("GeneralMaster", VALIDATION_PATH_TO_FIELDNAME);
	}

	@Override
	public void prepare() throws Exception {
		//System.err.println("test::" + model.id());
		
		if (model.getDocid() > 0) {
			model = facade.findGeneralMasterById(model.getDocid());
		}
		//availableCountries = facade.findAllCountries();
		//availableRoles = facade.getAvailableRoles();
	}

	@Override
	public String save() {
		
		System.err.println("here");
		if (METHOD_ADD.equals(getCurrentMethod())) {
			facade.createGeneralMaster(model);
		} else if (METHOD_EDIT.equals(getCurrentMethod())) {
				facade.updateGeneralMaster(model);
			
		}
		return SUCCESS;
	}

		

	@Override
	public void validate() {
		/*if (METHOD_ADD.equals(getCurrentMethod()) && CustomStringUtil.isBlank(password.getPassword())) {
			addFieldError("password.password", getText("entity.user.validation_error.password.blank"));
		}

		if (CustomStringUtil.isNotBlank(password.getPassword())
				&& !password.getPassword().equals(password.getPasswordConfirmation())) {
			addFieldError("password.password", getText("entity.user.password_mismatch"));
			addFieldError("password.passwordConfirmation", getText("entity.user.password_mismatch"));
		}*/
	}

	
	@Override
	protected GeneralMaster createModel() {
		return new GeneralMaster();
	}
}
