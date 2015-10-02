package com.app.model.impl;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.app.model.impl.validation.ValidateByService;
import com.app.model.impl.validation.ValidateByServices;
import com.app.validation.CountryEntityValidator;



/**
 * The persistent class for the COUNTRY database table.
 */
////Entity
////Table(name = "COUNTRY")
//@ValidateByServices(services = {
		//@ValidateByService(serviceName = "countryEntityValidator", serviceMethod = "validateMaxLocationResult", serviceClass = CountryEntityValidator.class, message = "entity.country.max_location_result.validation_error", fieldname = "maxLocationResult"),
		//@ValidateByService(serviceName = "countryEntityValidator", serviceMethod = "validateAddressTemplate", serviceClass = CountryEntityValidator.class, message = "entity.country.addressTemplate.validation_error", fieldname = "addressTemplate") })
public class Country implements Serializable {
	private static final long serialVersionUID = -3235622542234761931L;

	private static final int MAX_COUNTRYCODE_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 255;
	private static final int MAX_DEFAULTLANGUAGE_LENGTH = 50;
	private static final int MAX_ADDRESSTEMPLATE_LENGTH = 2000;
	
	

	//Id
	@Length(max = MAX_COUNTRYCODE_LENGTH)
	@NotBlank(message = "common.validation.notblank.message")
	//Column(name = "COUNTRYCODE", nullable = false, length = MAX_COUNTRYCODE_LENGTH, updatable = false)
	private String countrycode;

	/**
	 * MessageKey for Country
	 */
	@Length(max = MAX_NAME_LENGTH)
	@NotBlank(message = "common.validation.notblank.message")
	//Column(name = "NAME", nullable = false, length = MAX_NAME_LENGTH)
	private String name;

	//Column(name = "MAXLOCATIONRESULT")
	private Long maxLocationResult;

	@Length(max = MAX_DEFAULTLANGUAGE_LENGTH)
	@NotBlank(message = "common.validation.notblank.message")
	//Column(name = "DEFAULTLANGUAGE", nullable = false, length = MAX_DEFAULTLANGUAGE_LENGTH)
	private String defaultLanguage = "en-GB";

	@Length(max = MAX_ADDRESSTEMPLATE_LENGTH)
	@NotBlank(message = "common.validation.notblank.message")
	//Column(name = "ADDRESSTEMPLATE", nullable = false, length = MAX_ADDRESSTEMPLATE_LENGTH)
	private String addressTemplate;

	/**
	 * Getter for primary key of a country.
	 * 
	 * //return the countrycode of the country.
	 */
	public String getCountrycode() {
		return this.countrycode;
	}

	/**
	 * Sets a new countrycode for this country.
	 * 
	 * //param countrycode
	 *            the new countrycode to set.
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	/**
	 * Getter for the name of the country. Usally a messageKey to localize the
	 * display value.
	 * 
	 * //return the name of the country.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a new name.
	 * 
	 * //param name
	 *            the new name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the maxLocationResult-attribute. Country wide override of
	 * {//link EnvironmentProperty.IntegerProperty#MAX_LOCATIONS_PER_SEARCH_RESULT}
	 * . Could be overriden by Application setting.
	 * 
	 * //return maxLocationResult-attribute
	 */
	public Long getMaxLocationResult() {
		return maxLocationResult;
	}

	/**
	 * Sets a new maxLocationResult.
	 * 
	 * //param maxLocationResult
	 *            the new maxLocationResult to set.
	 */
	public void setMaxLocationResult(Long maxLocationResult) {
		this.maxLocationResult = maxLocationResult;
	}

	/**
	 * The default language for this country. if no language is specified for
	 * import or add operations this default language will be used.
	 * 
	 * //return the default language of the country
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * Sets a new default language for this country.
	 * 
	 * //param defaultLanguage
	 *            the new langauge to set.
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Gets the country specific address template, which will be used to display
	 * location addresses.
	 * 
	 * //return the address template
	 */
	public String getAddressTemplate() {
		return addressTemplate;
	}

	/**
	 * Sets a new address template.
	 * 
	 * //param addressTemplate
	 *            the new address template to set.
	 */
	public void setAddressTemplate(String addressTemplate) {
		this.addressTemplate = addressTemplate;
	}
}