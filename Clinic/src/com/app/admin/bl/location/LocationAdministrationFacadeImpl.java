package com.app.admin.bl.location;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.exception.ImportFailedException;
import com.app.impex.ExportResult;
import com.app.impex.ImportResult;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.model.impl.EnvironmentProperty.StringProperty;
import com.app.services.CountryService;
import com.app.services.EnvironmentPropertiesService;
import com.app.services.FileService;
import com.app.services.LocationService;
import com.app.services.LocationTypeService;
import com.app.services.ProductCategoryService;

@Service("locationAdministrationFacade")
public class LocationAdministrationFacadeImpl implements LocationAdministrationFacade {

	@Resource
	private LocationService locationService;

	@Resource
	private CountryService countryService;

	@Resource
	private LocationTypeService locationTypeService;

	@Resource
	private ProductCategoryService productCategoryService;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Resource
	private FileService fileService;

	@Override
	public List<Country> getAllowedCountriesForUser(String username) {
		return countryService.getAllowedCountriesForUser(username);
	}

	@Override
	public Country findCountryByPk(String countrycode) {
		return countryService.findCountryByPk(countrycode);
	}

	@Override
	public List<String> getAvailableLanguagesForCountry(Country country) {
		return countryService.getAvailableLanguagesForCountry(country);
	}

	@Override
	public List<Location> findAllLocations(Country country) {
		return locationService.findAll(country);
	}

	@Override
	public List<Location> findLocations(Country country, String language, String search) {
		return locationService.findLocations(country, language, search);
	}

	@Override
	public Location findLocation(Long locationId) {
		return locationService.findById(locationId);
	}

	@Override
	public void createLocation(Location location) {
		locationService.createLocation(location);
	}

	@Override
	public void deleteLocation(Location location) {
		locationService.deleteLocation(location);
	}

	@Override
	public List<LocationType> findAllLocationTypes(Country country, String language) {
		return locationTypeService.findAll(country, language);
	}

	@Override
	public List<ProductCategory> findAllProductCategories(Country country, String language) {
		return productCategoryService.findAll(country, language);
	}

	@Override
	public ImportResult importLocationsFromCsv(String filename, File csvFile, Country targetCountry)
			throws ImportFailedException {
		return fileService.importLocationsFromCsv(filename, csvFile, targetCountry);
	}

	@Override
	public ExportResult exportLocationsToCsv(Country country, String language) {
		return fileService.exportLocationsToCsv(country, language);
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}

	@Override
	public String getGoogleMapsClientIdIntranet() {
		return environmentPropertiesService.getStringProperty(StringProperty.GOOGLE_MAPS_CLIENT_ID_INTRANET);
	}
}
