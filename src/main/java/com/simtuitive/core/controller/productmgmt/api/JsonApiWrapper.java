/**
 * 
 */
package com.simtuitive.core.controller.productmgmt.api;

/**
 * @author Veeramani N S
 *
 */

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents output document according to JSON API output specification
 * 
 * @param <T>
 * @author Surath Patnaik
 */
@JsonInclude(Include.NON_NULL)
public class JsonApiWrapper<T> {

	private T data;
	private List<Error> errors;
	private MetaInfo meta;

	private Links links;

	/**
	 *
	 * @param data
	 */
	public JsonApiWrapper(T data) {
		this.data = data;
	}

	/**
	 *
	 * @param data
	 * @param selfUrl
	 */
	public JsonApiWrapper(T data, String selfUrl) {
		this.data = data;
		links = new Links(selfUrl);
	}

	/**
	 *
	 * @param data
	 * @param selfUrl
	 * @param linkObjects
	 */
	public JsonApiWrapper(T data, String selfUrl, List<Link> linkObjects) {
		this.data = data;
		links = new Links(selfUrl, linkObjects);
	}

	/**
	 *
	 * @param error
	 */
	public JsonApiWrapper(Error error) {
		errors = new ArrayList();
		errors.add(error);
	}

	/**
	 *
	 * @param error
	 * @param selfUrl
	 */
	public JsonApiWrapper(List<Error> errorList, String selfUrl) {
		errors = errorList;
		links = new Links(selfUrl);
	}

	/**
	 *
	 * @param error
	 * @param selfUrl
	 */
	public JsonApiWrapper(Error error, String selfUrl) {
		errors = new ArrayList();
		errors.add(error);

		links = new Links(selfUrl);
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the errors
	 */
	public List<Error> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<Error> errors) {
		this.errors = errors;
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
	 * @param e
	 */
	public void addError(Error e) {
		if (errors == null) {
			errors = new ArrayList();
		}

		errors.add(e);
	}

	/**
	 * @return the links
	 */
	public Links getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(Links links) {
		this.links = links;
	}

	/**
	 *
	 * @param link
	 */
	public void addLink(Link link) {
		if (links.getRelated() == null) {
			links.setRelated(new ArrayList<>());
		}

		links.add(link);
	}

	/**
	 *
	 * @param linkObjects
	 */
	public void addLinkObjects(List<Link> linkObjects) {
		if (links.getRelated() == null) {
			links.setRelated(linkObjects);
		}
	}
}
