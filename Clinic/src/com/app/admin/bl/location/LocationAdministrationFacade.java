/**
 * 
 */
package com.app.admin.bl.location;

import java.io.File;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.app.exception.ImportFailedException;
import com.app.impex.ExportResult;
import com.app.impex.ImportResult;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;

/**
 * @author jomu
 * 
 */
public interface LocationAdministrationFacade {

	/**
	 * Gets all Countires which an user is allowed to administer Locations for.
	 * 
	 * @param username
	 *            the username of the user for which the countries should be
	 *            loaded.
	 * 
	 * @return a List of allowed countries for this user.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN')")
	@PostFilter("hasPermission(filterObject)")
	List<Country> getAllowedCountriesForUser(String username);

	/**
	 * Loads a Country from the underlying databse specified by the provided
	 * country code.
	 * 
	 * @param countrycode
	 *            the identifier of the country
	 * @return the country from db or <code>null</code> if it does not exist.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#countrycode)")
	Country findCountryByPk(String countrycode);

	/**
	 * Determinates the available languages for the given country. which means
	 * languages in which locations for the given country are present.
	 * 
	 * @param country
	 *            for which the languages are searched.
	 * @return list of available languages
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#country)")
	List<String> getAvailableLanguagesForCountry(Country country);

	/**
	 * Loads all Locations of the specified country.
	 * 
	 * @param country
	 *            the country for which the locations should be loaded.
	 * @return a List of Locations found.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#country)")
	List<Location> findAllLocations(Country country);

	/**
	 * Searches <code>Location</code>s by the provided search term. If the
	 * {@code language} string contains {@code "-"} and the search for the
	 * language string yields no results an new search will be applied with the
	 * substring before the last {@code "-"} as new language string. This will
	 * be done until no {@code "-"} could be found in the language string.
	 * 
	 * @param country
	 *            all Locations searches are country specific.
	 * @param language
	 *            the language of the <code>Location</code>s . <code>null</code>
	 *            is possible.
	 * @param search
	 *            the search term for the Location search. can be
	 *            <code>null</code>.
	 * @return List of Locations.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#country)")
	List<Location> findLocations(Country country, String language, String search);

	/**
	 * Finds a location specified by its primary key from the underlaying
	 * database.
	 * 
	 * @param locationId
	 *            the key of the location.
	 * @return the found location or <code>null</code> if no location with this
	 *         key could be found.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN')")
	@PostAuthorize("returnObject != null and hasPermission(returnObject.country)")
	Location findLocation(Long locationId);

	/**
	 * Saves a location to the underlying database.
	 * 
	 * @param location
	 *            the location to be saved.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#location.country)")
	void createLocation(Location location);

	/**
	 * Deletes the given location from the underlying database.
	 * 
	 * @param location
	 *            the location to delete
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#location.country)")
	void deleteLocation(Location location);

	/**
	 * Loads all LocationTypes of a country with the specified language. If the
	 * {@code language} string contains {@code "-"} and the search for the
	 * language string yields no results an new search will be applied with the
	 * substring before the last {@code "-"} as new language string. This will
	 * be done until no {@code "-"} could be found in the language string.
	 * 
	 * @param country
	 *            the country for which the locations types should be loaded.
	 * @param language
	 *            the language in which the locations types should be loaded.
	 * @return a list of the found LocationTypes or an empty list if no
	 *         LocationsTpeyes for this country with this language could be
	 *         found.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#country)")
	List<LocationType> findAllLocationTypes(Country country, String language);

	/**
	 * Loads all product categories for a given country with the specified
	 * language. If the {@code language} string contains {@code "-"} and the
	 * search for the language string yields no results an new search will be
	 * applied with the substring before the last {@code "-"} as new language
	 * string. This will be done until no {@code "-"} could be found in the
	 * language string.
	 * 
	 * @param country
	 *            the country for which the product categories should be loaded.
	 * @param language
	 *            the language in which the product categorie should be
	 *            obtained.
	 * @return a list of product categories or an empty list if no product
	 *         categories could be found.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#country)")
	List<ProductCategory> findAllProductCategories(Country country, String language);

	/**
	 * <p>
	 * Imports locations from the given csv file for the specified country. All
	 * previously defined locations for that specific country with the language
	 * specified by the file name will be erased.
	 * </p>
	 * 
	 * <p>
	 * The <code>filename</code> must follow the scheme
	 * <code>somethingFileSpecific_countrycode_languagecode.csv</code>. The
	 * countrycode in the filename must equal the countrycode of the specified
	 * <code>targetCountry</code>.
	 * </p>
	 * 
	 * <p>
	 * For example: <code>locations_gb_en.csv</code>, if the targeted country
	 * for the import ist the United Kingdom and the imported locations are
	 * imported for the english language.
	 * </p>
	 * 
	 * @param filename
	 *            the name of the file to import
	 * @param csvFile
	 *            the file which is an csv file
	 * @param targetCountry
	 *            the country for which this file should be imported.
	 * @return the result of the export.
	 * @throws ImportFailedException
	 *             when an error occurred during import
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#targetCountry)")
	ImportResult importLocationsFromCsv(final String filename, final File csvFile, final Country targetCountry)
			throws ImportFailedException;

	/**
	 * Exports the locations for a given country and a specified language in csv
	 * file format.
	 * 
	 * @param country
	 *            the country of the locations which should be exported.
	 * @param language
	 *            the language of the locations which should be exported
	 * @return the result of the export.
	 */
	@PreAuthorize("hasRole('ROLE_LOCATION_ADMIN') and hasPermission(#country)")
	ExportResult exportLocationsToCsv(final Country country, final String language);

	/**
	 * Retrieves the page size for pagination.
	 * 
	 * @return the page size for pagination.
	 */
	int getPageSize();

	/**
	 * Retrieves the Google Maps Client Id for Intranet Environment Property;
	 * 
	 * @return the environment property GOOGLE_MAPS_CLIENT_ID_INTRANET
	 */
	String getGoogleMapsClientIdIntranet();
}
