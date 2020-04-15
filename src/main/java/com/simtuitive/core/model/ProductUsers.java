package com.simtuitive.core.model;

import java.io.Serializable;

public class ProductUsers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productName;
	private Long registerUsers;
	private Long usersOnlineNow;
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
	 * @return the registerUsers
	 */
	public Long getRegisterUsers() {
		return registerUsers;
	}
	/**
	 * @param registerUsers the registerUsers to set
	 */
	public void setRegisterUsers(Long registerUsers) {
		this.registerUsers = registerUsers;
	}
	/**
	 * @return the usersOnlineNow
	 */
	public Long getUsersOnlineNow() {
		return usersOnlineNow;
	}
	/**
	 * @param usersOnlineNow the usersOnlineNow to set
	 */
	public void setUsersOnlineNow(Long usersOnlineNow) {
		this.usersOnlineNow = usersOnlineNow;
	}
	public ProductUsers(String productName, Long registerUsers, Long usersOnlineNow) {
		super();
		this.productName = productName;
		this.registerUsers = registerUsers;
		this.usersOnlineNow = usersOnlineNow;
	}
	@Override
	public String toString() {
		return "ProductUsers [productName=" + productName + ", registerUsers=" + registerUsers + ", usersOnlineNow="
				+ usersOnlineNow + "]";
	}
}
