/**
 * 
 */
package com.app.fe.web.admin.location;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractBaseAction;
import com.app.model.impl.Country;
import com.app.util.CustomStringUtil;

/**
 * Index Action für die Location Administration.
 * 
 * @author jomu
 */
public class LocationIndexAction extends AbstractBaseAction {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Länder der Anwendung, auf die der Nutzer zugreifen darf.
	 */
	private List<Country> countries = Collections.emptyList();

	private Country country;

	/**
	 * Service der die Länder liefert.
	 */
	@Resource
	private transient LocationAdministrationFacade facade;

	public LocationIndexAction() {
		country = new Country();
	}

	@Override
	public void prepare() throws Exception {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (CustomStringUtil.isNotBlank(country.getCountrycode())) {
			country = facade.findCountryByPk(country.getCountrycode());
		}
		countries = facade.getAllowedCountriesForUser(username);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public Country getCountry() {
		return country;
	}
}
