package com.simtuitive.core.controller.productmgmt.api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents error section of JSON API output specification
 * 
 * @Surath Patnaik
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

	private int status;
	private String code;
	private String title;
	private String detail;
	private MetaInfo meta;

	/**
	 * 
	 * @param status
	 * @param code
	 * @param title
	 * @param detail
	 */
	public Error(int status, String code, String title, String detail) {
		this.status = status;
		this.code = code;
		this.title = title;
		this.detail = detail;
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
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the meta
	 */
	public MetaInfo getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(MetaInfo meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "Error [status=" + status + ", code=" + code + ", title=" + title + ", detail=" + detail + "]";
	}

}
