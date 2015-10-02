package com.clinic.admin.services;

import java.util.List;

import com.clinic.model.impl.AppCountry;

public interface AppCountryService {

	/**
	 * Find a Country by Id.
	 * 
	 * @param id
	 *            the Id of the Country to load.
	 * @return the found Country or <code>null</code>
	 */
	AppCountry findById(Long id);

	/**
	 * Finds all Countries.
	 * 
	 * @return a list of all Countries known to the system.
	 */
	List<AppCountry> findAll();

	/**
	 * Persists a new Country to the underlaying database.
	 * 
	 * @param Country
	 *            Country to persist
	 */
	void create(AppCountry country);

	/**
	 * Persists a new Country  to the underlaying database.
	 * 
	 * @param Country
	 *            Country to persist
	 */
	void update(AppCountry country);

	/**
	 * Deletes a Country from the underlaying database.
	 * 
	 * @param Country
	 *            to delete
	 */
	void delete(AppCountry country);

}
