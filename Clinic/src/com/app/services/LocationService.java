/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.dto.LocationDto;

/**
 * Service for dealing with Location Entities.
 * 
 * @author jomn
 * @author jomu
 */
public interface LocationService {

	/**
	 * Saves a location to the underlying database.
	 * 
	 * @param location
	 *            the location to be saved.
	 */
	void createLocation(Location location);

	/**
	 * Finds a location specified by its primary key from the underlaying
	 * database.
	 * 
	 * @param locationId
	 *            the key of the location.
	 * @return the found location or <code>null</code> if no location with this
	 *         key could be found.
	 */
	Location findById(Long locationId);

	/**
	 * Deletes the given location from the underlying database.
	 * 
	 * @param location
	 *            the location to delete
	 */
	void deleteLocation(Location location);

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
	List<Location> findLocations(Country country, String language, String search);

	/**
	 * Searches <code>Location</code>s by a radius search around the provided
	 * GeoLocation, using the provided unit of measurement to calculate the
	 * distances between the <code>Location</code>s and GeoLocation and limit
	 * the results by the provided {@code limit} parameter. The returned List is
	 * sorted by distance.
	 * 
	 * @param latitude
	 *            the latitude value of the geo loation to start search from
	 * @param longitude
	 *            the longitude value of the geo location to start search from
	 * @param application
	 *            the application for which this search is performed
	 * @param locationTypeIds
	 *            a list of LocationType Ids which will be used for filtering
	 *            the search results
	 * @param productCategoryIds
	 *            a list of ProductCategory Ids which will be used for filtering
	 *            the search results
	 * @param language
	 *            the language for which Locations should be found
	 * @return a sorted List auf Locations
	 */
	List<LocationDto> findNearestLocations(Double latitude, Double longitude, Application application,
			List<Long> locationTypeIds, List<Long> productCategoryIds, String language);

	/**
	 * Retrieves all Locations from the database.
	 * 
	 * @return a List of Locations.
	 */
	List<Location> findAll();

	/**
	 * Loads all Locations of the specified country.
	 * 
	 * @param country
	 *            the country for which the locations should be loaded.
	 * @return a List of Locations found.
	 */
	List<Location> findAll(Country country);

	/**
	 * Retrieves all <code>Location</code>s for the given country for the
	 * specified language.If the {@code language} string contains {@code "-"}
	 * and the search for the language string yields no results an new search
	 * will be applied with the substring before the last {@code "-"} as new
	 * language string.
	 * 
	 * @param country
	 *            all Locations searches are country specific.
	 * @param language
	 *            the language of the <code>Location</code>s . <code>null</code>
	 *            is possible
	 * @return List of Locations.
	 */
	List<Location> findAll(Country country, String language);

	/**
	 * Counts the locations for a country with the specified language. No
	 * additional language handling will be applied for this method.
	 * 
	 * @param country
	 *            the country for which the locations should be counted.
	 * @param language
	 *            the language for the Location search. can be <code>null</code>
	 * @return the count of the locations.
	 */
	long countLocations(Country country, String language);

	/**
	 * Deletes all Locations of the given Country with the specified Language If
	 * no language is provided e.g. language is <code>null</code>, all locations
	 * of that country will be deleted. No additional language handling will be
	 * applied for this method.
	 * 
	 * @param country
	 *            the country in which the locations should be deleted.
	 * @param language
	 *            the language of the locations which should be deleted, can be
	 *            {@code null}
	 * @return number of deleted locations
	 */
	int deleteAllLocations(Country country, String language);

	/**
	 * Builds for given location the address formatted by the location's country
	 * address template.
	 * 
	 * @param location
	 *            location for which the address should be generated
	 * @return the address formatting aaccording to the country template
	 */
	String getAddressDisplayValue(Location location);
}
