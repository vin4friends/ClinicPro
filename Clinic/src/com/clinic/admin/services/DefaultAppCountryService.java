/**
 * 
 */
package com.clinic.admin.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.clinic.model.impl.AppCountry;

/**
 * Implementation of the AppCountryService-Interface.
 * 
 * @author Vinoth
 */
@Service("appCountryService")
public class DefaultAppCountryService implements AppCountryService {

	@Resource
	private GenericDao dao;

	
	@Override
	public AppCountry findById(Long id) {
		return dao.find(AppCountry.class, id);
	}

	@Override
	public List<AppCountry> findAll() {
		System.out.println();
		List<AppCountry> l= dao.findAll(AppCountry.class);
		
		return l;
	}

	@Override
	public void create(AppCountry country) {
	
		dao.persist(country);
	}

	@Override
	public void update(AppCountry country) {
		dao.update(country);
	}

	
	public void delete(AppCountry country) {
		dao.remove(country);
	}


}
