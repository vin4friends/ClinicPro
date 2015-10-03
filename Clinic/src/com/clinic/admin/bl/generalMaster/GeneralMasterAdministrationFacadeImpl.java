/**
 * 
 */
package com.clinic.admin.bl.generalMaster;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.services.EnvironmentPropertiesService;
import com.clinic.admin.model.impl.GeneralMaster;
import com.clinic.admin.services.GeneralMasterService;

/**
 * @author Vineet
 * 
 */
@Service("generalMasterAdministrationFacade")
public class GeneralMasterAdministrationFacadeImpl implements
		GeneralMasterAdministrationFacade {

	@Resource
	private GeneralMasterService generalMasterService;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Override
	public List<GeneralMaster> findAllGeneralMaster() {
		return generalMasterService.findAll();
	}

	@Override
	public GeneralMaster findGeneralMasterById(Long id) {
		return generalMasterService.findById(id);
	}

	@Override
	public void createGeneralMaster(GeneralMaster generalMaster) {
		generalMasterService.create(generalMaster);
	}

	@Override
	public void updateGeneralMaster(GeneralMaster generalMaster) {
		generalMasterService.update(generalMaster);
	}

	@Override
	public void deleteGeneralMaster(GeneralMaster generalMaster) {
		generalMasterService.delete(generalMaster);
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService
				.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}
}
