/**
 * 
 */
package com.app.services.validation;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.Application;
import com.app.model.impl.Application_;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.services.ApplicationService;
import com.app.services.EnvironmentPropertiesService;
import com.app.validation.ApplicationEntityValidator;

/**
 * @author jomu
 * 
 */
@Service("applicationEntityValidator")
public class ApplicationEntityValidatorImpl implements ApplicationEntityValidator {

	@Resource
	private GenericDao dao;

	@Resource
	private ApplicationService applicationService;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Override
	public boolean validateMaxLocationResult(Object application, List<Object> messageArguments) {
		Application entity = (Application) application;

		Long maxLocationResult = entity.getMaxLocationResult();

		int minAllowed = 1;
		int maxAllowed = environmentPropertiesService.getIntegerProperty(
				IntegerProperty.MAX_LOCATIONS_PER_SEARCH_RESULT).intValue();

		if (maxLocationResult == null
				|| (maxLocationResult.intValue() >= minAllowed && maxLocationResult.intValue() <= maxAllowed)) {
			return true;
		}

		messageArguments.add(minAllowed);
		messageArguments.add(maxAllowed);
		return false;
	}

	@Override
	public boolean validateApplicationKey(Object application, List<Object> messageArguments) {
		Application entity = (Application) application;

		// no id, only check for unique application key
		if (entity.getId() == null) {
			return applicationService.isApplicationKeyUnique(entity.getApplicationKey());
		} else {/*
		
			// check for unchanged application key
			final CriteriaBuilder cb = dao.getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<Application> root = query.from(Application.class);

			query.where(cb.equal(root.get(Application_.applicationKey), entity.getApplicationKey()));
			query.select(root.get(Application_.id));

			Long id = dao.findSingle(query);
			if (id == null || (id != null && id.equals(entity.getId()))) {
				return true;
			} else {
				return false;
			}
		*/
			return false;}
	}
}
