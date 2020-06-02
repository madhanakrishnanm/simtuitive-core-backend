package com.simtuitive.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simtuitive.core.service.abstracts.IEventService;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.simtuitive.core.controller.requestpayload.EventRequestPayload;
import com.simtuitive.core.controller.requestpayload.LicenseRequestPayload;
import com.simtuitive.core.controller.responsepayload.EventResponsePayload;
import com.simtuitive.core.controller.responsepayload.LicenseResponsePayload;
import com.simtuitive.core.globalexception.BadArgumentException;
import com.simtuitive.core.model.Event;
import com.simtuitive.core.model.License;
import com.simtuitive.core.service.abstracts.ILicenseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;



@CrossOrigin
@RestController
@RequestMapping("/api/v1/events")
public class EventController extends BaseController{

	@Autowired
	private IEventService eventservice;

	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/add-event", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> createEvent(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody EventRequestPayload payload, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		EventResponsePayload userResponse = null;
		String createdby = request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);
		payload.setCreatedBy(createdby);		
		String tmp = builder.path("/add-event").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.addEvent(payload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/add-booking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> createBooking(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody EventRequestPayload payload, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		EventResponsePayload userResponse = null;
		String createdby = request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);
		payload.setCreatedBy(createdby);		
		String tmp = builder.path("/add-booking").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.addEvent(payload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/get-booking-pending", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<EventResponsePayload>> getPendingBooking(@ApiIgnore UriComponentsBuilder builder,
			 HttpServletRequest request, HttpServletResponse response) {
		List<EventResponsePayload> userResponse=null;				
		String tmp = builder.path("/get-booking-pending").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getBookingPending();
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/get-booking-approved", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<EventResponsePayload>> getApprovedBooking(@ApiIgnore UriComponentsBuilder builder,
			 HttpServletRequest request, HttpServletResponse response) {
		List<EventResponsePayload> userResponse=null;				
		String tmp = builder.path("/get-booking-approved").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getBookingApproved();
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/get-booking-rejected", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<EventResponsePayload>> getRejectedBooking(@ApiIgnore UriComponentsBuilder builder,
			 HttpServletRequest request, HttpServletResponse response) {
		List<EventResponsePayload> userResponse=null;				
		String tmp = builder.path("/get-booking-rejected").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getBookingRejected();
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/get-booking-canceled", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<EventResponsePayload>> getCancelBooking(@ApiIgnore UriComponentsBuilder builder,
			 HttpServletRequest request, HttpServletResponse response) {
		List<EventResponsePayload> userResponse=null;				
		String tmp = builder.path("/get-booking-canceled").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getBookingCanceled();
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/update-event", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> updateEvent(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody EventRequestPayload payload, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		EventResponsePayload userResponse = null;
		String createdby = request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);				
		String tmp = builder.path("/update-event").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.updateEvent(payload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/update-booking", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> updateBooking(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody EventRequestPayload payload, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		EventResponsePayload userResponse = null;
		String createdby = request.getUserPrincipal().getName();
		payload.setModifiedBy(createdby);			
		String tmp = builder.path("/update-booking").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.updateEvent(payload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/get-booking-id", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> getBooking(@ApiIgnore UriComponentsBuilder builder,
			@RequestParam("id") String id,	 HttpServletRequest request, HttpServletResponse response) {
		EventResponsePayload userResponse=null;				
		String tmp = builder.path("/get-booking-id").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getEvent(id);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-event-id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> getEventId(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody EventRequestPayload payload,	 HttpServletRequest request, HttpServletResponse response) {
		EventResponsePayload userResponse=null;				
		String tmp = builder.path("/get-event-id").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getEvent(payload.getId());
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-booking-action", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<EventResponsePayload> updateEventAction(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody EventRequestPayload payload,HttpServletRequest request, HttpServletResponse response) {
		EventResponsePayload userResponse=null;				
		String tmp = builder.path("/update-booking-action").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.updateBookingAction(payload.getId(),payload.getStatus());
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-all-booking", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<EventResponsePayload>> getAllBooking(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response) {
		List<EventResponsePayload> userResponse=null;				
		String tmp = builder.path("/update-booking-action").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getAllBooking();
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates an event", response = Event.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of event Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-all-event", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<EventResponsePayload>> getAllEvent(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response) {
		List<EventResponsePayload> userResponse=null;				
		String tmp = builder.path("/update-booking-action").build().toString();
		Link l1 = new Link(tmp, "event-managment");
		userResponse = eventservice.getAllEvent();
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}
}
