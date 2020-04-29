package com.simtuitive.core.controller;

import javax.servlet.http.HttpServletRequest;

import com.simtuitive.core.common.Constants;
import com.simtuitive.core.globalexception.BadArgumentException;
import com.simtuitive.core.globalexception.BaseRuntimeException;

/**
 * This is the base marker controller, to provide any base functionality common
 * to all controllers.
 *
 * @author Surath Patnaik
 */
public abstract class BaseController {

	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String SELF_LINK = "self";
	public static final int PAGE_SIZE = 20;
	public static final int EVERYTHING = 10000;

	/**
	 * Method to check to bad argument of type {@code String}
	 *
	 * @param args
	 */
	protected void checkArguments(String... args) {
		String[] inArgs = args;

		for (String arg : inArgs) {
			if (arg == null || arg.isEmpty()) {
				BaseRuntimeException e = new BadArgumentException(Constants.BAD_ARGUMENT_MESSAGE);
				e.setCode(Constants.BAD_ARGUMENT_CODE);
				e.setTitle(Constants.BAD_ARGUMENT_TITLE);
				throw e;
			}
		}
	}

	/**
	 * Method to check to bad arguments
	 *
	 * @param args
	 */
	protected void checkArguments(Object... args) {
		Object[] inArgs = args;

		for (Object arg : inArgs) {
			if (arg == null) {
				throwBadArgumentException();
			}

			if (arg instanceof String && ((String) arg).isEmpty()) {
				throwBadArgumentException();
			}
		}
	}

	/**
	 * Helper method to throw generic {@code BadArgumentException}
	 * 
	 */
	protected void throwBadArgumentException() {
		BaseRuntimeException e = new BadArgumentException(Constants.BAD_ARGUMENT_MESSAGE);
		e.setCode(Constants.BAD_ARGUMENT_CODE);
		e.setTitle(Constants.BAD_ARGUMENT_TITLE);
		throw e;
	}

	/**
	 * Access method to get self link from {@code request} object to be embedded in
	 * JSON API output
	 *
	 * @param request
	 * @return
	 */
	protected String getSelfLink(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}

}
