/**
 * 
 */
package com.simtuitive.core.controller.requestpayload;

import java.util.Date;
import java.util.List;

import com.simtuitive.core.model.EventModules;
import com.simtuitive.core.model.EventSessions;

/**
 * @author Veeramani N S
 *
 */
public class EventRequestPayload {
	public String id;
	public String orgId;
	public String orgName;
	public String clientId;
	public String clientName;
	public String productId;
	public String productName;
	public String eventName;
	public String noOfParticipants;
	public String tollPass;
	public Date startDate;
	public Date endDate;
	public String notes;
	private Date createdAt;
	private String status;	
	private Date updatedAt;
	private String modifiedBy;
	private String createdBy;
	private String type;
	private List<EventSessions> sessions;
	private String bookingStatus;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the noOfParticipants
	 */
	public String getNoOfParticipants() {
		return noOfParticipants;
	}
	/**
	 * @param noOfParticipants the noOfParticipants to set
	 */
	public void setNoOfParticipants(String noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
	}
	/**
	 * @return the tollPass
	 */
	public String getTollPass() {
		return tollPass;
	}
	/**
	 * @param tollPass the tollPass to set
	 */
	public void setTollPass(String tollPass) {
		this.tollPass = tollPass;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * @return the sessions
	 */
	public List<EventSessions> getSessions() {
		return sessions;
	}
	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(List<EventSessions> sessions) {
		this.sessions = sessions;
	}
	/**
	 * @return the bookingStatus
	 */
	public String getBookingStatus() {
		return bookingStatus;
	}
	/**
	 * @param bookingStatus the bookingStatus to set
	 */
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public EventRequestPayload(String id, String orgId, String orgName, String clientId, String clientName,
			String productId, String productName, String eventName, String noOfParticipants, String tollPass,
			Date startDate, Date endDate, String notes, Date createdAt, String status, Date updatedAt,
			String modifiedBy, String createdBy, String type, List<EventSessions> sessions, String bookingStatus) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.orgName = orgName;
		this.clientId = clientId;
		this.clientName = clientName;
		this.productId = productId;
		this.productName = productName;
		this.eventName = eventName;
		this.noOfParticipants = noOfParticipants;
		this.tollPass = tollPass;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notes = notes;
		this.createdAt = createdAt;
		this.status = status;
		this.updatedAt = updatedAt;
		this.modifiedBy = modifiedBy;
		this.createdBy = createdBy;
		this.type = type;
		this.sessions = sessions;
		this.bookingStatus = bookingStatus;
	}
	@Override
	public String toString() {
		return "EventRequestPayload [id=" + id + ", orgId=" + orgId + ", orgName=" + orgName + ", clientId=" + clientId
				+ ", clientName=" + clientName + ", productId=" + productId + ", productName=" + productName
				+ ", eventName=" + eventName + ", noOfParticipants=" + noOfParticipants + ", tollPass=" + tollPass
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", notes=" + notes + ", createdAt=" + createdAt
				+ ", status=" + status + ", updatedAt=" + updatedAt + ", modifiedBy=" + modifiedBy + ", createdBy="
				+ createdBy + ", type=" + type + ", sessions=" + sessions + ", bookingStatus=" + bookingStatus + "]";
	}	
	
}
