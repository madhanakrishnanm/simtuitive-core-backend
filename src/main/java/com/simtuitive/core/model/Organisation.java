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

	private String organizationName;
	@Id
	private String organizationId;
	private String location;
	private String industry;
	private String dealOwnerName;
	private String dealOwnerEmail;
	private Long dealOwnerMobile;
	private Date createdAt;
	private String status;
	private Long creditLimit;
	private Date updatedAt;
	private String modifiedBy;
	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}
	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	/**
	 * @return the organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
	 * @return the dealOwnerName
	 */
	public String getDealOwnerName() {
		return dealOwnerName;
	}
	/**
	 * @param dealOwnerName the dealOwnerName to set
	 */
	public void setDealOwnerName(String dealOwnerName) {
		this.dealOwnerName = dealOwnerName;
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
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
	public Organisation(String organizationName, String location, String industry,
			String dealOwnerName, String dealOwnerEmail, Long dealOwnerMobile, Date createdAt, String status,
			Long creditLimit, Date updatedAt, String modifiedBy) {
		super();
		this.organizationName = organizationName;		
		this.location = location;
		this.industry = industry;
		this.dealOwnerName = dealOwnerName;
		this.dealOwnerEmail = dealOwnerEmail;
		this.dealOwnerMobile = dealOwnerMobile;
		this.createdAt = createdAt;
		this.status = status;
		this.creditLimit = creditLimit;
		this.updatedAt = updatedAt;
		this.modifiedBy = modifiedBy;
	}
	public Organisation() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Organisation [organizationName=" + organizationName + ", organizationId=" + organizationId
				+ ", location=" + location + ", industry=" + industry + ", dealOwnerName=" + dealOwnerName
				+ ", dealOwnerEmail=" + dealOwnerEmail + ", dealOwnerMobile=" + dealOwnerMobile + ", createdAt="
				+ createdAt + ", status=" + status + ", creditLimit=" + creditLimit + ", updatedAt=" + updatedAt
				+ ", modifiedBy=" + modifiedBy + "]";
	}

}
