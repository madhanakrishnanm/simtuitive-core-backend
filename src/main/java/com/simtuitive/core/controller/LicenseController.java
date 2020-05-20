package com.simtuitive.core.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.simtuitive.core.controller.requestpayload.LicenseRequestPayload;
import com.simtuitive.core.controller.responsepayload.LicenseResponsePayload;
import com.simtuitive.core.model.License;
import com.simtuitive.core.service.abstracts.ILicenseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.http.HttpStatus;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/lic")
public class LicenseController  extends BaseController{

	@Autowired
	private ILicenseService licenseservice;
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an license", response = License.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of license Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/add-license", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<LicenseResponsePayload> createLicense(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody LicenseRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		LicenseResponsePayload userResponse = null;
		String createdby = request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);
		payload.setCreatedBy(createdby);
		String tmp = builder.path("/add-license").build().toString();
		Link l1 = new Link(tmp, "license-managment");		
		userResponse = licenseservice.addLicense(payload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an license", response = License.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of license Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/update-license", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<LicenseResponsePayload> updateLicense(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody LicenseRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		LicenseResponsePayload userResponse = null;
		String createdby = request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);		
		String tmp = builder.path("/update-license").build().toString();
		Link l1 = new Link(tmp, "license-managment");		
		userResponse = licenseservice.updateLicense(payload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an license", response = License.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of license Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/get-license", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<LicenseResponsePayload> getLicense(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody LicenseRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		LicenseResponsePayload userResponse = null;		
		String tmp = builder.path("get-license").build().toString();
		Link l1 = new Link(tmp, "license-managment");		
		userResponse = licenseservice.getLicense(payload.getLicenseId());
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an license", response = License.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of license Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/delete-license", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<LicenseResponsePayload> deleteLicense(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody LicenseRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		LicenseResponsePayload userResponse = null;		
		String tmp = builder.path("delete-license").build().toString();
		Link l1 = new Link(tmp, "license-managment");		
		userResponse = licenseservice.deleteLicense(payload.getLicenseId());
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}

}
