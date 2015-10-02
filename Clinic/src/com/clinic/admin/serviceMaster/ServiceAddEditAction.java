/**
 * 
 */
package com.clinic.admin.serviceMaster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractAddEditAction;
import com.clinic.admin.bl.serviceMaster.ServiceAdministrationFacade;
import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.model.impl.ServiceMaster;


/**
 * @author Vineet
 * 
 */
public class ServiceAddEditAction extends AbstractAddEditAction<ServiceMaster> {

	private static final long serialVersionUID = 1L;

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	@Resource
	private transient ServiceAdministrationFacade facade;

	private List<ServiceGroup> serviceGroups = null;
	

	static {
		//VALIDATION_PATH_TO_FIELDNAME.put("model.countries", "selectedCountries");
		//VALIDATION_PATH_TO_FIELDNAME.put("model.grantedAuthorities", "selectedRole");
	}

	public ServiceAddEditAction() {
		super("ServiceMaster", VALIDATION_PATH_TO_FIELDNAME);
	}

	@Override
	public void prepare() throws Exception {
		//System.err.println("test::" + model.id());
		
		serviceGroups = facade.getServiceGroup();
		if (model.getId() > 0) {
			model = facade.findServiceById(model.getId());
		}
		//availableCountries = facade.findAllCountries();
		//availableRoles = facade.getAvailableRoles();
	}

	@Override
	public String save() {
		
		System.err.println("here");
		if (METHOD_ADD.equals(getCurrentMethod())) {
			facade.createServiceMaster(model);
		} else if (METHOD_EDIT.equals(getCurrentMethod())) {
				facade.updateServiceMaster(model);
			
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
	protected ServiceMaster createModel() {
		return new ServiceMaster();
	}
	

	/**
	 * @return the serviceGroups
	 */
	public List<ServiceGroup> getServiceGroups() {
		return serviceGroups;
	}
}
