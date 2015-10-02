/**
 * 
 */
package com.app.impex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jomu
 * 
 */
public abstract class AbstractImpexResult implements Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Prefix for errorMessages for lookup in a resource bundle.
	 */
	private final String errorMessagePrefix;

	/**
	 * Error messages which occured during an Import or Export.
	 */
	private final List<ErrorMessage> errorMessages;

	/**
	 * Constructor.
	 */
	public AbstractImpexResult() {
		this("");
	}

	/**
	 * Constructor.
	 * 
	 * @param errorMessagePrefix
	 *            the prefix for error messages.
	 */
	public AbstractImpexResult(String errorMessagePrefix) {
		this.errorMessagePrefix = errorMessagePrefix;
		errorMessages = new ArrayList<ErrorMessage>();
	}

	/**
	 * Adds an error message to this result, which can be presented to the user.
	 * 
	 * @param errorMessage
	 *            messageKey of error message without defined
	 *            <code>errorMessagePrefix</code>
	 * @param messageArguments
	 *            optional message arguments for error message
	 * @return <code>true</code> if this errorMessage was added successfully.
	 */
	public boolean addErrorMessage(String errorMessage, Object... messageArguments) {
		return errorMessages.add(new ErrorMessage(errorMessagePrefix + errorMessage, messageArguments));
	}

	/**
	 * Returns a list of error messages associatied with this result.
	 * 
	 * @return list of error messages
	 */
	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Checks if an error occurred during import or export.
	 * 
	 * @return <code>true</code>, if an error occurred, otherwise
	 *         <code>false</code>.
	 */
	public boolean hasErrors() {
		return !errorMessages.isEmpty();
	}

	/**
	 * Container for error message with its message arguments.
	 * 
	 * @author jomu
	 */
	public static final class ErrorMessage {
		private final String messageKey;
		private final Object[] messageArguments;

		public ErrorMessage(String messageKey, Object[] messageArguments) {
			this.messageKey = messageKey;
			this.messageArguments = messageArguments;
		}

		public String getMessageKey() {
			return messageKey;
		}

		public Object[] getMessageArguments() {
			return messageArguments;
		}
	}
}
