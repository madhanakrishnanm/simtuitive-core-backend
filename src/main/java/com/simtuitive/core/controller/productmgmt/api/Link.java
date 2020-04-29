package com.simtuitive.core.controller.productmgmt.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents link object under links section of JSON API output specification
 * 
 * @author Surath Patnaik
 */
@JsonInclude(Include.NON_NULL)
public class Link {

	// @todo description field to be used as json tag description -- using custom
	// serializer
	private String description;
	private String href;
	private MetaInfo meta;

	public Link() {
	}

	public Link(String href, String description) {
		this.href = href;
		this.description = description;
	}

	public Link(String href, String description, MetaInfo meta) {
		this.href = href;
		this.description = description;
		this.meta = meta;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
