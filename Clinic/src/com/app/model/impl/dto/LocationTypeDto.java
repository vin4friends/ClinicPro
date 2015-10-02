/**
 * 
 */
package com.app.model.impl.dto;

import com.app.model.impl.Application;
import com.app.model.impl.LocationType;

/**
 * 
 * Data transfer object for transferring location type to the finder frontend.
 * @author jomn
 *
 */
public class LocationTypeDto {

	private final Long id;
	
	private final String locationType;
	
	private final Long iconId;

	/**
	 */
	public LocationTypeDto(LocationType locationType, Application application) {
		super();
		this.id = locationType.getId();
		this.locationType = locationType.getLocationType();
		
		if(application.getImageSet().getLocationTypeIcon(locationType)!= null){
			this.iconId = application.getImageSet().getLocationTypeIcon(locationType).getIcon().getId();
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
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @return the iconId
	 */
	public Long getIconId() {
		return iconId;
	}
	
	
	
}
