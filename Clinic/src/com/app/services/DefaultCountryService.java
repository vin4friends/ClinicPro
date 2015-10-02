/**
 * 
 */
package com.app.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.Country;
import com.app.model.impl.Country_;
import com.app.model.impl.Location;
import com.app.security.LfAuthentication;

/**
 * Implementation of the CountryService-Interface.
 * 
 * @author jomu
 */
@Service("countryService")
public class DefaultCountryService implements CountryService {

	@Resource
	private transient GenericDao dao;

	@Override
	public List<Country> getAllowedCountriesForUser(String username) {
		// Super admin sees all countries
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof LfAuthentication) {
			LfAuthentication lfAuth = (LfAuthentication) auth;

			if (lfAuth.isSuperAdmin()) {
			}
		}
		
		

		// all others are seeing the countries which are attached to the
		
		//select * from country inner join on C 
		
		
		
	/*	CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Country> query = cb.createQuery(Country.class);

		Root<LfUser> user = query.from(LfUser.class);
		Join<LfUser, Country> countries = user.join(LfUser_.countries);

		query.select(countries);
		query.where(cb.equal(user.get(LfUser_.login), username));

		List<Country> results = dao.findList(query);
*/
		List<Country> results=dao.getAllowedCountriesForUser(username);
		System.err.println("here in method");
		return results;
	}

	@Override
	public List<Country> findAll() {
		return dao.findAll(Country.class);
	}

	@Override
	public Country findCountryByPk(String countryCode) {
		return (Country) dao.findByUniqueAttributeN(Country_.countrycode, countryCode);
	}

	@Override
	public List<String> getAvailableLanguagesForCountry(Country country) {
	
	Criteria crt=dao.getCriteria(Location.class);
	Criterion where=Restrictions.eq("country", country);
	crt.add(where);
	crt.setProjection(Projections.groupProperty("language"));
	return dao.findList(crt);
	
		
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<String> query = cb.createQuery(String.class);
		Root<Location> location = query.from(Location.class);
		Path<String> language = location.get(Location_.language);

		query.where(cb.equal(location.get(Location_.country), country));
		query.select(language);
		query.groupBy(language);
		
*/
		//return dao.getAvailableLanguagesForCountry(country);
	}
}
