package com.simtuitive.core.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WorkOrder {
	@Id
	private String workId;
	private String workClientName;
	private Date workStartDate;
	private Date workEndDate; 
	private Long noOfLearners;
	private Long noOfSessions;
	private String notesForAdmin;
	private String simName;
	private List<SimType> simType;
	private Date workReportDate;
	private Long workStatus;//confirm=2,pending=1,cancelled=4
	private Long workMinimumPack;
	private Date workCreatedDate;
	/**
	 * @return the workId
	 */
	public String getWorkId() {
		return workId;
	}
	/**
	 * @param workId the workId to set
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	/**
	 * @return the workClientName
	 */
	public String getWorkClientName() {
		return workClientName;
	}
	/**
	 * @param workClientName the workClientName to set
	 */
	public void setWorkClientName(String workClientName) {
		this.workClientName = workClientName;
	}
	/**
	 * @return the workStartDate
	 */
	public Date getWorkStartDate() {
		return workStartDate;
	}
	/**
	 * @param workStartDate the workStartDate to set
	 */
	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}
	/**
	 * @return the workEndDate
	 */
	public Date getWorkEndDate() {
		return workEndDate;
	}
	/**
	 * @param workEndDate the workEndDate to set
	 */
	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}
	/**
	 * @return the noOfLearners
	 */
	public Long getNoOfLearners() {
		return noOfLearners;
	}
	/**
	 * @param noOfLearners the noOfLearners to set
	 */
	public void setNoOfLearners(Long noOfLearners) {
		this.noOfLearners = noOfLearners;
	}
	/**
	 * @return the noOfSessions
	 */
	public Long getNoOfSessions() {
		return noOfSessions;
	}
	/**
	 * @param noOfSessions the noOfSessions to set
	 */
	public void setNoOfSessions(Long noOfSessions) {
		this.noOfSessions = noOfSessions;
	}
	/**
	 * @return the notesForAdmin
	 */
	public String getNotesForAdmin() {
		return notesForAdmin;
	}
	/**
	 * @param notesForAdmin the notesForAdmin to set
	 */
	public void setNotesForAdmin(String notesForAdmin) {
		this.notesForAdmin = notesForAdmin;
	}
	/**
	 * @return the simName
	 */
	public String getSimName() {
		return simName;
	}
	/**
	 * @param simName the simName to set
	 */
	public void setSimName(String simName) {
		this.simName = simName;
	}
	/**
	 * @return the simType
	 */
	public List<SimType> getSimType() {
		return simType;
	}
	/**
	 * @param simType the simType to set
	 */
	public void setSimType(List<SimType> simType) {
		this.simType = simType;
	}
	/**
	 * @return the workReportDate
	 */
	public Date getWorkReportDate() {
		return workReportDate;
	}
	/**
	 * @param workReportDate the workReportDate to set
	 */
	public void setWorkReportDate(Date workReportDate) {
		this.workReportDate = workReportDate;
	}
	/**
	 * @return the workStatus
	 */
	public Long getWorkStatus() {
		return workStatus;
	}
	/**
	 * @param workStatus the workStatus to set
	 */
	public void setWorkStatus(Long workStatus) {
		this.workStatus = workStatus;
	}
	/**
	 * @return the workMinimumPack
	 */
	public Long getWorkMinimumPack() {
		return workMinimumPack;
	}
	/**
	 * @param workMinimumPack the workMinimumPack to set
	 */
	public void setWorkMinimumPack(Long workMinimumPack) {
		this.workMinimumPack = workMinimumPack;
	}
	/**
	 * @return the workCreatedDate
	 */
	public Date getWorkCreatedDate() {
		return workCreatedDate;
	}
	/**
	 * @param workCreatedDate the workCreatedDate to set
	 */
	public void setWorkCreatedDate(Date workCreatedDate) {
		this.workCreatedDate = workCreatedDate;
	}
	public WorkOrder(String workClientName, Date workStartDate, Date workEndDate, Long noOfLearners,
			Long noOfSessions, String notesForAdmin, String simName, List<SimType> simType, Date workReportDate,
			Long workStatus, Long workMinimumPack, Date workCreatedDate) {
		super();		
		this.workClientName = workClientName;
		this.workStartDate = workStartDate;
		this.workEndDate = workEndDate;
		this.noOfLearners = noOfLearners;
		this.noOfSessions = noOfSessions;
		this.notesForAdmin = notesForAdmin;
		this.simName = simName;
		this.simType = simType;
		this.workReportDate = workReportDate;
		this.workStatus = workStatus;
		this.workMinimumPack = workMinimumPack;
		this.workCreatedDate = workCreatedDate;
	}
	@Override
	public String toString() {
		return "WorkOrder [workId=" + workId + ", workClientName=" + workClientName + ", workStartDate=" + workStartDate
				+ ", workEndDate=" + workEndDate + ", noOfLearners=" + noOfLearners + ", noOfSessions=" + noOfSessions
				+ ", notesForAdmin=" + notesForAdmin + ", simName=" + simName + ", simType=" + simType
				+ ", workReportDate=" + workReportDate + ", workStatus=" + workStatus + ", workMinimumPack="
				+ workMinimumPack + ", workCreatedDate=" + workCreatedDate + "]";
	}
		
}
