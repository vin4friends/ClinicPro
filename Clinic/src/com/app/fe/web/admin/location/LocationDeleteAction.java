/**
 * 
 */
package com.app.fe.web.admin.location;

import javax.annotation.Resource;

import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractDeleteAction;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.util.CustomStringUtil;

/**
 * @author jomu
 * 
 */
public class LocationDeleteAction extends AbstractDeleteAction<Location> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private transient LocationAdministrationFacade facade;

	private Country country;

	public LocationDeleteAction() {
		super("LocationDelete");
		country = new Country();
	}

	@Override
	protected Location createModel() {
		return new Location();
	}

	@Override
	public void prepare() throws Exception {
		model = facade.findLocation(model.getId());

		if (CustomStringUtil.isNotBlank(country.getCountrycode())) {
			country = facade.findCountryByPk(country.getCountrycode());
		} else {
			country = model.getCountry();
		}
	}

	@Override
	public String delete() {
		facade.deleteLocation(model);
		return SUCCESS;
	}

	public Country getCountry() {
		return country;
	}
}
