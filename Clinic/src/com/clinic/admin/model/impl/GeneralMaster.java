package com.clinic.admin.model.impl;
// Generated Oct 3, 2015 1:48:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Generalmaster generated by hbm2java
 */
public class GeneralMaster {

	private Long docid;
	private String mastertype;
	private String name;
	private short isActiveDummy =0;
	private Boolean isActive = Boolean.FALSE;
	
	private String createdby;
	private Date createdtime;
	private String updatedby;
	private Date updatedtime;

	public GeneralMaster() {
	}

	public GeneralMaster(String mastertype, String name, Date createdtime,
			Date updatedtime) {
		this.mastertype = mastertype;
		this.name = name;
		this.createdtime = createdtime;
		this.updatedtime = updatedtime;
	}

	public GeneralMaster(String mastertype, String name, Boolean isactive,
			String createdby, Date createdtime, String updatedby,
			Date updatedtime) {
		this.mastertype = mastertype;
		this.name = name;
		this.isActive = isactive;
		this.createdby = createdby;
		this.createdtime = createdtime;
		this.updatedby = updatedby;
		this.updatedtime = updatedtime;
	}

	public Long getDocid() {
		return this.docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public String getMastertype() {
		return this.mastertype;
	}

	public void setMastertype(String mastertype) {
		this.mastertype = mastertype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedtime() {
		return this.updatedtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}
	
	/**
	 * @return the isActiveDummy
	 */
	public short getIsActiveDummy() {
		return isActiveDummy;
	}
	/**
	 * @param isActiveDummy the isActiveDummy to set
	 */
	public void setIsActiveDummy(short isActiveDummy) {
		this.isActiveDummy = isActiveDummy;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive=isActive;
		isActiveDummy=(short) (isActive?1:0);
	}
	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return getIsActiveDummy()==0?false:true;
	}

}