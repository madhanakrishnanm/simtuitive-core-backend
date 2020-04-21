package com.simtuitive.core.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleHasPermission {

	private String roleid;
	private String permissionid;
	/**
	 * @return the roleid
	 */
	public String getRoleid() {
		return roleid;
	}
	/**
	 * @param roleid the roleid to set
	 */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	/**
	 * @return the permissionid
	 */
	public String getPermissionid() {
		return permissionid;
	}
	/**
	 * @param permissionid the permissionid to set
	 */
	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}
	public RoleHasPermission(String roleid, String permissionid) {
		super();
		this.roleid = roleid;
		this.permissionid = permissionid;
	}
	public RoleHasPermission() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RoleHasPermission [roleid=" + roleid + ", permissionid=" + permissionid + "]";
	}
	
}
