/**
 * 
 */
package com.simtuitive.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.ProductUsers;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.model.User;
import com.simtuitive.core.service.abstracts.IRoleHasPermissionService;
import com.simtuitive.core.service.abstracts.IRolesService;
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
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {

	@Autowired
	private IUserService userservice;
	
	@Autowired
	private IRoleHasPermissionService rolehasservice;
	
	@Autowired
	private IRolesService roleservice;

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

	@RequestMapping(value = "/add-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> createUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
		checkArguments(userpayload);
		checkArguments(userpayload.getEmail());
//		checkArguments(userpayload.getUserType());
		User userResponse = null;
		userResponse = userservice.addUser(userpayload);
		String tmp = builder.path(Constants.PATH_CREATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_CREATE_ADMIN_DETAIL);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates a Admin user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> login(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/oauth/token").forward(request, response);	
		User userResponse = null;
		userResponse = userservice.addUser(userpayload);
		String tmp = builder.path(Constants.PATH_CREATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_CREATE_ADMIN_DETAIL);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	// update
	
	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " Updates a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Map<String, Map<String, Long>>> getSample(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		Map<String, Map<String, Long>> userresponse = generateCounts();
		String tmp = builder.path(Constants.PATH_GET_DASHBOARD).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_DASHBOARD);
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
	@RequestMapping(value = "/product-users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<ProductUsers>> getProductCount(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		List<ProductUsers> userresponse = generateProductCounts();
		String tmp = builder.path(Constants.PATH_GET_DASHBOARD).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_DASHBOARD);
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}
	private List<ProductUsers>  generateProductCounts() {
		// TODO Auto-generated method stub
		List<ProductUsers>  userresponse = new ArrayList<ProductUsers>();
		ProductUsers prod1=new ProductUsers("Business Ethics", 765L, 734L);
		userresponse.add(prod1);
		ProductUsers prod2=new ProductUsers("Data Visualization", 5333L, 5334L);
		userresponse.add(prod2);
		ProductUsers prod3=new ProductUsers("Business Acumen", 3334L, 653L);
		userresponse.add(prod3);
		ProductUsers prod4=new ProductUsers("Business Finance", 789L, 764L);
		userresponse.add(prod4);
		ProductUsers prod5=new ProductUsers("Agile Project Management", 700L, 833L);
		userresponse.add(prod5);
		return userresponse;
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
	@RequestMapping(value = "/get-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> getUser(@ApiIgnore UriComponentsBuilder builder, HttpServletRequest request,
			HttpServletResponse response) {
		User userResponse = userservice.getUser(request.getUserPrincipal().getName());	
		System.out.println("userresponse"+userResponse.getRole());
		Roles userrole=roleservice.getRoleId(userResponse.getRole());
		System.out.println("Role"+userrole.toString());
		userResponse.setPassword(null);		
		List<Permissions>permissionlist=userservice.buildRolePermission(userrole.getRoleid());
		
		userResponse.setPermissions(permissionlist);
		String tmp = builder.path("/get").build().toString();
		Link l1 = new Link(tmp, " User Detail get");
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
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
	@RequestMapping(value = "/get-user-id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<User> getUserById(@ApiIgnore UriComponentsBuilder builder,@RequestBody UserRequestPayload userpayload, HttpServletRequest request,
			HttpServletResponse response) {
		User userResponse = userservice.getUserDetails(userpayload);
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
		private Map<String, Map<String, Long>> generateCounts() {
			Map<String, Long> retailUserscount = new HashMap<String, Long>();
			retailUserscount.put("currentMonth", 20005L);
			retailUserscount.put("pastMonth", 20000L);
			Map<String, Long> enterpriseUserscount = new HashMap<String, Long>();
			enterpriseUserscount.put("currentMonth", 244L);
			enterpriseUserscount.put("pastMonth", 255L);
			Map<String, Long> usersOnlineNowcount = new HashMap<String, Long>();
			usersOnlineNowcount.put("yesterday", 202L);
			usersOnlineNowcount.put("today", 234L);			
		Map<String, Map<String, Long>> userresponse = new HashMap<String, Map<String,  Long>>();
		userresponse.put("retailUsers", retailUserscount);
		userresponse.put("enterpriseUsers", enterpriseUserscount);
		userresponse.put("usersOnlineNow", usersOnlineNowcount);		
		return userresponse;
	}
}
