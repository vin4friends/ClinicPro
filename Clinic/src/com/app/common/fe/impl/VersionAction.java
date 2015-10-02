/**
 * 
 */
package com.app.common.fe.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.app.VersionProperties;

/**
 * Action for providing version information for the frontend to display.
 * 
 * @author jomu
 */
public class VersionAction extends AbstractBaseAction {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 8434456735044081622L;

	/**
	 * Version properties which are loaded at application start from
	 * ${lf.external.configuration.dir}/version.properties.
	 */
	@Resource
	private VersionProperties versionProperties;

	/**
	 * Getter for the version properties which are loaded at application start
	 * from ${lf.external.configuration.dir}/version.properties.
	 * 
	 * @return the versionProperties
	 */
	public VersionProperties getVersionProperties() {
		return versionProperties;
	}

	/**
	 * returns the current datetime of the system running the application.
	 * 
	 * @return current datetime
	 */
	public Date getSystemTime() {
		Date now = new Date();
		return now;
	}

	/**
	 * returns the ip-address of the server which is running the application.
	 * 
	 * @return the ip as String
	 */
	public String getIpAddress() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ipAddress = request.getLocalAddr();
		return ipAddress;
	}
}
