/**
 * 
 */
package com.clinic.admin.serviceGroup;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractAddEditAction;
import com.clinic.admin.bl.serviceGroup.ServiceGroupAdministrationFacade;
import com.clinic.admin.model.impl.ServiceGroup;


/**
 * @author Vinoth
 * 
 */
public class ServiceGroupAddEditAction extends AbstractAddEditAction<ServiceGroup> {

	private static final long serialVersionUID = 1L;

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	@Resource
	private transient ServiceGroupAdministrationFacade facade;

	

	static {
		//VALIDATION_PATH_TO_FIELDNAME.put("model.countries", "selectedCountries");
		//VALIDATION_PATH_TO_FIELDNAME.put("model.grantedAuthorities", "selectedRole");
	}

	public ServiceGroupAddEditAction() {
		super("ServiceGroup", VALIDATION_PATH_TO_FIELDNAME);
	}

	@Override
	public void prepare() throws Exception {
		//System.err.println("test::" + model.id());
		
		if (model.getId() > 0) {
			model = facade.findServiceGroupById(model.getId());
		}
		//availableCountries = facade.findAllCountries();
		//availableRoles = facade.getAvailableRoles();
	}

	@Override
	public String save() {
		
		System.err.println("here");
		if (METHOD_ADD.equals(getCurrentMethod())) {
			facade.createServiceGroup(model);
		} else if (METHOD_EDIT.equals(getCurrentMethod())) {
				facade.updateServiceGroup(model);
			
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
	protected ServiceGroup createModel() {
		return new ServiceGroup();
	}
}
