/**
 * 
 */
package com.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.LanguageUtil;
import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.dto.ProductCategoryDto;
import com.app.util.CustomStringUtil;

/**
 * Implementation of the ProductCategoryService-Interface.
 * 
 * @author jomu
 */
@Service("productCategoryService")
public class DefaultProductCategoryService implements ProductCategoryService {

	@Resource
	private transient GenericDao dao;

	@Override
	public List<ProductCategory> findAll(Country country) {
		return findAll(country, null);
	}

	@Override
	public List<ProductCategory> findAll(Country country, String language) {
		if (country == null || CustomStringUtil.isBlank(country.getCountrycode())) {
			return Collections.emptyList();
		}

		final String[] lanCandidates = LanguageUtil
				.getLanguageCandidates(language);

		if (lanCandidates != null) {
			for (String candidateLanguage : lanCandidates) {
				final Criteria ct = buildFindQuery(
						country, candidateLanguage);
				List<ProductCategory> result = dao.findList(ct);
				if (!result.isEmpty()) {
					return result;
				}
			}
		} else {
			final Criteria query = buildFindQuery(
					country, language);
			return dao.findList(query);
		}

		return Collections.emptyList();
	}

	private Criteria buildFindQuery(Country country,
			String language) {
		Criteria c=dao.getCriteria(ProductCategory.class);
		Criterion where=Restrictions.eq("country",country );
		if(CustomStringUtil.isNotBlank(language))
		{
			where=Restrictions.and(where, Restrictions.eq("language", language));
		}
		c.add(where);
		return c;
		//ProductCategory_.country
	/*	final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<ProductCategory> query = cb
				.createQuery(ProductCategory.class);
		Root<ProductCategory> productCategory = query
				.from(ProductCategory.class);

		Predicate where = cb.equal(
				productCategory.get(ProductCategory_.country), country);
		if (StringUtils.isNotBlank(language)) {
			where = cb.and(where, cb.equal(
					productCategory.get(ProductCategory_.language), language));
		}

		query.select(productCategory);
		query.where(where);

		return query;*/
	
	}

	@Override
	public List<ProductCategoryDto> findProductCategoriesByApplication(
			Application application, String language) {
		if (application == null || application.getCountries().isEmpty()) {
			return Collections.emptyList();
		}

		final String[] lanCandidates = LanguageUtil
				.getLanguageCandidates(language);

		if (lanCandidates != null) {
			for (String candidateLanguage : lanCandidates) {
				final Criteria query = buildProductCategoriesByApplicationQuery(
						application, candidateLanguage);
				List<ProductCategory> result = dao.findList(query);
				if (!result.isEmpty()) {
					return transferToDto(result, application);
				}
			}
		} else {
			final Criteria query = buildProductCategoriesByApplicationQuery(
					application, language);
			List<ProductCategory> result = dao.findList(query);
			return transferToDto(result, application);
		}

		return Collections.emptyList();
		
	}

	private List<ProductCategoryDto> transferToDto(
			List<ProductCategory> productCategories, Application application) {
		List<ProductCategoryDto> productCategoryDtos = new ArrayList<ProductCategoryDto>();
		for (ProductCategory cat : productCategories) {
			productCategoryDtos.add(new ProductCategoryDto(cat, application));
		}
		return productCategoryDtos;
	}

	private Criteria buildProductCategoriesByApplicationQuery(
			Application application, String lanCandidate) {
		
		Criteria c=dao.getCriteria(ProductCategory.class);
		
		
		Criterion where=Restrictions.in("country", application.getCountries());
		
		
		if(CustomStringUtil.isNotBlank(lanCandidate))
		{
		where=Restrictions.and(where, Restrictions.eq("language", lanCandidate));	
		}
		c.add(where);
		c.addOrder(Order.asc("country"));

		c.addOrder(Order.asc("theOrder"));
		
		return c;
		
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<ProductCategory> query = cb
				.createQuery(ProductCategory.class);
		final Root<ProductCategory> productCategory = query
				.from(ProductCategory.class);
		final Path<Country> country = productCategory
				.get(ProductCategory_.country);
		final Path<String> language = productCategory
				.get(ProductCategory_.language);
		final Path<Integer> theOrder = productCategory
				.get(ProductCategory_.theOrder);

		Predicate where = country.in(application.getCountries());

		if (StringUtils.isNotBlank(lanCandidate)) {
			where = cb.and(where, cb.equal(language, lanCandidate));
		}

		query.select(productCategory);
		query.where(where);
		query.orderBy(cb.asc(country), cb.asc(theOrder));

		return query;*/
	
	
	}
		
}
