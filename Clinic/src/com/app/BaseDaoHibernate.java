package com.app;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class BaseDaoHibernate<T, PK extends Serializable>

{
	
	@Autowired
	public BaseDaoHibernate(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 HibernateTemplate hibernateTemplate;

	private Class<T> persistentClass;
		

    public BaseDaoHibernate(Class<T> persistentClass) {
    	
        this.setPersistentClass(persistentClass);
     //   this.hibernateTemplate = new HibernateTemplate(getSessionFactory());
    }

    @SuppressWarnings("unchecked")
	public List<T> getAll() {
    	
        return hibernateTemplate.loadAll(this.getPersistentClass());
    }

    @SuppressWarnings("unchecked")
	public T get(PK id) {
        T entity = (T) hibernateTemplate.get(this.getPersistentClass(), id);

        if (entity == null) {
            
            throw new ObjectRetrievalFailureException(this.getPersistentClass(), id);
        }

        return entity;
    }
    
    @SuppressWarnings("unchecked")
	public boolean exists(PK id) {
        T entity = (T) hibernateTemplate.get(this.getPersistentClass(), id);
        if (entity == null) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
	public T merge(T object) {
        return (T) hibernateTemplate.merge(object);
    }
    
    public void save(T object){
    	hibernateTemplate.saveOrUpdate(object);
    }
    
    public void delete(T object){
    	hibernateTemplate.delete(object);
    }
    
   /* public void saveAll(List<T> listOfT){
    	hibernateTemplate.saveOrUpdateAll(listOfT);
    }*/

    public void remove(PK id) {
        hibernateTemplate.delete(this.get(id));
    }
    
    public HibernateTemplate getHibernateTemplate(){
    	return this.hibernateTemplate;
    }
    
    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
    	this.hibernateTemplate = hibernateTemplate;
    }
    
    public void saveAll(List<T> listOfT){
    	hibernateTemplate.saveOrUpdateAll(listOfT);
    }

	public void setPersistentClass(Class<T> clazz) {
		this.persistentClass = clazz;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
    

	
	
	
	
	
	
	

}
