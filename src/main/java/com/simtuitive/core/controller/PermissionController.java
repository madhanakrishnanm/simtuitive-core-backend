///**
// * 
// */
//package com.simtuitive.core.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.simtuitive.core.controller.productmgmt.api.JsonApiWrapper;
//import com.simtuitive.core.controller.productmgmt.api.Link;
//import com.simtuitive.core.controller.requestpayload.UserRoleRequestPayload;
//import com.simtuitive.core.globalexception.ResourceNotFoundException;
//import com.simtuitive.core.globalexception.UserRoleServiceException;
//import com.simtuitive.core.model.Permissions;
//import com.simtuitive.core.service.abstracts.IUserRoleService;
//
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import springfox.documentation.annotations.ApiIgnore;
//
///**
// * @author Veeramani N S
// *
// */
//@CrossOrigin
//@EnableAutoConfiguration
//@RestController
//@RequestMapping("api/v1/role")
//public class PermissionController {
//
//	@Autowired
//	private IUserRoleService userroleService;
//
//	// Create Role
//	@ResponseStatus(HttpStatus.CREATED)
////	@PreAuthorize("@customPermissionsEvaluator.has")
//	@PreAuthorize("hasAuthority('ADMIN')")
//	@ApiOperation(value = " Creates a role ", response = Permissions.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
//			@ApiResponse(code = 401, message = "Not authorized!"),
//			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
//			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
//			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
//			@ApiResponse(code = 500, message = "Internal server error") })
//	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public JsonApiWrapper<Permissions> createRole(@ApiIgnore UriComponentsBuilder builder, @RequestBody UserRoleRequestPayload userrole,
//			HttpServletRequest request, HttpServletResponse response)
//			throws UserRoleServiceException, ResourceNotFoundException {
//		Permissions roleresponse = userroleService.createRole(userrole);
//		String tmp = builder.path("/create").build().toString();
//		Link l1 = new Link(tmp, " Role Detail");
//
//		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
//
//	}
//
//	// update
//	@ResponseStatus(HttpStatus.IM_USED)
//	@PreAuthorize("hasAuthority('ADMIN')")
//	@ApiOperation(value = " Get a role ", response = Permissions.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
//			@ApiResponse(code = 401, message = "Not authorized!"),
//			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
//			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
//			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
//			@ApiResponse(code = 500, message = "Internal server error") })
//	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public JsonApiWrapper<Permissions> updateRole(@ApiIgnore UriComponentsBuilder builder, @RequestBody UserRoleRequestPayload userrole,
//			HttpServletRequest request, HttpServletResponse response)
//			throws UserRoleServiceException, ResourceNotFoundException {
//
//		Permissions roleresponse = userroleService.updateRole(userrole);
//		String tmp = builder.path("/update").build().toString();
//		Link l1 = new Link(tmp, " Role Detail");
//
//		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
//	}
//
//	// getAll
//	@ResponseStatus(HttpStatus.ACCEPTED)
//	@ApiOperation(value = " Get all roles ", response = Permissions.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
//			@ApiResponse(code = 401, message = "Not authorized!"),
//			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
//			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
//			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
//			@ApiResponse(code = 500, message = "Internal server error") })
//	@RequestMapping(value = "/getAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public JsonApiWrapper<List<Permissions>> getAllRoles(@ApiIgnore UriComponentsBuilder builder,
//			HttpServletRequest request, HttpServletResponse response)
//			throws UserRoleServiceException, ResourceNotFoundException {
//		List<Permissions> roleresponse = userroleService.getAllRoles();
//		String tmp = builder.path("/getAll").build().toString();
//		Link l1 = new Link(tmp, " All Role Detail");
//		return new JsonApiWrapper<>(roleresponse, request.getRequestURL().toString(), Arrays.asList(l1));
//	}
//}
