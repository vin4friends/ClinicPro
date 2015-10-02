/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.Application;

/**
 * Service for dealing with Application Entities.
 * 
 * @author jomu
 */
public interface ApplicationService {

	/**
	 * Find an application by Id.
	 * 
	 * @param id
	 *            the Id of the searched application.
	 * @return the found application or <code>null</code> if no application
	 *         could be found for the given id.
	 */
	Application findApplication(Long id);

	/**
	 * Find an application by its unique key.
	 * 
	 * @param applicationKey
	 *            the unique key of the searched application.
	 * @return the found application or <code>null</code> if no application
	 *         could be found for the given id.
	 */
	Application getApplication(String applicationKey);

	/**
	 * Persist a apllication Entity into the database.
	 * 
	 * @param application
	 *            the entity to persist.
	 */
	void createApplication(Application application);

	/**
	 * Finds all Application.
	 * 
	 * @return List of found Applications
	 */
	List<Application> findAll();

	/**
	 * removes an application from the underlying database.
	 * 
	 * @param application
	 *            the application to remove
	 */
	void deleteApplication(Application application);

	/**
	 * retrieves max number of locations per search result. first it is checked
	 * if a max result is defined for the application. if not it is checked if
	 * the application is defined for only one country and if this country has a
	 * correspending value set which could be used. if there is also nothing
	 * found the environment property is returned.
	 * 
	 * @return returns max number of locations per search result for the
	 *         specified application.
	 */
	int getMaxNumberOfLocationsPerSearchResult(Application application);

	/**
	 * Generates a unique identifier for identifiying a application via finder
	 * frontend.
	 * 
	 * @return a 8 byte alpha numeric string for identifying an application
	 */
	String getNewApplicationKey();

	/**
	 * Checks if the provided applicationKey is unique.
	 * 
	 * @param applicationKey
	 *            the application key to test for uniqueness
	 * @return {@code true} if the applicationKey is unique otherwise
	 *         {@code false}
	 */
	boolean isApplicationKeyUnique(String applicationKey);

	/**
	 * Build an iframe example url.
	 * 
	 * @param application
	 *            the applicatin for which the example url should be build.
	 * @return the example url as string
	 */
	String getIframeExampleUrl(Application application);

	/**
	 * Build an iframe example url for internet versions.
	 * 
	 * @return the example url as string
	 */
	String getIframeExampleUrlInternet();

	/**
	 * Build an iframe example url for intranet versions.
	 * 
	 * @return the example url as string
	 */
	String getIframeExampleUrlIntranet();

}
