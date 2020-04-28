package com.simtuitive.core.controller.requestpayload;

import java.util.Date;

public class RolesRequestPayload {
	private String roleid;
	private String rolename;
	private String description;	
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
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
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

	public RolesRequestPayload(String roleid, String rolename, String description) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.description = description;
		
	}

	@Override
	public String toString() {
		return "RolesRequestPayload [roleid=" + roleid + ", rolename=" + rolename + ", description=" + description
				+ "]";
	}

	

}
