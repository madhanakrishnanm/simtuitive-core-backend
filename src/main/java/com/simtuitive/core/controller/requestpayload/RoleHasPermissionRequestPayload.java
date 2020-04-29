package com.simtuitive.core.controller.requestpayload;

public class RoleHasPermissionRequestPayload {
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
	public RoleHasPermissionRequestPayload(String roleid, String permissionid) {
		super();
		this.roleid = roleid;
		this.permissionid = permissionid;
	}
	
	@Override
	public String toString() {
		return "RoleHasPermissionRequestPayload [roleid=" + roleid + ", permissionid=" + permissionid + "]";
	}
	
}
