/*
 * Created on 17.05.2010
 *
 * Copyright(c) 1995 - 2010 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */
package com.app.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Pointcut System Konfiguration.
 * 
 * @author jomu
 */
@Aspect
public class LocationfinderSystemArchitecture {

	/**
	 * Pointcut-Konfiguration für den Dao-Layer annotiert mit @Repository.
	 */
	@Pointcut("execution (* (@org.springframework.stereotype.Repository *).*(..))")
	public void daoLayer() {

	}

	/**
	 * Pointcut-Konfiguration für den Service-Layer annotiert mit @Service.
	 */
	@Pointcut("execution (* (@org.springframework.stereotype.Service *).*(..))")
	public void serviceLayer() {

	}

}
