/**
 * 
 */
package com.app.fe.web.admin.location;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractBaseAction;
import com.app.impex.ExportResult;
import com.app.impex.AbstractImpexResult.ErrorMessage;
import com.app.model.impl.Country;
import com.app.util.CustomStringUtil;

/**
 * @author jomu
 */
public class LocationExportAction extends AbstractBaseAction {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Country country;

	private List<String> languages;

	private String selectedLanguage;

	private ExportResult exportResult;

	@Resource
	private transient LocationAdministrationFacade facade;

	/** Konstruktor. */
	public LocationExportAction() {
		country = new Country();
	}

	@Override
	public void prepare() throws Exception {
		country = facade.findCountryByPk(country.getCountrycode());
		languages = facade.getAvailableLanguagesForCountry(country);
	}

	@SkipValidation
	@Override
	public String execute() throws Exception {
		return INPUT;
	}

	public String export() {
		exportResult = facade.exportLocationsToCsv(country, selectedLanguage);
		if (exportResult.hasErrors()) {
			for (ErrorMessage msg : exportResult.getErrorMessages()) {
				addActionError(getText(msg.getMessageKey(), Arrays.asList(msg.getMessageArguments())));
			}
			return INPUT;
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (CustomStringUtil.isBlank(selectedLanguage)) {
			addFieldError("selectedLanguage", "could not be null");
		}

		if (!languages.contains(selectedLanguage)) {
			addFieldError("selectedLanguage", "unknown language");
		}
	}

	public List<String> getLanguages() {
		return languages;
	}

	public String getSelectedLanguage() {
		return selectedLanguage;
	}

	public void setSelectedLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}

	public Country getCountry() {
		return country;
	}

	public ExportResult getExportResult() {
		return exportResult;
	}
}
