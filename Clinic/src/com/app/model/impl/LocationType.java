package com.app.model.impl;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the LOCATIONTYPE database table.
 */
////@Entity
////@Table(name = "LOCATIONTYPE")
public class LocationType implements Serializable {
	private static final long serialVersionUID = -2015187302603801531L;

	private static final int MAX_LANGUAGE_LENGTH = 50;
	private static final int MAX_LOCATIONTYPE_LENGTH = 255;

	////@Id
	////@Column(name = "ID", nullable = false, updatable = true)
	////@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	////@ManyToOne
	////@JoinColumn(name = "COUNTRYCODE", referencedColumnName = "COUNTRYCODE")
	private Country country;

	@NotEmpty
	@Length(max = MAX_LANGUAGE_LENGTH)
	////@Column(name = "LANGUAGE", nullable = false, length = MAX_LANGUAGE_LENGTH)
	private String language;

	@NotEmpty
	@Length(max = MAX_LOCATIONTYPE_LENGTH)
	////@Column(name = "LOCATIONTYPE", nullable = false, length = MAX_LOCATIONTYPE_LENGTH)
	private String locationType;



	/**
	 * The country in which this LocationType can occur.
	 * 
	 * ////@return the country.
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Set a new country.
	 * 
	 * ////@param country
	 *            the new country to set.
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Language in which this LocationType is stored.
	 * 
	 * ////@return the language of this LocationType.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Set a new language.
	 * 
	 * ////@param language
	 *            the new language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * The displayvalue for this LocationType. (localized name actually).
	 * 
	 * ////@return the locationtype.
	 */
	public String getLocationType() {
		return this.locationType;
	}

	/**
	 * set a new name for this locationtype.
	 * 
	 * ////@param locationType
	 *            the new name to set.
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	/**
	 * Getter for the primary key.
	 * 
	 * ////@return the primary key.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Setter for primaray key.
	 * 
	 * ////@param id
	 *            the new primary key
	 */
	public void setId(Long id) {
		this.id = id;
	}

}