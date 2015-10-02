package com.clinic.admin.services;

import java.util.List;

import com.clinic.admin.model.impl.ServiceGroup;

public interface ServiceGroupService {

	
	ServiceGroup findById(Long id);

	
	List<ServiceGroup> findAll();

	void create(ServiceGroup serviceGroup);

	void update(ServiceGroup serviceGroup);

	
	void delete(ServiceGroup serviceGroup);
	
	public List<ServiceGroup> getServiceGroups();

}
