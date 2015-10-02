/**
 * 
 */
package com.clinic.fe.web.admin.country;

import java.util.List;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractListAction;
import com.clinic.admin.bl.country.CountryAdministrationFacade;
import com.clinic.model.impl.AppCountry;


/**
 * @author Vinoth
 * 
 */
public class CountryIndexAction extends AbstractListAction<AppCountry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Resource
	private transient CountryAdministrationFacade countryAdministrationFacade;

	@Override
	public List<AppCountry> getList() {
		
		return countryAdministrationFacade.findAllcountry();
	}

	@Override
	protected int getPageSizeInternal() {
		return countryAdministrationFacade.getPageSize();
	}
}
