/**
 * 
 */
package com.app.services;

import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.GenericDao;
import com.app.model.impl.Application;
import com.app.model.impl.Application_;
import com.app.model.impl.Country;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.model.impl.EnvironmentProperty.StringProperty;
import com.app.model.impl.enums.ApplicationType;
import com.app.util.CustomStringUtil;

/**
 * Implementation of the ApplicationService-Interface.
 * 
 * @author jomu
 */
@Service("applicationService")
public class DefaultApplicationService implements ApplicationService {

	private static final int DEFAULT_APP_KEY_LENGTH = 8;

	/**
	 * Secure random generator used for application key generation.
	 */
	private final SecureRandom random;

	/**
	 * Creates a secure random generator.
	 */
	public DefaultApplicationService() {
		this.random = new SecureRandom();
	}

	/**
	 * Set of characters used for application key generation.
	 */
	private static final char[] CHARACTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '0', };

	@Resource
	private GenericDao dao;

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	@Override
	public Application findApplication(Long id) {
		return dao.find(Application.class, id);
	}

	@Override
	public Application getApplication(String applicationKey) {
		//return dao.findByUniqueAttribute(Application_.applicationKey, applicationKey);
		
		return (Application) dao.findByUniqueAttributeN(Application_.applicationKey,applicationKey);
	}

	@Override
	public void createApplication(Application application) {
		if(application.getId()!=null){dao.update(application);}else{dao.persist(application);}
		//dao.persist(application);
	}

	@Override
	public List<Application> findAll() {
		return dao.findAll(Application.class);
	}

	@Override
	public void deleteApplication(Application application) {
		dao.remove(application);
	}

	@Override
	public int getMaxNumberOfLocationsPerSearchResult(Application application) {
		if (application.getMaxLocationResult() != null) {
			return application.getMaxLocationResult().intValue();
		}

		if (application.getCountries().size() == 1) {
			// if only one country is specified for an application use this
			// maxResult attribute
			Long maxResult = application.getCountries().iterator().next().getMaxLocationResult();
			if (maxResult != null) {
				maxResult.intValue();
			}
		}
		return environmentPropertiesService.getIntegerProperty(IntegerProperty.MAX_LOCATIONS_PER_SEARCH_RESULT);
	}

	@Override
	public String getNewApplicationKey() {
		String keyCandidate = generateApplicationKey(DEFAULT_APP_KEY_LENGTH);
		if (isApplicationKeyUnique(keyCandidate)) {
			return keyCandidate;
		} else {
			// recursion try to generate a new key candidate which might be
			// unique.
			return getNewApplicationKey();
		}
	}

	@Override
	public boolean isApplicationKeyUnique(String applicationKey) {
		boolean flag=false;
		if (CustomStringUtil.isBlank(applicationKey)) {
			flag=false;
		}
		
		Object obj= dao.findByUniqueAttributeN(Application_.applicationKey,"applicationKey");
		if(null!=obj && obj instanceof Application )
		{
			flag=false;
			
		}
		else if(obj==null)
		{
			flag= true;
		}
		
		//its done 
		
		
		/*final CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Application> application = query.from(Application.class);
		query.where(cb.equal(application.get(Application_.applicationKey), applicationKey));
		query.select(cb.count(application.get(Application_.id)));

		Long count = dao.findSingle(query);
		if (count != null && count.equals(0L)) {
			return true;
		} else {
			return false;*/
		return flag;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.services.ApplicationService#getIframeExampleUrl
	 * (com.app.model.impl.Application)
	 */
	@Override
	public String getIframeExampleUrl(Application application) {

		String pattern = "";
		if (ApplicationType.INTRANET.equals(application.getType())) {
			pattern = getIframeExampleUrlIntranet();
		} else if (ApplicationType.INTERNET.equals(application.getType())) {
			pattern = getIframeExampleUrlInternet();
		}
		return replaceExampleUrlParams(pattern, application);
	}

	@Override
	public String getIframeExampleUrlInternet() {
		String pattern = environmentPropertiesService
				.getStringProperty(StringProperty.APPLICATION_EXAMPLE_URL_INTERNET);
		return pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.services.ApplicationService#
	 * getIframeExampleUrlIntranet
	 * (com.app.model.impl.Application)
	 */
	@Override
	public String getIframeExampleUrlIntranet() {
		String pattern = environmentPropertiesService
				.getStringProperty(StringProperty.APPLICATION_EXAMPLE_URL_INTRANET);
		return pattern;
	}

	private String replaceExampleUrlParams(String exampleUrl, Application application) {
		String langString = "";
		if (application.getCountries().size() == 0) {
			langString = new Country().getDefaultLanguage();
		} else {
			langString = application.getCountries().iterator().next().getDefaultLanguage();
		}
		return MessageFormat.format(exampleUrl, application.getApplicationKey(), langString);
	}

	/**
	 * Generates an application key.
	 * 
	 * @param appKeyLength
	 *            the length of the key to generate
	 * @return the generated key.
	 */
	private String generateApplicationKey(int appKeyLength) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < appKeyLength; i++) {
			sb.append(CHARACTERS[random.nextInt(CHARACTERS.length)]);
		}
		return sb.toString();
	}

}
