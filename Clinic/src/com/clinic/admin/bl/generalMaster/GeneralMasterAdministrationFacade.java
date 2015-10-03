/**
 * 
 */
package com.clinic.admin.bl.generalMaster;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.clinic.admin.model.impl.GeneralMaster;

/**
 * @author Vineet
 * 
 */
public interface GeneralMasterAdministrationFacade {
	/**
	 * Finds all countrys.
	 * 
	 * @return a list of all countrys known to the system.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	List<GeneralMaster> findAllGeneralMaster();

	/**
	 * Find a AppCountry by Id.
	 * 
	 * @param id
	 *            the Id of the country to load.
	 * @return the found country or <code>null</code>
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	GeneralMaster findGeneralMasterById(Long id);

	/**
	 * Persists a new country with the given password to the underlaying database.
	 * 
	 * @param country
	 *            country to persist
	 * @param password
	 *            password to set for country
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void createGeneralMaster(GeneralMaster generalMaster);

	/**
	 * Persists a new country with the given password to the underlaying database.
	 * 
	 * @param country
	 *            country to persist
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void updateGeneralMaster(GeneralMaster generalMaster);

	

	/**
	 * Deletes a country from the underlaying database.
	 * 
	 * @param country
	 *            to delete
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void deleteGeneralMaster(GeneralMaster generalMaster);



	/**
	 * Retrieves the page size for pagination.
	 * 
	 * @return the page size for pagination.
	 */
	int getPageSize();
}
