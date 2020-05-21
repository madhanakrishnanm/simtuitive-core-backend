package com.simtuitive.core.controller.responsepayload;

import java.math.BigDecimal;
import java.util.Date;

public class LicenseResponsePayload {
	private String licenseId;
	private String organization;
	private String orgId;
	private String productName;
	private String productId;
	private String  paymentStatus;	
	private int creditLimit ;	
	private String narration;
	private BigDecimal sellingPrice;
	private BigDecimal dealSize;
	private Long numberOfLicense;
	private Date createdAt;
	private Date modifiedAt;
	private String createdBy;
	private String modifiedBy;
	private String status;
	/**
	 * @return the licenseId
	 */
	public String getLicenseId() {
		return licenseId;
	}
	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}
	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the creditLimit
	 */
	public int getCreditLimit() {
		return creditLimit;
	}
	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	/**
	 * @return the narration
	 */
	public String getNarration() {
		return narration;
	}
	/**
	 * @param narration the narration to set
	 */
	public void setNarration(String narration) {
		this.narration = narration;
	}
	/**
	 * @return the sellingPrice
	 */
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	/**
	 * @return the dealSize
	 */
	public BigDecimal getDealSize() {
		return dealSize;
	}
	/**
	 * @param dealSize the dealSize to set
	 */
	public void setDealSize(BigDecimal dealSize) {
		this.dealSize = dealSize;
	}
	/**
	 * @return the numberOfLicense
	 */
	public Long getNumberOfLicense() {
		return numberOfLicense;
	}
	/**
	 * @param numberOfLicense the numberOfLicense to set
	 */
	public void setNumberOfLicense(Long numberOfLicense) {
		this.numberOfLicense = numberOfLicense;
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
	 * @return the modifiedAt
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}
	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	public LicenseResponsePayload(String licenseId, String organization, String orgId, String productName,
			String productId, String paymentStatus, int creditLimit, String narration, BigDecimal sellingPrice,
			BigDecimal dealSize, Long numberOfLicense, Date createdAt, Date modifiedAt, String createdBy,
			String modifiedBy,String status) {
		super();
		this.licenseId = licenseId;
		this.organization = organization;
		this.orgId = orgId;
		this.productName = productName;
		this.productId = productId;
		this.paymentStatus = paymentStatus;
		this.creditLimit = creditLimit;
		this.narration = narration;
		this.sellingPrice = sellingPrice;
		this.dealSize = dealSize;
		this.numberOfLicense = numberOfLicense;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.status = status;
	}
	@Override
	public String toString() {
		return "LicenseResponsePayload [licenseId=" + licenseId + ", organization=" + organization + ", orgId=" + orgId
				+ ", productName=" + productName + ", productId=" + productId + ", paymentStatus=" + paymentStatus
				+ ", creditLimit=" + creditLimit + ", narration=" + narration + ", sellingPrice=" + sellingPrice
				+ ", dealSize=" + dealSize + ", numberOfLicense=" + numberOfLicense + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}
	
	
}
