/**
 * 
 */
package com.app.model.impl;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * Environment Properties are specified by a Key.
 * 
 * //author jomu
 */
//Entity
//Table(name = "ENVPROPS")
public class EnvironmentProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_KEY_LENGTH = 50;
	private static final int MAX_VALUE_LENGTH = 2000;

	/**
	 * Key Enum for Integer environment properties.
	 */
	public static enum IntegerProperty {
		MAX_LOCATIONS_PER_SEARCH_RESULT("clinic.admin.max_search_results", 30), //
		PAGINATION_PAGE_SIZE("clinic.admin.pagination.page_size", 2);

		private final String key;
		private final int defaultValue;

		private IntegerProperty(String key, int defaultValue) {
			this.key = key;
			this.defaultValue = defaultValue;
		}

		public String getKey() {
			return key;
		}

		public int getDefaultValue() {
			return defaultValue;
		}
	}

	/**
	 * Key Enum for String environment properties.
	 */
	public static enum StringProperty {
		APPLICATION_EXAMPLE_URL_INTERNET("lf.envprop.fe.web.application.example_url.internet",
				"https://app.com/finder?app_id={0}"), //
		APPLICATION_EXAMPLE_URL_INTRANET("lf.envprop.fe.web.application.example_url.intranet",
				"https://app.com/finder?app_id={0}"), //
		GOOGLE_ANALYTICS_ACCOUNT_INTERNET("lf.envprop.google.analytics.account.internet", "UA-XXXXX-X"), //
		GOOGLE_ANALYTICS_ACCOUNT_INTRANET("lf.envprop.google.analytics.account.intranet", "UA-XXXXX-X"), //
		GOOGLE_MAPS_CLIENT_ID_INTERNET("lf.envprop.google.maps.client_id.internet", ""), //
		GOOGLE_MAPS_CLIENT_ID_INTRANET("lf.envprop.google.maps.client_id.intranet", ""), //
		IMPEX_DEFAULT_CHARSET("lf.envprop.bl.impex.default_charset", "UTF-8"), //
		IMPEX_CATEGORY_COLUMN_PREFIX("lf.envprop.bl.impex.category_prefix", "cat"), //
		IMPEX_EXPORT_FILE_NAME_PREFIX("lf.envprop.bl.impex.export.filename_prefix", "export_locations_"), //
		IMPEX_EXPORT_FILE_NAME_SUFFIX("lf.envprop.bl.impex.export.filename_suffix", ".csv");

		private final String key;
		private final String defaultValue;

		private StringProperty(String key, String defaultValue) {
			this.key = key;
			this.defaultValue = defaultValue;
		}

		public String getKey() {
			return key;
		}

		public String getDefaultValue() {
			return defaultValue;
		}
	}

	//Id
	@Length(max = MAX_KEY_LENGTH)
	//Column(name = "TKEY", nullable = false, length = MAX_KEY_LENGTH)
	private String tkey;

	@Length(max = MAX_VALUE_LENGTH)
	//Column(name = "TVALUE", updatable = true, length = MAX_VALUE_LENGTH)
	private String tvalue;

	public String getTkey() {
		return tkey;
	}
	public void setTkey(String key) {
		this.tkey = key;
	}

	public String getTvalue() {
		return tvalue;
	}
	public void setTvalue(String value) {
		this.tvalue = value;
	}
}
