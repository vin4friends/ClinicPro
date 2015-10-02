/**
 * 
 */
package com.clinic.admin.bl.serviceGroup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.services.EnvironmentPropertiesService;
import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.services.ServiceGroupService;

/**
 * @author Vinoth
 * 
 */
@Service("serviceGroupAdministrationFacade")
public class ServiceGroupAdministrationFacadeImpl implements
		ServiceGroupAdministrationFacade {

	@Resource
	private ServiceGroupService serviceGroupService;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Override
	public List<ServiceGroup> findAllServiceGroup() {
		return serviceGroupService.findAll();
	}

	@Override
	public ServiceGroup findServiceGroupById(Long id) {
		return serviceGroupService.findById(id);
	}

	@Override
	public void createServiceGroup(ServiceGroup serviceGroup) {
		serviceGroupService.create(serviceGroup);
	}

	@Override
	public void updateServiceGroup(ServiceGroup serviceGroup) {
		serviceGroupService.update(serviceGroup);
	}

	@Override
	public void deleteServiceGroup(ServiceGroup serviceGroup) {
		serviceGroupService.delete(serviceGroup);
	}

	@Override
	public int getPageSize() {
		return environmentPropertiesService
				.getIntegerProperty(IntegerProperty.PAGINATION_PAGE_SIZE);
	}
}
