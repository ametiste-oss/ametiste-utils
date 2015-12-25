package org.ametiste.utils.ws;

public class ExternalServiceRuntimeException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 2560331782176201235L;

	public ExternalServiceRuntimeException(String string, Exception e) {
		super(string, e);
	}

}
