/**
 * 
 */
package com.app.admin.bl.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.Country;
import com.app.model.impl.LfUser;
import com.app.model.impl.Role;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.security.PasswordGenerator;
import com.app.services.CountryService;
import com.app.services.EnvironmentPropertiesService;
import com.app.services.LfUserService;
import com.app.wrapper.PasswordWrapper;

/**
 * @author jomu
 * 
 */
@Service("userAdministrationFacade")
public class UserAdministrationFacadeImpl implements UserAdministrationFacade {

	@Resource
	private LfUserService userService;

	@Resource
	private CountryService countryService;

	@Resource
	private PasswordGenerator passwordGenerator;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Override
	public List<LfUser> findAllUser() {
		return userService.findAll();
	}

	@Override
	public LfUser findUserById(Long id) {
		return userService.findById(id);
	}

	@Override
	public void createUser(LfUser user, PasswordWrapper password) {
		userService.create(user, password);
	}

	@Override
	public void updateUser(LfUser user) {
		userService.update(user);
	}

	@Override
	public void updateUser(LfUser user, PasswordWrapper password) {
		userService.update(user, password);
	}

	@Override
	public void deleteUser(LfUser user) {
		userService.delete(user);
	}

	@Override
	public void lockUser(LfUser user) {
		userService.lockUser(user);
	}

	@Override
	public void unlockUser(LfUser user) {
		userService.unlockUser(user);
	}

	@Override
	public List<Role> getAvailableRoles() {
		return userService.getAvailableRoles();
	}

	@Override
	public List<Country> findAllCountries() {
		return countryService.findAll();
	}

	@Override
	public String getNewPassword() {
		return passwordGenerator.getNewPassword();
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}
}
