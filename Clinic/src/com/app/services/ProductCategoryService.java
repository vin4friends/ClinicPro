/**
 * 
 */
package com.app.services;

import java.util.List;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.dto.ProductCategoryDto;

/**
 * Service for dealing with ProductCategories.
 * 
 * @author jomu
 */
public interface ProductCategoryService {

	/**
	 * Retrieves all ProductCategories of a given country.
	 * 
	 * @param country
	 *            the country for which the product categories should be loaded.
	 * @return a list of product categories or an empty list if no product
	 *         categories could be found.
	 */
	List<ProductCategory> findAll(Country country);

	/**
	 * Loads all product categories for a given country with the specified
	 * language. If the {@code language} string contains {@code "-"} and the
	 * search for the language string yields no results an new search will be
	 * applied with the substring before the last {@code "-"} as new language
	 * string. This will be done until no {@code "-"} could be found in the
	 * language string.
	 * 
	 * @param country
	 *            the country for which the product categories should be loaded.
	 * @param language
	 *            the language in which the product categorie should be
	 *            obtained.
	 * @return a list of product categories or an empty list if no product
	 *         categories could be found.
	 */
	List<ProductCategory> findAll(Country country, String language);

	/**
	 * Loads all ProductCategories of countries associated with the given
	 * application for the specified language. If the {@code language} string
	 * contains {@code "-"} and the search for the language string yields no
	 * results an new search will be applied with the substring before the last
	 * {@code "-"} as new language string. This will be done until no
	 * {@code "-"} could be found in the language string.
	 * 
	 * @param application
	 *            the Application for which ProductCategories should be loaded
	 * @param language
	 *            the language for which the categories should be loaded
	 * @return a List of ProductCategories
	 */
	List<ProductCategoryDto> findProductCategoriesByApplication(Application application, String language);
}
