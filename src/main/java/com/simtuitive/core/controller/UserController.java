/**
 * 
 */
package com.simtuitive.core.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.simtuitive.core.common.Constants;
import com.simtuitive.core.controller.productmgmt.api.JsonApiWrapper;
import com.simtuitive.core.controller.productmgmt.api.Link;
import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.globalexception.ResourceNotFoundException;
import com.simtuitive.core.globalexception.UserServiceException;
import com.simtuitive.core.model.User;
import com.simtuitive.core.service.abstracts.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Veeramani N S
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userservice;

	// Create User
//	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates a Admin user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> createUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
		checkArguments(userpayload);
		checkArguments(userpayload.getUserEmail());
		checkArguments(userpayload.getUserType());
		User userResponse = null;
		userResponse = userservice.addUser(userpayload);
		String tmp = builder.path(Constants.PATH_CREATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_CREATE_ADMIN_DETAIL);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}

	// update
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " Updates a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> updateAdminUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
		User userResponse = userservice.updateUser(userpayload);
		String tmp = builder.path(Constants.PATH_UPDATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_UPDATE_ADMIN_DETAIL);

		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}

	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " Changes password of a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/changepassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> changePasswordUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
		String tmp = builder.path(Constants.PATH_PASSWORD_CHANGE).build().toString();
		boolean same = userservice.samePasswordOrNOr(userpayload);
		if (same) {
			User userResponse = userservice.getUserDetails(userpayload);
			Link l2 = new Link(tmp, Constants.PASSWORD_UPDATE_FAILURE);
			return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l2));
		} else {
			User userResponse = userservice.changePasswordUser(userpayload);
			Link l1 = new Link(tmp, Constants.PASSWORD_UPDATED_SUCCESS);
			return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
		}
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/getuser", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> getUser(@ApiIgnore UriComponentsBuilder builder, HttpServletRequest request,
			HttpServletResponse response) {
		User userResponse = userservice.getUser(request.getUserPrincipal().getName());
		userResponse.setPassword(null);
		String tmp = builder.path("/get").build().toString();
		Link l1 = new Link(tmp, " User Detail get");
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get all users ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful getAll of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/getAllByType", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<User>> getAllUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestParam("userType") String userType, HttpServletRequest request, HttpServletResponse response) {
		List<User> userresponse = userservice.getAllUser(userType);
		String tmp = builder.path("/getAll").build().toString();
		Link l1 = new Link(tmp, " User Detail getAll");
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));

	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<User>> deleteUserByEmail(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		checkArguments(userpayload);
		List<User> userresponse = userservice.deleteUser(userpayload.getUserId());
		String tmp = builder.path("/delete").build().toString();
		Link l1 = new Link(tmp, " User Detail Delete");
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return Constants.LOGOUT_URL;
	}
}
