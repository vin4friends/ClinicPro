/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.Country;

/**
 * Service for dealing with Country Entities.
 * 
 * @author jomu
 */
public interface CountryService {

	/**
	 * Gets all Countires which an user is allowed to administer Locations for.
	 * 
	 * @param username
	 *            the username of the user for which the countries should be
	 *            loaded.
	 * 
	 * @return a List of allowed countries for this user.
	 */
	List<Country> getAllowedCountriesForUser(String username);

	/**
	 * Loads all countries from the underlying database.
	 * 
	 * @return a list of all countries.
	 */
	List<Country> findAll();

	/**
	 * Loads a Country from the underlying databse specified by the provided
	 * country code.
	 * 
	 * @param countryCode
	 *            the identifier of the country
	 * @return the country from db or <code>null</code> if it does not exist.
	 */
	Country findCountryByPk(String countryCode);

	/**
	 * Determinates the available languages for the given country. which means
	 * languages in which locations for the given country are present.
	 * 
	 * @param country
	 *            for which the languages are searched.
	 * @return list of available languages
	 */
	List<String> getAvailableLanguagesForCountry(Country country);
}
