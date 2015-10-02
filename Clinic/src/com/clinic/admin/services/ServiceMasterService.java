package com.clinic.admin.services;

import java.util.List;

import com.clinic.admin.model.impl.ServiceMaster;

public interface ServiceMasterService {

	
	ServiceMaster findById(Long id);

	
	List<ServiceMaster> findAll();

	void create(ServiceMaster serviceMaster);

	void update(ServiceMaster serviceMaster);

	
	void delete(ServiceMaster serviceMaster);

}
