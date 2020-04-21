package com.simtuitive.core.controller;

import java.util.Arrays;

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
import com.simtuitive.core.controller.requestpayload.RoleHasPermissionRequestPayload;
import com.simtuitive.core.globalexception.ResourceNotFoundException;
import com.simtuitive.core.globalexception.UserRoleServiceException;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.service.abstracts.IRoleHasPermissionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;
@CrossOrigin
@EnableAutoConfiguration
@RestController
@RequestMapping("api/v1/haspermissions")
public class RoleHasPermisssionController extends BaseController{
	@Autowired
	private IRoleHasPermissionService rolehasspservice;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value = " Creates a HasPermission ", response = RoleHasPermission.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/add-haspermission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RoleHasPermission> createHasPermission(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RoleHasPermissionRequestPayload payload,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		RoleHasPermission roleresponse =rolehasspservice.addRolePermission(payload); 
		String tmp = builder.path("/create").build().toString();
		Link l1 = new Link(tmp, " RoleHasPermission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value = " Creates a HasPermission ", response = RoleHasPermission.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-haspermission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RoleHasPermission> updateHasPermission(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RoleHasPermissionRequestPayload payload,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		RoleHasPermission roleresponse =rolehasspservice.updateRolePermission(payload); 
		String tmp = builder.path("/update").build().toString();
		Link l1 = new Link(tmp, " RoleHasPermission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value = " Get a HasPermission ", response = RoleHasPermission.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-haspermission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RoleHasPermission> get(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RoleHasPermissionRequestPayload payload,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		RoleHasPermission roleresponse =rolehasspservice.getPermission(payload.getRoleid()); 
		String tmp = builder.path("/get").build().toString();
		Link l1 = new Link(tmp, " RoleHasPermission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}
}
