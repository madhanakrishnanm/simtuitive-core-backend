package com.simtuitive.core.globalexception;

/**
 * Base Exception class to represent REST application specific exception
 * conditions -- to enable error handling and avoiding runtime exceptions to be
 * thrown to client.
 * 
 * When possible, REST controllers must throw one of the
 * {@code BaseRuntimeException} types so that {@code JsonApiWrapperAspect} can
 * process and provide proper error messages in output.
 * 
 */
public abstract class BaseRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int status;
	protected String code;
	protected String title;
	protected String selfUrl;

	public BaseRuntimeException() {
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String code, String title, String selfUrl, String message) {
		super(message);
		this.code = code;
		this.title = title;
		this.selfUrl = selfUrl;
	}

	public BaseRuntimeException(int status, String code, String title, String selfUrl) {
		this.status = status;
		this.code = code;
		this.title = title;
		this.selfUrl = selfUrl;
	}

	public BaseRuntimeException(int status, String code, String title, String selfUrl, String message) {
		super(message);
		this.status = status;
		this.code = code;
		this.title = title;
		this.selfUrl = selfUrl;
	}

	public BaseRuntimeException(int status, String code, String title, String selfUrl, Throwable cause) {
		super(cause);
		this.status = status;
		this.code = code;
		this.title = title;
		this.selfUrl = selfUrl;
	}

	public BaseRuntimeException(int status, String code, String title, String selfUrl, String message,
			Throwable cause) {
		super(message, cause);
		this.status = status;
		this.code = code;
		this.title = title;
		this.selfUrl = selfUrl;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the selfUrl
	 */
	public String getSelfUrl() {
		return selfUrl;
	}

	/**
	 * @param selfUrl the selfUrl to set
	 */
	public void setSelfUrl(String selfUrl) {
		this.selfUrl = selfUrl;
	}

}
