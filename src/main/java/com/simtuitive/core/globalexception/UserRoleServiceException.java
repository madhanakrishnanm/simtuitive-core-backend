/**
 * 
 */
package com.simtuitive.core.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Veeramani N S
 *
 */
@ResponseStatus(code =HttpStatus.FORBIDDEN)
public class UserRoleServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6203571421458917659L;

	public UserRoleServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRoleServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
