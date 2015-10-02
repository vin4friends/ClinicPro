package com.clinic.admin.model.impl;

import org.hibernate.validator.constraints.NotBlank;

public class ServiceGroup {

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdTime
	 */
	public java.util.Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * @return the updatedTime
	 */
	public java.util.Date getUpdatedTime() {
		return updatedTime;
	}
	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(java.util.Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	private long id;
	
	@NotBlank(message = "entity.serviceGroup.validation_error.code.blank")
	//@Column(name = "CODE", nullable = false, length = 50, unique = true)
	private String code;
	
	@NotBlank(message = "entity.serviceGroup.validation_error.name.blank")
	//@Column(name = "NAME", nullable = false, length = 200, unique = true)
	private String name;
	private String comments;
	private short isActiveDummy =0;
	private Boolean isActive = Boolean.FALSE;
	private String createdBy;
	private java.util.Date createdTime;
	private java.util.Date updatedTime;
	private String updatedBy;
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
