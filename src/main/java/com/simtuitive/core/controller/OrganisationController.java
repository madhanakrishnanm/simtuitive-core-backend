package com.simtuitive.core.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.simtuitive.core.common.Constants;
import com.simtuitive.core.controller.productmgmt.api.JsonApiWrapper;
import com.simtuitive.core.controller.productmgmt.api.Link;
import com.simtuitive.core.controller.requestpayload.OrganisationRequestPayload;
import com.simtuitive.core.controller.responsepayload.OrganisationResponsePayload;
import com.simtuitive.core.model.Organisation;
import com.simtuitive.core.service.abstracts.IOrganisationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/org")
public class OrganisationController extends BaseController {

	@Autowired
	private IOrganisationService organisationservice;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an Organisation", response = Organisation.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/add-org", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<OrganisationResponsePayload> createOrganisation(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody OrganisationRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		OrganisationResponsePayload userResponse = null;
		String createdby=request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);
		userResponse = organisationservice.addOrganisation(payload);
		String tmp = builder.path(Constants.PATH_CREATE_ORGANISATION).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_CREATE_ORGANISATION_DETAIL);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " Updates an Organisation ", response = Organisation.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-org", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<OrganisationResponsePayload> updateOrganisation(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody OrganisationRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		String modify=request.getUserPrincipal().getName();
		payload.setModifiedBy(modify);
		OrganisationResponsePayload userResponse = organisationservice.updateOrganisation(payload);
		String tmp = builder.path(Constants.PATH_UPDATE_ORGANISATION).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_UPDATE_ORGANISATION_DETAIL);

		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " get an Organisation ", response = Organisation.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-org", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<OrganisationResponsePayload> findOrganisation(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody OrganisationRequestPayload payload, HttpServletRequest request, HttpServletResponse response) {
		OrganisationResponsePayload userResponse = organisationservice.getOrganisation(payload.getId());
		String tmp = builder.path(Constants.PATH_GET_ORG).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_ORGANISATION_DETAIL);

		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " getall Organisations ", response = Organisation.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/getall-org", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<OrganisationResponsePayload>> findAllOrganisation(@ApiIgnore UriComponentsBuilder builder,
			 HttpServletRequest request, HttpServletResponse response) {
		List<OrganisationResponsePayload> userResponse = organisationservice.findAll();
		String tmp = builder.path(Constants.PATH_GET_ALL_ORG).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_ALL_ORGANISATION_DETAIL);

		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " getall Organisations ", response = Organisation.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/getall-orgname", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Map<String,String>> findAllOrganisationName(@ApiIgnore UriComponentsBuilder builder,
			 HttpServletRequest request, HttpServletResponse response) {
		Map<String,String>userResponse = organisationservice.findAllOrganisationName();
		String tmp = builder.path(Constants.PATH_GET_ALL_ORG).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_ALL_ORGANISATION_DETAIL);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
}
