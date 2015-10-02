/**
 * 
 */
package com.app.fe.web.admin.user;

import javax.annotation.Resource;

import com.app.admin.bl.user.UserAdministrationFacade;
import com.app.common.fe.impl.AbstractDeleteAction;
import com.app.model.impl.LfUser;

/**
 * @author Vinoth
 * 
 */
public class UserDeleteAction extends AbstractDeleteAction<LfUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	private transient UserAdministrationFacade facade;

	public UserDeleteAction() {
		super("UserDelete");
	}

	@Override
	public void prepare() throws Exception {
		model = facade.findUserById(model.getId());
	}

	@Override
	public String delete() {
		facade.deleteUser(model);
		return SUCCESS;
	}

	@Override
	protected LfUser createModel() {
		return new LfUser();
	}
}
