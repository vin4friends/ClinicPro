/**
 * 
 */
package com.clinic.admin.bl.country;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.services.EnvironmentPropertiesService;
import com.clinic.admin.services.AppCountryService;
import com.clinic.model.impl.AppCountry;

/**
 * @author Vinoth
 * 
 */
@Service("countryAdministrationFacade")
public class CountryAdministrationFacadeImpl implements
		CountryAdministrationFacade {

	@Resource
	private AppCountryService appCountryService;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Override
	public List<AppCountry> findAllcountry() {
		return appCountryService.findAll();
	}

	@Override
	public AppCountry findcountryById(Long id) {
		return appCountryService.findById(id);
	}

	@Override
	public void createcountry(AppCountry country) {
		appCountryService.create(country);
	}

	@Override
	public void updatecountry(AppCountry country) {
		appCountryService.update(country);
	}

	@Override
	public void deletecountry(AppCountry country) {
		appCountryService.delete(country);
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService
				.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}
}
