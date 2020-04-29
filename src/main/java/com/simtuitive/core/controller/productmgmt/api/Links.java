package com.simtuitive.core.controller.productmgmt.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents links section of JSON API output specification
 * 
 * @author Surath Patnaik
 */
@JsonInclude(Include.NON_NULL)
public class Links {

	// for now we will send only one link
	private String self;
	// @todo custom serializer to be written to support this
	private List<Link> related;

	/**
	 * 
	 * @param self
	 */
	public Links(String self) {
		this.self = self;
	}

	/**
	 *
	 * @param self
	 * @param linkObjects
	 */
	public Links(String self, List<Link> linkObjects) {
		this.self = self;
		this.related = linkObjects;
	}

	/**
	 * @return the self
	 */
	public String getSelf() {
		return self;
	}

	/**
	 * @param self the self to set
	 */
	public void setSelf(String self) {
		this.self = self;
	}

	/**
	 * @return the related
	 */
	public List<Link> getRelated() {
		return related;
	}

	/**
	 * @param related the related to set
	 */
	public void setRelated(List<Link> related) {
		this.related = related;
	}

	/**
	 *
	 * @param link
	 */
	public void add(Link link) {
		if (related == null) {
			related = new ArrayList<>();
		}

		related.add(link);
	}
}
