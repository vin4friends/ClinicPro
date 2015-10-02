/**
 * 
 */
package com.app.fe.web.admin.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractAddEditAction;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;
import com.app.util.CustomStringUtil;

/**
 * @author jomn
 */
public class LocationAddEditAction extends AbstractAddEditAction<Location> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private LocationAdministrationFacade facade;

	private String googleMapsClientId;

	private Country country = new Country();

	private List<LocationType> locationTypes = new ArrayList<LocationType>();

	private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	static {
		VALIDATION_PATH_TO_FIELDNAME.put("model.locationType", "selectedLocationType");
		VALIDATION_PATH_TO_FIELDNAME.put("model.productCategories", "selectedProductCategories");
	}

	public LocationAddEditAction() {
		super("Location", VALIDATION_PATH_TO_FIELDNAME);
	}

	@Override
	protected Location createModel() {
		return new Location();
	}

	@Override
	public void prepare() throws Exception {
		if (model.getId() != null) {
			model = facade.findLocation(model.getId());
		}
		if (country.getCountrycode() != null) {
			country = facade.findCountryByPk(country.getCountrycode());
			// XXX: remove language setting if multilanguage support is added
			model.setLanguage(country.getDefaultLanguage());
		}

		googleMapsClientId = facade.getGoogleMapsClientIdIntranet();
		locationTypes = facade.findAllLocationTypes(country, model.getLanguage());
		productCategories = facade.findAllProductCategories(country, model.getLanguage());
	}

	@Override
	public String save() {
		model.setCountry(country);
		facade.createLocation(model);
		return SUCCESS;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<LocationType> getLocationTypes() {
		return locationTypes;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public List<Long> getSelectedProductCategories() {
		final List<Long> selectedProductCategories = new ArrayList<Long>();
		for (ProductCategory productCategory : model.getProductCategories()) {
			selectedProductCategories.add(productCategory.getId());
		}

		return selectedProductCategories;
	}

	public void setSelectedProductCategories(List<Long> selectedProductCategories) {
		model.getProductCategories().clear();
		final Map<Long, ProductCategory> idToProductCategoryMap = new HashMap<Long, ProductCategory>(
				productCategories.size());

		for (ProductCategory pc : productCategories) {
			idToProductCategoryMap.put(pc.getId(), pc);
		}

		for (Long productCategoryId : selectedProductCategories) {
			ProductCategory pc = idToProductCategoryMap.get(productCategoryId);
			if (pc != null) {
				model.getProductCategories().add(pc);
			}
		}
	}

	public Long getSelectedLocationType() {
		if (model.getLocationType() != null) {
			return model.getLocationType().getId();
		}
		return null;
	}

	public void setSelectedLocationType(Long selectedLocationType) {
		for (LocationType type : locationTypes) {
			if (type.getId().equals(selectedLocationType)) {
				model.setLocationType(type);
			}
		}
	}

	public boolean isGoogleMapsClientIdSet() {
		return CustomStringUtil.isNotBlank(googleMapsClientId);
	}

	public String getGoogleMapsClientId() {
		return googleMapsClientId;
	}
}
