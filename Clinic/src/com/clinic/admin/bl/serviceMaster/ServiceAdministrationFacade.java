/**
 * 
 */
package com.clinic.admin.bl.serviceMaster;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.model.impl.ServiceMaster;

/**
 * @author Vineet
 * 
 */
public interface ServiceAdministrationFacade {
	/**
	 * Finds all services.
	 * 
	 * @return a list of all services known to the system.
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	List<ServiceMaster> findAllService();

	/**
	 * Find a AppCountry by Id.
	 * 
	 * @param id
	 *            the Id of the country to load.
	 * @return the found country or <code>null</code>
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	ServiceMaster findServiceById(Long id);

	/**
	 * Persists a new country with the given password to the underlaying database.
	 * 
	 * @param country
	 *            country to persist
	 * @param password
	 *            password to set for country
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void createServiceMaster(ServiceMaster serviceMaster);

	/**
	 * Persists a new country with the given password to the underlaying database.
	 * 
	 * @param country
	 *            country to persist
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void updateServiceMaster(ServiceMaster serviceMaster);

	

	/**
	 * Deletes a country from the underlaying database.
	 * 
	 * @param country
	 *            to delete
	 */
	@PreAuthorize("hasRole('ROLE_SUPPORT_ADMIN')")
	void deleteServiceMaster(ServiceMaster serviceMaster);



	/**
	 * Retrieves the page size for pagination.
	 * 
	 * @return the page size for pagination.
	 */
	int getPageSize();
	
	List<ServiceGroup> getServiceGroup();
}
