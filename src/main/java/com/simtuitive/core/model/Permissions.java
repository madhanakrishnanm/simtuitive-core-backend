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
public class Permissions implements   Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private List<String> tabs;
	private List<String> Learners;
	
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
	/**
	 * @return the learners
	 */
	public List<String> getLearners() {
		return Learners;
	}
	/**
	 * @param learners the learners to set
	 */
	public void setLearners(List<String> learners) {
		Learners = learners;
	}
	public Permissions(List<String> tabs, List<String> learners) {		
		
		this.tabs = tabs;
		Learners = learners;
	}
	public Permissions() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Permissions [tabs=" + tabs + ", Learners=" + Learners + "]";
	}
	
	
	
}
