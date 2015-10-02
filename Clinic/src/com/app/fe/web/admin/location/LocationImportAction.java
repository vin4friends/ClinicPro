/**
 * 
 */
package com.app.fe.web.admin.location;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.app.admin.bl.location.LocationAdministrationFacade;
import com.app.common.fe.impl.AbstractBaseAction;
import com.app.exception.ImportFailedException;
import com.app.impex.ImportResult;
import com.app.impex.AbstractImpexResult.ErrorMessage;
import com.app.model.impl.Country;

/**
 * Action die den Upload von CSV Dateien ermöglicht.
 * 
 * @author jomu
 */
public class LocationImportAction extends AbstractBaseAction {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Der Country für den der Upload erfolgt.
	 */
	private Country country;

	@NotNull(message = "admin.location.import.error.no_file_uploaded")
	private File file;

	private String filename;

	private String actionMethod;

	private ImportResult uploadResult;

	@Resource
	private transient LocationAdministrationFacade facade;

	private static final Map<String, String> VALIDATION_PATH_TO_FIELDNAME = new HashMap<String, String>();

	static {
		VALIDATION_PATH_TO_FIELDNAME.put("file", "upload");
	}

	public LocationImportAction() {
		super(VALIDATION_PATH_TO_FIELDNAME);
		country = new Country();
	}

	@Override
	public void prepare() throws Exception {
		country = facade.findCountryByPk(country.getCountrycode());
	}

	@SkipValidation
	@Override
	public String execute() throws Exception {
		actionMethod = "upload";
		return INPUT;
	}

	public String upload() {
		try {
			uploadResult = facade.importLocationsFromCsv(filename, file, country);
		} catch (ImportFailedException e) {
			uploadResult = e.getImportResult();
		}
		if (uploadResult.hasErrors()) {
			for (ErrorMessage msg : uploadResult.getErrorMessages()) {
				addActionError(getText(msg.getMessageKey(), Arrays.asList(msg.getMessageArguments())));
			}
			return INPUT;
		}
		return SUCCESS;
	}

	public Country getCountry() {
		return country;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public ImportResult getUploadResult() {
		return uploadResult;
	}
}
