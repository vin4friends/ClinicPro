/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.LocationType;
import com.app.model.impl.dto.LocationTypeDto;

/**
 * Service for dealing with LocationTypes.
 * 
 * @author jomu
 */
public interface LocationTypeService {

	/**
	 * Retrieves all Locationtypes for a given country.
	 * 
	 * @param country
	 *            the country for which the locations types should be loaded.
	 * @return a List of the found LocationTypes.
	 */
	List<LocationType> findAll(Country country);

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
	List<LocationType> findAll(Country country, String language);

	/**
	 * Get a specific LocationType. No additional language handling is
	 * performed.
	 * 
	 * @param country
	 *            country to which this location type belongs. may not be
	 *            {@code null}.
	 * @param language
	 *            language for the searched location type. may not be
	 *            {@code null}.
	 * @param locationType
	 *            the actual locationtype for this country and language. may not
	 *            be {@code null}.
	 * @return <code>null</code> if not LocationType could be found, otherwise
	 *         the found LocationType.
	 */
	LocationType getLocationType(Country country, String language, String locationType);

	/**
	 * Loads all LocationTypes of countries associated with the given
	 * application for the specified language. If the {@code language} string
	 * contains {@code "-"} and the search for the language string yields no
	 * results an new search will be applied with the substring before the last
	 * {@code "-"} as new language string. This will be done until no
	 * {@code "-"} could be found in the language string.
	 * 
	 * @param application
	 *            the Application for which LocationTypes should be loaded.
	 * @param language
	 *            the language in which the locations types should be loaded.
	 * @return a list of the found LocationTypes or an empty list if no
	 *         LocationsTypes for the countries associated with the application
	 *         with this language could be found.
	 */
	List<LocationTypeDto> getLocationTypesByApplication(Application application, String language);

	/**
	 * Persists a LocationType to the underlying database.
	 * 
	 * @param locationType
	 *            the LocationType to be persisted.
	 */
	void createLocationType(LocationType locationType);
}
