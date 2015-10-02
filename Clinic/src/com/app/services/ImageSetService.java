/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.ImageSet;

/**
 * @author jomn
 *
 */
public interface ImageSetService {
	
	/**
	 * Loads all imageSets from the underlying database.
	 * 
	 * @return a list of all imageSets.
	 */
	List<ImageSet> findAll();
}
