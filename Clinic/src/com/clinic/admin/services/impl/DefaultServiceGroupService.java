/**
 * 
 */
package com.clinic.admin.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.services.ServiceGroupService;

/**
 * Implementation of the AppCountryService-Interface.
 * 
 * @author Vinoth
 */
@Service("serviceGroupService")
public class DefaultServiceGroupService implements ServiceGroupService {

	@Resource
	private GenericDao dao;

	
	@Override
	public ServiceGroup findById(Long id) {
		return dao.find(ServiceGroup.class, id);
	}

	@Override
	public List<ServiceGroup> findAll() {
		System.out.println();
		List<ServiceGroup> l= dao.findAll(ServiceGroup.class);
		
		return l;
	}

	@Override
	public void create(ServiceGroup serviceGroup) {
	
		dao.persist(serviceGroup);
	}

	@Override
	public void update(ServiceGroup serviceGroup) {
		dao.update(serviceGroup);
	}

	
	public void delete(ServiceGroup serviceGroup) {
		dao.remove(serviceGroup);
	}

	public List<ServiceGroup> getServiceGroups() {
		return dao.getServiceGroups();
	}


}
