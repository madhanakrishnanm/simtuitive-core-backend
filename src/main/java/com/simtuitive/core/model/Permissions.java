/**
 * 
 */
package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Veeramani N S
 *
 */

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permissions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String permissionId;
	private String name;
	private String type;
	private String description;
	private Date createdOn;
	private Date modifiedOn;
	
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
	
	public Permissions( String name, String type, String description, Date createdOn,
			Date modifiedOn, int rank) {
		super();
		
		this.name = name;
		this.type = type;
		this.description = description;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.rank = rank;
	}
	public Permissions() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Permissions [permissionId=" + permissionId + ", name=" + name + ", type=" + type + ", description="
				+ description + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", rank=" + rank + "]";
	}
	
	
	
	
}
