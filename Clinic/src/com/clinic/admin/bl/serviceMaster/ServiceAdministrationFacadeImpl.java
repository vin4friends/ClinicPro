/**
 * 
 */
package com.clinic.admin.bl.serviceMaster;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.services.EnvironmentPropertiesService;
import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.model.impl.ServiceMaster;
import com.clinic.admin.services.ServiceGroupService;
import com.clinic.admin.services.ServiceMasterService;

/**
 * @author Vinoth
 * 
 */
@Service("serviceAdministrationFacade")
public class ServiceAdministrationFacadeImpl implements
		ServiceAdministrationFacade {

	@Resource
	private ServiceMasterService serviceMasterService;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Resource
	private ServiceGroupService serviceGroupService;
	@Override
	public List<ServiceMaster> findAllService() {
		return serviceMasterService.findAll();
	}

	@Override
	public ServiceMaster findServiceById(Long id) {
		return serviceMasterService.findById(id);
	}

	@Override
	public void createServiceMaster(ServiceMaster serviceMaster) {
		serviceMasterService.create(serviceMaster);
	}

	@Override
	public void updateServiceMaster(ServiceMaster serviceMaster) {
		serviceMasterService.update(serviceMaster);
	}

	@Override
	public void deleteServiceMaster(ServiceMaster serviceMaster) {
		serviceMasterService.delete(serviceMaster);
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService
				.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}

	@Override
	public List<ServiceGroup> getServiceGroup() {
		return serviceGroupService.getServiceGroups();
	}
}
