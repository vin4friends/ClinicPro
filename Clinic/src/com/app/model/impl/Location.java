package com.app.model.impl;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * The persistent class for the LOCATION database table.
 */
//@Entity
//@Table(name = "LOCATION")
public class Location implements Serializable {
	private static final long serialVersionUID = -6292648705364520243L;

	private static final int MAX_LANGUAGE_LENGTH = 50;
	private static final int MAX_POSTCODE_LENGTH = 20;
	private static final int MAX_TOWN_LENGTH = 255;
	private static final int MAX_STREET_LENGTH = 255;
	private static final int MAX_DISTRICT_LENGTH = 255;
	private static final int MAX_COUNTY_LENGTH = 255;
	private static final int MAX_STATE_LENGTH = 255;
	private static final int MAX_SITECODE_LENGTH = 50;
	private static final int MAX_SITENAME_LENGTH = 255;
	private static final int MAX_TRADINGNAME_LENGTH = 255;
	private static final int MAX_TELEFAX_LENGTH = 50;
	private static final int MAX_TELEPHONE_LENGTH = 50;
	private static final int MAX_EMAIL_LENGTH = 320;
	private static final int MAX_URL_LENGTH = 2000;
	private static final int MAX_CONTACT_LENGTH = 255;
	private static final int MAX_OPENWEEKDAY_LENGTH = 255;
	private static final int MAX_OPENWEEKEND_LENGTH = 255;
	private static final int MAX_FREETEXT_LENGTH = 2000;
	
	
	
	private transient   Double distance;

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull(message = "entity.location.validation_error.country.null")
	//@ManyToOne
	//@JoinColumn(name = "COUNTRYCODE", referencedColumnName = "COUNTRYCODE", nullable = false)
	private Country country;

	@Length(max = MAX_LANGUAGE_LENGTH)
	@NotBlank(message = "entity.location.validation_error.language.blank")
	//@Column(name = "LANGUAGE", nullable = false, length = MAX_LANGUAGE_LENGTH)
	private String language;

	@NotNull(message = "entity.location.validation_error.locationtype.null")
	//@ManyToOne(optional = false)
	//@JoinColumn(name = "LOCATIONTYPEID", referencedColumnName = "ID")
	private LocationType locationType;

	@Length(max = MAX_POSTCODE_LENGTH)
	//@Column(name = "POSTCODE", length = MAX_POSTCODE_LENGTH)
	private String postcode;

	@Length(max = MAX_TOWN_LENGTH)
	@NotBlank(message = "entity.location.validation_error.town.blank")
	//@Column(name = "TOWN", nullable = false, length = MAX_TOWN_LENGTH)
	private String town;

	@Length(max = MAX_STREET_LENGTH)
	//@Column(name = "STREET", length = MAX_STREET_LENGTH)
	private String street;

	@Length(max = MAX_DISTRICT_LENGTH)
	//@Column(name = "DISTRICT", length = MAX_DISTRICT_LENGTH)
	private String district;

	@Length(max = MAX_COUNTY_LENGTH)
	//@Column(name = "COUNTY", length = MAX_COUNTY_LENGTH)
	private String county;

	@Length(max = MAX_STATE_LENGTH)
	//@Column(name = "STATE", length = MAX_STATE_LENGTH)
	private String state;

	@NotNull(message = "entity.location.validation_error.latitude.null")
	@Digits(integer = 2, fraction = 6)
	//@Column(name = "LATITUDE", nullable = false)
	private Double latitude;

	@NotNull(message = "entity.location.validation_error.longitude.null")
	@Digits(integer = 3, fraction = 6)
	//@Column(name = "LONGITUDE", nullable = false)
	private Double longitude;

	@Length(max = MAX_SITECODE_LENGTH)
	//@Column(name = "SITECODE", length = MAX_SITECODE_LENGTH)
	private String sitecode;

	@Length(max = MAX_SITENAME_LENGTH)
	//@Column(name = "SITENAME", length = MAX_SITENAME_LENGTH)
	private String sitename;

	@Length(max = MAX_TRADINGNAME_LENGTH)
	//@Column(name = "TRADINGNAME", length = MAX_TRADINGNAME_LENGTH)
	private String tradingname;

	@Length(max = MAX_TELEFAX_LENGTH)
	//@Column(name = "TELEFAX", length = MAX_TELEFAX_LENGTH)
	private String telefax;

	@Length(max = MAX_TELEPHONE_LENGTH)
	@NotBlank(message = "entity.location.validation_error.telephone.blank")
	//@Column(name = "TELEPHONE", nullable = false, length = MAX_TELEPHONE_LENGTH)
	private String telephone;

	@Length(max = MAX_EMAIL_LENGTH)
	@Email
	//@Column(name = "EMAIL", length = MAX_EMAIL_LENGTH)
	private String email;

	@Length(max = MAX_URL_LENGTH)
	@URL
	//@Column(name = "URL", length = MAX_URL_LENGTH)
	private String url;

	@Length(max = MAX_CONTACT_LENGTH)
	//@Column(name = "CONTACT", length = MAX_CONTACT_LENGTH)
	private String contact;

	@Length(max = MAX_OPENWEEKDAY_LENGTH)
	@NotBlank(message = "entity.location.validation_error.openweekday.blank")
	//@Column(name = "OPENWEEKDAY", nullable = false, length = MAX_OPENWEEKDAY_LENGTH)
	private String openweekday;

	@Length(max = MAX_OPENWEEKEND_LENGTH)
	//@Column(name = "OPENWEEKEND", length = MAX_OPENWEEKEND_LENGTH)
	private String openweekend;

	@Length(max = MAX_FREETEXT_LENGTH)
	//@Column(name = "FREETEXT", length = MAX_FREETEXT_LENGTH)
	private String freeText;

	@NotNull
	//@Column(name = "ISONLINE", nullable = false)
	private boolean isOnline;

	private short isOnlinePersist;
	
	@NotEmpty(message = "entity.location.validation_error.productcategories.empty")
	@Valid
	//@ManyToMany
	//@JoinTable(name = "LOCATIONPRODUCTCATEGORY", joinColumns = //@JoinColumn(name = "LOCATIONID", referencedColumnName = "ID"), inverseJoinColumns = //@JoinColumn(name = "CATEGORYID", referencedColumnName = "ID"))
	//@OrderBy("theOrder asc")
	private  Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

	/**
	 * The contact for a location.
	 * 
	 * //@return contact
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * Setter for a new contact.
	 * 
	 * //@param contact
	 *            the new contact to set.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * Getter to which Country this location belongs.
	 * 
	 * //@return the country for this location.
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Setter for a new country.
	 * 
	 * //@param country
	 *            the new country to set.
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Getter for county-attribute.
	 * 
	 * //@return the county-attribute.
	 */
	public String getCounty() {
		return this.county;
	}

	/**
	 * Setter for county-attribute.
	 * 
	 * //@param county
	 *            the new county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * Getter for state-attribute.
	 * 
	 * //@return the state-attribute.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Setter for state-attribute.
	 * 
	 * //@param state
	 *            the new state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Getter for district-attribute.
	 * 
	 * //@return the district-attribute.
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * Setter for district-attribute.
	 * 
	 * //@param district
	 *            the new district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * Getter for email-attribute.
	 * 
	 * //@return the email-attribute.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email-attribute.
	 * 
	 * //@param email
	 *            the new email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for freeText-attribute.
	 * 
	 * //@return the freeText-attribute.
	 */
	public String getFreeText() {
		return freeText;
	}

	/**
	 * Setter for freeText-attribute.
	 * 
	 * //@param freeText
	 *            the new freeText to set
	 */
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	/**
	 * Getter for isOnline-attribute. this indicates whether or not this
	 * location should be visible outside of the back office frontend.
	 * 
	 * //@return the isOnline-attribute.
	 */
	@Transient
	public boolean getIsOnline() {
		return isOnlinePersist==0?false:true;
	}

	/**
	 * Setter for isOnline-attribute.
	 * 
	 * //@param isOnline
	 *            the new isOnline to set
	 */
	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
		this.isOnlinePersist=(short) (isOnline?1:0);
		
	}

	/**
	 * Getter for language-attribute. the language identicates for which
	 * language this location could be displayed.
	 * 
	 * //@return the language-attribute.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Setter for language-attribute.
	 * 
	 * //@param language
	 *            the new language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Getter for latitude-attribute.
	 * 
	 * //@return the latitude-attribute.
	 */
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * Setter for latitude-attribute.
	 * 
	 * //@param latitude
	 *            the new latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Getter for the primary key attribute.
	 * 
	 * //@return the id of this location.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Setter for id-attribute.
	 * 
	 * //@param id
	 *            the new id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for longitude-attribute.
	 * 
	 * //@return the longitude-attribute.
	 */
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * Setter for longitude-attribute.
	 * 
	 * //@param longitude
	 *            the new longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Getter for openweekday-attribute.
	 * 
	 * //@return the openweekday-attribute.
	 */
	public String getOpenweekday() {
		return this.openweekday;
	}

	/**
	 * Setter for openweekday-attribute.
	 * 
	 * //@param openweekday
	 *            the new openweekday to set
	 */
	public void setOpenweekday(String openweekday) {
		this.openweekday = openweekday;
	}

	/**
	 * Getter for openweekend-attribute.
	 * 
	 * //@return the openweekend-attribute.
	 */
	public String getOpenweekend() {
		return this.openweekend;
	}

	/**
	 * Setter for openweekend-attribute.
	 * 
	 * //@param openweekend
	 *            the new openweekend to set
	 */
	public void setOpenweekend(String openweekend) {
		this.openweekend = openweekend;
	}

	/**
	 * Getter for postcode-attribute.
	 * 
	 * //@return the postcode-attribute.
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Setter for postcode-attribute.
	 * 
	 * //@param postcode
	 *            the new postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Getter for sitecode-attribute.
	 * 
	 * //@return the sitecode-attribute.
	 */
	public String getSitecode() {
		return this.sitecode;
	}

	/**
	 * Setter for sitecode-attribute.
	 * 
	 * //@param sitecode
	 *            the new sitecode to set
	 */
	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	/**
	 * Getter for sitename-attribute.
	 * 
	 * //@return the sitename-attribute.
	 */
	public String getSitename() {
		return this.sitename;
	}

	/**
	 * Setter for sitename-attribute.
	 * 
	 * //@param sitename
	 *            the new sitename to set
	 */
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	/**
	 * Getter for locationType-attribute.
	 * 
	 * //@return the locationType-attribute.
	 */
	public LocationType getLocationType() {
		return this.locationType;
	}

	/**
	 * Setter for locationType-attribute.
	 * 
	 * //@param locationType
	 *            the new locationType to set
	 */
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	/**
	 * Getter for street-attribute.
	 * 
	 * //@return the street-attribute.
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Setter for street-attribute.
	 * 
	 * //@param street
	 *            the new street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Getter for telefax-attribute.
	 * 
	 * //@return the telefax-attribute.
	 */
	public String getTelefax() {
		return this.telefax;
	}

	/**
	 * Setter for telefax-attribute.
	 * 
	 * //@param telefax
	 *            the new telefax to set
	 */
	public void setTelefax(String telefax) {
		this.telefax = telefax;
	}

	/**
	 * Getter for telephone-attribute.
	 * 
	 * //@return the telephone-attribute.
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * Setter for telephone-attribute.
	 * 
	 * //@param telephone
	 *            the new telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Getter for town-attribute.
	 * 
	 * //@return the town-attribute.
	 */
	public String getTown() {
		return this.town;
	}

	/**
	 * Setter for town-attribute.
	 * 
	 * //@param town
	 *            the new town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * Getter for tradingname-attribute.
	 * 
	 * //@return the tradingname-attribute.
	 */
	public String getTradingname() {
		return this.tradingname;
	}

	/**
	 * Setter for tradingname-attribute.
	 * 
	 * //@param tradingname
	 *            the new tradingname to set
	 */
	public void setTradingname(String tradingname) {
		this.tradingname = tradingname;
	}

	/**
	 * Getter for url-attribute.
	 * 
	 * //@return the url-attribute.
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Setter for url-attribute.
	 * 
	 * //@param url
	 *            the new url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Adds a ProductCategory to the categories of this lcoation.
	 * 
	 * //@param category
	 *            the category to add.
	 */
	public void addProductCategory(ProductCategory category) {
		if (!getProductCategories().contains(category)) {
			getProductCategories().add(category);
		}
	}

	/**
	 * Getter for productCategories-attribute.
	 * 
	 * //@return the productCategories-attribute.
	 */
	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}

	public void setIsOnlinePersist(short isOnlinePersist) {
		this.isOnlinePersist=isOnlinePersist;
	}

	public short getIsOnlinePersist() {
		return isOnlinePersist;			
	}

	
}