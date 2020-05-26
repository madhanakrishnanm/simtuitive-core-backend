package com.simtuitive.core.model;

/**
 * 
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Veeramani N S
 *
 */
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements GrantedAuthority, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String userId;
	private String userName;
	@Email
	private String userEmail;
	private String orgId;
	private String orgName;
	private String password;
	private Long status;// 1-active,2-inactive,3-suspend
	private String clientDealOwner;
	private Date createdDate;
	private String clientLocation;
	private String clientGst;
	private String clientPan;
	private List<User> clientSpoc;
	private String simEventName;
	private String smeAssigned;
	private Long noOfMilestone;
	private Long noOfMilestoneAttended;
	private Long noOfMilestoneCompleted;
	private List<Permissions> permissions;
	private String role;
	private Date lastLoggedIn;
	private String billingAddress;

	/**
	 * @return the lastLoggedIn
	 */
	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	/**
	 * @param lastLoggedIn the lastLoggedIn to set
	 */
	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the clientDealOwner
	 */
	public String getClientDealOwner() {
		return clientDealOwner;
	}

	/**
	 * @param clientDealOwner the clientDealOwner to set
	 */
	public void setClientDealOwner(String clientDealOwner) {
		this.clientDealOwner = clientDealOwner;
	}

	/**
	 * 
	 * /**
	 * 
	 * @return the clientLocation
	 */
	public String getClientLocation() {
		return clientLocation;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @param clientLocation the clientLocation to set
	 */
	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
	}

	/**
	 * @return the clientGst
	 */
	public String getClientGst() {
		return clientGst;
	}

	/**
	 * @param clientGst the clientGst to set
	 */
	public void setClientGst(String clientGst) {
		this.clientGst = clientGst;
	}

	/**
	 * @return the clientPan
	 */
	public String getClientPan() {
		return clientPan;
	}

	/**
	 * @param clientPan the clientPan to set
	 */
	public void setClientPan(String clientPan) {
		this.clientPan = clientPan;
	}

	/**
	 * @return the clientSpoc
	 */
	public List<User> getClientSpoc() {
		return clientSpoc;
	}

	/**
	 * @param clientSpoc the clientSpoc to set
	 */
	public void setClientSpoc(List<User> clientSpoc) {
		this.clientSpoc = clientSpoc;
	}

	/**
	 * @return the simEventName
	 */
	public String getSimEventName() {
		return simEventName;
	}

	/**
	 * @param simEventName the simEventName to set
	 */
	public void setSimEventName(String simEventName) {
		this.simEventName = simEventName;
	}

	/**
	 * @return the smeAssigned
	 */
	public String getSmeAssigned() {
		return smeAssigned;
	}

	/**
	 * @param smeAssigned the smeAssigned to set
	 */
	public void setSmeAssigned(String smeAssigned) {
		this.smeAssigned = smeAssigned;
	}

	/**
	 * @return the noOfMilestone
	 */
	public Long getNoOfMilestone() {
		return noOfMilestone;
	}

	/**
	 * @param noOfMilestone the noOfMilestone to set
	 */
	public void setNoOfMilestone(Long noOfMilestone) {
		this.noOfMilestone = noOfMilestone;
	}

	/**
	 * @return the noOfMilestoneAttended
	 */
	public Long getNoOfMilestoneAttended() {
		return noOfMilestoneAttended;
	}

	/**
	 * @param noOfMilestoneAttended the noOfMilestoneAttended to set
	 */
	public void setNoOfMilestoneAttended(Long noOfMilestoneAttended) {
		this.noOfMilestoneAttended = noOfMilestoneAttended;
	}

	/**
	 * @return the noOfMilestoneCompleted
	 */
	public Long getNoOfMilestoneCompleted() {
		return noOfMilestoneCompleted;
	}

	/**
	 * @param noOfMilestoneCompleted the noOfMilestoneCompleted to set
	 */
	public void setNoOfMilestoneCompleted(Long noOfMilestoneCompleted) {
		this.noOfMilestoneCompleted = noOfMilestoneCompleted;
	}

	/**
	 * @return the permissions
	 */
	public List<Permissions> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<Permissions> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

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
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	// client specific
	public User(String userName, String userEmail, String orgId, String orgName, String password, Long status,
			Date createdDate, List<Permissions> permissions, String role, Date lastLoggedIn, String billingAddress) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.orgId = orgId;
		this.orgName = orgName;
		this.password = password;
		this.status = status;
		this.createdDate = createdDate;
		this.permissions = permissions;
		this.role = role;
		this.billingAddress = billingAddress;
		this.lastLoggedIn = lastLoggedIn;
	}

	// learner specific
	public User(String userName, String userEmail, String orgId, String password, Long status, Date createdDate,
			String simEventName, String smeAssigned, Long noOfMilestone, Long noOfMilestoneAttended,
			Long noOfMilestoneCompleted, List<Permissions> permissions, String role) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.orgId = orgId;
		this.password = password;
		this.status = status;
		this.createdDate = createdDate;
		this.simEventName = simEventName;
		this.smeAssigned = smeAssigned;
		this.noOfMilestone = noOfMilestone;
		this.noOfMilestoneAttended = noOfMilestoneAttended;
		this.noOfMilestoneCompleted = noOfMilestoneCompleted;
		this.permissions = permissions;
		this.role = role;
	}

	public User(String userName, String userEmail, String password, Long status, List<Permissions> permissions,
			String role, Date lastLoggedIn) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.status = status;
		this.permissions = permissions;
		this.role = role;
		this.lastLoggedIn = lastLoggedIn;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", orgId=" + orgId
				+ ", password=" + password + ", status=" + status + ", clientDealOwner=" + clientDealOwner
				+ ", createdDate=" + createdDate + ", clientLocation=" + clientLocation + ", clientGst=" + clientGst
				+ ", clientPan=" + clientPan + ", clientSpoc=" + clientSpoc + ", simEventName=" + simEventName
				+ ", smeAssigned=" + smeAssigned + ", noOfMilestone=" + noOfMilestone + ", noOfMilestoneAttended="
				+ noOfMilestoneAttended + ", noOfMilestoneCompleted=" + noOfMilestoneCompleted + ", permissions="
				+ permissions + ", role=" + role + "]";
	}

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	public User(User user) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}
}
