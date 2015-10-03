/**
 * 
 */
package com.clinic.admin.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.clinic.admin.model.impl.GeneralMaster;
import com.clinic.admin.services.GeneralMasterService;

/**
 * Implementation of the AppCountryService-Interface.
 * 
 * @author Vineet
 */
@Service("seneralMasterService")
public class DefaultGeneralMasterService implements GeneralMasterService {

	@Resource
	private GenericDao dao;

	
	@Override
	public GeneralMaster findById(Long id) {
		return dao.find(GeneralMaster.class, id);
	}

	@Override
	public List<GeneralMaster> findAll() {
		System.out.println();
		List<GeneralMaster> l= dao.findAll(GeneralMaster.class);
		
		return l;
	}

	@Override
	public void create(GeneralMaster generalMaster) {
	
		dao.persist(generalMaster);
	}

	@Override
	public void update(GeneralMaster generalMaster) {
		dao.update(generalMaster);
	}

	
	public void delete(GeneralMaster generalMaster) {
		dao.remove(generalMaster);
	}

	@Override
	public List<GeneralMaster> getGeneralmasters() {
		// TODO Auto-generated method stub
		return null;
	}


}
