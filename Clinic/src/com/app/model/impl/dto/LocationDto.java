/**
 * 
 */
package com.app.model.impl.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.app.model.impl.ImageSet;
import com.app.model.impl.Location;
import com.app.model.impl.LocationTypeIcon;
import com.app.model.impl.enums.ApplicationType;

/**
 * Data transfer object for transferring location data to the finder frontend.
 * 
 * @author jomu
 */
public class LocationDto implements Serializable {

	private static final long serialVersionUID = -7627555933069314977L;

	/** the latitude of the corresponding location */
	private final String latitude;
	/** the longitude of the corresponding location */
	private final String longitude;
	/** the sitename of the corresponding location */
	private final String sitename;
	/** the sitecode of the corresponding location */
	private final String sitecode;
	/** the tradingname of the corresponding location */
	private final String tradingname;
	/** the address of the corresponding location */
	private final String address;
	/** the telephone of the corresponding location */
	private final String telephone;
	/** the telefax of the corresponding location */
	private final String telefax;
	/** the email of the corresponding location */
	private final String email;
	/** the url of the corresponding location */
	private final String url;
	/** the contact of the corresponding location */
	private final String contact;
	/** the openweekday of the corresponding location */
	private final String openweekday;
	/** the openweekend of the corresponding location */
	private final String openweekend;
	/** the type of the location */
	private final IconDto locationType;
	/** the icons of the productcategories of the corresponding location */
	private final List<IconDto> icons;
	/** the distance of this location to the search location */
	private final String distance;
	/** the freetextof this location */
	private final String freetext;

	public LocationDto(final Location source, String distance, List<IconDto> icons, String address, ApplicationType type, ImageSet imageSet) {
		this.latitude = source.getLatitude().toString();
		this.longitude = source.getLongitude().toString();
		this.distance = distance;

		this.tradingname = source.getTradingname();
		this.telephone = source.getTelephone();
		this.telefax = source.getTelefax();
		this.email = source.getEmail();
		this.url = source.getUrl();
		this.contact = source.getContact();
		this.openweekday = source.getOpenweekday();
		this.openweekend = source.getOpenweekend();

		LocationTypeIcon ltIcon = imageSet.getLocationTypeIcon(source.getLocationType());
		this.locationType = ltIcon != null ? new IconDto(ltIcon.getIcon()
				.getId(), source.getLocationType().getLocationType()) : new IconDto(-1L, "");

		this.icons = Collections.unmodifiableList(icons);

		// additional attributes for intranet applications
		if (ApplicationType.INTRANET.equals(type)) {
			this.sitename = source.getSitename();
			this.sitecode = source.getSitecode();
			this.freetext = source.getFreeText();
		} else {
			this.sitename = null;
			this.sitecode = null;
			this.freetext = null;
		}

		this.address = address;
	}

	/**
	 * Get the latitude of this dto which corresponds to the latitude of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Get the longitude of this dto which corresponds to the longitude of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * Get the sitename of this dto which corresponds to the sitename of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getSitename() {
		return sitename;
	}

	/**
	 * Get the sitecode of this dto which corresponds to the sitecode of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getSitecode() {
		return sitecode;
	}

	/**
	 * Get the tradingname of this dto which corresponds to the tradingname of
	 * the location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getTradingname() {
		return tradingname;
	}

	/**
	 * Get the address of this dto which corresponds to the address of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Get the telephone of this dto which corresponds to the telephone of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Get the telefax of this dto which corresponds to the telefax of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getTelefax() {
		return telefax;
	}

	/**
	 * Get the email of this dto which corresponds to the email of the location
	 * from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get the url of this dto which corresponds to the url of the location from
	 * which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Get the contact of this dto which corresponds to the contact of the
	 * location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Get the openweekday of this dto which corresponds to the openweekday of
	 * the location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getOpenweekday() {
		return openweekday;
	}

	/**
	 * Get the openweekend of this dto which corresponds to the openweekend of
	 * the location from which this dto is build from.
	 * 
	 * @return the latitude attribute.
	 */
	public String getOpenweekend() {
		return openweekend;
	}

	/**
	 * 
	 * @return the type of the location
	 */
	public IconDto getLocationType() {
		return locationType;
	}

	/**
	 * Get a list of icons which where associated to the productcategories of
	 * the location this dto was build from.
	 * 
	 * @return the list of icons
	 */
	public List<IconDto> getIcons() {
		return icons;
	}

	/**
	 * Get the distance to the search location of the location this dto was
	 * build from.
	 * 
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * Get the freetext of the location which corresponds to the freetext of the
	 * location from which this dto is build from.
	 * 
	 * @return the freetext
	 */
	public String getFreetext() {
		return freetext;
	}

}
