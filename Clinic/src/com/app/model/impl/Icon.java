/**
 * 
 */
package com.app.model.impl;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * //@author jomu
 * 
 */
////@Entity
////@Table(name = "ICON")
public class Icon implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private static final int MAX_MIMETYPE_LENGTH = 50;

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;

	//@Length(max = MAX_MIMETYPE_LENGTH)
	//@NotNull(message = "common.validation.notnull.message")
	//@Column(name = "MIMETYPE", nullable = false, length = MAX_MIMETYPE_LENGTH)
	private String mimeType;

	//@Lob
	//@Basic(fetch = FetchType.LAZY)
	//@Column(name = "ICONFILE", nullable = false)
	//@NotNull(message = "common.validation.notnull.message")
	private byte[] iconFile;
	
	private Blob iconFileBob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getIconFile() {
		
		return iconFile;
	}

	public void setIconFile(byte[] iconFile) {
		
		
		this.iconFile=iconFile;
	}

	public void setIconFileBob(Blob iconFileBob) throws SQLException {
		this.iconFileBob = iconFileBob;
				
		if(iconFileBob!=null)
		{
			
		System.out.println();
		}
		
	}

	public Blob getIconFileBob() {
		return iconFileBob;
	}
}
