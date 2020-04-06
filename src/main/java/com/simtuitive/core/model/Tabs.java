package com.simtuitive.core.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tabs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tabname;
	private boolean isAdd;
	private boolean isRead;
	private boolean isEdit;
	private boolean isDelete;

	/**
	 * @return the isAdd
	 */
	public boolean isAdd() {
		return isAdd;
	}

	/**
	 * @param isAdd the isAdd to set
	 */
	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	/**
	 * @return the tabname
	 */
	public String getTabname() {
		return tabname;
	}

	/**
	 * @param tabname the tabname to set
	 */
	public void setTabname(String tabname) {
		this.tabname = tabname;
	}

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the isEdit
	 */
	public boolean isEdit() {
		return isEdit;
	}

	/**
	 * @param isEdit the isEdit to set
	 */
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	/**
	 * @return the isDelete
	 */
	public boolean isDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Tabs(String tabname, boolean isAdd, boolean isRead, boolean isEdit, boolean isDelete) {
		super();
		this.tabname = tabname;
		this.isAdd = isAdd;
		this.isRead = isRead;
		this.isEdit = isEdit;
		this.isDelete = isDelete;
	}

	public Tabs() {
		super();
		// TODO Auto-generated constructor stub
	}

}
