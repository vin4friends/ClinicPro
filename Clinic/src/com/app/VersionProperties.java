/**
 * 
 */
package com.app;

/**
 * Container for version properties.
 * 
 * @author jomu
 */
public class VersionProperties {

	/**
	 * the name of the continuous integration job
	 */
	private String continuousIntegrationProject;

	/**
	 * the build id
	 */
	private String created;

	/**
	 * the release version of the application (${lf.version.release})
	 */
	private String release;

	/**
	 * build number
	 */
	private String build;

	/**
	 * the name of the environment on which the system is installed
	 */
	private String environment;

	/**
	 * should the version information be displayed on all pages of the
	 * application
	 */
	private boolean showOnPages;

	/**
	 * the name of the used property file
	 */
	private String propertyFile;

	/**
	 * Setter for the attribute continuousIntegrationProject.
	 * 
	 * @param continuousIntegrationProject
	 *            the attribute continuousIntegrationProject to set
	 */
	public void setContinuousIntegrationProject(String continuousIntegrationProject) {
		this.continuousIntegrationProject = continuousIntegrationProject;
	}

	/**
	 * Getter for the attribute continuousIntegrationProject.
	 * 
	 * @return the attribute continuousIntegrationProject
	 */
	public String getContinuousIntegrationProject() {
		return continuousIntegrationProject;
	}

	/**
	 * Setter for the attribute created.
	 * 
	 * @param created
	 *            the attribute created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * Getter for the attribute created.
	 * 
	 * @return the attribute created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Setter for the attribute release.
	 * 
	 * @param release
	 *            the attribute release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**
	 * Getter for the attribute release.
	 * 
	 * @return the attribute release
	 */
	public String getRelease() {
		return release;
	}

	/**
	 * Setter for the attribute build.
	 * 
	 * @param build
	 *            the attribute build to set
	 */
	public void setBuild(String build) {
		this.build = build;
	}

	/**
	 * Getter for the attribute build.
	 * 
	 * @return the attribute build
	 */
	public String getBuild() {
		return build;
	}

	/**
	 * Setter for the attribute propertyFile.
	 * 
	 * @param propertyFile
	 *            the name of the propertyFile to set
	 */
	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}

	/**
	 * Getter for the attribute propertyFile.
	 * 
	 * @return the attribute propertyFile
	 */
	public String getPropertyFile() {

		return propertyFile;
	}

	/**
	 * @return the name of the runtime environment.
	 */
	public String getEnvironment() {

		return environment;
	}

	/**
	 * Setter for the name of the runtime environment
	 * 
	 * @param environment
	 *            name of the runtime environment
	 */
	public void setEnvironment(String environment) {

		this.environment = environment;
	}

	/**
	 * Getter for the attribute showOnPages.
	 * 
	 * @return the attribute showOnPages
	 */
	public boolean getShowOnPages() {
		return showOnPages;
	}

	/**
	 * Setter for the attribute showOnPages.
	 * 
	 * @param showOnPages
	 *            the attribute showOnPages to set
	 */
	public void setShowOnPages(boolean showOnPages) {
		this.showOnPages = showOnPages;
	}
}
