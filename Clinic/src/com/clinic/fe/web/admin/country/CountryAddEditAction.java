/**
 * 
 */
package com.clinic.fe.web.admin.country;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;


import com.app.common.fe.impl.AbstractAddEditAction;
import com.app.util.CustomStringUtil;
import com.clinic.admin.bl.country.CountryAdministrationFacade;
import com.clinic.model.impl.AppCountry;


/**
 * @author Vinoth
 * 
 */
public class CountryAddEditAction extends AbstractAddEditAction<AppCountry> {

	private static final long serialVersionUID = 1L;

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	@Resource
	private transient CountryAdministrationFacade facade;

	//private List<Country> availableCountries = new ArrayList<Country>();


	//private List<Role> availableRoles = new ArrayList<Role>();

	//private String generatedPassword;

	static {
		//VALIDATION_PATH_TO_FIELDNAME.put("model.countries", "selectedCountries");
		//VALIDATION_PATH_TO_FIELDNAME.put("model.grantedAuthorities", "selectedRole");
	}

	public CountryAddEditAction() {
		super("Country", VALIDATION_PATH_TO_FIELDNAME);
	}

	@Override
	public void prepare() throws Exception {
		System.err.println("test::" + model.getCountryId());
		
		if (model.getCountryId() > 0) {
			model = facade.findcountryById(model.getCountryId());
		}
		//availableCountries = facade.findAllCountries();
		//availableRoles = facade.getAvailableRoles();
	}

	@Override
	public String save() {
		
		System.err.println("here");
		if (METHOD_ADD.equals(getCurrentMethod())) {
			facade.createcountry(model);
		} else if (METHOD_EDIT.equals(getCurrentMethod())) {
				facade.updatecountry(model);
			
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
	protected AppCountry createModel() {
		return new AppCountry();
	}
}
