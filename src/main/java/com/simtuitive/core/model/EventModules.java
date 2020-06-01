package com.simtuitive.core.model;

import java.io.Serializable;

public class EventModules implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String moduleName;
	public String moduleNumber;
	
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * @return the before
	 */
	/**
	 * @return the moduleNumber
	 */
	public String getModuleNumber() {
		return moduleNumber;
	}
	/**
	 * @param moduleNumber the moduleNumber to set
	 */
	public void setModuleNumber(String moduleNumber) {
		this.moduleNumber = moduleNumber;
	}
	public EventModules(String moduleName, String moduleNumber) {
		super();
		this.moduleName = moduleName;
		this.moduleNumber = moduleNumber;
	}
	@Override
	public String toString() {
		return "EventModules [moduleName=" + moduleName + ", moduleNumber=" + moduleNumber + "]";
	}
	
	
	
	
}
