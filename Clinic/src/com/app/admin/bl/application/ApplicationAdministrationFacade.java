/**
 * 
 */
package com.app.admin.bl.application;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.ImageSet;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;

/**
 * @author jomu
 * 
 */
public interface ApplicationAdministrationFacade {
	/**
	 * Find an application by Id.
	 * 
	 * @param id
	 *            the Id of the searched application.
	 * @return the found application or <code>null</code> if no application
	 *         could be found for the given id.
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	Application findApplication(Long id);

	/**
	 * 
	 * @param application
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	void createApplication(Application application);

	/**
	 * Finds all Application.
	 * 
	 * @return List of found Applications
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	List<Application> findAll();

	/**
	 * removes an application from the underlying database.
	 * 
	 * @param application
	 *            the application to remove
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	void deleteApplication(Application application);

	/**
	 * Loads all countries from the underlying database.
	 * 
	 * @return a list of all countries.
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	List<Country> getAllCountries();

	/**
	 * Loads all imageSets from the underlying database.
	 * 
	 * @return a list of all imageSets.
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	List<ImageSet> getAllImageSets();
	
	/**
	 * Generates a unique identifier for identifiying a application via finder
	 * frontend.
	 * 
	 * @return a alpha numeric string for identifying an application
	 */
	String getNewApplicationKey();

	/**
	 * Build an iframe example url.
	 * 
	 * @param application
	 *            the application for which the example url should be build.
	 * @return the example url for internet as string
	 */
	String getIFrameUrl(Application application);

	/**
	 * get iframe example url for internet.
	 * 
	 * @return the example url for internet as string
	 */
	String getIframeExampleUrlInternet();

	/**
	 * get an iframe example url for intranet.
	 * 
	 * @return the example url for intranet as string
	 */
	String getIframeExampleUrlIntranet();

	/**
	 * Retrieves the page size for pagination.
	 * 
	 * @return the page size for pagination.
	 */
	int getPageSize();
	
	/**
	 * 
	 * @param country the country
	 * 
	 * @return a list of product categories
	 */
	List<ProductCategory> getProductCategoriesForCountry(Country country);
	
	/**
	 * 
	 * @param country the country
	 * 
	 * @return a list of location types
	 */
	List<LocationType> getLocationTypesForCountry(Country country);
}
