/**
 * 
 */
package com.app.services;

import java.io.File;

import com.app.exception.ImportFailedException;
import com.app.impex.ExportResult;
import com.app.impex.ImportResult;
import com.app.model.impl.Country;

/**
 * Service for importing and exporting Locations to a File. Usually a CSV.
 * 
 * @author jomu
 */
public interface FileService {

	/**
	 * The filename pattern which will be used to extract countrycode und
	 * languagecode from a given filename. the filename of the uploaded file
	 * must correspond to this pattern.
	 */
	String FILENAME_REGEX = "^.*_(.*)_(.*)\\.csv$";

	/**
	 * <p>
	 * Imports locations from the given csv file for the specified country. All
	 * previously defined locations for that specific country with the language
	 * specified by the file name will be erased.
	 * </p>
	 * 
	 * <p>
	 * The <code>filename</code> must follow the scheme
	 * <code>somethingFileSpecific_countrycode_languagecode.csv</code>. The
	 * countrycode in the filename must equal the countrycode of the specified
	 * <code>targetCountry</code>.
	 * </p>
	 * 
	 * <p>
	 * For example: <code>locations_gb_en.csv</code>, if the targeted country
	 * for the import ist the United Kingdom and the imported locations are
	 * imported for the english language.
	 * </p>
	 * 
	 * @param filename
	 *            the name of the file to import
	 * @param csvFile
	 *            the file which is an csv file
	 * @param targetCountry
	 *            the country for which this file should be imported.
	 * @return the result of the export.
	 * @throws ImportFailedException
	 *             when an error occurred during import
	 */
	ImportResult importLocationsFromCsv(final String filename, final File csvFile, final Country targetCountry)
			throws ImportFailedException;

	/**
	 * Exports the locations for a given country and a specified language in csv
	 * file format.
	 * 
	 * @param country
	 *            the country of the locations which should be exported.
	 * @param language
	 *            the language of the locations which should be exported
	 * @return the result of the export.
	 */
	ExportResult exportLocationsToCsv(final Country country, final String language);
}
