/**
 * 
 */
package com.app.fe.web.admin.location;

import java.util.List;

import javax.annotation.Resource;

import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractListAction;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.util.CustomStringUtil;

/**
 * @author jomn
 * 
 */
public class LocationListAction extends AbstractListAction<Location> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2789479698845316044L;

	private Country country;

	private List<Location> locations;

	@Resource
	private LocationAdministrationFacade facade;

	/**
	 * Der Suchstring
	 */
	private String q;

	public LocationListAction() {
		country = new Country();
	}

	public String list() {
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		super.prepare();
		if (country.getCountrycode() == null
				|| country.getCountrycode().isEmpty()) {
			country.setCountrycode(country.getDefaultLanguage().split("-")[1]);
		}
		if (CustomStringUtil.isNotBlank(country.getCountrycode())) {
			country = facade.findCountryByPk(country.getCountrycode());
		}
		if (CustomStringUtil.isBlank(q)) {
			locations = facade.findAllLocations(country);
		} else {
			locations = facade.findLocations(country, null, q);
		}

	}

	@Override
	protected int getPageSizeInternal() {
		return facade.getPageSize();
	}

	@Override
	public List<Location> getList() {
		return locations;
	}

	public Country getCountry() {
		return country;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
