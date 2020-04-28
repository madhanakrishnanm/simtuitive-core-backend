package com.simtuitive.core.controller.responsepayload;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.simtuitive.core.model.Roles;



public class PermissionsResponsePayload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4918027656475893756L;
	private String permissionId;
	private String name;
	private String type;
	private String description;
	private Date createdOn;
	private Date modifiedOn;
	private Long status;
	private int rank;
	private List<Roles> roles;
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
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}
	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}
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
	 * @return the roles
	 */
	public List<Roles> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	public PermissionsResponsePayload(String permissionId, String name, String type, String description, Date createdOn,
			Date modifiedOn, Long status, int rank, List<Roles> roles) {
		super();
		this.permissionId = permissionId;
		this.name = name;
		this.type = type;
		this.description = description;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.status = status;
		this.rank = rank;
		this.roles = roles;
	}
	
	public PermissionsResponsePayload() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PermissionsResponsePayload [permissionId=" + permissionId + ", name=" + name + ", type=" + type
				+ ", description=" + description + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", status=" + status + ", rank=" + rank + ", roles=" + roles + "]";
	}
	
	
		
}
