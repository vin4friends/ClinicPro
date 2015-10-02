package com.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.impl.Application;
import com.app.model.impl.Country;
import com.app.model.impl.Country_;
import com.app.model.impl.LfUser;
import com.app.model.impl.LfUser_;
import com.app.model.impl.Location;
import com.app.model.impl.Location_;
import com.app.model.impl.SingularAttribute;
import com.app.util.CustomStringUtil;
import com.clinic.admin.model.impl.ServiceGroup;
import com.clinic.admin.model.impl.ServiceMaster;

//@Repository(value="dao")
public class GenericDaoImpl implements GenericDao {

	private HibernateTemplate hibernateTemplate;

	@Override
	public void persist(Object o) {
		getHibernateTemplate().save(o);
	}

	@Override
	public void update(Object o) {
		getHibernateTemplate().merge(o);
		
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		/*
		 * final CriteriaBuilder cb = em.getCriteriaBuilder(); final
		 * CriteriaQuery<T> query = cb.createQuery(clazz); query.from(clazz);
		 * 
		 * return findList(query);
		 */

		return (List<T>) getHibernateTemplate().loadAll(clazz);

	}

	public <T> T find(Class<T> clazz, Long id) {

		return hibernateTemplate.get(clazz, id);
		// setPersistentClass((Class<BaseObject>) clazz);
		// return (T) get(id);

		// List l=hibernateTemplate.find("from Role r where  r.id=10004");
		// System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+l.size());

		/*
		 * Session
		 * ses=hibernateTemplate.getSessionFactory().getCurrentSession();
		 * ses.beginTransaction(); Criteria
		 * clzCri=ses.createCriteria(Role.class);
		 * clzCri.add(Restrictions.idEq(id));
		 * 
		 * 
		 * List l=clzCri.list(); System.out.println(l.size());
		 */

		/*
		 * Role l= hibernateTemplate.get(Role.class, 10004L);
		 * System.out.println(l.getName()); return null;
		 */
		// hibernateTemplate.
		// return em.find(clazz, id);
	}

	// public <X, T> X findByUniqueAttribute(SingularAttribute<X, T>
	// uniqueAttribute, Object value) {

	/*
	 * final CriteriaBuilder cb = em.getCriteriaBuilder(); Definierendes Entity
	 * final Class<X> clazz = uniqueAttribute.getDeclaringType().getJavaType();
	 * final CriteriaQuery<X> query = cb.createQuery(clazz); final Root<X> root
	 * = query.from(clazz); query.where(cb.equal(root.get(uniqueAttribute),
	 * value)); return null;
	 */
	// return findSingle(query);
	// }
	/*
	 * @Override public <T> List<T> findList(CriteriaQuery<T> query) { //return
	 * em.createQuery(query).getResultList(); return null; }
	 * 
	 * @Override public <T> List<T> findList(CriteriaQuery<T> query, int
	 * startPosition, int maxResults) { return null; //return
	 * em.createQuery(query
	 * ).setFirstResult(startPosition).setMaxResults(maxResults
	 * ).getResultList(); }
	 * 
	 * @Override public <T> T findSingle(CriteriaQuery<T> query) { try { return
	 * em.createQuery(query).getSingleResult(); } catch (NoResultException e) {
	 * return null; } return null; }
	 * 
	 * @Override public CriteriaBuilder getCriteriaBuilder() { //return
	 * em.getCriteriaBuilder(); return null; }
	 */

	public void remove(Object o) {
		hibernateTemplate.delete(o);
	}

	@Override
	public Object findByUniqueAttributeN(SingularAttribute single,
			String paramValue) {
		String alias = "aliaswe";
		StringBuffer builder = new StringBuffer();
		builder.append("from ").append(single.getClassName()).append(" ")
				.append(alias).append(" where ").append(alias).append(".")
				.append(single.getColoumnName()).append("=?");

		List listValues = getHibernateTemplate().find(builder.toString(),paramValue);

		if (null != listValues && listValues.size() != 0) {
			return listValues.get(0);

		}
		return null;

	}

	/*
	 * public Object findByUniObject(SingularAttribute single, String
	 * paramValue) {
	 * 
	 * return
	 * getHibernateTemplate().find("from EnvironmentProperty en where key= ?"
	 * ,"lf.envprop.google.maps.client_id.intranet");
	 * 
	 * }
	 */

	@Override
	public List<Country> getAllowedCountriesForUser(String username) {
		// TODO Auto-generated method stub

		/*
		 * Session
		 * ses=hibernateTemplate.getSessionFactory().getCurrentSession();
		 * Criteria userCrit=ses.createCriteria(LfUser.class);
		 * countryCriteria=userCrit.createAlias("country", arg1);
		 */
		

		LfUser list = (LfUser) findByUniqueAttributeN(LfUser_.login,username);
		
		
		return new ArrayList(list.getCountries());
	}

	@Override
	public List<String> getAvailableLanguagesForCountry(Country country) {
		// TODO Auto-generated method stub
		List l = new ArrayList();
		l.add("en_US");
		return l;
	}

	@Override
	public <T> List<T> findList(Object o, int result, int mx) {
		// TODO Auto-generated method stub

		hibernateTemplate.setMaxResults(mx);
		if (o instanceof Criteria) {
			((Criteria) o).setFirstResult(result);
			((Criteria) o).setMaxResults(mx);
			return ((Criteria) o).list();
		}
		return null;
	}

	@Override
	public <T> List<T> findList(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof Criteria) {
			return ((Criteria) o).list();
		}

		return null;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	public Criteria getCriteria(Class clazz) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createCriteria(clazz);

	}

	@Override
	public <T> T findSingle(Criteria ct) {
		// TODO Auto-generated method stub
		List l = ct.list();
		if (l != null && l.size() == 1) {
			return (T) l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Criteria getCriteria(Class clazz, String alias) {
		return getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createCriteria(clazz, alias);

	}

	/*
	 * public QuickView fetchQuickViewDetails(Long quickViewId) { QuickView
	 * quickView = null; List<QuickView> quickViewList = hibernateTemplate.find(
	 * "from QuickView quickView where quickView.quickViewId=?", quickViewId);
	 * 
	 * if (null != quickViewList && quickViewList.size() != 0) { quickView =
	 * quickViewList.get(0);
	 * 
	 * } return quickView;
	 * 
	 * }
	 * 
	 * public QuickView fetchQuickViewByName(String quickViewName) { QuickView
	 * quickView = null; List<QuickView> quickViewList = hibernateTemplate.find(
	 * "from QuickView quickView where lower(quickView.quickViewName)=lower(?)",
	 * quickViewName);
	 * 
	 * if (null != quickViewList && quickViewList.size() != 0) { quickView =
	 * quickViewList.get(0);
	 * 
	 * } return quickView;
	 * 
	 * }
	 */

	public List<Object[]> findNearestLocations(Double latitude,
			Double longitude, int maxRecords, List<Long> typeIds,
			List<Long> categoryIds, short isOnline, Application apo, String lang) {

		Double earthRadius = apo.getUomDistance().getEarthRadius();
		String query = "acos(sin(radians("
				+ latitude
				+ "))*sin(radians(loc.latitude))+cos(radians("
				+ latitude
				+ "))*cos(radians(loc.latitude))*cos(radians(loc.longitude)-radians("
				+ longitude + ")))*" + earthRadius;

		hibernateTemplate.setMaxResults(maxRecords);

		return hibernateTemplate
				.findByNamedParam(
						"select distinct "
								+ query
								+ " as distance,loc  from Location loc inner join loc.productCategories pr  where loc.locationType.id in (:ids) and pr.id in(:catIds) and loc.isOnlinePersist=:ISON and loc.country in(:COUCODES)and loc.language=:LANG order by "
								+ query + " asc ", new String[] { "ids",
								"catIds", "ISON", "COUCODES", "LANG" },
						new Object[] { typeIds, categoryIds, isOnline,
								apo.getCountries(), lang });

	}
	private Criterion buildPredicate(
			SingularAttribute<Location, String> singu, String searchTerm) {
		return Restrictions.ilike(singu.getColoumnName(),  searchTerm ,MatchMode.ANYWHERE);
	}
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
		return searchPred;
	}
	
	@Transactional
	public List<Object[]> test(Double latitude, Double longitude, int i,
			Double earthRadius) {
		
		Country country=(Country) findByUniqueAttributeN(Country_.countrycode,"IE");
		
		Criteria ct=getCriteria(Location.class);
		ct.add(Restrictions.eq("country", country));
		ct.add(Restrictions.eq("language", "en-IE"));
		ct.setProjection(Projections.rowCount()).uniqueResult();
		//System.out.println(ct.list());
		
		
		return null;
		
		
		
		/*String search="Worsley";
		Country country=(Country) findByUniqueAttributeN(Country_.countrycode,"GB");
		
		Criteria criteria = getCriteria(Location.class);
		Criterion where = Restrictions.eq("country", country);
		

		if (CustomStringUtil.isNotBlank(search)) {
			String lowerCaseSearch = search.toLowerCase();
			String[] searchTerms = lowerCaseSearch.split(" ");
			Criterion searchCriterion = redicate(lowerCaseSearch,
					Restrictions.disjunction());
			Criterion searchCriterion=Restrictions.disjunction()
			.add(Restrictions.or( buildPredicate( Location_.tradingname, lowerCaseSearch), buildPredicate(Location_.sitecode, lowerCaseSearch)))
			.add(Restrictions.or(buildPredicate( Location_.sitename, lowerCaseSearch),buildPredicate( Location_.street, lowerCaseSearch)))
			.add(Restrictions.or(buildPredicate( Location_.town, lowerCaseSearch),buildPredicate(Location_.postcode, lowerCaseSearch) ));
		
		
		
			
			for (String searchTerm : searchTerms) {
				searchCriterion = buildSearchPredicate(searchTerm,
						searchCriterion);
			}

			where = Restrictions.and(where, searchCriterion);

		}

		criteria.add(where);
		criteria.addOrder(Order.asc("sitecode"));
		List l=criteria.list();
		System.out.println("----------------------------"+l.size());
		for(int k=0;k<l.size();k++)
		{
		System.out.println("************************************"+((Location)(l.get(k))).getId());
		}
		return null;*/
		/*
		 * LocationTemp l=new LocationTemp();
		 * 
		 * hibernateTemplate.setMaxResults(i); return
		 * hibernateTemplate.find("select distinct acos(sin(radians("
		 * +latitude+"))*sin(radians(location.latitude))+cos(radians("+latitude+
		 * "))*cos(radians(location.latitude))*cos(radians(location.longitude)-radians("
		 * +longitude+")))*"+earthRadius+
		 * " as distance,location.id  from Location location order by (acos(sin(radians("
		 * +latitude+"))*sin(radians(location.latitude))+cos(radians("+latitude+
		 * "))*cos(radians(location.latitude))*cos(radians(location.longitude)-radians("
		 * +longitude+")))*"+earthRadius+")");
		 */

		/*Application apo = (Application) findByUniqueAttributeN(
				Application_.applicationKey, "rIwcKCp3");
		List<Long> typeIds = new ArrayList<Long>();
		typeIds.add(1l);
		typeIds.add(2l);
		List<Long> categoryIds = new ArrayList<Long>();
		categoryIds.add(1l);
		categoryIds.add(2l);
		categoryIds.add(3l);
		categoryIds.add(4l);
		categoryIds.add(5l);
		categoryIds.add(6l);
		categoryIds.add(7l);
		latitude = 51.4528837;
		longitude = -0.9739059999999427;
		earthRadius = 3959.0;
		String query = "distinct acos(sin(radians("
				+ latitude
				+ "))*sin(radians(loc.latitude))+cos(radians("
				+ latitude
				+ "))*cos(radians(loc.latitude))*cos(radians(loc.longitude)-radians("
				+ longitude + ")))*" + earthRadius + "";
		String orBy = "acos(sin(radians("
				+ latitude
				+ "))*sin(radians(loc.latitude))+cos(radians("
				+ latitude
				+ "))*cos(radians(loc.latitude))*cos(radians(loc.longitude)-radians("
				+ longitude + ")))*" + earthRadius;
		String lang = "en-GB";*/
		// Criteria criteria=getCriteria(Location.class,"location");

		/*
		 * criteria.createCriteria("location.locationType",
		 * JoinFragment.LEFT_OUTER_JOIN);
		 * criteria.add(Restrictions.in("locationType.id",typeIds));
		 * 
		 * 
		 * criteria.createAlias("location.productCategories","productCategories",
		 * JoinFragment.LEFT_OUTER_JOIN);
		 * criteria.add(Restrictions.in("productCategories.id", categoryIds));
		 */
	/*	short isOnline = 1;
		hibernateTemplate.setMaxResults(30);

		return hibernateTemplate
				.findByNamedParam(
						"select "
								+ query
								+ " as distance,loc  from Location loc inner join loc.productCategories pr  where loc.locationType.id in (:ids) and pr.id in(:catIds) and loc.isOnline=:ISON and loc.country in(:COUCODES)and loc.language=:LANG order by "
								+ orBy + " asc ", new String[] { "ids",
								"catIds", "ISON", "COUCODES", "LANG" },
						new Object[] { typeIds, categoryIds, isOnline,
								apo.getCountries(), lang });
*/
		/*
		 * String language="en-GB";
		 * 
		 * 
		 * 
		 * //where=Restrictions.and(where, where=Restrictions.in("location.id",
		 * set));
		 * 
		 * criteria.add(Restrictions.eq("location.isOnline", (short)1))
		 * .add(Restrictions.in("location.country",apo.getCountries()));
		 * if(typeIds!=null&& !typeIds.isEmpty()) {
		 * 
		 * criteria.createCriteria("location.locationType",
		 * JoinFragment.INNER_JOIN);
		 * criteria.add(Restrictions.in("locationType.id",typeIds));
		 * 
		 * } else {
		 * 
		 * criteria.add(Restrictions.isNull("location.locationType"));
		 * 
		 * }
		 * 
		 * if(categoryIds!=null&&!categoryIds.isEmpty()) {
		 * criteria.createAlias("location.productCategories"
		 * ,"productCategories", JoinFragment.INNER_JOIN);
		 * criteria.add(Restrictions.in("productCategories.id", categoryIds)); }
		 * else {
		 * criteria.add(Restrictions.isEmpty("location.productCategories")); }
		 * if (StringUtils.isNotBlank(language)) {
		 * criteria.add(Restrictions.eq("location.language", language)); }
		 * 
		 * 
		 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 * criteria.setFirstResult(0); criteria.setMaxResults(30);
		 * 
		 * return criteria.list();
		 */

		// JdbcTemplate jt;
		// LocationTemp loc=new LocationTemp();
		// Criteria criteria=getCriteria(Location.class,"location"); //Criteria
		// c=criteria.createCriteria("Location");
		/*
		 * Application application=
		 * (Application)findByUniqueAttributeN(Application_.applicationKey,
		 * "rIwcKCp3");
		 * 
		 * Criterion where=Restrictions.eq("isOnline", (short)1);
		 * where=Restrictions.and(where,
		 * Restrictions.in("country",application.getCountries())); if(false) {
		 * 
		 * criteria.createAlias("location.locationType", "locationType");
		 * //where=Restrictions.and(where,
		 * Restrictions.in("locationType.locationTypeId", typeIds)); } else {
		 * 
		 * where=Restrictions.and(where,
		 * Restrictions.isNull("location.locationType"));
		 * 
		 * }
		 * 
		 * if(false) { criteria.createAlias("location.productCategories",
		 * "productCategories"); //where=Restrictions.and(where,
		 * Restrictions.in("productCategories.productCategoryId", categoryIds));
		 * } else { where=Restrictions.and(where,
		 * Restrictions.isEmpty("location.productCategories")); } if (true) {
		 * where = Restrictions.and(where, Restrictions.eq("location.language",
		 * "en-GB")); }
		 * 
		 * criteria.add(where);
		 */
		// criteria.setFirstResult(0);
		// criteria.setMaxResults(20);

		/*
		 * criteria.setProjection(Projections.projectionList()
		 * .add(Projections.alias(
		 * Projections.sqlProjection("(select distinct acos(sin(radians("
		 * +51.4528837+"))" +
		 * "*sin(radians(LATITUDE))+cos(radians("+51.4528837+"))" +
		 * "*cos(radians(latitude))*cos(radians(longitude)-radians("
		 * +-0.9739059999999427+")))*"+3959.0+" " +
		 * "from admin096.location) as distance ",new String[]{"distance"}, new
		 * Type[]{new DoubleType()}), "aliass")))
		 * .addOrder(Order.asc("aliass")).
		 * setResultTransformer(Transformers.aliasToBean
		 * (Location.class));//.setResultTransformer
		 * (Criteria.DISTINCT_ROOT_ENTITY);;
		 */

		// return c.list();
		// return
		// hibernateTemplate.findByValueBean("select distinct acos(sin(radians(51.4528837))*sin(radians(latitude))+cos(radians(51.4528837))*cos(radians(longitude))*cos(radians(longitude)-radians(-0.9739059999999427)))*3959.0 as distance ,id as locId from Location ",loc);
		/*
		 * SessionFactoryImplementor sfi =
		 * (SessionFactoryImplementor)hibernateTemplate.getSessionFactory();
		 * String name = sfi.getSettings().getDefaultSchemaName();
		 * hibernateTemplate.setMaxResults(20); Query
		 * query=hibernateTemplate.getSessionFactory
		 * ().getCurrentSession().createSQLQuery(
		 * "select distinct acos(sin(radians(51.4528837))*sin(radians(location.LATITUDE))+cos(radians(51.4528837))*cos(radians(location.LATITUDE))*cos(radians(location.LONGITUDE)-radians(-0.9739059999999427)))*3959.0 as \"distance\" ,location.id as \"locId\" from "
		 * +name+".location location order by \"distance\"");
		 * 
		 * 
		 * query.setResultTransformer(Transformers.aliasToBean(LocationTemp.class
		 * ));
		 */

		// return criteria.list();
		// List l1=
		// hibernateTemplate.findByValueBean("select distinct acos(sin(radians("+51.4528837+"))*sin(radians(location.LATITUDE))+cos(radians("+51.4528837+"))*cos(radians(location.LATITUDE))*cos(radians(location0_.LONGITUDE)-radians("+-0.9739059999999427+")))*"+3959.0+" as distance,location.id  from location order by distance",
		// l);
		/*
		 * Criteria
		 * criteria=hibernateTemplate.getSessionFactory().getCurrentSession
		 * ().createCriteria(Location.class,"location");
		 * 
		 * returncriteria.setProjection(Projections.sqlProjection(
		 * "select distinct acos(sin(radians("
		 * +51.4528837+"))*sin(radians(location.LATITUDE))+cos(radians("
		 * +51.4528837+
		 * "))*cos(radians(location.LATITUDE))*cos(radians(location0_.LONGITUDE)-radians("
		 * +-0.9739059999999427+")))*"+3959.0+" as distance",new
		 * String[]{"distance"}, new Type[]{new
		 * DoubleType()})).addOrder(Order.asc
		 * ("")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		 * 
		 * 
		 * .setResultTransformer(Transformers.aliasToBean(LocationDto.class)).list
		 * ();
		 */

	}

	@Override
	public List<ServiceGroup> getServiceGroups() {
		List<ServiceGroup> list = getAll("ServiceGroup");
		return list;
	}

	public List getAll(String tableName) {
		StringBuffer builder = new StringBuffer();
		builder.append("from ").append(tableName);

		List listValues = getHibernateTemplate().find(builder.toString());
		return listValues;
	}
	

}
