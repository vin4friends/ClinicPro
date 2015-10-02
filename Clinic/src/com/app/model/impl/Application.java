package com.app.model.impl;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.app.model.impl.enums.ApplicationType;
import com.app.model.impl.enums.DistanceUnitOfMeasurement;
import com.app.model.impl.enums.MapVisibility;

/**
 * The persistent class for the APPLICATION database table.
 */
////@Entity
////@Table(name = "APPLICATION")

public class Application extends BaseObject {
	private static final long serialVersionUID = -7395046039899609282L;

	private final static int MAX_APPKEY_LENGTH = 255;
	private final static int MAX_NAME_LENGTH = 255;
	private final static int MAX_DESCRIPTION_LENGTH = 2000;
	private final static int MAX_CSSURL_LENGTH = 2000;
	private final static int MAX_ENUM_LENGTH = 50;
	private final static int MAX_SEARCH_INFO_LENGTH = 255;
	private final static int MAX_IFRAME_ID_LENGTH = 255;
	private final static int MAX_HELPER_URL_LENGTH = 255;

	////@Id
	////@Column(name = "ID", nullable = false, updatable = true)
	////@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotBlank(message = "entity.application.validation_error.application_key.blank")
	@Length(max = MAX_APPKEY_LENGTH)
	////@Column(name = "APPKEY", length = MAX_APPKEY_LENGTH, nullable = false, updatable = false, unique = true)
	private String applicationKey;

	@NotBlank(message = "entity.application.validation_error.name.blank")
	@Length(max = MAX_NAME_LENGTH)
	////@Column(name = "NAME", length = MAX_NAME_LENGTH, unique = true, nullable = false)
	private String name;

	@Length(max = MAX_DESCRIPTION_LENGTH)
	////@Column(name = "DESCRIPTION", length = MAX_DESCRIPTION_LENGTH)
	private String description;

	@URL
	@Length(max = MAX_CSSURL_LENGTH)
	@NotBlank(message = "entity.application.validation_error.css_url.blank")
	////@Column(name = "CSSURL", nullable = false, length = MAX_CSSURL_LENGTH)
	private String cssUrl;

	@NotNull
	//@Column(name = "ALLOWDESELECT", nullable = false)
	private Boolean allowDeselect;

	@NotNull(message = "entity.application.validation_error.type.null")
	////@Enumerated(EnumType.STRING)
	////@Column(name = "TYPE", nullable = false, length = MAX_ENUM_LENGTH)
	private ApplicationType type;
	

	@NotNull(message = "entity.application.validation_error.uom_distance.null")
	////////@Enumerated(EnumType.STRING)
	////@Column(name = "UOMDISTANCE", nullable = false, length = MAX_ENUM_LENGTH)
	private DistanceUnitOfMeasurement uomDistance;

	@NotNull(message = "entity.application.validation_error.showMapLandingpage.null")
	////@Enumerated(EnumType.STRING)
	////@Column(name = "SHOWMAPLANDINGPAGE", nullable = false, length = MAX_ENUM_LENGTH)
	private MapVisibility showMapLandingpage;

	@NotNull(message = "entity.application.validation_error.showMapDirections.null")
	////@Enumerated(EnumType.STRING)
	////@Column(name = "SHOWMAPDIRECTIONS", nullable = false, length = MAX_ENUM_LENGTH)
	private MapVisibility showMapDirections;

	//@Column(name = "MAXLOCATIONRESULT")
	private Long maxLocationResult;

	@Length(max = MAX_SEARCH_INFO_LENGTH)
	//@Column(name = "SEARCHINFOTEXT", length = MAX_SEARCH_INFO_LENGTH)
	private String searchInfoText;

	@NotNull(message = "entity.application.validation_error.latitude.null")
	@Digits(integer = 2, fraction = 6)
	//@Column(name = "STARTLATITUDE", nullable = false)
	private Double startLatitude;

	@NotNull(message = "entity.application.validation_error.longitude.null")
	@Digits(integer = 3, fraction = 6)
	//@Column(name = "STARTLONGITUDE", nullable = false)
	private Double startLongitude;

	@NotNull(message = "entity.application.validation_error.zoom.null")
	//@Column(name = "STARTZOOM", nullable = false)
	private Long startZoom;

	@NotEmpty(message = "entity.application.validation_error.iframe_id.empty")
	@Length(max = MAX_IFRAME_ID_LENGTH)
	//@olumn(name = "IFRAMEID", nullable = false)
	private String iframeId;

	@NotEmpty(message = "entity.application.validation_error.helper_url.empty")
	@Length(max = MAX_HELPER_URL_LENGTH)
	//@Column(name = "HELPERURL", nullable = false)
	private String helperUrl;

	@NotEmpty(message = "entity.application.validation_error.countries.empty")
	//@ManyToMany
	//@JoinTable(name = "APPLICATIONCOUNTRY", joinColumns = //@JoinColumn(name = "APPLICATIONID", referencedColumnName = "ID"), inverseJoinColumns = //@JoinColumn(name = "COUNTRYCODE", referencedColumnName = "COUNTRYCODE"))
	private Set<Country> countries = new HashSet<Country>();

	@NotNull(message = "entity.application.validation_error.imageset.null")
	//@ManyToOne
	//@JoinColumn(name = "IMAGESETID", referencedColumnName = "ID")
	private ImageSet imageSet;

	private long imageSetId;
	/**
	 * Getter for the primary key of this application.
	 * 
	 * //@return the id-attribute.
	 */
	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}
	
	public Long getId() {
		return this.id;
	}

	/**
	 * Setter for a new id.
	 * 
	 * //@param id
	 *            the new id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the unique identifier of this application which is used in the
	 * finder frontend to get the right application. hobefully without the
	 * possibility to guess other {//@code applicationKey}s.
	 * 
	 * //@return the applicationKey-attribute
	 */
	public String getApplicationKey() {
		return applicationKey;
	}

	/**
	 * Setter for a new applicationKey.
	 * 
	 * //@param applicationKey
	 *            the new applicationKey to set.
	 */
	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

	/**
	 * Getter for name of this application.
	 * 
	 * //@return the name-attribute.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for a new name.
	 * 
	 * //@param name
	 *            the new name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for description of this application.
	 * 
	 * //@return the description-attribute.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter for a new description.
	 * 
	 * //@param description
	 *            the new description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for url to a css file used in the finder frontend.
	 * 
	 * //@return the cssUrl-attribute.
	 */
	public String getCssUrl() {
		return cssUrl;
	}

	/**
	 * Setter for a new cssUrl.
	 * 
	 * //@param cssUrl
	 *            the new cssUrl to set.
	 */
	public void setCssUrl(String cssUrl) {
		this.cssUrl = cssUrl;
	}

	/**
	 * Getter for the allowDeselect-attribute. If this is set to {//@code true}
	 * deselect links for location type and product categories should be
	 * displayed on the finder frontend.
	 * 
	 * //@return the allowDeselect-attribute.
	 */
	public Boolean getAllowDeselect() {
		return allowDeselect;
	}

	/**
	 * Setter for a new allowDeselect.
	 * 
	 * //@param allowDeselect
	 *            the new allowDeselect to set.
	 */
	public void setAllowDeselect(Boolean allowDeselect) {
		this.allowDeselect = allowDeselect;
	}

	/**
	 * Getter for the type of the application. e.g.: is this an intranet or
	 * internet application.
	 * 
	 * //@return the type-attribute.
	 */
	/*public ApplicationType getType() {
		return type;
	}

	*//**
	 * Setter for a new type.
	 * 
	 * //@param type
	 *            the new type to set.
	 *//*
	public void setType(ApplicationType type) {
		this.type = type;
	}*/

	/**
	 * Getter for the unit of measurement, which will be used for distance
	 * calculation and presentation.
	 * 
	 * //@return the uomDistance-attribute.
	 */
	public DistanceUnitOfMeasurement getUomDistance() {
		return uomDistance;
	}

	/**
	 * Setter for a new uomDistance.
	 * 
	 * //@param uomDistance
	 *            the new uomDistance to set.
	 */
	public void setUomDistance(DistanceUnitOfMeasurement uomDistance) {
		this.uomDistance = uomDistance;
	}

	/**
	 * Getter for the MapVisibility of the Landingpage.
	 * 
	 * //@return the showMapLandingpage-attribute.
	 */
	public MapVisibility getShowMapLandingpage() {
		return showMapLandingpage;
	}

	/**
	 * Setter for a new showMapLandingpage.
	 * 
	 * //@param showMapLandingpage
	 *            the new showMapLandingpage to set.
	 */
	public void setShowMapLandingpage(MapVisibility showMapLandingpage) {
		this.showMapLandingpage = showMapLandingpage;
	}

	/**
	 * Getter for the MapVisibility of the Direcetionspage.
	 * 
	 * //@return the showMapDirections-attribute.
	 */
	public MapVisibility getShowMapDirections() {
		return showMapDirections;
	}

	/**
	 * Setter for a new showMapDirections.
	 * 
	 * //@param showMapDirections
	 *            the new showMapDirections to set.
	 */
	public void setShowMapDirections(MapVisibility showMapDirections) {
		this.showMapDirections = showMapDirections;
	}

	/**
	 * Only use this method to display the value. For actual logic use the
	 * method at the application service.
	 * 
	 * //@return the max number of locations per search result
	 * //@see {//@code com.app.services.ApplicationService.getMaxNumberOfLocationsPerSearchResult(Application)}
	 */
	public Long getMaxLocationResult() {
		return maxLocationResult;
	}

	/**
	 * Setter for a new maxLocationResult.
	 * 
	 * //@param maxLocationResult
	 *            the new maxLocationResult to set.
	 */
	public void setMaxLocationResult(Long maxLocationResult) {
		this.maxLocationResult = maxLocationResult;
	}

	/**
	 * Getter for the Search Info Text to display above the search field in the
	 * finder client frontend.
	 * 
	 * //@return the searchInfoText attribute
	 */
	public String getSearchInfoText() {
		return searchInfoText;
	}

	/**
	 * Set a new search info text.
	 * 
	 * //@param searchInfoText
	 *            the new text to set.
	 */
	public void setSearchInfoText(String searchInfoText) {
		this.searchInfoText = searchInfoText;
	}

	/**
	 * //@return the startLatitude
	 */
	public Double getStartLatitude() {
		return startLatitude;
	}

	/**
	 * //@param startLatitude
	 *            the startLatitude to set
	 */
	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	/**
	 * //@return the startLongitude
	 */
	public Double getStartLongitude() {
		return startLongitude;
	}

	/**
	 * //@param startLongitude
	 *            the startLongitude to set
	 */
	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}

	/**
	 * //@return the startZoom
	 */
	public Long getStartZoom() {
		return startZoom;
	}

	/**
	 * //@param startZoom
	 *            the startZoom to set
	 */
	public void setStartZoom(Long startZoom) {
		this.startZoom = startZoom;
	}

	/**
	 * //@return the iframeId
	 */
	public String getIframeId() {
		return iframeId;
	}

	/**
	 * //@param iframeId
	 *            the iframeId to set
	 */
	public void setIframeId(String iframeId) {
		this.iframeId = iframeId;
	}

	/**
	 * //@return the helperUrl
	 */
	public String getHelperUrl() {
		return helperUrl;
	}

	/**
	 * //@param helperUrl
	 *            the helperUrl to set
	 */
	public void setHelperUrl(String helperUrl) {
		this.helperUrl = helperUrl;
	}

	/**
	 * Getter for the countries of the application. Only locations in this
	 * countries will be considered during search operations.
	 * 
	 * //@return the country-attribute.
	 */
	public Set<Country> getCountries() {
		return countries;
	}

	/**
	 * //@return the imageSet
	 */
	public ImageSet getImageSet() {
		return imageSet;
	}

	/**
	 * //@param imageSet
	 *            the imageSet to set
	 */
	public void setImageSet(ImageSet imageSet) {
		this.imageSet = imageSet;
	}

	public void setImageSetId(long imageSetId) {
		this.imageSetId = imageSetId;
	}

	public long getImageSetId() {
		return imageSetId;
	}

	

	public void setType(ApplicationType type) {
		this.type = type;
	}

	public ApplicationType getType() {
		return type;
	}

	

}