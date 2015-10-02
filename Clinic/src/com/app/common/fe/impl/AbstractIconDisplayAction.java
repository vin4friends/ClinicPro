/**
 * 
 */
package com.app.common.fe.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.app.model.impl.Icon;

/**
 * Action for displaying Icons to the User.
 * 
 * @author jomu
 */
public abstract class AbstractIconDisplayAction extends AbstractBaseAction {
	private static final long serialVersionUID = -6001269962033299364L;

	/** id of icon to show */
	private Long id;
	/** icon to show */
	private Icon icon;
	/** icon data as stream */
	protected InputStream imageStream;

	@Override
	public String execute() throws Exception {
		if (id != null) {
			icon = findIcon(id);
			if (icon != null) {
				imageStream = new ByteArrayInputStream(icon.getIconFile());
			}
		}

		if (imageStream == null) {
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * Setter for id.
	 * 
	 * @param id
	 *            the new id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the mime type of the icon or {@code null} if no icon could be
	 *         found
	 */
	public String getMimeType() {
		return icon != null ? icon.getMimeType() : null;
	}

	/**
	 * @return the icon data as stream or {@code null} if no icon could be found
	 */
	public InputStream getImageStream() {
		return imageStream;
	}

	protected abstract Icon findIcon(Long id);
}
