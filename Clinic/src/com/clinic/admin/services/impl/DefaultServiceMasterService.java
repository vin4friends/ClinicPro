/**
 * 
 */
package com.clinic.admin.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.clinic.admin.model.impl.ServiceMaster;
import com.clinic.admin.services.ServiceMasterService;

/**
 * Implementation of the Service Master service-Interface.
 * 
 * @author Vineet
 */
@Service("serviceMasterService")
public class DefaultServiceMasterService implements ServiceMasterService {

	@Resource
	private GenericDao dao;

	
	@Override
	public ServiceMaster findById(Long id) {
		return dao.find(ServiceMaster.class, id);
	}

	@Override
	public List<ServiceMaster> findAll() {
		System.out.println("here in find all of service master");
		List<ServiceMaster> l= dao.findAll(ServiceMaster.class);
		
		return l;
	}

	@Override
	public void create(ServiceMaster serviceMaster) {
	
		dao.persist(serviceMaster);
	}

	@Override
	public void update(ServiceMaster serviceMaster) {
		dao.update(serviceMaster);
	}

	
	public void delete(ServiceMaster serviceMaster) {
		dao.remove(serviceMaster);
	}


}
