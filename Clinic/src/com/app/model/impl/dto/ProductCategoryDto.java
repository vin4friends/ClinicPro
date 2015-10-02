/**
 * 
 */
package com.app.model.impl.dto;

import com.app.model.impl.Application;
import com.app.model.impl.ProductCategory;

/**
 * 
 * Data transfer object for transferring product category to the finder frontend.
 * @author jomn
 *
 */
public class ProductCategoryDto {

	private final Long id;
	
	private final String name;
	
	private final Long iconId;

	/**
	 */
	public ProductCategoryDto(ProductCategory productCategory, Application application) {
		super();
		this.id = productCategory.getId();
		this.name = productCategory.getName();
		if(application.getImageSet().getProductCategoryIcon(productCategory)!= null){
			this.iconId = application.getImageSet().getProductCategoryIcon(productCategory).getIcon().getId();
		}else{
			this.iconId = null;
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the iconId
	 */
	public Long getIconId() {
		return iconId;
	}
	
	
	
}
