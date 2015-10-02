/**
 * 
 */
package com.app.model.impl.dto;

import java.io.Serializable;

/**
 * A data transfer object to transfer the icon id and name of the entity which
 * holds this icon to the finder frontend.
 * 
 * @author jomu
 * 
 */
public class IconDto implements Serializable {
	private static final long serialVersionUID = -3597568050897985975L;

	private final Long id;
	private final String title;

	public IconDto(final Long id, final String title) {
		this.id = id;
		this.title = title;
	}

	/**
	 * the id off the icon.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * the name of the entity to which this icon was attached.
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
}
