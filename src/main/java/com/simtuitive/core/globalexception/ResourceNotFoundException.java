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
@ResponseStatus(code =HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8587346720293455700L;

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
