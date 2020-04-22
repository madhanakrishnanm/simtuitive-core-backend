package com.simtuitive.core.controller.requestpayload;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class OrganisationRequestPayload implements Serializable {
	private String orgName;	
	private String orgId;
	private String location;
	private String industry;
	private String dealOwner;
	private String dealOwnerEmail;
	private Long dealOwnerMobile;	
	private String status;
	private Long creditLimit;	
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
	 * @return the dealOwner
	 */
	public String getDealOwner() {
		return dealOwner;
	}
	/**
	 * @param dealOwner the dealOwner to set
	 */
	public void setDealOwner(String dealOwner) {
		this.dealOwner = dealOwner;
	}
	/**
	 * @return the dealOwnerEmail
	 */
	public String getDealOwnerEmail() {
		return dealOwnerEmail;
	}
	/**
	 * @param dealOwnerEmail the dealOwnerEmail to set
	 */
	public void setDealOwnerEmail(String dealOwnerEmail) {
		this.dealOwnerEmail = dealOwnerEmail;
	}
	/**
	 * @return the dealOwnerMobile
	 */
	public Long getDealOwnerMobile() {
		return dealOwnerMobile;
	}
	/**
	 * @param dealOwnerMobile the dealOwnerMobile to set
	 */
	public void setDealOwnerMobile(Long dealOwnerMobile) {
		this.dealOwnerMobile = dealOwnerMobile;
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
	public OrganisationRequestPayload(String orgName, String orgId, String location, String industry, String dealOwner,
			String dealOwnerEmail, Long dealOwnerMobile, String status, Long creditLimit, String modifiedBy) {
		super();
		this.orgName = orgName;
		this.orgId = orgId;
		this.location = location;
		this.industry = industry;
		this.dealOwner = dealOwner;
		this.dealOwnerEmail = dealOwnerEmail;
		this.dealOwnerMobile = dealOwnerMobile;
		this.status = status;
		this.creditLimit = creditLimit;
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "OrganisationRequestPayload [orgName=" + orgName + ", orgId=" + orgId + ", location=" + location
				+ ", industry=" + industry + ", dealOwner=" + dealOwner + ", dealOwnerEmail=" + dealOwnerEmail
				+ ", dealOwnerMobile=" + dealOwnerMobile + ", status=" + status + ", creditLimit=" + creditLimit
				+ ", modifiedBy=" + modifiedBy + "]";
	}
		
}