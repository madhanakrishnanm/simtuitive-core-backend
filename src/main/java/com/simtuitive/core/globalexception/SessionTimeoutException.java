package com.simtuitive.core.globalexception;

import org.springframework.http.HttpStatus;

public class SessionTimeoutException extends BaseRuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionTimeoutException() {
	    }

	    public SessionTimeoutException(String message) {
	        super(message);
	        status = HttpStatus.UNAUTHORIZED.value();
	    }

	    public SessionTimeoutException(Throwable cause) {
	        super(cause);
	        status = HttpStatus.UNAUTHORIZED.value();
	    }

	    public SessionTimeoutException(String message, Throwable cause) {
	        super(message, cause);
	        status = HttpStatus.UNAUTHORIZED.value();
	    }

	    public SessionTimeoutException(String code, String title, String selfUrl, String message) {
	        super(code, title, selfUrl, message);
	        status = HttpStatus.UNAUTHORIZED.value();
	    }

	    public SessionTimeoutException(int status, String code, String title, String selfUrl) {
	        super(status, code, title, selfUrl);
	    }

	    public SessionTimeoutException(int status, String code, String title, String selfUrl, String message) {
	        super(status, code, title, selfUrl, message);
	    }

	    public SessionTimeoutException(int status, String code, String title, String selfUrl, Throwable cause) {
	        super(status, code, title, selfUrl, cause);
	    }

	    public SessionTimeoutException(int status, String code, String title, String selfUrl, String message,
	                                         Throwable cause) {
	        super(status, code, title, selfUrl, message, cause);
	    }


}

