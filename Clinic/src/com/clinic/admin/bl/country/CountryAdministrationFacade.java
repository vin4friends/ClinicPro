/**
 * 
 */
package com.clinic.admin.bl.country;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.clinic.model.impl.AppCountry;

/**
 * @author jomu
 * 
 */
public interface CountryAdministrationFacade {
	/**
	 * Finds all countrys.
	 * 
	 * @return a list of all countrys known to the system.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	List<AppCountry> findAllcountry();

	/**
	 * Find a AppCountry by Id.
	 * 
	 * @param id
	 *            the Id of the country to load.
	 * @return the found country or <code>null</code>
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	AppCountry findcountryById(Long id);

	/**
	 * Persists a new country with the given password to the underlaying database.
	 * 
	 * @param country
	 *            country to persist
	 * @param password
	 *            password to set for country
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void createcountry(AppCountry country);

	/**
	 * Persists a new country with the given password to the underlaying database.
	 * 
	 * @param country
	 *            country to persist
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void updatecountry(AppCountry country);

	

	/**
	 * Deletes a country from the underlaying database.
	 * 
	 * @param country
	 *            to delete
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void deletecountry(AppCountry country);



	/**
	 * Retrieves the page size for pagination.
	 * 
	 * @return the page size for pagination.
	 */
	int getPageSize();
}
