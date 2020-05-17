/**
 * 
 */
package com.simtuitive.core.controller;

import java.util.ArrayList;
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
import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.controller.responsepayload.PermissionsResponsePayload;
import com.simtuitive.core.globalexception.BadArgumentException;
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
	public JsonApiWrapper<PermissionsResponsePayload> createPermission(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody PermissionsRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		String tmp = builder.path("/create").build().toString();
		Link l1 = new Link(tmp, " Permission Detail");
		checkPermission(payload.getName(),payload.getType(),l1.getHref());
		
		PermissionsResponsePayload roleresponse = permissionservice.create(payload);		
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));

	}

	private void checkPermission(String name, String type, String href) {
		// TODO Auto-generated method stub
		if (name.isEmpty()|| type.isEmpty()) {
			throw new BadArgumentException("204", "Create Permission", href,"Permission name or type is not entered");
		} else {
			boolean namecheck = permissionservice.permissionExistsByName(name);
			boolean typecheck = permissionservice.permissionExistsByType(type);
			if (namecheck&&typecheck) {				
				throw new BadArgumentException("409", "Create Permission", href, "Permission Already exist!");
			}
		}
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
	public JsonApiWrapper<PermissionsResponsePayload> updatePermission(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody PermissionsRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		PermissionsResponsePayload roleresponse = permissionservice.update(payload);
		String tmp = builder.path("/update").build().toString();
		Link l1 = new Link(tmp, " Role Detail");

		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
	}

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
	@RequestMapping(value = "/delete-permission", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<PermissionsResponsePayload> deletePermission(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody PermissionsRequestPayload payload, HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		PermissionsResponsePayload roleresponse = permissionservice.delete(payload.getPermissionId());
		String tmp = builder.path("/delete").build().toString();
		Link l1 = new Link(tmp, " Permission Detail");

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
	public JsonApiWrapper<List<PermissionsResponsePayload>> getAllRoles(@ApiIgnore UriComponentsBuilder builder,@RequestParam("pageNo") Optional<String> pageno
			,@RequestParam("query") Optional<String> query,@RequestParam("name") Optional<String> name,@RequestParam("type") Optional<String> type,HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		Page<Permissions> roleresponse = permissionservice.getall(pageno,query,type,name);
		List<PermissionsResponsePayload> result = permissionservice.findAll(roleresponse.getContent());
		String tmp = builder.path("/get-permissions").build().toString();
		Link l1 = new Link(tmp, " All Permission Detail");				
		PaginationResponse page=new PaginationResponse((int)((Long)roleresponse.getTotalElements()).intValue(),roleresponse.getTotalPages() ,roleresponse.getSize(), roleresponse.getPageable().getPageNumber());
		return new JsonApiWrapper<>(result, request.getRequestURL().toString(), Arrays.asList(l1),page);
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
	public JsonApiWrapper<PermissionsResponsePayload> getById(@ApiIgnore UriComponentsBuilder builder,@RequestBody PermissionsRequestPayload payload,
			HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		PermissionsResponsePayload roleresponse = permissionservice.get(payload.getPermissionId());
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
	@RequestMapping(value = "/find-permission-type", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<String>> getpermissiontype(@ApiIgnore UriComponentsBuilder builder,
			@RequestParam("query") Optional<String> query,HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		List<String> roleresponse = permissionservice.getPermissionTypeAll(query);
		String tmp = builder.path("/find-permission-type").build().toString();
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
	@RequestMapping(value = "/find-permission-name", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<String>> getpermissionname(@ApiIgnore UriComponentsBuilder builder,
			@RequestParam("query") Optional<String> query,HttpServletRequest request, HttpServletResponse response)
			throws UserRoleServiceException, ResourceNotFoundException {
		List<String> roleresponse = permissionservice.getPermissionNameAll(query);
		String tmp = builder.path("/find-permission-type").build().toString();
		Link l1 = new Link(tmp, " All Permission Detail");
		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
	}

}
