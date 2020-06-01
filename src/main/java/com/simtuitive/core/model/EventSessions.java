package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventSessions implements Serializable{

	private String sessionName;
	private Date sessiondate;
	private List<EventModules>modules;
	/**
	 * @return the sessionName
	 */
	public String getSessionName() {
		return sessionName;
	}
	/**
	 * @param sessionName the sessionName to set
	 */
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	/**
	 * @return the sessiondate
	 */
	public Date getSessiondate() {
		return sessiondate;
	}
	/**
	 * @param sessiondate the sessiondate to set
	 */
	public void setSessiondate(Date sessiondate) {
		this.sessiondate = sessiondate;
	}
	/**
	 * @return the modules
	 */
	public List<EventModules> getModules() {
		return modules;
	}
	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<EventModules> modules) {
		this.modules = modules;
	}
	public EventSessions(String sessionName, Date sessiondate, List<EventModules> modules) {
		super();
		this.sessionName = sessionName;
		this.sessiondate = sessiondate;
		this.modules = modules;
	}
	@Override
	public String toString() {
		return "EventSessions [sessionName=" + sessionName + ", sessiondate=" + sessiondate + ", modules=" + modules
				+ "]";
	}
	
	
}
