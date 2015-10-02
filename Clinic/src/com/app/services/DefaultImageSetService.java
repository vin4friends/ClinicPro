/**
 * 
 */
package com.app.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.ImageSet;

/**
 * @author jomn
 *
 */
@Service("imageSetService")
public class DefaultImageSetService implements ImageSetService {

	@Resource
	private transient GenericDao dao;
	
	/* (non-Javadoc)
	 * @see com.app.services.ImageSetService#findAll()
	 */
	@Override
	public List<ImageSet> findAll() {
		return dao.findAll(ImageSet.class);
	}

}
