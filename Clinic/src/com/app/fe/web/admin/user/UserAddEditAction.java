/**
 * 
 */
package com.app.fe.web.admin.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.app.admin.bl.user.UserAdministrationFacade;
import com.app.common.fe.impl.AbstractAddEditAction;
import com.app.model.impl.Country;
import com.app.model.impl.LfUser;
import com.app.model.impl.Role;
import com.app.util.CustomStringUtil;
import com.app.wrapper.PasswordWrapper;

/**
 * @author jomu
 * 
 */
public class UserAddEditAction extends AbstractAddEditAction<LfUser> {

	private static final long serialVersionUID = 1L;

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	@Resource
	private transient UserAdministrationFacade facade;

	private List<Country> availableCountries = new ArrayList<Country>();

	private final PasswordWrapper password;

	private List<Role> availableRoles = new ArrayList<Role>();

	private String generatedPassword;

	static {
		VALIDATION_PATH_TO_FIELDNAME.put("model.countries", "selectedCountries");
		VALIDATION_PATH_TO_FIELDNAME.put("model.grantedAuthorities", "selectedRole");
	}

	public UserAddEditAction() {
		super("User", VALIDATION_PATH_TO_FIELDNAME);
		password = new PasswordWrapper();
	}

	@Override
	public void prepare() throws Exception {
		if (model.getId() != null) {
			model = facade.findUserById(model.getId());
		}
		availableCountries = facade.findAllCountries();
		availableRoles = facade.getAvailableRoles();
	}

	@Override
	public String save() {
		System.err.println("here in user edit");
		if (METHOD_ADD.equals(getCurrentMethod())) {
			facade.createUser(model, password);
		} else if (METHOD_EDIT.equals(getCurrentMethod())) {
			if (CustomStringUtil.isBlank(password.getPassword())) {
				facade.updateUser(model);
			} else {
				facade.updateUser(model, password);
			}
		}
		return SUCCESS;
	}

	@SkipValidation
	public String generatePassword() {
		generatedPassword = facade.getNewPassword();
		password.setPassword(generatedPassword);
		password.setPasswordConfirmation(generatedPassword);
		return INPUT;
	}

	public String lock() {
		facade.lockUser(model);
		return SUCCESS;
	}

	public String unlock() {
		facade.unlockUser(model);
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (METHOD_ADD.equals(getCurrentMethod()) && CustomStringUtil.isBlank(password.getPassword())) {
			addFieldError("password.password", getText("entity.user.validation_error.password.blank"));
		}

		if (CustomStringUtil.isNotBlank(password.getPassword())
				&& !password.getPassword().equals(password.getPasswordConfirmation())) {
			addFieldError("password.password", getText("entity.user.password_mismatch"));
			addFieldError("password.passwordConfirmation", getText("entity.user.password_mismatch"));
		}
	}

	public List<Country> getAvailableCountries() {
		return availableCountries;
	}

	public List<String> getSelectedCountries() {
		final List<String> selectedCountries = new ArrayList<String>();
		for (Country country : model.getCountries()) {
			selectedCountries.add(country.getCountrycode());
		}
		return selectedCountries;
	}

	public void setSelectedCountries(List<String> selectedCountries) {
		model.getCountries().clear();
		for (Country country : availableCountries) {
			if (selectedCountries.contains(country.getCountrycode())) {
				model.getCountries().add(country);
			}
		}
	}

	public List<Role> getAvailableRoles() {
		return availableRoles;
	}

	public Long getSelectedRole() {
		if (model.getGrantedAuthorities().size() == 1) {
			return model.getGrantedAuthorities().iterator().next().getId();
		}

		return null;
	}

	public void setSelectedRole(Long selectedRole) {
		model.getGrantedAuthorities().clear();
		for (Role role : availableRoles) {
			if (role.getId().equals(selectedRole)) {
				model.getGrantedAuthorities().add(role);
			}
		}
	}

	public PasswordWrapper getPassword() {
		return password;
	}

	public String getGeneratedPassword() {
		return generatedPassword;
	}

	public void setGeneratedPassword(String generatedPassword) {
		this.generatedPassword = generatedPassword;
	}

	@Override
	protected LfUser createModel() {
		return new LfUser();
	}
}
