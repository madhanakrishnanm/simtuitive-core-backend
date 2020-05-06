package com.simtuitive.core.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.simtuitive.core.controller.productmgmt.api.JsonApiWrapper;
import com.simtuitive.core.controller.productmgmt.api.Link;
import com.simtuitive.core.controller.productmgmt.api.PaginationResponse;
import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.controller.responsepayload.RolesResponsePayload;
import com.simtuitive.core.globalexception.ResourceNotFoundException;
import com.simtuitive.core.globalexception.UserRoleServiceException;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.service.RolesServiceImpl;
import com.simtuitive.core.service.abstracts.IRolesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@EnableAutoConfiguration
@RestController
@RequestMapping("api/v1/roles")
public class RolesController extends BaseController {

	@Autowired
	private IRolesService roleservice;
	
	@Autowired
	private RolesServiceImpl impl;

	// Create Role
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Creates a role ", response = Roles.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/add-role", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RolesResponsePayload> createRole(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody RolesRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		System.out.println("new value"+payload.toString());
		RolesResponsePayload roleresponse = roleservice.addRole(payload);
		String tmp = builder.path("/create").build().toString();
		Link l1 = new Link(tmp, " Role Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Update a role ", response = Roles.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-role", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RolesResponsePayload> updateRole(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody RolesRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		RolesResponsePayload roleresponse = roleservice.updateRole(payload);
		String tmp = builder.path("/update").build().toString();
		Link l1 = new Link(tmp, " Role Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Update a role ", response = Roles.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/delete-role", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RolesResponsePayload> deleteRole(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody RolesRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		RolesResponsePayload roleresponse = roleservice.deleteRole(payload.getRoleId());
		String tmp = builder.path("/delete").build().toString();
		Link l1 = new Link(tmp, " Role Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('Super Admin')")
	@ApiOperation(value = " Creates a role ", response = Roles.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-role", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<RolesResponsePayload> getRole(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody RolesRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		RolesResponsePayload roleresponse = roleservice.getRole(payload.getRoleId());
		String tmp = builder.path("/get").build().toString();
		Link l1 = new Link(tmp, " Role Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}

	@PreAuthorize("hasAuthority('Super Admin') or hasAuthority('Admin') ")
	@ApiOperation(value = " Creates a role ", response = Roles.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-all-role", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<RolesResponsePayload>> getAllRole(@ApiIgnore UriComponentsBuilder builder, @RequestParam("pageno") Optional<String> pageno,HttpServletRequest request,
			HttpServletResponse response) throws UserRoleServiceException, ResourceNotFoundException {
		Page<Roles> roleresponse = roleservice.getall(pageno);
		List<RolesResponsePayload> result = impl.getPayload(roleresponse.getContent());
		String tmp = builder.path("/get-all-role").build().toString();
		Link l1 = new Link(tmp, " Role Details");
		PaginationResponse page=new PaginationResponse(roleresponse.getNumberOfElements(),roleresponse.getTotalPages() ,roleresponse.getSize(), roleresponse.getPageable().getPageNumber());
		return new JsonApiWrapper<>(result, request.getRequestURL().toString(), Arrays.asList(l1),page);

	}
}
