/**
 * 
 */
package com.app.model.impl;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * //@author jomn
 *
 */
//@Entity
//@Table(name = "LOCATIONTYPEICON")
public class LocationTypeIcon {

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	//@ManyToOne
	//@JoinColumn(name = "LOCATIONTYPEID", referencedColumnName = "ID")
	private LocationType locationType;
	
	//@ManyToOne
	//@JoinColumn(name = "ICONID", referencedColumnName = "ID")
	private Icon icon;
	
	//@ManyToOne
	//@JoinColumn(name = "IMAGESETID", referencedColumnName = "ID")
	private ImageSet imageSet;


	/**
	 * //@return the locationType
	 */
	public LocationType getLocationType() {
		return locationType;
	}

	/**
	 * //@param locationType the locationType to set
	 */
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	/**
	 * //@return the icon
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * //@param icon the icon to set
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * //@return the imageSet
	 */
	public ImageSet getImageSet() {
		return imageSet;
	}

	/**
	 * //@param imageSet the imageSet to set
	 */
	public void setImageSet(ImageSet imageSet) {
		this.imageSet = imageSet;
	}

	/**
	 * //@return the id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
