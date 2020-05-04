package com.simtuitive.core.model;

import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RedisHash("SessionInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionInfo {
	private String emailId;
    private String role;
    private String sessionId;  
    private Date sessionCreatedTime;
    private Date lastAccessTime;
    private Date sessionExpiryTime;
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the sessionCreatedTime
	 */
	public Date getSessionCreatedTime() {
		return sessionCreatedTime;
	}
	/**
	 * @param sessionCreatedTime the sessionCreatedTime to set
	 */
	public void setSessionCreatedTime(Date sessionCreatedTime) {
		this.sessionCreatedTime = sessionCreatedTime;
	}
	/**
	 * @return the lastAccessTime
	 */
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	/**
	 * @param lastAccessTime the lastAccessTime to set
	 */
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	/**
	 * @return the sessionExpiryTime
	 */
	public Date getSessionExpiryTime() {
		return sessionExpiryTime;
	}
	/**
	 * @param sessionExpiryTime the sessionExpiryTime to set
	 */
	public void setSessionExpiryTime(Date sessionExpiryTime) {
		this.sessionExpiryTime = sessionExpiryTime;
	}
	
	
	public SessionInfo(String emailId, String role, String sessionId, Date sessionCreatedTime, Date lastAccessTime,
			Date sessionExpiryTime) {
		super();
		this.emailId = emailId;
		this.role = role;
		this.sessionId = sessionId;
		this.sessionCreatedTime = sessionCreatedTime;
		this.lastAccessTime = lastAccessTime;
		this.sessionExpiryTime = sessionExpiryTime;
	}
	public SessionInfo() {
	
		// TODO Auto-generated constructor stub
	}
    
    
}
