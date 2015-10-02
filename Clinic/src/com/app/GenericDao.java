package com.app;

import java.util.List;

import org.hibernate.Criteria;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.LocationType;
import com.app.model.impl.SingularAttribute;
import com.clinic.admin.model.impl.ServiceGroup;


/**
 * interface for a database access handler, which provides generic handling for
 * entities.
 * 
 * @author jomn
 * 
 */
public interface GenericDao {

	/**
	 * 
	 * @param o
	 *            object to persist in database, should not have an setted id
	 */
	void persist(Object o);

	/**
	 * 
	 * @param o
	 *            object to update in the database, should have an setted id
	 */
	void update(Object o);

	/**
	 * 
	 * @param o
	 *            the object to be removed out of database
	 */
	void remove(Object o);

	/**
	 * find a entity by its id
	 * 
	 * @param <T>
	 *            the type of the requested entity
	 * @param clazz
	 *            the class object of the wished entity
	 * @param id
	 *            the identitfier of the entity
	 * @return if available the entity with the provided id
	 */
	<T> T find(Class<T> clazz, Long id);

	/**
	 * get all stored entities of the provided type
	 * 
	 * @param <T>
	 *            the type of the requested entity
	 * @param clazz
	 *            the class object of the wished entity
	 * @return all entities in a List
	 */
	<T> List<T> findAll(Class<T> clazz);

	//Application findApplicationByKey(String applicationKey);

	Object findByUniqueAttributeN(SingularAttribute uniqueAttribute, String value);

	List<Country> getAllowedCountriesForUser(String username);

	List<String> getAvailableLanguagesForCountry(Country country);
	
	Criteria getCriteria(Class clazz);

	Criteria getCriteria(Class clazz,String alias);
	/**
	 * find a entity by a unique attribute
	 * 
	 * @param <X>
	 *            the type of the entity
	 * @param <T>
	 *            the type of the unique attribute
	 * @param uniqueAttribute
	 *            the singular attribute defining the attribute to be checked
	 * @param value
	 *            the value of the unique attribute
	 * @return the entity with the unique aatribute
	 * @throws NoResultException
	 *             exception if there is no entity with this unique attribute
	 * @throws NonUniqueResultException
	 *             exception if there are more than on result
	 */
	/*<X, T> X findByUniqueAttribute(SingularAttribute<X, T> uniqueAttribute, Object value) throws NoResultException,
			NonUniqueResultException;*/

	/**
	 * find a list of entities defined by a criteria query
	 * 
	 * @param <T>
	 *            the type of the requested entity
	 * @param query
	 *            the criteriaQuery object, defining filters
	 * @return the list of entities fulfilling the criteria
	 */
	/*<T> List<T> findList(CriteriaQuery<T> query);*/
	
	<T> List<T> findList(Object o);

	/**
	 * find a list of entities defined by a criteria query and limit the results
	 * to {@code maxResult} starting from {@code offset}.
	 * 
	 * @param <T>
	 *            the type of the requested entity
	 * @param query
	 *            the criteriaQuery object, defining filters
	 * @param startPosition
	 *            start index of result set
	 * @param maxResults
	 *            number of results
	 * @return the list of entities fulfilling the criteria
	 */
	/*<T> List<T> findList(CriteriaQuery<T> query, int startPosition, int maxResults);*/
	<T> List<T> findList(Object o,int result,int mx);

	<T> T findSingle(Criteria ct);

	/**
	 * find a single entity with a criteriaQuery
	 * 
	 * @param <T>
	 *            the type of the requested entity
	 * @param query
	 *            the criteriaQuery object, defining filters
	 * @return
	 */
	/*<T> T findSingle(CriteriaQuery<T> query);*/

	/**
	 * 
	 * @param i 
	 * @param longitude 
	 * @param latitude 
	 * @return the criteriBuilder, useful for creating criteriaQueries
	 */
	/*CriteriaBuilder getCriteriaBuilder();*/
	
	public List<Object[]> findNearestLocations(Double latitude,
			Double longitude, int maxRecords, List<Long> typeIds,
			List<Long> categoryIds, short isOnline, Application apo, String lang);
	
	List test(Double latitude, Double longitude, int i,Double earthRadius);
	
	public List<ServiceGroup> getServiceGroups();

}