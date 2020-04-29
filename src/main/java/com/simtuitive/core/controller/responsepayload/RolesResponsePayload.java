package com.simtuitive.core.controller.responsepayload;

import java.util.Date;

public class RolesResponsePayload {
	private String roleId;
	private String roleName;
	private String description;
	private Date createdOn;
	private Date modifiedOn;
	private Long status;
	
	
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
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
	
	public RolesResponsePayload(String roleId, String roleName, String description, Date createdOn, Date modifiedOn,Long status) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.status = status;
	}
	@Override
	public String toString() {
		return "RolesResponsePayload [roleid=" + roleId + ", rolename=" + roleName + ", description=" + description
				+ ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", status=" + status + "]";
	}
	
	
	
}
