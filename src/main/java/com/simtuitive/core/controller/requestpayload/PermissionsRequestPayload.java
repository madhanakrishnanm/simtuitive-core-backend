package com.simtuitive.core.controller.requestpayload;

import java.io.Serializable;
import java.util.List;

import com.simtuitive.core.model.Tabs;

public class PermissionsRequestPayload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4918027656475893756L;
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
	public PermissionsRequestPayload(List<String> tabs, List<String> learners) {
		super();
		this.tabs = tabs;
		Learners = learners;
	}
	public PermissionsRequestPayload() {
		
		// TODO Auto-generated constructor stub
	}	
	
}
