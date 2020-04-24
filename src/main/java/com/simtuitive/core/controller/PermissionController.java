/**
 * 
 */
package com.simtuitive.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.simtuitive.core.controller.productmgmt.api.JsonApiWrapper;
import com.simtuitive.core.controller.productmgmt.api.Link;
import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.globalexception.ResourceNotFoundException;
import com.simtuitive.core.globalexception.UserRoleServiceException;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.service.abstracts.IPermissionService;
import com.simtuitive.core.service.abstracts.IRoleHasPermissionService;
import com.simtuitive.core.service.abstracts.IRolesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Veeramani N S
 *
 */
@CrossOrigin
@EnableAutoConfiguration
@RestController
@RequestMapping("api/v1/permissions")
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService permissionservice;
	
	@Autowired
	private IRoleHasPermissionService haspermissionservice;
	
	@Autowired
	private IRolesService roleservice;
	

	// Create Role
	@ResponseStatus(HttpStatus.CREATED)
//	@PreAuthorize("@customPermissionsEvaluator.has")
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Creates a role ", response = Permissions.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/add-permission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Permissions> createPermission(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody PermissionsRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		Permissions roleresponse = permissionservice.create(payload);
		String tmp = builder.path("/create").build().toString();
		Link l1 = new Link(tmp, " Permission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}

	// update
	@ResponseStatus(HttpStatus.IM_USED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Get a role ", response = Permissions.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-permission", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Permissions> updatePermission(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody PermissionsRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {

		Permissions roleresponse = permissionservice.update(payload);
		String tmp = builder.path("/update").build().toString();
		Link l1 = new Link(tmp, " Role Detail");

		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
	}

	// getAll
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Get all roles ", response = Permissions.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-permissions", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<Permissions>> getAllRoles(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		List<Permissions> roleresponse = permissionservice.findAll();
		String tmp = builder.path("/get-permissions").build().toString();
		Link l1 = new Link(tmp, " All Permission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Get all roles ", response = Permissions.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-permission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Permissions> getById(@ApiIgnore UriComponentsBuilder builder,@RequestBody PermissionsRequestPayload payload,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		Permissions roleresponse = permissionservice.get(payload.getPermissionId());
		String tmp = builder.path("/get-permission").build().toString();
		Link l1 = new Link(tmp, " All Permission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Get all roles ", response = Permissions.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-permission-roles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<Roles>> getRoleId(@ApiIgnore UriComponentsBuilder builder,@RequestBody PermissionsRequestPayload payload,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {		
		List<RoleHasPermission> permissions=haspermissionservice.getrolesByid(payload.getPermissionId());
		List<Roles>roleresponse=new ArrayList<>();
		Roles role = null;
		for(RoleHasPermission per:permissions) {
			 role=roleservice.getRole(per.getRoleid());
			 roleresponse.add(role);
		}		
		String tmp = builder.path("/get-permission-roleids").build().toString();
		Link l1 = new Link(tmp, " All Permission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
	}
}
