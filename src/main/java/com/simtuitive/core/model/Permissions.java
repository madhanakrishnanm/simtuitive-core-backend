/**
 * 
 */
package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Veeramani N S
 *
 */

@Document
public class Permissions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> tabs;

	/**
	 * @return the tabs
	 */
	public List<String> getTabs() {
		return tabs;
	}

	/**
	 * @param tabs the tabs to set
	 */
	public void setTabs(List<String> tabs) {
		this.tabs = tabs;
	}

	public Permissions(List<String> tabs) {

		this.tabs = tabs;

	}

	public Permissions() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Permissions [tabs=" + tabs + "]";
	}

}
