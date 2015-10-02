package com.clinic.admin.model.impl;

import org.hibernate.validator.constraints.NotBlank;

public class ServiceMaster {

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
	
	@NotBlank(message = "entity.service.validation_error.code.blank")
	//@Column(name = "CODE", nullable = false, length = 50, unique = true)
	private String code;
	
	@NotBlank(message = "entity.service.validation_error.name.blank")
	//@Column(name = "NAME", nullable = false, length = 200, unique = true)
	private String name;

	@NotBlank(message = "entity.service.validation_error.typeCode.blank")
	//@Column(name = "TYPECODE", nullable = false, length = 50, unique = true)
	private String typeCode;
	
	@NotBlank(message = "entity.service.validation_error.groupCode.blank")
	//@Column(name = "GROUPCODE", nullable = false, length = 50, unique = true)
	private String groupCode;
	
	@NotBlank(message = "entity.service.validation_error.visitType.blank")
	//@Column(name = "VISITTYPE", nullable = false, length = 50, unique = true)
	private String visitType;

	private short isAppointableDummy = 0;
	private Boolean isAppointable;
	private String specialInstructions;
	private short isMultipleDummy = 0;

	private Boolean isMultiple;
	private short isChargableDummy = 0;

	private Boolean isChargable;
	private java.math.BigDecimal sellingPrice;
	private java.math.BigDecimal estimationTimeMins;
	private short isResourceDependentDummy = 0;

	private Boolean isResourceDependent;
	private short isPackageServiceDummy = 0;

	private Boolean isPackageService;
	private short isSurgicalServiceDummy = 0;

	private Boolean isSurgicalService;
	private short isEquipmentRequiredDummy = 0;

	private Boolean isEquipmentRequired;
	private short isOTRequiredDummy = 0;

	private Boolean isOTRequired;
	private short isHighRatedDummy = 0;

	private Boolean isHighRated;
	private short isAuthorisationRequiredDummy = 0;

	private Boolean isAuthorisationRequired;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private String cptCode;
	private String activityType;

	
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
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * @return the visitType
	 */
	public String getVisitType() {
		return visitType;
	}
	/**
	 * @param visitType the visitType to set
	 */
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	/**
	 * @return the isAppointableDummy
	 */
	public short getIsAppointableDummy() {
		return isAppointableDummy;
	}
	/**
	 * @param isAppointableDummy the isAppointableDummy to set
	 */
	public void setIsAppointableDummy(short isAppointableDummy) {
		this.isAppointableDummy = isAppointableDummy;
	}
	/**
	 * @return the isAppointable
	 */
	public Boolean getIsAppointable() {
		return getIsAppointableDummy()==0?false:true;
	}
	/**
	 * @param isAppointable the isAppointable to set
	 */
	public void setIsAppointable(Boolean isAppointable) {
		this.isAppointable = isAppointable;
	}
	/**
	 * @return the specialInstructions
	 */
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	/**
	 * @param specialInstructions the specialInstructions to set
	 */
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	/**
	 * @return the isMultipleDummy
	 */
	public short getIsMultipleDummy() {
		return isMultipleDummy;
	}
	/**
	 * @param isMultipleDummy the isMultipleDummy to set
	 */
	public void setIsMultipleDummy(short isMultipleDummy) {
		this.isMultipleDummy = isMultipleDummy;
	}
	/**
	 * @return the isMultiple
	 */
	public Boolean getIsMultiple() {
		return getIsMultipleDummy()==0?false:true;
	}
	/**
	 * @param isMultiple the isMultiple to set
	 */
	public void setIsMultiple(Boolean isMultiple) {
		this.isMultiple = isMultiple;
		isMultipleDummy=(short) (isMultiple?1:0);

	}
	/**
	 * @return the isChargableDummy
	 */
	public short getIsChargableDummy() {
		return isChargableDummy;
	}
	/**
	 * @param isChargableDummy the isChargableDummy to set
	 */
	public void setIsChargableDummy(short isChargableDummy) {
		this.isChargableDummy = isChargableDummy;
	}
	/**
	 * @return the isChargable
	 */
	public Boolean getIsChargable() {
		return getIsChargableDummy()==0?false:true;
	}
	/**
	 * @param isChargable the isChargable to set
	 */
	public void setIsChargable(Boolean isChargable) {
		this.isChargable = isChargable;
		isChargableDummy=(short) (isChargable?1:0);
	}
	/**
	 * @return the sellingPrice
	 */
	public java.math.BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(java.math.BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	/**
	 * @return the estimationTimeMins
	 */
	public java.math.BigDecimal getEstimationTimeMins() {
		return estimationTimeMins;
	}
	/**
	 * @param estimationTimeMins the estimationTimeMins to set
	 */
	public void setEstimationTimeMins(java.math.BigDecimal estimationTimeMins) {
		this.estimationTimeMins = estimationTimeMins;
	}
	/**
	 * @return the isResourceDependentDummy
	 */
	public short getIsResourceDependentDummy() {
		return isResourceDependentDummy;
	}
	/**
	 * @param isResourceDependentDummy the isResourceDependentDummy to set
	 */
	public void setIsResourceDependentDummy(short isResourceDependentDummy) {
		this.isResourceDependentDummy = isResourceDependentDummy;
	}
	/**
	 * @return the isResourceDependent
	 */
	public Boolean getIsResourceDependent() {
		return getIsResourceDependentDummy()==0?false:true;
	}
	/**
	 * @param isResourceDependent the isResourceDependent to set
	 */
	public void setIsResourceDependent(Boolean isResourceDependent) {
		this.isResourceDependent = isResourceDependent;
		isResourceDependentDummy=(short) (isResourceDependent?1:0);
	}
	/**
	 * @return the isPackageServiceDummy
	 */
	public short getIsPackageServiceDummy() {
		return isPackageServiceDummy;
	}
	/**
	 * @param isPackageServiceDummy the isPackageServiceDummy to set
	 */
	public void setIsPackageServiceDummy(short isPackageServiceDummy) {
		this.isPackageServiceDummy = isPackageServiceDummy;
	}
	/**
	 * @return the isPackageService
	 */
	public Boolean getIsPackageService() {
		return getIsPackageServiceDummy()==0?false:true;
	}
	/**
	 * @param isPackageService the isPackageService to set
	 */
	public void setIsPackageService(Boolean isPackageService) {
		this.isPackageService = isPackageService;
		isPackageServiceDummy=(short) (isPackageService?1:0);
	}
	/**
	 * @return the isSurgicalServiceDummy
	 */
	public short getIsSurgicalServiceDummy() {
		return isSurgicalServiceDummy;
	}
	/**
	 * @param isSurgicalServiceDummy the isSurgicalServiceDummy to set
	 */
	public void setIsSurgicalServiceDummy(short isSurgicalServiceDummy) {
		this.isSurgicalServiceDummy = isSurgicalServiceDummy;
	}
	/**
	 * @return the isSurgicalService
	 */
	public Boolean getIsSurgicalService() {
		return getIsSurgicalServiceDummy()==0?false:true;
	}
	/**
	 * @param isSurgicalService the isSurgicalService to set
	 */
	public void setIsSurgicalService(Boolean isSurgicalService) {
		this.isSurgicalService = isSurgicalService;
		isSurgicalServiceDummy=(short) (isSurgicalService?1:0);
	}
	/**
	 * @return the isEquipmentRequiredDummy
	 */
	public short getIsEquipmentRequiredDummy() {
		return isEquipmentRequiredDummy;
	}
	/**
	 * @param isEquipmentRequiredDummy the isEquipmentRequiredDummy to set
	 */
	public void setIsEquipmentRequiredDummy(short isEquipmentRequiredDummy) {
		this.isEquipmentRequiredDummy = isEquipmentRequiredDummy;
	}
	/**
	 * @return the isEquipmentRequired
	 */
	public Boolean getIsEquipmentRequired() {
		return getIsEquipmentRequiredDummy()==0?false:true;
	}
	/**
	 * @param isEquipmentRequired the isEquipmentRequired to set
	 */
	public void setIsEquipmentRequired(Boolean isEquipmentRequired) {
		this.isEquipmentRequired = isEquipmentRequired;
		isEquipmentRequiredDummy=(short) (isEquipmentRequired?1:0);
	}
	/**
	 * @return the isOTRequiredDummy
	 */
	public short getIsOTRequiredDummy() {
		return isOTRequiredDummy;
	}
	/**
	 * @param isOTRequiredDummy the isOTRequiredDummy to set
	 */
	public void setIsOTRequiredDummy(short isOTRequiredDummy) {
		this.isOTRequiredDummy = isOTRequiredDummy;
	}
	/**
	 * @return the isOTRequired
	 */
	public Boolean getIsOTRequired() {
		return getIsOTRequiredDummy()==0?false:true;
	}
	/**
	 * @param isOTRequired the isOTRequired to set
	 */
	public void setIsOTRequired(Boolean isOTRequired) {
		this.isOTRequired = isOTRequired;
		isOTRequiredDummy=(short) (isOTRequired?1:0);
	}
	/**
	 * @return the isHighRatedDummy
	 */
	public short getIsHighRatedDummy() {
		return isHighRatedDummy;
	}
	/**
	 * @param isHighRatedDummy the isHighRatedDummy to set
	 */
	public void setIsHighRatedDummy(short isHighRatedDummy) {
		this.isHighRatedDummy = isHighRatedDummy;
	}
	/**
	 * @return the isHighRated
	 */
	public Boolean getIsHighRated() {
		return getIsHighRatedDummy()==0?false:true;
	}
	/**
	 * @param isHighRated the isHighRated to set
	 */
	public void setIsHighRated(Boolean isHighRated) {
		this.isHighRated = isHighRated;
		isHighRatedDummy=(short) (isHighRated?1:0);
	}
	/**
	 * @return the isAuthorisationRequiredDummy
	 */
	public short getIsAuthorisationRequiredDummy() {
		return isAuthorisationRequiredDummy;
	}
	/**
	 * @param isAuthorisationRequiredDummy the isAuthorisationRequiredDummy to set
	 */
	public void setIsAuthorisationRequiredDummy(short isAuthorisationRequiredDummy) {
		this.isAuthorisationRequiredDummy = isAuthorisationRequiredDummy;
	}
	/**
	 * @return the isAuthorisationRequired
	 */
	public Boolean getIsAuthorisationRequired() {
		return getIsAuthorisationRequiredDummy()==0?false:true;
	}
	/**
	 * @param isAuthorisationRequired the isAuthorisationRequired to set
	 */
	public void setIsAuthorisationRequired(Boolean isAuthorisationRequired) {
		this.isAuthorisationRequired = isAuthorisationRequired;
		isAuthorisationRequiredDummy=(short) (isAuthorisationRequired?1:0);
	}
	/**
	 * @return the startDate
	 */
	public java.util.Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public java.util.Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the cptCode
	 */
	public String getCptCode() {
		return cptCode;
	}
	/**
	 * @param cptCode the cptCode to set
	 */
	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}
	/**
	 * @return the activityType
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * @param activityType the activityType to set
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

}
