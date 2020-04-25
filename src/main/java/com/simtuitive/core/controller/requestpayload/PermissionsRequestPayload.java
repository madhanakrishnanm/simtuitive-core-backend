package com.simtuitive.core.controller.requestpayload;

import java.io.Serializable;
import java.util.List;



public class PermissionsRequestPayload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4918027656475893756L;
	private String permissionId;
	private String name;
	private String type;
	private String description;	
	private List<String> roleids;
	private int rank;
	
	
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * @return the permissionId
	 */
	public String getPermissionId() {
		return permissionId;
	}
	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	/**
	 * @return the roleids
	 */
	public List<String> getRoleids() {
		return roleids;
	}
	/**
	 * @param roleids the roleids to set
	 */
	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}
	public PermissionsRequestPayload(String permissionId, String name, String type, String description,
			List<String> roleids, int rank) {
		super();
		this.permissionId = permissionId;
		this.name = name;
		this.type = type;
		this.description = description;
		this.roleids = roleids;
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "PermissionsRequestPayload [permissionId=" + permissionId + ", name=" + name + ", type=" + type
				+ ", description=" + description + ", roleids=" + roleids + ", rank=" + rank + "]";
	}
	
	
	
}
