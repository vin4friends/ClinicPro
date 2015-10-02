/**
 * 
 */
package com.app.admin.bl.application;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.ImageSet;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.services.ApplicationService;
import com.app.services.CountryService;
import com.app.services.EnvironmentPropertiesService;
import com.app.services.ImageSetService;
import com.app.services.LocationTypeService;
import com.app.services.ProductCategoryService;

/**
 * @author jomu
 * 
 */
@Service("applicationAdministrationFacade")
public class ApplicationAdministrationFacadeImpl implements ApplicationAdministrationFacade {

	@Resource
	private ApplicationService applicationService;
	
	@Resource
	private LocationTypeService locationTypeService;
	
	@Resource
	private ProductCategoryService productCategoryService;

	@Resource
	private ImageSetService imageSetService;
	
	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Resource
	CountryService countryService;

	@Override
	public Application findApplication(Long id) {
		return applicationService.findApplication(id);
	}

	@Override
	public void createApplication(Application application) {
		applicationService.createApplication(application);
	}

	@Override
	public List<Application> findAll() {
		return applicationService.findAll();
	}

	@Override
	public void deleteApplication(Application application) {
		applicationService.deleteApplication(application);
	}

	@Override
	public List<Country> getAllCountries() {
		return countryService.findAll();
	}

	
	
	/* (non-Javadoc)
	 * @see com.app.admin.bl.application.ApplicationAdministrationFacade#getAllImageSets()
	 */
	@Override
	public List<ImageSet> getAllImageSets() {
		return imageSetService.findAll();
	}

	@Override
	public String getNewApplicationKey() {
		return applicationService.getNewApplicationKey();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.admin.bl.application.ApplicationAdministrationFacade
	 * #getIFrameUrl(com.app.model.impl.Application)
	 */
	@Override
	public String getIFrameUrl(Application application) {
		return applicationService.getIframeExampleUrl(application);
	}

	@Override
	public String getIframeExampleUrlInternet() {
		return applicationService.getIframeExampleUrlInternet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.admin.bl.application.ApplicationAdministrationFacade
	 * #getIframeExampleUrlIntranet()
	 */
	@Override
	public String getIframeExampleUrlIntranet() {
		return applicationService.getIframeExampleUrlIntranet();
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}

	/* (non-Javadoc)
	 * @see com.app.admin.bl.application.ApplicationAdministrationFacade#getProductCategoriesForCountry(com.app.model.impl.Country)
	 */
	@Override
	public List<ProductCategory> getProductCategoriesForCountry(Country country) {
		return productCategoryService.findAll(country);
	}

	/* (non-Javadoc)
	 * @see com.app.admin.bl.application.ApplicationAdministrationFacade#getLocationTypesForCountry(com.app.model.impl.Country)
	 */
	@Override
	public List<LocationType> getLocationTypesForCountry(Country country) {
		return locationTypeService.findAll(country);
	}
	
	
}
