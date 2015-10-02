/**
 * 
 */
package com.app.impex;

import java.io.InputStream;

/**
 * Das Ergebnis eines Exports.
 * 
 * @author jomu
 */
public class ExportResult extends AbstractImpexResult {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private String filename;

	/**
	 * CSV as Stream for serving it to the user.
	 */
	private InputStream csvStream;

	public ExportResult(String errorMessagePrefix) {
		super(errorMessagePrefix);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getCsvStream() {
		return csvStream;
	}

	public void setCsvStream(InputStream csvStream) {
		this.csvStream = csvStream;
	}
}
