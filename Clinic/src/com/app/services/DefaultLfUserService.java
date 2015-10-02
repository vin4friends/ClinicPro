/**
 * 
 */
package com.app.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.LfUser;
import com.app.model.impl.Role;
import com.app.security.crypto.password.StandardPasswordEncoder;
import com.app.wrapper.PasswordWrapper;

/**
 * Implementation of the LfUserService-Interface.
 * 
 * @author jomu
 */
@Service("lfUserService")
public class DefaultLfUserService implements LfUserService {

	@Resource
	private GenericDao dao;

	private static final StandardPasswordEncoder PASSWORD_ENCODER = new StandardPasswordEncoder();

	@Override
	public LfUser findById(Long id) {
		return dao.find(LfUser.class, id);
	}

	@Override
	public List<LfUser> findAll() {
		return dao.findAll(LfUser.class);
	}

	@Override
	public void create(LfUser user, PasswordWrapper password) {
		user.setPassword(encodePassword(password.getPassword()));
		dao.persist(user);
	}

	@Override
	public void update(LfUser user) {
		dao.update(user);
	}

	@Override
	public void update(LfUser user, PasswordWrapper password) {
		user.setPassword(encodePassword(password.getPassword()));
		dao.update(user);
	}

	@Override
	public void delete(LfUser user) {
		dao.remove(user);
	}

	@Override
	public List<Role> getAvailableRoles() {
		return dao.findAll(Role.class);
	}

	@Override
	public void lockUser(LfUser user) {
		user.setIsLocked(Boolean.TRUE);
		dao.update(user);

	}

	@Override
	public void unlockUser(LfUser user) {
		user.setIsLocked(Boolean.FALSE);
		dao.update(user);
	}

	private String encodePassword(String password) {
		return PASSWORD_ENCODER.encode(password);
	}
}
