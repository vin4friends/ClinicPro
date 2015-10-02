/**
 * 
 */
package com.app.exception;

import com.app.impex.ImportResult;

/**
 * Exception to indicate that an CSV Import failed. It is necessary to throw an
 * Exception to Rollback the Transaction so no changes would be written in to
 * the Database.
 * 
 * @author jomu
 */
public class ImportFailedException extends Exception {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private final ImportResult importResult;

	public ImportFailedException(String message, ImportResult importResult) {
		super(message);
		this.importResult = importResult;
	}

	public ImportResult getImportResult() {
		return importResult;
	}
}
