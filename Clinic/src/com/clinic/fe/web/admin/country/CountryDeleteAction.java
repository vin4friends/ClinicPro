/**
 * 
 */
package com.clinic.fe.web.admin.country;

import javax.annotation.Resource;

import com.app.common.fe.impl.AbstractDeleteAction;
import com.clinic.admin.bl.country.CountryAdministrationFacade;
import com.clinic.model.impl.AppCountry;

/**
 * @author Vinoth
 * 
 */
public class CountryDeleteAction extends AbstractDeleteAction<AppCountry> {

	private static final long serialVersionUID = 1L;

	@Resource
	private transient CountryAdministrationFacade facade;

	public CountryDeleteAction() {
		super("CountryDelete");
	}

	@Override
	public void prepare() throws Exception {
		model = facade.findcountryById(model.getCountryId());
	}

	@Override
	public String delete() {
		facade.deletecountry(model);
		return SUCCESS;
	}

	@Override
	protected AppCountry createModel() {
		return new AppCountry();
	}
}
