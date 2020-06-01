package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventSessions implements Serializable{

	private String name;
	private Date date;
	private List<EventModules>modules;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	public EventSessions(String name, Date date, List<EventModules> modules) {
		super();
		this.name = name;
		this.date = date;
		this.modules = modules;
	}
	@Override
	public String toString() {
		return "EventSessions [name=" + name + ", date=" + date + ", modules=" + modules + "]";
	}
		
	
}
