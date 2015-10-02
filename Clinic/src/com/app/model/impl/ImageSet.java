/**
 * 
 */
package com.app.model.impl;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * //@author jomn
 * 
 */
//@Entity
//@Table(name = "IMAGESET")
public class ImageSet {

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;

	//@Column(name= "NAME", nullable = false)
	private String name;
	
	//@OneToMany(mappedBy="imageSet")
	private Set<ProductCategoryIcon> productCategoryIcons = new HashSet<ProductCategoryIcon>();

	//@OneToMany(mappedBy="imageSet")
	private Set<LocationTypeIcon> locationTypeIcons = new HashSet<LocationTypeIcon>();
	
	/**
	 * //@return the id
	 */
	public Long getId() {
		return id;
	}

	
	
	/**
	 * //@return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * //@param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * //@return the productCategoryIcons
	 */
	public Set<ProductCategoryIcon> getProductCategoryIcons() {
		return productCategoryIcons;
	}

	/**
	 * //@param productCategoryIcons
	 *            the productCategoryIcons to set
	 */
	public void setProductCategoryIcons(
			Set<ProductCategoryIcon> productCategoryIcons) {
		this.productCategoryIcons = productCategoryIcons;
	}

	/**
	 * //@return the locationTypeIcons
	 */
	public Set<LocationTypeIcon> getLocationTypeIcons() {
		return locationTypeIcons;
	}

	/**
	 * //@param locationTypeIcons the locationTypeIcons to set
	 */
	public void setLocationTypeIcons(Set<LocationTypeIcon> locationTypeIcons) {
		this.locationTypeIcons = locationTypeIcons;
	}

	public LocationTypeIcon getLocationTypeIcon(LocationType locationType){
		for(LocationTypeIcon icon : locationTypeIcons){
			if(locationType.getId().equals(icon.getLocationType().getId())){
				return icon;
			}
		}
		return null;
	}

	public ProductCategoryIcon getProductCategoryIcon(ProductCategory productCategory){
		for(ProductCategoryIcon icon : productCategoryIcons){
			if(productCategory.getId().equals(icon.getProductCategory().getId())){
				return icon;
			}
		}
		return null;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
}
