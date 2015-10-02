/**
 * 
 */
package com.app.fe.web.admin.user;

import java.util.List;

import javax.annotation.Resource;

import com.app.admin.bl.user.UserAdministrationFacade;
import com.app.common.fe.impl.AbstractListAction;
import com.app.model.impl.LfUser;

/**
 * @author jomu
 * 
 */
public class UserIndexAction extends AbstractListAction<LfUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private transient UserAdministrationFacade facade;

	@Override
	public List<LfUser> getList() {
		return facade.findAllUser();
	}

	@Override
	protected int getPageSizeInternal() {
		return facade.getPageSize();
	}
}
