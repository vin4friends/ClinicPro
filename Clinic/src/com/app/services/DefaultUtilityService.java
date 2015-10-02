package com.app.services;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.app.model.impl.Application;

public class DefaultUtilityService implements InitializingBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultUtilityService.class);
	@Resource
	private ApplicationService applicationService;
	@Autowired
	private ApplicationContext applicationContext;

	private static DefaultUtilityService INSTANCE = null;

	private DefaultUtilityService() {
	}

	public static DefaultUtilityService getInstance() {
		if (INSTANCE == null) {
			synchronized (DefaultUtilityService.class) {
				if (INSTANCE == null) {
					try {
						// INSTANCE=new DefaultUtilityService();
					} catch (Exception e) {
						throw new IllegalStateException("xyz", e);
					}
					INSTANCE = new DefaultUtilityService();
				}
			}
		}

		return INSTANCE;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public String getIframeId(String appKey) {
		String iframeId = "";
		try {

			Application ap = applicationService.getApplication(appKey);
			if (ap != null) {
				iframeId = ap.getIframeId();
			}
		} catch (Exception e) {

			LOGGER.error("exception occured during iframe retrieve "
					+ e.getMessage());
		}
		return iframeId;
	}

	@Override
	public void afterPropertiesSet() {
		try
		{
		INSTANCE = (DefaultUtilityService) getApplicationContext().getBean(
				"utilityBean");
		}catch(Exception e)
		{
			LOGGER.error("error occured while instaniating");
		}
	}

}