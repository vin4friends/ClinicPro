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
import org.hibernate.validator.constraints.NotBlank;

/**
 * The persistent class for the PRODUCTCATEGORY database table.
 */
////@Entity
////@Table(name = "PRODUCTCATEGORY")
public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 9222484703322126254L;

	private static final int MAX_NAME_LENGTH = 255;
	private static final int MAX_LANGUAGE_LENGTH = 50;

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotBlank(message = "common.validation.notblank.message")
	@Length(max = MAX_NAME_LENGTH)
	//@Column(name = "NAME", nullable = false, length = MAX_NAME_LENGTH)
	private String name;

	//@ManyToOne
	//@JoinColumn(name = "COUNTRYCODE", referencedColumnName = "COUNTRYCODE")
	private Country country;

	@Length(max = MAX_LANGUAGE_LENGTH)
	@NotBlank(message = "common.validation.notblank.message")
	//@Column(name = "LANGUAGE", nullable = false, length = MAX_LANGUAGE_LENGTH)
	private String language;

	@NotNull(message = "common.validation.notnull.message")
	//@Column(name = "THEORDER", nullable = false, precision = 8)
	private Integer theOrder;

	/**
	 * Getter for the primary key of the entity.
	 * 
	 * //@return the id of the entity.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Setter for the primary key of the entity.
	 * 
	 * //@param id
	 *            the new id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the country to which this ProductCategory belongs.
	 * 
	 * //@return the country.
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Setter for a new country to which this ProductCategory belongs.
	 * 
	 * //@param country
	 *            the new country to set.
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * The language for which this ProductCategory can be used.
	 * 
	 * //@return the language.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Sets a new language.
	 * 
	 * //@param language
	 *            the new language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * The name of this ProductCategory. Allready localized for language
	 * specified by the {//@link #getLanguage()}-method.
	 * 
	 * //@return the localized name of the product category.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set a new name for this ProductCategory.
	 * 
	 * //@param name
	 *            the new name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The Display Order of the ProductCategories.
	 * 
	 * //@return the order number.
	 */
	public Integer getTheOrder() {
		return this.theOrder;
	}

	/**
	 * set a new position in the order of the ProductCategories.
	 * 
	 * //@param theOrder
	 *            the new position
	 */
	public void setTheOrder(Integer theOrder) {
		this.theOrder = theOrder;
	}

}