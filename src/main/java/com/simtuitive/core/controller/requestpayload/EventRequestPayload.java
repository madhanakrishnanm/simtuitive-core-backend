/**
 * 
 */
package com.simtuitive.core.controller.requestpayload;

import java.util.Date;
import java.util.List;

import com.simtuitive.core.controller.responsepayload.OrganisationResponsePayload;
import com.simtuitive.core.controller.responsepayload.UserResponsePayload;
import com.simtuitive.core.model.EventModules;
import com.simtuitive.core.model.EventSessions;
import com.simtuitive.core.model.Organisation;

/**
 * @author Veeramani N S
 *
 */
public class EventRequestPayload {
	public String id;
	public String orgId;
	public String orgName;
	public UserResponsePayload client;
	public String clientName;
	public String productId;
	public String productName;
	public String name;
	public String noOfParticipants;
	public String tollGate;
	public String startDate;
	public String endDate;
	public OrganisationResponsePayload organization;
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
	 * @return the client
	 */
	public UserResponsePayload getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(UserResponsePayload client) {
		this.client = client;
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
	 * @return the tollGate
	 */
	public String getTollGate() {
		return tollGate;
	}
	/**
	 * @param tollGate the tollGate to set
	 */
	public void setTollGate(String tollGate) {
		this.tollGate = tollGate;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the organization
	 */
	public OrganisationResponsePayload getOrganization() {
		return organization;
	}
	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(OrganisationResponsePayload organization) {
		this.organization = organization;
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
	public EventRequestPayload(String id, String orgId, String orgName, UserResponsePayload client, String clientName,
			String productId, String productName, String name, String noOfParticipants, String tollGate,
			String startDate, String endDate, OrganisationResponsePayload organization, String notes, Date createdAt,
			String status, Date updatedAt, String modifiedBy, String createdBy, String type,
			List<EventSessions> sessions, String bookingStatus) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.orgName = orgName;
		this.client = client;
		this.clientName = clientName;
		this.productId = productId;
		this.productName = productName;
		this.name = name;
		this.noOfParticipants = noOfParticipants;
		this.tollGate = tollGate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organization = organization;
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
		return "EventRequestPayload [id=" + id + ", orgId=" + orgId + ", orgName=" + orgName + ", client=" + client
				+ ", clientName=" + clientName + ", productId=" + productId + ", productName=" + productName + ", name="
				+ name + ", noOfParticipants=" + noOfParticipants + ", tollGate=" + tollGate + ", startDate="
				+ startDate + ", endDate=" + endDate + ", organization=" + organization + ", notes=" + notes
				+ ", createdAt=" + createdAt + ", status=" + status + ", updatedAt=" + updatedAt + ", modifiedBy="
				+ modifiedBy + ", createdBy=" + createdBy + ", type=" + type + ", sessions=" + sessions
				+ ", bookingStatus=" + bookingStatus + "]";
	}
	
	
		
}
