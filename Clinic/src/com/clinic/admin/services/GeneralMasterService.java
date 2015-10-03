package com.clinic.admin.services;

import java.util.List;

import com.clinic.admin.model.impl.GeneralMaster;

public interface GeneralMasterService {

	
	GeneralMaster findById(Long id);

	 
	List<GeneralMaster> findAll();

	void create(GeneralMaster generalmaster);

	void update(GeneralMaster generalmaster);

	
	void delete(GeneralMaster generalmaster);
	
	public List<GeneralMaster> getGeneralmasters();

}
