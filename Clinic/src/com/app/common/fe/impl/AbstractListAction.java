/**
 * 
 */
package com.app.common.fe.impl;

import java.util.List;

/**
 * @author jomn
 * 
 */
public abstract class AbstractListAction<T> extends AbstractBaseAction {
	private static final long serialVersionUID = -5788871390830050859L;

	private int pageSize = 20;

	@Override
	public void prepare() throws Exception {
		pageSize = getPageSizeInternal();
	}

	public abstract List<T> getList();

	protected abstract int getPageSizeInternal();

	public int getPageSize() {
		return pageSize;
	}
}
