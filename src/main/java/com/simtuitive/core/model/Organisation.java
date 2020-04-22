package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organisation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orgName;
	@Id
	private String orgId;
	private String location;
	private String industry;
	private String clientDealOwnerName;
	private String clientDealOwnerEmail;
	private Long clientDealOwnerMobile;
	private Date creationDate;
	private String status;
	private Long creditLimit;
	private Date modifiedDate;
	private String modifiedBy;

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the clientDealOwnerName
	 */
	public String getClientDealOwnerName() {
		return clientDealOwnerName;
	}

	/**
	 * @param clientDealOwnerName the clientDealOwnerName to set
	 */
	public void setClientDealOwnerName(String clientDealOwnerName) {
		this.clientDealOwnerName = clientDealOwnerName;
	}

	/**
	 * @return the clientDealOwnerEmail
	 */
	public String getClientDealOwnerEmail() {
		return clientDealOwnerEmail;
	}

	/**
	 * @param clientDealOwnerEmail the clientDealOwnerEmail to set
	 */
	public void setClientDealOwnerEmail(String clientDealOwnerEmail) {
		this.clientDealOwnerEmail = clientDealOwnerEmail;
	}

	/**
	 * @return the clientDealOwnerMobile
	 */
	public Long getClientDealOwnerMobile() {
		return clientDealOwnerMobile;
	}

	/**
	 * @param clientDealOwnerMobile the clientDealOwnerMobile to set
	 */
	public void setClientDealOwnerMobile(Long clientDealOwnerMobile) {
		this.clientDealOwnerMobile = clientDealOwnerMobile;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the creditLimit
	 */
	public Long getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Organisation() {

		// TODO Auto-generated constructor stub
	}

	public Organisation(String orgName, String location, String industry, String clientDealOwnerName,
			String clientDealOwnerEmail, Long clientDealOwnerMobile, Date creationDate, String status, Long creditLimit,
			Date modifiedDate, String modifiedBy) {
		super();
		this.orgName = orgName;
		this.location = location;
		this.industry = industry;
		this.clientDealOwnerName = clientDealOwnerName;
		this.clientDealOwnerEmail = clientDealOwnerEmail;
		this.clientDealOwnerMobile = clientDealOwnerMobile;
		this.creationDate = creationDate;
		this.status = status;
		this.creditLimit = creditLimit;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}

}
