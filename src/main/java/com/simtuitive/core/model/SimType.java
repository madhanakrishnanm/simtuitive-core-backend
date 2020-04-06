package com.simtuitive.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SimType {
	
	@Id
	private String simTypeId;
	private String simTypeName;
	/**
	 * @return the simTypeId
	 */
	public String getSimTypeId() {
		return simTypeId;
	}
	/**
	 * @param simTypeId the simTypeId to set
	 */
	public void setSimTypeId(String simTypeId) {
		this.simTypeId = simTypeId;
	}
	/**
	 * @return the simTypeName
	 */
	public String getSimTypeName() {
		return simTypeName;
	}
	/**
	 * @param simTypeName the simTypeName to set
	 */
	public void setSimTypeName(String simTypeName) {
		this.simTypeName = simTypeName;
	}
	public SimType(String simTypeId, String simTypeName) {
		super();
		this.simTypeId = simTypeId;
		this.simTypeName = simTypeName;
	}
	@Override
	public String toString() {
		return "SimType [simTypeId=" + simTypeId + ", simTypeName=" + simTypeName + "]";
	}
	
}
