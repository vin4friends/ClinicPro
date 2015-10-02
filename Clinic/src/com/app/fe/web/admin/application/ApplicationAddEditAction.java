/**
 * 
 */
package com.app.fe.web.admin.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.app.admin.bl.application.ApplicationAdministrationFacade;
import com.app.common.fe.impl.AbstractAddEditAction;
import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.ImageSet;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.enums.ApplicationType;
import com.app.model.impl.enums.DistanceUnitOfMeasurement;
import com.app.model.impl.enums.MapVisibility;
import com.app.util.CustomStringUtil;

/**
 * @author jomu
 */
public class ApplicationAddEditAction extends
		AbstractAddEditAction<Application> {

	@Resource
	private transient ApplicationAdministrationFacade facade;

	private List<Country> availableCountries = new ArrayList<Country>();


	/* holds the information about icon availibility of imagesets for specific countries */
	private List<CountryImageSetIconAvailabilty> countryImageSetIconAvailabilties;

	private List<ImageSet> availableImageSets = new ArrayList<ImageSet>();

	private static final List<ApplicationType> AVAILABLE_APPLICATIONTYPES = new ArrayList<ApplicationType>(
			Arrays.asList(ApplicationType.values()));

	private static final List<DistanceUnitOfMeasurement> AVAILABLE_DISTANCEUNITS = new ArrayList<DistanceUnitOfMeasurement>(
			Arrays.asList(DistanceUnitOfMeasurement.values()));

	private static final List<MapVisibility> AVAILABLE_MAPVISIBILITIES = new ArrayList<MapVisibility>(
			Arrays.asList(MapVisibility.values()));

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	private String exampleUrl;

	private String urlInternetPattern;

	private String urlIntranetPattern;

	static {
		VALIDATION_PATH_TO_FIELDNAME.put("model.type", "model.type");
		VALIDATION_PATH_TO_FIELDNAME.put("model.uomDistance",
				"model.uomDistance");
		VALIDATION_PATH_TO_FIELDNAME.put("model.showMapLandingpage",
				"model.showMapLandingpage");
		VALIDATION_PATH_TO_FIELDNAME.put("model.showMapDirections",
				"model.showMapDirections");
		VALIDATION_PATH_TO_FIELDNAME
				.put("model.countries", "selectedCountries");
		VALIDATION_PATH_TO_FIELDNAME.put("model.imageSet", "selectedImageSet");
	}

	public ApplicationAddEditAction() {
		super("Application", VALIDATION_PATH_TO_FIELDNAME);
	}

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void prepare() throws Exception {
		if (model.getId() != null) {
			model = facade.findApplication(model.getId());
		}
		if (CustomStringUtil.isBlank(model.getApplicationKey())) {
			model.setApplicationKey(facade.getNewApplicationKey());
		}

		availableCountries = facade.getAllCountries();
		availableImageSets = facade.getAllImageSets();

		if (model != null && model.getType() != null
				&& model.getCountries() != null) {
			exampleUrl = facade.getIFrameUrl(model);
		}

		urlInternetPattern = facade.getIframeExampleUrlInternet();
		urlIntranetPattern = facade.getIframeExampleUrlIntranet();
		prepareCISIA();
	}

	public void prepareCISIA() {
		countryImageSetIconAvailabilties = new ArrayList<CountryImageSetIconAvailabilty>();
		for (Country country : availableCountries) {
			CountryImageSetIconAvailabilty cISIA = new CountryImageSetIconAvailabilty(
					country.getCountrycode());
			List<LocationType> locTypes = facade
					.getLocationTypesForCountry(country);
			List<ProductCategory> prodCats = facade
					.getProductCategoriesForCountry(country);
			for (ImageSet imageSet : availableImageSets) {
				IconAvailabilty iconAvailabilty = new IconAvailabilty();
				iconAvailabilty.setIconSetId(imageSet.getId());
				
				iconAvailabilty.setLtIconsEmpty( true);
				iconAvailabilty.setLtIconsComplete(true);
				for (LocationType type : locTypes) {
					if (imageSet.getLocationTypeIcon(type) == null) {
						iconAvailabilty.setLtIconsComplete(false);
					} else {
						iconAvailabilty.setLtIconsEmpty(false);
					}
				}
				
				iconAvailabilty.setPcIconsEmpty(true);
				iconAvailabilty.setPcIconsComplete(true);
				for (ProductCategory cat : prodCats) {
					if(imageSet.getProductCategoryIcon(cat) == null){
						iconAvailabilty.setPcIconsComplete(false);
					}else{
						iconAvailabilty.setPcIconsEmpty(false);
					}
				}
				
				cISIA.getIconAvailability().add(iconAvailabilty);

			}
			countryImageSetIconAvailabilties.add(cISIA);

		}
	}

	@Override
	public String save() {
		facade.createApplication(model);
		return SUCCESS;
	}

	@Override
	protected Application createModel() {
		return new Application();
	}

	@SkipValidation
	public String generateKey() {
		model.setApplicationKey(facade.getNewApplicationKey());
		exampleUrl = facade.getIFrameUrl(model);
		// urlInternetPattern = facade.getIframeExampleUrlInternet();
		// urlIntranetPattern = facade.getIframeExampleUrlIntranet();
		return INPUT;
	}

	public List<Country> getAvailableCountries() {
		return availableCountries;
	}

	public List<String> getSelectedCountries() {
		final List<String> selectedCountries = new ArrayList<String>();
		for (Country country : model.getCountries()) {
			selectedCountries.add(country.getCountrycode());
		}
		return selectedCountries;
	}

	public void setSelectedCountries(List<String> selectedCountries) {
		model.getCountries().clear();
		for (Country country : availableCountries) {
			if (selectedCountries.contains(country.getCountrycode())) {
				model.getCountries().add(country);
			}
		}
	}

	public Long getSelectedImageSet() {
		return model.getImageSet().getId();
	}

	public void setSelectedImageSet(Long setId) {
		for (ImageSet imageSet : availableImageSets) {
			if (imageSet.getId().equals(setId)) {
				model.setImageSet(imageSet);
				return;
			}
		}
	}

	/**
	 * @return the availableImageSets
	 */
	public List<ImageSet> getAvailableImageSets() {
		return availableImageSets;
	}


	public List<ApplicationType> getAvailableApplicationTypes() {
		return AVAILABLE_APPLICATIONTYPES;
	}

	public List<DistanceUnitOfMeasurement> getAvailableDistanceUnits() {
		return AVAILABLE_DISTANCEUNITS;
	}

	public List<MapVisibility> getAvailableMapvisibilities() {
		return AVAILABLE_MAPVISIBILITIES;
	}

	public String getExampleUrl() {
		return exampleUrl;
	}

	public String getUrlInternetPattern() {
		return urlInternetPattern;
	}

	public String getUrlIntranetPattern() {
		return urlIntranetPattern;
	}

	/**
	 * @return the countryImageSetIconAvailabilties
	 */
	public List<CountryImageSetIconAvailabilty> getCISIA() {
		return countryImageSetIconAvailabilties;
	}
	
	/**
	 * 
	 * @return url of helper js file
	 */
	public String getHelperJsUrl(){
		return exampleUrl.split("/index?")[0] + "/js/iframe-helper.js";
	}
	
}

class CountryImageSetIconAvailabilty {
	private final String countryCode;

	private List<IconAvailabilty> iconAvailability;

	/**
	 */
	public CountryImageSetIconAvailabilty(String countryCode) {
		super();
		this.countryCode = countryCode;
		this.iconAvailability = new ArrayList<IconAvailabilty>();
	}

	/**
	 * @return the countryId
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return the iconAvailability
	 */
	public List<IconAvailabilty> getIconAvailability() {
		return iconAvailability;
	}

	/**
	 * @param iconAvailability
	 *            the iconAvailability to set
	 */
	public void setIconAvailability(List<IconAvailabilty> iconAvailability) {
		this.iconAvailability = iconAvailability;
	}

}

class IconAvailabilty {

	private Long imageSetId;
	private boolean ltIconsComplete = true;
	private boolean ltIconsEmpty = true;
	private boolean pcIconsComplete = true;
	private boolean pcIconsEmpty = true;
	
	
	/**
	 * @return the iconSetId
	 */
	public Long getImageSetId() {
		return imageSetId;
	}

	/**
	 * @param iconSetId the iconSetId to set
	 */
	public void setIconSetId(Long iconSetId) {
		this.imageSetId = iconSetId;
	}

	/**
	 * @return the ltIconsComplete
	 */
	public boolean isLtIconsComplete() {
		return ltIconsComplete;
	}

	/**
	 * @param ltIconsComplete the ltIconsComplete to set
	 */
	public void setLtIconsComplete(boolean ltIconsComplete) {
		this.ltIconsComplete = ltIconsComplete;
	}

	/**
	 * @return the ltIconsEmpty
	 */
	public boolean isLtIconsEmpty() {
		return ltIconsEmpty;
	}

	/**
	 * @param ltIconsEmpty the ltIconsEmpty to set
	 */
	public void setLtIconsEmpty(boolean ltIconsEmpty) {
		this.ltIconsEmpty = ltIconsEmpty;
	}

	/**
	 * @return the pcIconsComplete
	 */
	public boolean isPcIconsComplete() {
		return pcIconsComplete;
	}

	/**
	 * @param pcIconsComplete the pcIconsComplete to set
	 */
	public void setPcIconsComplete(boolean pcIconsComplete) {
		this.pcIconsComplete = pcIconsComplete;
	}

	/**
	 * @return the pcIconsEmpty
	 */
	public boolean isPcIconsEmpty() {
		return pcIconsEmpty;
	}

	/**
	 * @param pcIconsEmpty the pcIconsEmpty to set
	 */
	public void setPcIconsEmpty(boolean pcIconsEmpty) {
		this.pcIconsEmpty = pcIconsEmpty;
	}

	
}
