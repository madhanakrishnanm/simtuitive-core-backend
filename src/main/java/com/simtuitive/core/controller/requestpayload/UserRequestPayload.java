package com.simtuitive.core.controller.requestpayload;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;

import com.simtuitive.core.model.User;
import com.simtuitive.core.model.Permissions;

public class UserRequestPayload implements Serializable {
	/**
	 * 
	 */
	private String userId;
	private String name;	
	private String email;
	private String clientOrgname;
	private String password;
	private Long status;
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
	private String userType;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the clientOrgname
	 */
	public String getClientOrgname() {
		return clientOrgname;
	}
	/**
	 * @param clientOrgname the clientOrgname to set
	 */
	public void setClientOrgname(String clientOrgname) {
		this.clientOrgname = clientOrgname;
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
	 * @return the clientLocation
	 */
	public String getClientLocation() {
		return clientLocation;
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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public UserRequestPayload(String userId, String name, String email, String clientOrgname, String password,
			Long status, String clientDealOwner, Date createdDate, String clientLocation, String clientGst,
			String clientPan, List<User> clientSpoc, String simEventName, String smeAssigned, Long noOfMilestone,
			Long noOfMilestoneAttended, Long noOfMilestoneCompleted, List<Permissions> permissions, String role,
			String userType) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.clientOrgname = clientOrgname;
		this.password = password;
		this.status = status;
		this.clientDealOwner = clientDealOwner;
		this.createdDate = createdDate;
		this.clientLocation = clientLocation;
		this.clientGst = clientGst;
		this.clientPan = clientPan;
		this.clientSpoc = clientSpoc;
		this.simEventName = simEventName;
		this.smeAssigned = smeAssigned;
		this.noOfMilestone = noOfMilestone;
		this.noOfMilestoneAttended = noOfMilestoneAttended;
		this.noOfMilestoneCompleted = noOfMilestoneCompleted;
		this.permissions = permissions;
		this.role = role;
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "UserRequestPayload [userId=" + userId + ", name=" + name + ", email=" + email + ", clientOrgname="
				+ clientOrgname + ", password=" + password + ", status=" + status + ", clientDealOwner="
				+ clientDealOwner + ", createdDate=" + createdDate + ", clientLocation=" + clientLocation
				+ ", clientGst=" + clientGst + ", clientPan=" + clientPan + ", clientSpoc=" + clientSpoc
				+ ", simEventName=" + simEventName + ", smeAssigned=" + smeAssigned + ", noOfMilestone=" + noOfMilestone
				+ ", noOfMilestoneAttended=" + noOfMilestoneAttended + ", noOfMilestoneCompleted="
				+ noOfMilestoneCompleted + ", permissions=" + permissions + ", role=" + role + ", userType=" + userType
				+ "]";
	}

	
}