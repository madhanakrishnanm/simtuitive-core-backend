package com.simtuitive.core.controller.requestpayload;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class OrganisationRequestPayload implements Serializable {
	private String orgName;	
	private String orgId;
	private String location;
	private String industry;
	private String clientDealOwnerName;
	private String clientDealOwnerEmail;
	private Long clientDealOwnerMobile;	
	private Long status;
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
	public OrganisationRequestPayload(String orgName, String orgId, String location, String industry,
			String clientDealOwnerName, String clientDealOwnerEmail, Long clientDealOwnerMobile, Long status,
			Long creditLimit, String modifiedBy) {
		super();
		this.orgName = orgName;
		this.orgId = orgId;
		this.location = location;
		this.industry = industry;
		this.clientDealOwnerName = clientDealOwnerName;
		this.clientDealOwnerEmail = clientDealOwnerEmail;
		this.clientDealOwnerMobile = clientDealOwnerMobile;
		this.status = status;
		this.creditLimit = creditLimit;
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "OrganisationRequestPayload [orgName=" + orgName + ", orgId=" + orgId + ", location=" + location
				+ ", industry=" + industry + ", clientDealOwnerName=" + clientDealOwnerName + ", clientDealOwnerEmail="
				+ clientDealOwnerEmail + ", clientDealOwnerMobile=" + clientDealOwnerMobile + ", status=" + status
				+ ", creditLimit=" + creditLimit + ", modifiedBy=" + modifiedBy + "]";
	}
	
}