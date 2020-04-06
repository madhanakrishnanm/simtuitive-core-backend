/**
 * 
 */
package com.simtuitive.core.globalexception;

/**
 * @author Veeramani N S
 *
 */

public class UserServiceException extends Exception {
	private String username;

	private static final long serialVersionUID = 250442731098154235L;

	public static UserServiceException createWith(String username) {
		return new UserServiceException(username);
	}

	private UserServiceException(String username) {
		this.username = username;
	}

	@Override
	public String getMessage() {
		return "User '" + username + "' not found";
	}
}
