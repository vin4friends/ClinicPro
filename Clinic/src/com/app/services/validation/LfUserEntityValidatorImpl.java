/**
 * 
 */
package com.app.services.validation;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.LfUser;
import com.app.model.impl.LfUser_;
import com.app.model.impl.Role;
import com.app.security.LfAuthentication;
import com.app.validation.LfUserEntityValidator;

/**
 * Implementation of the LfUserEntityValidator-Interface.
 * 
 * @author jomu
 */
@Service("lfUserEntityValidator")
public class LfUserEntityValidatorImpl implements LfUserEntityValidator {

	@Resource
	private GenericDao dao;

	@Override
	public boolean validateLocationAdminCountries(Object lfUser, List<Object> messageArguments) {
		LfUser user = (LfUser) lfUser;

		// only location admins are allowed to have countries attached
		if (isLocationAdmin(user) && user.getCountries().isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean validateSupportAndSuperAdminCountries(Object lfUser, List<Object> messageArguments) {
		LfUser user = (LfUser) lfUser;

		// super admins and support admins are not allowed to have countries
		if (!isLocationAdmin(user) && !user.getCountries().isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean validateUniqueLogin(Object lfUser, List<Object> messageArguments) {
		LfUser user = (LfUser) lfUser;

		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<Long> query = cb.createQuery(Long.class);

		Root<LfUser> dbUser = query.from(LfUser.class);
		query.where(cb.equal(dbUser.get(LfUser_.login), user.getLogin()));
		query.select(dbUser.get(LfUser_.id));

		Long id = dao.findSingle(query);

		if (id == null || id.equals(user.getId())) {
			return true;
		}

		return false;*/
		return false;
	}

	private boolean isLocationAdmin(LfUser user) {
		for (Role role : user.getGrantedAuthorities()) {
			if (role.getAuthority().equals(LfAuthentication.ROLES.ROLE_LOCATION_ADMIN.name())) {
				return true;
			}
		}
		return false;
	}
}
