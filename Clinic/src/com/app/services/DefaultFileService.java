/**
 * 
 */
package com.app.services;

import static com.app.logging.LoggingConstants.MARKER_IMPEX;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.metamodel.Attribute;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.Util;

import com.app.GenericDao;
import com.app.LanguageUtil;
import com.app.exception.ImportFailedException;
import com.app.impex.AbstractImpexResult;
import com.app.impex.ExportResult;
import com.app.impex.ImportResult;
import com.app.model.impl.Country;
import com.app.model.impl.Location;
import com.app.model.impl.LocationType;
import com.app.model.impl.Location_;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.SingularAttribute;
import com.app.model.impl.EnvironmentProperty.StringProperty;
import com.app.util.CustomStringUtil;
import com.glaforge.i18n.io.SmartEncodingInputStream;

/**
 * Default Implementation of the FileService-Interface.
 * 
 * @author jomu
 */
@Service("fileService")
public class DefaultFileService implements FileService, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFileService.class);

	private static final CsvPreference DEFAULT_CSV_PREFERENCE = CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;

	private static final String IMPORT_ERROR_MESSAGE_PREFIX = "admin.location.import.error.";

	private static final String EXPORT_ERROR_MESSAGE_PREFIX = "admin.location.export.error.";

	private static final Map<String, SingularAttribute<Location, ?>> CSV_TO_LOCATION_MAPPING = new LinkedHashMap<String, SingularAttribute<Location, ?>>();

	private static final Map<String, String> LOCATION_TO_CSV_MAPPING = new LinkedHashMap<String, String>();

	private static final Pattern FILENAME_PATTERN = Pattern.compile(FILENAME_REGEX);

	private static final String CATEGORY_PREFIX_SEPARATOR = " ";

	@Resource
	private transient ValidatorFactory validatorFactory;

	@Resource
	private Validator validator;

	static {
		BeanUtilsBean.setInstance(new BeanUtilsBean2());
	}

	@Resource
	private transient ProductCategoryService productCategoryService;

	@Resource
	private transient LocationTypeService locationTypeService;

	@Resource
	private transient LocationService locationService;

	@Resource
	private transient EnvironmentPropertiesService environmentPropertiesService;

	@Resource
	private transient GenericDao dao;

	/**
	 * Das MetaModel wird erst beim Bauen des EntityManagers wirklich erzeugt.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// CSV to Location Mapping
		CSV_TO_LOCATION_MAPPING.put("lon", Location_.longitude);
		CSV_TO_LOCATION_MAPPING.put("lat", Location_.latitude);
		CSV_TO_LOCATION_MAPPING.put("outlet type", Location_.locationType);
		CSV_TO_LOCATION_MAPPING.put("sitecode", Location_.sitecode);
		CSV_TO_LOCATION_MAPPING.put("site Name", Location_.sitename);
		CSV_TO_LOCATION_MAPPING.put("trading Name", Location_.tradingname);
		CSV_TO_LOCATION_MAPPING.put("street", Location_.street);
		CSV_TO_LOCATION_MAPPING.put("district", Location_.district);
		CSV_TO_LOCATION_MAPPING.put("town", Location_.town);
		CSV_TO_LOCATION_MAPPING.put("postcode", Location_.postcode);
		CSV_TO_LOCATION_MAPPING.put("state", Location_.state);
		CSV_TO_LOCATION_MAPPING.put("country", Location_.country);
		CSV_TO_LOCATION_MAPPING.put("county", Location_.county);
		CSV_TO_LOCATION_MAPPING.put("telephone", Location_.telephone);
		CSV_TO_LOCATION_MAPPING.put("Fax", Location_.telefax);
		CSV_TO_LOCATION_MAPPING.put("email", Location_.email);
		CSV_TO_LOCATION_MAPPING.put("website url", Location_.url);
		CSV_TO_LOCATION_MAPPING.put("contact", Location_.contact);
		CSV_TO_LOCATION_MAPPING.put("OpenWeekdays", Location_.openweekday);
		CSV_TO_LOCATION_MAPPING.put("OpenWeekends", Location_.openweekend);
		CSV_TO_LOCATION_MAPPING.put("freetext", Location_.freeText);

		for (Map.Entry<String, SingularAttribute<Location, ?>> entry : CSV_TO_LOCATION_MAPPING.entrySet()) {
			LOCATION_TO_CSV_MAPPING.put(entry.getValue().getColoumnName(), entry.getKey());
		}

		validator = validatorFactory.getValidator();
	}

	@Override
	public ImportResult importLocationsFromCsv(final String filename, final File csvFile, final Country country)
			throws ImportFailedException {
		LOGGER.info(MARKER_IMPEX, "Starting import of csv file '{}' for country '{}'.", new Object[] { filename,
				country.getCountrycode() });

		final ImportResult result = new ImportResult(IMPORT_ERROR_MESSAGE_PREFIX);
		final Filename fileInfo = getCountryAndLanguageFromFilename(filename);

		if (fileInfo == null) {
			stopImportAndLogError(result, ErrorMessage.INVALID_FILENAME, filename);
		}

		// test if csv is valid for selected country.
		if (!fileInfo.countrycode.equalsIgnoreCase(country.getCountrycode())) {
			stopImportAndLogError(result, ErrorMessage.SELECTED_COUNTRY_DO_NOT_MATCH, country.getCountrycode(),
					fileInfo.countrycode, filename);
		}

		// check if extracted language is known
		if (!isLanguageKnown(country, fileInfo.language)) {
			stopImportAndLogError(result, ErrorMessage.UNKNWON_LANGUAGE, fileInfo.language, filename);
		}

		CsvListReader reader = null;
		FileInputStream fis = null;
		SmartEncodingInputStream seis = null;
		try {
			fis = new FileInputStream(csvFile);
			seis = new SmartEncodingInputStream(fis, SmartEncodingInputStream.BUFFER_LENGTH_4KB, getDefaultCharset());

			if (!getDefaultCharset().equals(seis.getEncoding())) {
				stopImportAndLogError(result, ErrorMessage.WRONG_ENCODING, getDefaultCharset().displayName(), seis
						.getEncoding().displayName());
			}

			reader = new CsvListReader(seis.getReader(), DEFAULT_CSV_PREFERENCE);
			final String[] csvHeader = reader.getCSVHeader(true);
			final List<ProductCategory> countryProductCategories = productCategoryService.findAll(country,
					fileInfo.language);
			final String[] countryHeader = buildCsvHeaderForCountry(countryProductCategories);

			final List<String> unknownColumns = getUnknownColumns(countryHeader, csvHeader);

			if (!unknownColumns.isEmpty()) {
				stopImportAndLogError(result, ErrorMessage.UNKNOWN_COLUMNS, columnsToString(unknownColumns));
			}

			result.setOldLocationCount(locationService.deleteAllLocations(country, fileInfo.language));
			final int newLocationCount = doImport(reader, csvHeader, result, country, fileInfo,
					countryProductCategories);
			if (newLocationCount == 0) {
				addErrorMessageAndLog(result, ErrorMessage.NO_VALID_ENTRIES_FOUND, filename);
			} else {
				result.setNewLocationCount(newLocationCount);
			}
		} catch (FileNotFoundException e) {
			stopImportAndLogError(result, ErrorMessage.FILE_NOT_FOUND, e);
		} catch (IOException e) {
			stopImportAndLogError(result, ErrorMessage.IO_EXCEPTION, e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (seis != null) {
					seis.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				LOGGER.error(MARKER_IMPEX, "While trying to close streams an IOException occurred.", e);
			}
		}

		if (result.hasErrors()) {
			stopImport(result);
		}

		LOGGER.info(MARKER_IMPEX, "Successful import of csv file '{}'.", filename);
		return result;
	}

	/**
	 * Reads the actual csv file in and validates the locations read from the
	 * file.
	 * 
	 * @param reader
	 *            the reader used to read the import file
	 * @param csvHeader
	 *            the extracted headers
	 * @param result
	 *            the import result
	 * @param country
	 *            the country for which the locations are imported
	 * @param fileInfo
	 *            the information about the filename of the import file
	 * @param countryProductCategories
	 *            the productcategories of the country
	 * @return number of successful imported locations
	 * @throws IOException
	 *             if an IOException occurs during import
	 */
	private int doImport(final CsvListReader reader, final String[] csvHeader, ImportResult result, Country country,
			Filename fileInfo, List<ProductCategory> countryProductCategories) throws IOException {
		List<String> line;
		int newLocCounter = 0;
		int lineNumber = 0;
		try {
			while ((line = reader.read()) != null) {
				lineNumber = reader.getLineNumber();
				if (csvHeader.length == line.size()) {
					final Map<String, String> destination = new HashMap<String, String>(line.size());
					Util.mapStringList(destination, csvHeader, line);

					final Location loc = convertCsvToLocation(result, lineNumber, destination, country,
							fileInfo.language);
					addProductCategoriesToLocation(loc, destination, countryProductCategories);
					if (assertValidLocationWhileLoggingErrors(loc, result, lineNumber)) {
						locationService.createLocation(loc);
						newLocCounter++;
					}
				} else {
					addErrorMessageAndLog(result, ErrorMessage.COLUMN_COUNT_MISMATCH, lineNumber, csvHeader.length,
							line.size());
				}
			}
		} catch (SuperCSVException e) {
			LOGGER.error(MARKER_IMPEX, "SuperCSVException occured:", e);
			addErrorMessageAndLog(result, ErrorMessage.SUPER_CSV_EXCEPTION, lineNumber, fileInfo.filename);
		}

		return newLocCounter;
	}

	@Override
	public ExportResult exportLocationsToCsv(final Country country, final String language) {
		LOGGER.info(MARKER_IMPEX, "Starting location export to csv for country '{}' and language '{}'.",
				country.getCountrycode(), language);
		ExportResult result = new ExportResult(EXPORT_ERROR_MESSAGE_PREFIX);
		long count = locationService.countLocations(country, language);

		if (count == 0) {
			result.addErrorMessage("no_locations_found", country.getCountrycode(), language);
		} else {
			final List<ProductCategory> countryProductCategories = productCategoryService.findAll(country, language);
			final List<Location> locations = locationService.findAll(country, language);
			final String[] csvHeader = buildCsvHeaderForCountry(countryProductCategories);

			final ByteArrayOutputStream csvStream = new ByteArrayOutputStream();
			OutputStreamWriter osWriter = new OutputStreamWriter(csvStream, getDefaultCharset());
			CsvMapWriter writer = null;
			try {
				writer = new CsvMapWriter(osWriter, DEFAULT_CSV_PREFERENCE);
				writer.writeHeader(csvHeader);

				for (Location location : locations) {
					final Map<String, String> csvLine = convertLocationToMap(location, countryProductCategories);
					writer.write(csvLine, csvHeader);
				}
			} catch (IOException e) {
				addErrorMessageAndLog(result, ErrorMessage.IO_EXCEPTION, e);
			} finally {
				try {
					if (writer != null) {
						writer.close();
					}
				} catch (IOException e) {
					LOGGER.error(MARKER_IMPEX, "While trying to close streams an IOException occurred.", e);
				}
			}

			// Needs to be after the writer.close() statement, so all data which
			// is currently bufferd will be flushed to the bytearray before the
			// bytearray is used for the bytearayinputstream
			if (!result.hasErrors()) {
				String filename = getExportFilenamePrefix() + country.getCountrycode() + "_" + language
						+ getExportFilenameSuffix();
				result.setCsvStream(new ByteArrayInputStream(csvStream.toByteArray()));
				result.setFilename(filename);
				LOGGER.info(MARKER_IMPEX, "Successful export to csv file '{}'.", filename);
			}
		}
		return result;
	}

	/**
	 * Builds a Location from read line of the csv.
	 * 
	 * @param line
	 *            the csv line representing a location.
	 * @param language
	 *            the language for which this location will be build.
	 * @return the Location Object filled with data from the csv line
	 */
	private Location convertCsvToLocation(ImportResult result, int lineNumber, Map<String, String> line,
			Country country, String language) {
		final Location location = new Location();
		final Map<String, Object> attributeNamesToValueMapping = new HashMap<String, Object>();

		location.setLanguage(language);
		location.setCountry(country);
		location.setIsOnline(Boolean.TRUE);

		for (Map.Entry<String, SingularAttribute<Location, ?>> mapping : CSV_TO_LOCATION_MAPPING.entrySet()) {
			final SingularAttribute<Location, ?> attribute = mapping.getValue();
			Object csvValue = line.get(mapping.getKey());
			// Convert comma to point delimiter
			if (attribute.getColoumnName().equals(Location_.longitude.getColoumnName()) || attribute.getColoumnName().equals(Location_.latitude.getColoumnName())) {
				String lonLat = ((String) csvValue).replaceAll(",", ".");

				if (CustomStringUtil.isNotBlank(lonLat)) {
					try {
						final BigDecimal bd = new BigDecimal(lonLat);
						final BigDecimal scaledBd = bd.setScale(6, RoundingMode.HALF_UP);
						csvValue = scaledBd.doubleValue();
					} catch (NumberFormatException e) {
						LOGGER.info(MARKER_IMPEX, "NumberFormatExcpetion occured:", e);
						addErrorMessageAndLog(result, ErrorMessage.NOT_A_NUMBER, lineNumber, mapping.getKey(), lonLat);
					}
				} else {
					csvValue = lonLat;
				}
			} else if (attribute.getColoumnName().equals(Location_.locationType.getColoumnName())) {
				// Check for Location type
				csvValue = getOrCreateLocationType(location, (String) csvValue);
			}

			if (attribute.getColoumnName().equals(Location_.country.getColoumnName())) {
				// ignore it for now
			} else {
				attributeNamesToValueMapping.put(attribute.getColoumnName(), csvValue);
			}
		}

		try {
			BeanUtils.populate(location, attributeNamesToValueMapping);
		} catch (IllegalAccessException e) {
			LOGGER.error(MARKER_IMPEX, "Error while populating bean with data.", e);
			return null;
		} catch (InvocationTargetException e) {
			LOGGER.error(MARKER_IMPEX, "Error while populating bean with data.", e);
			return null;
		}


		return location;
	}

	/**
	 * Utility method for exporting a location.
	 * 
	 * @param location
	 *            location to export
	 * @param countryProductCategories
	 *            product categories of country
	 * @return a map representation of the location, valid csv line
	 */
	private Map<String, String> convertLocationToMap(Location location, List<ProductCategory> countryProductCategories) {
		Map<String, String> result = new HashMap<String, String>();

		for (Map.Entry<String, SingularAttribute<Location, ?>> mapping : CSV_TO_LOCATION_MAPPING.entrySet()) {
			String value;
			if (mapping.getValue().getColoumnName().equals(Location_.locationType.getColoumnName())) {
				value = location.getLocationType().getLocationType();
			} else if (mapping.getValue().getColoumnName().equals(Location_.country.getColoumnName())) {
				value = location.getCountry().getCountrycode();
			} else {
				try {
					value = BeanUtils.getProperty(location, mapping.getValue().getColoumnName());
					if (value == null) {
						value = "";
					}
				} catch (IllegalAccessException e) {
					value = e.getMessage();
				} catch (InvocationTargetException e) {
					value = e.getMessage();
				} catch (NoSuchMethodException e) {
					value = e.getMessage();
				}
			}

			result.put(mapping.getKey(), value);
		}

		for (ProductCategory productCategory : countryProductCategories) {
			String value;
			if (location.getProductCategories().contains(productCategory)) {
				value = "Yes";
			} else {
				value = "No";
			}
			result.put(getCategoryColumnPrefix() + productCategory.getName(), value);
		}

		return result;
	}

	/**
	 * Adds a LocationType to a Location.
	 * 
	 * @param location
	 *            the location which should get the LocationType added.
	 * @param locationType
	 *            the name of the LocationType
	 */
	private LocationType getOrCreateLocationType(Location location, String locationType) throws ConversionException {
		if (CustomStringUtil.isBlank(locationType)) {
			return null;
		}

		LocationType dbType = locationTypeService.getLocationType(location.getCountry(), location.getLanguage(),
				locationType);

		if (dbType == null) {
			// LocationType newType = new LocationType();
			// newType.setCountry(location.getCountry());
			// newType.setLanguage(location.getLanguage());
			// newType.setLocationType(locationType);
			// locationTypeService.createLocationType(newType);
			//
			// dbType = newType;
		}

		return dbType;
	}

	/**
	 * Adds the ProductCategories to a Location. Should be called prior to
	 * persisting the Location into the database. Does nothing if
	 * {@code location == null}.
	 * 
	 * @param location
	 *            the location which should be get ProductCategories.
	 * @param line
	 *            the correspending csv line which represents the location
	 *            entity
	 * @param possibleProductCategories
	 *            the possible productcategories.
	 */
	private void addProductCategoriesToLocation(Location location, final Map<String, String> line,
			final List<ProductCategory> possibleProductCategories) throws ConversionException {
		if (location == null) {
			return;
		}

		final Map<String, ProductCategory> columnNameToCategoryeMap = buildProductCategoriesToColumnnamesMap(possibleProductCategories);

		for (Map.Entry<String, ProductCategory> entry : columnNameToCategoryeMap.entrySet()) {
			String lineValue = line.get(entry.getKey());
			if ((Boolean) BeanUtilsBean.getInstance().getConvertUtils().convert(lineValue, Boolean.class)) {
				location.getProductCategories().add(entry.getValue());
			}
		}
	}

	/**
	 * checks if all location type columns are present and all other columns
	 * starts with the category prefix.
	 * 
	 * @param knownColumnnames
	 *            expected column names
	 * @param actualHeader
	 *            columnnames of the csv to import
	 * @return a list of unknown column names or a empty list if all columns are
	 *         known.
	 */
	private List<String> getUnknownColumns(String[] knownColumnnames, String[] actualHeader) {
		List<String> unknownColumns = new ArrayList<String>();

		Arrays.sort(knownColumnnames, 0, knownColumnnames.length);

		// check if all known columns are present
		for (String column : actualHeader) {
			if (CustomStringUtil.isBlank(column)) {
				unknownColumns.add("empty column name");
			} else if (Arrays.binarySearch(knownColumnnames, column) < 0) {
				unknownColumns.add(column);
			}
		}

		return unknownColumns;
	}

	/**
	 * Builds the valid Csv Header.
	 * 
	 * @param productCategories
	 *            the expected product categories in the csv.
	 * @return the CsvHeader.
	 */
	private String[] buildCsvHeaderForCountry(List<ProductCategory> productCategories) {
		final List<String> result = new ArrayList<String>();

		result.addAll(CSV_TO_LOCATION_MAPPING.keySet());
		result.addAll(buildProductCategoriesToColumnnamesMap(productCategories).keySet());

		return result.toArray(new String[0]);
	}

	/**
	 * Build a mapping from Columnname to ProductCategory. The column name is
	 * the ColumnNamePrefix + ProductCategoryName.
	 * 
	 * @param productCategories
	 *            a List of ProductCategories for which the mapping should be
	 *            build.
	 * @return a Map with ColumnName to ProductCategory mapping.
	 */
	private Map<String, ProductCategory> buildProductCategoriesToColumnnamesMap(List<ProductCategory> productCategories) {
		final Map<String, ProductCategory> result = new HashMap<String, ProductCategory>(productCategories.size());

		for (ProductCategory category : productCategories) {
			result.put(getCategoryColumnPrefix() + category.getName(), category);
		}

		return result;
	}

	private Charset getDefaultCharset() {
		return Charset.forName(environmentPropertiesService.getStringProperty(StringProperty.IMPEX_DEFAULT_CHARSET));
	}

	private String getCategoryColumnPrefix() {
		return environmentPropertiesService.getStringProperty(StringProperty.IMPEX_CATEGORY_COLUMN_PREFIX)
				+ CATEGORY_PREFIX_SEPARATOR;
	}

	private String getExportFilenamePrefix() {
		return environmentPropertiesService.getStringProperty(StringProperty.IMPEX_EXPORT_FILE_NAME_PREFIX);
	}

	private String getExportFilenameSuffix() {
		return environmentPropertiesService.getStringProperty(StringProperty.IMPEX_EXPORT_FILE_NAME_SUFFIX);
	}

	private Filename getCountryAndLanguageFromFilename(final String filename) {
		if (filename != null) {
			Matcher matcher = FILENAME_PATTERN.matcher(filename);
			if (matcher.matches()) {
				// countrycode in match group 1 and language in match group 2.
				return new Filename(filename, matcher.group(1), matcher.group(2));
			} else {
				return null;
			}
		}
		return null;
	}

	private String columnsToString(List<String> columns) {
		Iterator<String> i = columns.iterator();
		if (!i.hasNext())
			return "empty list";

		final StringBuilder sb = new StringBuilder();
		for (;;) {
			String column = i.next();
			sb.append(column);
			if (!i.hasNext()) {
				return sb.toString();
			}
			sb.append(", ");
		}
	}

	/**
	 * Test if a language or parts of the language are known to us, by counting
	 * the ProductCategories which this language in the given country.
	 * 
	 * @param targetCountry
	 *            the country in which this language should be known
	 * @param language
	 *            the language which should be known
	 * @return {@code true} if at least one ProductCategory could be found for
	 *         the language in this country, otherwise {@code false}
	 */
	private boolean isLanguageKnown(Country targetCountry, String language) {
		if (CustomStringUtil.isBlank(language)) {
			return false;
		}

		final String[] languageCandidates = LanguageUtil.getLanguageCandidates(language);

		for (String candidate : languageCandidates) {
			Long count = countProductCategories(targetCountry, candidate);

			if (count != null && count > 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Counts the ProductCategories in a country for the given language.
	 * 
	 * @param targetCountry
	 *            the country in which the ProductCategories should be counted
	 * @param language
	 *            the language for which the Categories should be counted
	 * @return the count
	 */
	private Long countProductCategories(Country country, String language) {
		/*CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);

		Root<ProductCategory> category = query.from(ProductCategory.class);
		query.select(cb.count(category.get(ProductCategory_.id)));

		Predicate where = cb.equal(category.get(ProductCategory_.country), country);
		where = cb.and(where, cb.equal(category.get(ProductCategory_.language), language));
		query.where(where);

		return dao.findSingle(query);*/
		return 10l;
	}

	/**
	 * Validates the Constraints of a Location. If the {@code location} is
	 * {@code null} a {@link ErrorMessage#CREATION_IMPOSSIBLE} error is added to
	 * the ImportResult. For every constraint violation which occurs a
	 * {@link ErrorMessage#INVALID_DATA} error is added to the ImportResult
	 * 
	 * @param location
	 *            the location to validate
	 * @param result
	 *            the ImportResult to which errors should be logged
	 * @param lineNumber
	 *            the line which is currently processed while importing
	 * @return {@code true} if {@code location != null} and no constraint
	 *         violations occurs, otherwise {@code false}
	 */
	private boolean assertValidLocationWhileLoggingErrors(Location location, ImportResult result, int lineNumber) {
		if (location == null) {
			addErrorMessageAndLog(result, ErrorMessage.CREATION_IMPOSSIBLE, lineNumber);
			return false;
		}

		final Set<ConstraintViolation<Location>> violations = validator.validate(location);
		if (violations.size() == 0) {
			return true;
		}

		for (ConstraintViolation<Location> violation : violations) {
			String columnName = LOCATION_TO_CSV_MAPPING.get(violation.getPropertyPath().toString());
			if (CustomStringUtil.isBlank(columnName)) {
				columnName = violation.getPropertyPath().toString();
			}
			addErrorMessageAndLog(result, ErrorMessage.INVALID_DATA, lineNumber, columnName);
		}

		return false;
	}

	/**
	 * Logs an error and add it to the AbstractImpexResult.
	 * 
	 * @param result
	 *            the abstract impex result which will get the errMsg added.
	 * @param errMsg
	 *            the error which will be logged.
	 * @param t
	 *            the cause of the error
	 */
	private void addErrorMessageAndLog(AbstractImpexResult result, ErrorMessage errMsg, Throwable t) {
		LOGGER.info(errMsg.defaultMsg, t);
		result.addErrorMessage(errMsg.msgKey);
	}

	/**
	 * Logs an error and add it to the AbstractImpexResult.
	 * 
	 * @param result
	 *            the abstract impex result which will get the errMsg added.
	 * @param errMsg
	 *            the error which will be logged.
	 * @param msgArgs
	 *            the message arguments of the error message
	 */
	private void addErrorMessageAndLog(AbstractImpexResult result, ErrorMessage errMsg, Object... msgArgs) {
		LOGGER.info(MARKER_IMPEX, errMsg.defaultMsg, msgArgs);
		result.addErrorMessage(errMsg.msgKey, msgArgs);
	}

	/**
	 * Stops the import and logs the cause.
	 * 
	 * @param result
	 *            the import result which will be added to the
	 *            ImportFailedException.
	 * @param errMsg
	 *            the error which causes the stop.
	 * @param t
	 *            the cause of the stop
	 * @throws ImportFailedException
	 *             the stop signal...
	 */
	private void stopImportAndLogError(ImportResult result, ErrorMessage errMsg, Throwable t)
			throws ImportFailedException {
		addErrorMessageAndLog(result, errMsg, t);
		stopImport(result);
	}

	/**
	 * Stops the import and logs the cause.
	 * 
	 * @param result
	 *            the import result which will be added to the
	 *            ImportFailedException.
	 * @param errMsg
	 *            the error which causes the stop.
	 * @param msgArgs
	 *            the error arguments for logging.
	 * @throws ImportFailedException
	 *             the stop signal...
	 */
	private void stopImportAndLogError(ImportResult result, ErrorMessage errMsg, Object... msgArgs)
			throws ImportFailedException {
		addErrorMessageAndLog(result, errMsg, msgArgs);
		stopImport(result);
	}

	/**
	 * Stops the import.
	 * 
	 * @param result
	 *            the result that will be returned in the ImportFailedException
	 * @throws ImportFailedException
	 *             stop signal ...
	 */
	private void stopImport(ImportResult result) throws ImportFailedException {
		LOGGER.info(MARKER_IMPEX, "Import failed.");
		throw new ImportFailedException("import failed with errors", result);
	}

	/**
	 * Helper class for combining the results of the filename parsing.
	 */
	private static final class Filename {
		/** the original filename */
		final String filename;
		/** the extracted countrycode. */
		final String countrycode;
		/** the extracted language. */
		final String language;

		/**
		 * Constructor.
		 * 
		 * @param countryCode
		 *            from the filname extracted countrycode
		 * @param language
		 *            from the filname extracted language
		 */
		public Filename(String filename, String countryCode, String language) {
			this.filename = filename;
			this.countrycode = countryCode;
			this.language = language;
		}
	}

	/**
	 * Enum of ErrorMessages which are possible during import and export
	 */
	private static enum ErrorMessage {
		INVALID_FILENAME("invalid_filename", "Could not obtain country and language information from filename '{}'."), //
		SELECTED_COUNTRY_DO_NOT_MATCH("selected_country_do_not_match_csv",
				"Preselected countrycode '{}' does not match countrycode '{}' extracted from filename '{}'."), //
		UNKNWON_LANGUAGE("unknown_language", "Unknown language '{}' found in filename '{}', aborting import."), //
		WRONG_ENCODING("wrong_encoding", "File seems not to be {} encoded. Guessed encoding was {}."), //
		NOT_A_NUMBER("not_a_number", "Number in line '{}' column '{}' expected, but '{}' found."), //
		INVALID_DATA("invalid_data", "Invalid data in row {} for column {} found."), //
		CREATION_IMPOSSIBLE("creation_impossible", "Could not create location from row {}"), //
		COLUMN_COUNT_MISMATCH("column_count_not_equal",
				"Column count in line '{}' does not equal column count in header. Expected '{}' columns but found '{}'.s"), //
		SUPER_CSV_EXCEPTION("super_csv_exception", "Error while processing line {} in file {}, aborting import."), //
		NO_VALID_ENTRIES_FOUND("no_valid_entries_found", "No valid entry found in file {}."), //
		UNKNOWN_COLUMNS("unknown_colums", "Found unknown columns: {}"), //
		FILE_NOT_FOUND("file_not_found", "Uploaded file not found for processing."), //
		IO_EXCEPTION("io_exception", "While processing the file an IO Exception occured."); //

		/**
		 * message key of error message in resource bundles used for error
		 * display for the user
		 */
		private final String msgKey;
		/** message used to log into the log file */
		private final String defaultMsg;

		private ErrorMessage(String msgKey, String defaultMsg) {
			this.msgKey = msgKey;
			this.defaultMsg = defaultMsg;
		}
	}
}
