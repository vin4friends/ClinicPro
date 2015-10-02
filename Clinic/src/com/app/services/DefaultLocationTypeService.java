/**
 * 
 */
package com.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.app.GenericDao;
import com.app.LanguageUtil;
import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.LocationType;
import com.app.model.impl.dto.LocationTypeDto;
import com.app.util.CustomStringUtil;

/**
 * Implementation of the LocationTypeService-Interface.
 * 
 * @author jomu
 */
@Service("locationTypeService")
public class DefaultLocationTypeService implements LocationTypeService {

	@Resource
	private transient GenericDao dao;

	@Override
	public List<LocationType> findAll(Country country) {
		return findAll(country, null);
	}

	@Override
	public List<LocationType> findAll(Country country, String language) {
		if (country == null || CustomStringUtil.isBlank(country.getCountrycode())) {
			return Collections.emptyList();
		}

		final String[] lanCandidates = LanguageUtil
				.getLanguageCandidates(language);

		if (lanCandidates != null) {
			for (String candidateLanguage : lanCandidates) {
				final Criteria query = buildFindQuery(
						country, candidateLanguage);
				List<LocationType> result = dao.findList(query);
				if (!result.isEmpty()) {
					return result;
				}
			}
		} else {
			final Criteria query = buildFindQuery(country,
					language);
			return dao.findList(query);
		}

		return Collections.emptyList();
	}
	
	
	private CriteriaQuery<Location> buildFindQuery(Country country, String language, String search) {

		Session ses=null;
	Criteria criteria=ses.createCriteria(Location.class);
	Criterion where=Restrictions.eq("country", country);
	if(CustomStringUtil.isNotBlank(language))
			{
			
				where=Restrictions.and(where,Restrictions.eq("language", language));
			
			}
	
	if (CustomStringUtil.isNotBlank(search))
	{
		String lowerCaseSearch = search.toLowerCase();
		String[] searchTerms = lowerCaseSearch.split(" ");
		//Criterion crt1=buildSearchCriterion(criteria, location, searchTerm, searchPred)
	}
		
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<Location> query = cb.createQuery(Location.class);
		Root<Location> location = query.from(Location.class);
		query.select(location);
			
		Predicate where = cb.equal(location.get(Location_.country), country);

		if (StringUtils.isNotBlank(language)) {
			where = cb.and(where, cb.equal(location.get(Location_.language), language));
		}

		if (StringUtils.isNotBlank(search)) {
			String lowerCaseSearch = search.toLowerCase();
			String[] searchTerms = lowerCaseSearch.split(" ");

			Predicate searchPred = buildSearchPredicate(cb, location, lowerCaseSearch, cb.disjunction());

			for (String searchTerm : searchTerms) {
				searchPred = buildSearchPredicate(cb, location, searchTerm, searchPred);
			}

			where = cb.and(where, searchPred);
		}

		query.where(where);
		query.orderBy(cb.asc(location.get(Location_.sitecode)));

		return query;*/
		return null;
	}


	private Criteria buildFindQuery(Country country,
			String language) {
		
		Criteria ct=dao.getCriteria(LocationType.class);
		Criterion where=Restrictions.eq("country", country);
		
		if(CustomStringUtil.isNotBlank(language))
		{
			where=Restrictions.and(where, Restrictions.eq("language", language));
		}
		ct.add(where);
		return ct;
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<LocationType> query = cb
				.createQuery(LocationType.class);
		Root<LocationType> locationType = query.from(LocationType.class);

		Predicate where = cb.equal(locationType.get(LocationType_.country),
				country);
		if (StringUtils.isNotBlank(language)) {
			where = cb.and(where, cb.equal(
					locationType.get(LocationType_.language), language));
		}

		query.select(locationType);
		query.where(where);*/

			}

	@Override
	public LocationType getLocationType(Country country, String language,
			String locationType) {
		
		
		Assert.isTrue(
				country != null
						&& CustomStringUtil.isNotBlank(country.getCountrycode()),
				"Country.countrycode must not be empty.");
		Assert.hasLength(language, "Language must not be empty.");
		Assert.hasLength(locationType, "LocationType must not be empty.");

		Criteria ct=dao.getCriteria(LocationType.class);
		ct.add(Restrictions.eq("country", country));
		ct.add(Restrictions.eq("language", language));
		ct.add(Restrictions.eq("locationType",locationType));
		return dao.findSingle(ct);
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<LocationType> query = cb
				.createQuery(LocationType.class);
		Root<LocationType> rLocationType = query.from(LocationType.class);

		Predicate countryPred = cb.equal(
				rLocationType.get(LocationType_.country), country);
		Predicate languagePred = cb.equal(
				rLocationType.get(LocationType_.language), language);
		Predicate locationTypePred = cb.equal(
				rLocationType.get(LocationType_.locationType), locationType);

		query.select(rLocationType);
		query.where(cb.and(countryPred, languagePred, locationTypePred));

		return dao.findSingle(query);*/
		
		
	}

	@Override
	public List<LocationTypeDto> getLocationTypesByApplication(
			Application application, String language) {
		
		
		if (application == null || application.getCountries().isEmpty()) {
			return Collections.emptyList();
		}

		final String[] lanCandidates = LanguageUtil
				.getLanguageCandidates(language);

		if (lanCandidates != null) {
			for (String candidateLanguage : lanCandidates) {
				final Criteria query = buildLocationTypesByApplicationQuery(
						application, candidateLanguage);
				List<LocationType> result = dao.findList(query);
				if (!result.isEmpty()) {
					return transferToDto(result, application);
				}
			}
		} else {
			final Criteria query = buildLocationTypesByApplicationQuery(
					application, language);
			List<LocationType> res=dao.findList(query);
			return transferToDto(res, application);
		}

		return Collections.emptyList();
		
	}

	private List<LocationTypeDto> transferToDto(
			List<LocationType> locationTypes, Application application) {
		List<LocationTypeDto> locationTypeDtos = new ArrayList<LocationTypeDto>();
		for (LocationType type : locationTypes) {
			locationTypeDtos.add(new LocationTypeDto(type, application));
		}
		return locationTypeDtos;
	}

	private Criteria buildLocationTypesByApplicationQuery(
			Application application, String lanCandidate) {
		
		Criteria c=dao.getCriteria(LocationType.class);
		
		
		Criterion where=Restrictions.in("country", application.getCountries());
		
		
		if(CustomStringUtil.isNotBlank(lanCandidate))
		{
		where=Restrictions.and(where, Restrictions.eq("language", lanCandidate));	
		}
		c.add(where);
		c.addOrder(Order.asc("country"));

		c.addOrder(Order.asc("locationType"));
		
		return c;
		
		
		
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<LocationType> query = cb
				.createQuery(LocationType.class);
		final Root<LocationType> locationType = query.from(LocationType.class);
		final Path<Country> country = locationType.get(LocationType_.country);
		final Path<String> language = locationType.get(LocationType_.language);
		final Path<String> name = locationType.get(LocationType_.locationType);

		Predicate where = country.in(application.getCountries());

		if (StringUtils.isNotBlank(lanCandidate)) {
			where = cb.and(where, cb.equal(language, lanCandidate));
		}

		query.select(locationType);
		query.where(where);
		query.orderBy(cb.asc(country), cb.asc(name));

		return query;*/
		
	}

	@Override
	public void createLocationType(LocationType locationType) {
		dao.persist(locationType);
	}
}
