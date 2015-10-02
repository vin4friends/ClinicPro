/**
 * 
 */
package com.app.impex;

/**
 * Ein Ergebnis eines Datei-Imports.
 * 
 * @author jomu
 */
public class ImportResult extends AbstractImpexResult {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Anzahl bisheriger Locations im Land für das Locations importiert wurden.
	 */
	private long oldLocationCount;

	/**
	 * Anzahl Locations im Land nachdem das Importieren durchgeführt wurde.
	 */
	private long newLocationCount;

	/**
	 * Konstruktor.
	 * 
	 * @param errorMessagePrefix
	 *            der Prefix für Fehlermeldungen.
	 */
	public ImportResult(String errorMessagePrefix) {
		super(errorMessagePrefix);
	}

	public long getOldLocationCount() {
		return oldLocationCount;
	}

	public void setOldLocationCount(long oldLocationCount) {
		this.oldLocationCount = oldLocationCount;
	}

	public long getNewLocationCount() {
		return newLocationCount;
	}

	public void setNewLocationCount(long newLocationCount) {
		this.newLocationCount = newLocationCount;
	}
}
