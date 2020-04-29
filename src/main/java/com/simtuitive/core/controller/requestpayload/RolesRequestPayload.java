package com.simtuitive.core.controller.requestpayload;

public class RolesRequestPayload {
	private String roleId;
	private String roleName;
	private String description;
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
	public RolesRequestPayload(String roleId, String roleName, String description) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
	}
	@Override
	public String toString() {
		return "RolesRequestPayload [roleId=" + roleId + ", roleName=" + roleName + ", description=" + description
				+ "]";
	}	
	
	
	

}
