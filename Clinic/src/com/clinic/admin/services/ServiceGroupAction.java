/**
 * 
 */
package com.clinic.admin.services;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.GenericDao;
import com.app.LanguageUtil;
import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractListAction;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.util.CustomStringUtil;
import com.clinic.admin.model.impl.ServiceGroup;

/**
 * @author jomn
 * 
 */

public class ServiceGroupAction extends AbstractListAction<ServiceGroup> {

	@Resource
	private transient GenericDao dao;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2789479698845316044L;

	private ServiceGroup serviceGroup;

	private List<ServiceGroup> serviceGroupList;

	/**
	 * Der Suchstring
	 */
	private String q;

	public ServiceGroupAction() {
		serviceGroup = new ServiceGroup();
	}

	public String list() {
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		super.prepare();

		/*
		 * if (CustomStringUtil.isBlank(q)) { locations =
		 * facade.findAllLocations(country); } else { locations =
		 * facade.findLocations(country, null, q); }
		 */

		serviceGroupList = findServiceGroup(q);
	}

	@Override
	protected int getPageSizeInternal() {
		return 2;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

/*	public List<ServiceGroup> findServiceGroup1(String search) {
		String hql = "FROM ServiceGroup where id = :serviceGroup_ID";
		SessionFactory fac = new AnnotationConfiguration().configure().addAnnotatedClass(ServiceGroup.class).buildSessionFactory();
		Session ses = fac.getCurrentSession();
		Query query =  ses.createQuery(hql);
		query.setParameter("serviceGroup_ID", search);
		return query.list();
		//return dao.findList(query);
	}*/
	
	public List<ServiceGroup> findServiceGroup(String search) {
		final Criteria query = buildFindQuery(search);
		return dao.findList(query);
	}

	private Criteria buildFindQuery(String search) {

		Criteria criteria = dao.getCriteria(ServiceGroup.class);
		Criterion where = Restrictions.eq("code", search);

		criteria.add(where);
		criteria.addOrder(Order.asc("createdTime"));
		return criteria;
	}

	@Override
	public List<ServiceGroup> getList() {
		return serviceGroupList;
	}
}
