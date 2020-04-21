package com.simtuitive.core.controller;

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
import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.globalexception.ResourceNotFoundException;
import com.simtuitive.core.globalexception.UserRoleServiceException;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.service.abstracts.IRolesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@EnableAutoConfiguration
@RestController
@RequestMapping("api/v1/roles")
public class RolesController extends BaseController{
	
	@Autowired
	private IRolesService roleservice;
	// Create Role
		@ResponseStatus(HttpStatus.CREATED)
		@PreAuthorize("hasAuthority('ADMIN')")
		@ApiOperation(value = " Creates a role ", response = Roles.class)
		@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
				@ApiResponse(code = 401, message = "Not authorized!"),
				@ApiResponse(code = 403, message = "Not authorized to perform this action."),
				@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
				@ApiResponse(code = 404, message = "Operation cannot be performed now."),
				@ApiResponse(code = 500, message = "Internal server error") })
		@RequestMapping(value = "/add-role", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public JsonApiWrapper<Roles> createRole(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RolesRequestPayload payload,
				HttpServletRequest request, HttpServletResponse response)
				throws UserRoleServiceException, ResourceNotFoundException {
			Roles roleresponse =roleservice.addRole(payload); 
			String tmp = builder.path("/create").build().toString();
			Link l1 = new Link(tmp, " Role Detail");
			return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

		}
		@ResponseStatus(HttpStatus.CREATED)
		@PreAuthorize("hasAuthority('ADMIN')")
		@ApiOperation(value = " Update a role ", response = Roles.class)
		@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
				@ApiResponse(code = 401, message = "Not authorized!"),
				@ApiResponse(code = 403, message = "Not authorized to perform this action."),
				@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
				@ApiResponse(code = 404, message = "Operation cannot be performed now."),
				@ApiResponse(code = 500, message = "Internal server error") })
		@RequestMapping(value = "/update-role", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public JsonApiWrapper<Roles> updateRole(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RolesRequestPayload payload,
				HttpServletRequest request, HttpServletResponse response)
				throws UserRoleServiceException, ResourceNotFoundException {
			Roles roleresponse =roleservice.updateRole(payload); 
			String tmp = builder.path("/update").build().toString();
			Link l1 = new Link(tmp, " Role Detail");
			return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

		}
		
		@ResponseStatus(HttpStatus.CREATED)
		@PreAuthorize("hasAuthority('ADMIN')")
		@ApiOperation(value = " Creates a role ", response = Roles.class)
		@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
				@ApiResponse(code = 401, message = "Not authorized!"),
				@ApiResponse(code = 403, message = "Not authorized to perform this action."),
				@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
				@ApiResponse(code = 404, message = "Operation cannot be performed now."),
				@ApiResponse(code = 500, message = "Internal server error") })
		@RequestMapping(value = "/get-role", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public JsonApiWrapper<Roles> getRole(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RolesRequestPayload payload,
				HttpServletRequest request, HttpServletResponse response)
				throws UserRoleServiceException, ResourceNotFoundException {
			Roles roleresponse =roleservice.getRole(payload.getRoleid()); 
			String tmp = builder.path("/get").build().toString();
			Link l1 = new Link(tmp, " Role Detail");
			return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

		}
		
		
		@PreAuthorize("hasAuthority('ADMIN')")
		@ApiOperation(value = " Creates a role ", response = Roles.class)
		@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
				@ApiResponse(code = 401, message = "Not authorized!"),
				@ApiResponse(code = 403, message = "Not authorized to perform this action."),
				@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
				@ApiResponse(code = 404, message = "Operation cannot be performed now."),
				@ApiResponse(code = 500, message = "Internal server error") })
		@RequestMapping(value = "/getall-role", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public JsonApiWrapper<List<Roles>> getAllRole(@ApiIgnore UriComponentsBuilder builder, @RequestBody  RolesRequestPayload payload,
				HttpServletRequest request, HttpServletResponse response)
				throws UserRoleServiceException, ResourceNotFoundException {
			List<Roles> roleresponse =roleservice.getall(); 
			String tmp = builder.path("/getall").build().toString();
			Link l1 = new Link(tmp, " Role Details");
			return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

		}
}
