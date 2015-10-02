/**
 * 
 */
package com.app.services;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.LanguageUtil;
import com.app.message.GlobalMessageSource;
import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.Location_;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.ProductCategoryIcon;
import com.app.model.impl.SingularAttribute;
import com.app.model.impl.dto.IconDto;
import com.app.model.impl.dto.LocationDto;
import com.app.model.impl.enums.AddressTemplatePlaceholder;
import com.app.util.CustomStringUtil;
import com.app.util.NumberFormatUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Default implementation of LocationService-Interface.
 * 
 * @author jomn
 * @author jomu
 */
@Service("locationService")
public class DefaultLocationService implements LocationService {

	@Resource
	private GenericDao dao;

	@Resource
	private ApplicationService applicationService;

	@Resource
	private GlobalMessageSource globalMessageSource;

	/** the Encoding for Templates. */
	protected static final String TEMPLATE_ENCODING = "UTF-8";
	/** An empty String. Defaultvalue for Placeholder which are {@code null}. */
	private static final String EMPTY_STRING = " ";

	private static final String ALIAS_LOCATION = "location";
	private static final String ALIAS_DISTANCE = "distance";

	@Override
	public List<Location> findAll() {
		List<Location> result = dao.findAll(Location.class);
		return result;
	}

	@Override
	public void createLocation(Location location) {
		if(location.getId()!=null)
			{
			dao.update(location);
			}
		else
		{
			dao.persist(location);
		}
	}

	@Override
	public Location findById(Long locationId) {
		return dao.find(Location.class, locationId);
	}

	@Override
	public void deleteLocation(Location location) {
		//location.setCountry(null);
		//location.setLocationType(null);
		//location.getProductCategories().clear();
		dao.remove(location);
	}

	@Override
	public List<Location> findLocations(Country country, String language, String search) {
		
		final String[] candidates = LanguageUtil.getLanguageCandidates(language);
		
		if (candidates != null) {
			for (String candidateLanguage : candidates) {
				final Criteria query = buildFindQuery(country, candidateLanguage, search);
				List<Location> result = dao.findList(query);
				if (!result.isEmpty()) {
					return result;
				}
			}
		} else {
			final Criteria query = buildFindQuery(country, language, search);
			return dao.findList(query);
		}

		return Collections.emptyList();
		
		/*final String[] candidates = LanguageUtil.getLanguageCandidates(language);
		if (candidates != null) {
			for (String candidateLanguage : candidates) {
				final CriteriaQuery<Location> query = buildFindQuery(country, candidateLanguage, search);
				List<Location> result = dao.findList(query);
				if (!result.isEmpty()) {
					return result;
				}
			}
		} else {
			final CriteriaQuery<Location> query = buildFindQuery(country, language, search);
			return dao.findList(query);
		}

		return Collections.emptyList();*/
		
	
	}

	@Override
	public List<LocationDto> findNearestLocations(Double latitude, Double longitude, Application application,
			List<Long> locationTypeIds, List<Long> productCategoryIds, String language) {

		final String[] languageCandidates = LanguageUtil.getLanguageCandidates(language);
		
		for(String candidate: languageCandidates)
		{
					
			
			List<Object[]> result =dao.findNearestLocations(latitude, longitude, applicationService.getMaxNumberOfLocationsPerSearchResult(application), locationTypeIds, productCategoryIds, (short)1, application, language);
				/*buildLocationDtoQuery(latitude, longitude, locationTypeIds,
					productCategoryIds, application, candidate,distIds.keySet());*/
				
			
			//List<Location> loc=dao.findList(query,0,applicationService.getMaxNumberOfLocationsPerSearchResult(application));
			
			if(!result.isEmpty())
			{
				
				return buildLocationDtoListFromList(result,application);
				
			}
			
		}
		
	
		
		/*final String[] languageCandidates = LanguageUtil.getLanguageCandidates(language);

		for (String candidate : languageCandidates) {
			final CriteriaQuery<Tuple> query = buildLocationDtoQuery(latitude, longitude, locationTypeIds,
					productCategoryIds, application, candidate);
			final List<Tuple> tupleList = dao.findList(query, 0,
					applicationService.getMaxNumberOfLocationsPerSearchResult(application));

			if (!tupleList.isEmpty()) {
				return buildLocationDtoListFromTupleList(tupleList, application, language);
			}
		}
*/
		return Collections.emptyList();
		
	}

	private List<LocationDto> buildLocationDtoListFromList(List<Object[]> locList, Application application) {
		final List<LocationDto> result = new ArrayList<LocationDto>(locList.size());
		
				
		for (Object[] obj : locList) {

			final Location loc = (Location) (obj[0] instanceof Location ? obj[0]
					: obj[1]);
			final Double distance = (Double) (obj[0] instanceof Double ? obj[0]
					: obj[1]);

			final List<IconDto> icons = new ArrayList<IconDto>();
			for (ProductCategory pc : loc.getProductCategories()) {
				ProductCategoryIcon pcIcon = application.getImageSet()
						.getProductCategoryIcon(pc);
				if (pcIcon != null) {
					icons.add(new IconDto(pcIcon.getIcon().getId(), pc
							.getName()));
				}
			}

			String distanceFormatted = NumberFormatUtil.format("#.#", distance);
			String distanceWithUnit = globalMessageSource.getMessage(
					application.getUomDistance().getShortMessageKey(),
					new Object[] { distanceFormatted }, distanceFormatted,
					LocaleContextHolder.getLocale());
			String address = getAddressDisplayValue(loc);
			LocationDto dto = new LocationDto(loc, distanceWithUnit, icons,
					address, application.getType(), application.getImageSet());
			result.add(dto);
		}
		return result;
		
	}

	private Criteria buildLocationDtoQuery(Double latitude, Double longitude, List<Long> typeIds,
			List<Long> categoryIds, Application application, String language, Set set) {
		
		Criteria criteria=dao.getCriteria(Location.class,"location");
		
		
	
		Criterion	where=Restrictions.eq("location.isOnline", (short)1);
		where=Restrictions.and(where, where=Restrictions.in("location.id", set));
		
		where=Restrictions.and(where, Restrictions.in("location.country",application.getCountries()));
		if(typeIds!=null&& !typeIds.isEmpty())
		{
			
			criteria.createAlias("location.locationType", "locationType");
			where=Restrictions.and(where, Restrictions.in("locationType.id", typeIds));
		}
		else
		{
			
			where=Restrictions.and(where, Restrictions.isNull("location.locationType"));
			
		}
		
		if(categoryIds!=null&&!categoryIds.isEmpty())
		{
			criteria.createAlias("location.productCategories", "productCategories");
			where=Restrictions.and(where, Restrictions.in("productCategories.id", categoryIds));
		}
		else
		{
			where=Restrictions.and(where, Restrictions.isEmpty("location.productCategories"));
		}
		if (CustomStringUtil.isNotBlank(language)) {
			where = Restrictions.and(where, Restrictions.eq("location.language", language));
		}
		
	criteria.add(where);
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
		return criteria;
	
		/*query.multiselect(distanceFormula.alias(ALIAS_DISTANCE), location.alias(ALIAS_LOCATION));
		query.where(where);
		// order by distance column
		query.orderBy(cb.asc(distanceFormula));
		query.distinct(true);*/
		
		/*final Expression<Double> distanceFormula = buildDistanceFormula(locLat, locLon, latitude, longitude,
				application.getUomDistance().getEarthRadius());
		
					
		final CriteriaBuilder cb = dao.getCriteriaBuilder();
		final CriteriaQuery<Tuple> query = cb.createTupleQuery();
		final Root<Location> location = query.from(Location.class);

		final Path<Double> locLat = location.get(Location_.latitude);
		final Path<Double> locLon = location.get(Location_.longitude);

		final Expression<Double> distanceFormula = buildDistanceFormula(locLat, locLon, latitude, longitude,
				application.getUomDistance().getEarthRadius());

		// location must be online
		Predicate where = cb.isTrue(location.get(Location_.isOnline));

		// country filter
		where = cb.and(where, location.get(Location_.country).in(application.getCountries()));

		// LocationType Filter
		if (typeIds != null && !typeIds.isEmpty()) {
			Join<Location, LocationType> locationTypes = location.join(Location_.locationType);
			where = cb.and(where, locationTypes.get(LocationType_.id).in(typeIds));
		} else {
			where = cb.and(where, cb.isNull(location.get(Location_.locationType)));
		}

		// ProductCategory Filter
		if (categoryIds != null && !categoryIds.isEmpty()) {
			Join<Location, ProductCategory> productCategories = location.join(Location_.productCategories);
			where = cb.and(where, productCategories.get(ProductCategory_.id).in(categoryIds));
		} else {
			where = cb.and(where, cb.isEmpty(location.get(Location_.productCategories)));
		}

		// language filter
		if (StringUtils.isNotBlank(language)) {
			where = cb.and(where, cb.equal(location.get(Location_.language), language));
		}

		query.multiselect(distanceFormula.alias(ALIAS_DISTANCE), location.alias(ALIAS_LOCATION));
		query.where(where);
		// order by distance column
		query.orderBy(cb.asc(distanceFormula));
		query.distinct(true);

		return query;*/
		
	}

	

	/**
	 * <p>
	 * building of the distance calculation formula
	 * </p>
	 * 
	 * <code>
	 * acos(sin(radians(searchLatitude))*sin(radians(locationLatitude)) +
	 * cos(radians(searchLatitude))*cos(radians(locationLatitude))*cos(radians(
	 * locationLongitude)-radians(searchLongitude)))*radius
	 * cos(radians(locLon) - radians(longitude))
	 * </code>
	 * 
	 * @param locLat
	 *            latitude of the location in database
	 * @param locLon
	 *            longitude of the location in database
	 * @param searchLat
	 *            latitude of search location
	 * @param searchLon
	 *            longitude of searcgh location
	 * @param earthRadius
	 *            the radius of earth in miles or kilometres
	 * @return the expression for the distanceformula
	 */
	private double   buildDistanceFormula(Double locLat, Double locLon, Double searchLat,
			Double searchLon, Double earthRadius) {
		//final CriteriaBuilder cb = dao.getCriteriaBuilder();
		
		double part9=Math.toRadians(locLon)-Math.toRadians(searchLon);
		
		
		double part8=Math.cos(Math.toRadians(locLat));
		double part7=Math.cos(Math.toRadians(searchLat));
		double part6=(part7*(part8*part9));
	
		
		final double part5 = Math.sin(Math.toRadians(locLat));
		
		final double part4 = Math.sin(Math.toRadians(searchLat));
		
		final double part3=part4*part5;
		final double part2=part3+part6;
		
		final double part1=Math.acos(part2);
		
		return (part1*earthRadius);
		/*final xpression<Double> part9 = cb.function(
				"cos",
				Double.class,
				cb.diff(cb.function("radians", Double.class, locLon),
						cb.function("radians", Double.class, cb.literal(searchLon))));
		// cos(radians(locLat))
		final Expression<Double> part8 = cb.function("cos", Double.class, cb.function("radians", Double.class, locLat));
		// cos(radians(latitude))
		final Expression<Double> part7 = cb.function("cos", Double.class,
				cb.function("radians", Double.class, cb.literal(searchLat)));
		// part7 * part8 * part9
		final Expression<Double> part6 = cb.prod(part7, cb.prod(part8, part9));

		// sin(radians(locLat))
		final Expression<Double> part5 = cb.function("sin", Double.class, cb.function("radians", Double.class, locLat));
		// sin(radians(latitude))
		final Expression<Double> part4 = cb.function("sin", Double.class,
				cb.function("radians", Double.class, cb.literal(searchLat)));
		// part4 * part 5
		final Expression<Double> part3 = cb.prod(part4, part5);

		// part3 + part6
		final Expression<Double> part2 = cb.sum(part3, part6);

		// acos(part2)
		final Expression<Double> part1 = cb.function("acos", Double.class, part2);

		// part1 * earthRadius
		return cb.prod(part1, earthRadius);*/
		
	}

	private List<LocationDto> oldbuildLocationDtoListFromTupleList(List<Location> tupleList, Application application,
			String language) {
		/*final List<LocationDto> result = new ArrayList<LocationDto>(tupleList.size());

		for (Location tuple : tupleList) {
			final Location loc = tuple.get(ALIAS_LOCATION, Location.class);

			final Double distance = tuple.get(ALIAS_DISTANCE, Double.class);
			final List<IconDto> icons = new ArrayList<IconDto>();
			for (ProductCategory pc : loc.getProductCategories()) {
				ProductCategoryIcon pcIcon = application.getImageSet().getProductCategoryIcon(pc);
				if (pcIcon != null) {
					icons.add(new IconDto(pcIcon.getIcon().getId(), pc.getName()));
				}
			}

			String distanceFormatted = NumberFormatUtil.format("#.#", distance);
			String distanceWithUnit = globalMessageSource.getMessage(application.getUomDistance().getShortMessageKey(),
					new Object[] { distanceFormatted }, distanceFormatted, LocaleContextHolder.getLocale());
			String address = getAddressDisplayValue(loc);
			LocationDto dto = new LocationDto(loc, distanceWithUnit, icons, address, application.getType(), application.getImageSet());
			result.add(dto);
		}

		return result;*/
		return null;
	}

	private Criteria buildFindQuery(Country country, String language, String search) {

		
	Criteria criteria = dao.getCriteria(Location.class);
		Criterion where = Restrictions.eq("country", country);
		if (CustomStringUtil.isNotBlank(language)) {

			where = Restrictions.eq("language", language);

		}

		if (CustomStringUtil.isNotBlank(search)) {
			String lowerCaseSearch = search.toLowerCase();
			String[] searchTerms = lowerCaseSearch.split(" ");
			Criterion searchCriterion = getDisJunctionCriterion(lowerCaseSearch);
			for (String searchTerm : searchTerms) {
				searchCriterion = buildSearchPredicate(searchTerm,
						searchCriterion);
			}

			where = Restrictions.and(where, searchCriterion);

		}

		criteria.add(where);
		criteria.addOrder(Order.asc("sitecode"));
		return criteria;
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

	private Criterion getDisJunctionCriterion(String lowerCaseSearch) {
		Criterion searchCriterion=Restrictions.disjunction()
		.add(Restrictions.or( buildPredicate( Location_.tradingname, lowerCaseSearch), buildPredicate(Location_.sitecode, lowerCaseSearch)))
		.add(Restrictions.or(buildPredicate( Location_.sitename, lowerCaseSearch),buildPredicate( Location_.street, lowerCaseSearch)))
		.add(Restrictions.or(buildPredicate( Location_.town, lowerCaseSearch),buildPredicate(Location_.postcode, lowerCaseSearch) ));
		return searchCriterion;
	}
	
	

	@Override
	public List<Location> findAll(Country country) {
		return findLocations(country, null, null);
	}

	@Override
	public List<Location> findAll(Country country, String language) {
		return findLocations(country, language, null);
	}

	@Override
	public long countLocations(Country country, String language) {
		

		Criteria ct=dao.getCriteria(Location.class);
		ct.add(Restrictions.eq("country", country));
		ct.add(Restrictions.eq("language", language));
		ct.setProjection(Projections.rowCount()).uniqueResult();
		return (Long) dao.findList(ct).get(0);
		
		
		
	/*	CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);

		Root<Location> location = query.from(Location.class);
		query.select(cb.count(location.get(Location_.id)));

		Predicate where = cb.equal(location.get(Location_.country), country);

		if (StringUtils.isNotBlank(language)) {
			where = cb.and(where, cb.equal(location.get(Location_.language), language));
		}

		query.where(where);

		return dao.findSingle(query);*/
		
		
	}

	@Override
	public int deleteAllLocations(Country country, String language) {
		
		Criteria ct = buildFindQuery(country, language, null);
		List<Location> locationsToDelete = dao.findList(ct);

		final int result = locationsToDelete.size();
		for (Location location : locationsToDelete) {
			deleteLocation(location);
		}
		return result;
		/*final CriteriaQuery<Location> query = buildFindQuery(country, language, null);
		final List<Location> locationsToDelete = dao.findList(query);
		final int result = locationsToDelete.size();

		for (Location location : locationsToDelete) {
			deleteLocation(location);
		}

		return result;*/
	
	}

	@Override
	public String getAddressDisplayValue(Location location) {
		Map<String, String> model = new HashMap<String, String>();
		model.put(AddressTemplatePlaceholder.COUNTY.getKey(),
				CustomStringUtil.isNotEmpty(location.getCounty()) ? location.getCounty() : EMPTY_STRING);
		model.put(AddressTemplatePlaceholder.DISTRICT.getKey(),
				CustomStringUtil.isNotEmpty(location.getDistrict()) ? location.getDistrict() : EMPTY_STRING);
		model.put(AddressTemplatePlaceholder.POSTCODE.getKey(),
				CustomStringUtil.isNotEmpty(location.getPostcode()) ? location.getPostcode() : EMPTY_STRING);
		model.put(AddressTemplatePlaceholder.STATE.getKey(),
				CustomStringUtil.isNotEmpty(location.getState()) ? location.getState() : EMPTY_STRING);
		model.put(AddressTemplatePlaceholder.STREET.getKey(),
				CustomStringUtil.isNotEmpty(location.getStreet()) ? location.getStreet() : EMPTY_STRING);
		model.put(AddressTemplatePlaceholder.TOWN.getKey(),
				CustomStringUtil.isNotEmpty(location.getTown()) ? location.getTown() : EMPTY_STRING);

		try {
			Template t = new Template("AddressTemplate", new StringReader(location.getCountry().getAddressTemplate()),
					new Configuration());
			t.setEncoding(TEMPLATE_ENCODING);
			StringWriter output = new StringWriter();
			t.process(model, output);

			return output.toString();
		} catch (IOException e) {
			throw new RuntimeException("failed to process address template", e);
		} catch (TemplateException e) {
			throw new RuntimeException("failed to process address template", e);
		}
	}

	/**
	 * Builds the Criteria-Api Where Preidcates for the search of locations.
	 * 
	 * @param cb
	 *            CriteriaBuilder
	 * @param location
	 *            the location root
	 * @param searchTerm
	 *            the searchTerm
	 * @param searchPred
	 *            the old where-Predicate
	 * @return the new where predicate
	 */
	private Criterion buildSearchPredicate(String searchTerm,
			Criterion searchPred) {
		
		
		searchPred = Restrictions.or(searchPred,Restrictions
				.or(buildPredicate(Location_.tradingname, searchTerm),
						Restrictions.or(buildPredicate(Location_.sitecode,
								searchTerm), Restrictions.or(buildPredicate(
								Location_.street, searchTerm), Restrictions.or(
								buildPredicate(Location_.sitename, searchTerm),
								Restrictions.or(buildPredicate(Location_.town,
										searchTerm), buildPredicate(
										Location_.postcode, searchTerm)))))));
		
		
		/*searchPred = Restrictions.or(searchPred, buildPredicate( Location_.tradingname, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.sitename, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.street, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.town, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate(Location_.postcode, searchTerm));
		*/
		

		return searchPred;
	}

	
	private Criterion buildSearchCriterionfasd(String searchTerm,
			Criterion searchPred) {
		/*searchPred = cb.or(searchPred, buildPredicate(cb, location, "siteCode", searchTerm));
		searchPred = cb.or(searchPred, buildPredicate(cb, location, Location_.tradingname, searchTerm));
		searchPred = cb.or(searchPred, buildPredicate(cb, location, Location_.sitename, searchTerm));
		searchPred = cb.or(searchPred, buildPredicate(cb, location, Location_.street, searchTerm));
		searchPred = cb.or(searchPred, buildPredicate(cb, location, Location_.town, searchTerm));
		searchPred = cb.or(searchPred, buildPredicate(cb, location, Location_.postcode, searchTerm));*/
		
		searchPred = Restrictions.or(searchPred, buildPredicate(Location_.sitecode, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.tradingname, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.sitename, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.street, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate( Location_.town, searchTerm));
		searchPred = Restrictions.or(searchPred, buildPredicate(Location_.postcode, searchTerm));
		
		
		return searchPred;
	}
	
	/**
	 * builds a explicit predicate for a search term.
	 * 
	 * @param cb
	 *            CriteriaBuilder
	 * @param location
	 *            the location root
	 * @param singu
	 *            the attribute which should be used in the predicate
	 * @param searchTerm
	 *            the searchTerm
	 * @return the predicate
	 */
	private Criterion buildPredicate(
			SingularAttribute<Location, String> singu, String searchTerm) {
		return Restrictions.ilike(singu.getColoumnName(),  searchTerm,MatchMode.ANYWHERE);
	}
}
