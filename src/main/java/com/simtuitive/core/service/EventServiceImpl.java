/**
 * 
 */
package com.simtuitive.core.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.EventRequestPayload;
import com.simtuitive.core.controller.responsepayload.EventResponsePayload;
import com.simtuitive.core.model.Event;
import com.simtuitive.core.model.EventModules;
import com.simtuitive.core.model.User;
import com.simtuitive.core.repository.EventRepository;
import com.simtuitive.core.repository.UserRepository;
import com.simtuitive.core.service.abstracts.IEventService;
import com.simtuitive.core.util.PasswordHelper;

/**
 * @author Veeramani N S
 *
 */
@Service
public class EventServiceImpl extends BaseService implements IEventService {

	@Autowired
	private EventRepository eventrepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public EventResponsePayload addEvent(EventRequestPayload payload) throws ParseException {
		// TODO Auto-generated method stub
		Event buildEventNeeded = null;
		if (payload.getType().equalsIgnoreCase("Event")) {
			buildEventNeeded = buildEvent(payload);
		}
		if (payload.getType().equalsIgnoreCase("Booking")) {
			buildEventNeeded = buildBooking(payload);
		}
		Event created = eventrepository.save(buildEventNeeded);
		EventResponsePayload result = buildEventResponsePayload(created);
		return result;
	}

	private Event buildEvent(EventRequestPayload payload) throws ParseException {
		// TODO Auto-generated method stub
		User client=userRepository.findByuserEmail(payload.getClient());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		Date startdate = formatter.parse(payload.getStartDate());
		Date enddate = formatter.parse(payload.getEndDate());
		Event eventresult = new Event(payload.getOrganization().getOrganizationId(), payload.getOrganization().getOrganizationName(), client.getUserId(), client.getFirstName()+client.getLastName(), payload.getProductId(), payload.getProductName(), payload.getName(), payload.getNoOfParticipants(), payload.getTollGate(), startdate, enddate, payload.getNotes(), new Date(), "1", new Date(), payload.getModifiedBy(), payload.getCreatedBy(), payload.getType(), payload.getSessions(), PasswordHelper.generateEventPassword(8));
		return eventresult;
	}

	private Event buildBooking(EventRequestPayload payload) throws ParseException {
		// TODO Auto-generated method stub
		User client=userRepository.findByuserEmail(payload.getClient());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		Date startdate = formatter.parse(payload.getStartDate());
		Date enddate = formatter.parse(payload.getEndDate());
		Event booking = new Event(payload.getOrganization().getOrganizationId(), payload.getOrganization().getOrganizationName(),payload.getProductId(),payload.getProductName(), client.getUserId(),client.getFirstName()+client.getLastName(),payload.getName(),
				payload.getNoOfParticipants(), startdate, enddate, payload.getNotes(),
				new Date(), "Pending", new Date(), payload.getModifiedBy(), payload.getCreatedBy(), payload.getType());
		return booking;
	}

	private EventResponsePayload buildEventResponsePayload(Event created) {
		// TODO Auto-generated method stub
		EventResponsePayload addresponse = null;
		System.out.println("EventType" + created.getType());
		if (created.getType().equalsIgnoreCase("Event")) {
			addresponse = new EventResponsePayload(created.getEventId(), created.getOrgId(), created.getOrgName(),
					created.getClientId(), created.getClientName(), created.getProductId(), created.getProductName(),
					created.getEventName(), created.getNoOfParticipants(), created.getTollPass(),
					created.getStartDate(), created.getEndDate(), created.getNotes(), created.getCreatedAt(),
					created.getStatus(), created.getUpdatedAt(), created.getModifiedBy(), created.getCreatedBy(),
					created.getType(), created.getSessions(), created.getPasscode());
		}
		if (created.getType().equalsIgnoreCase("Booking")) {
			addresponse = new EventResponsePayload(created.getEventId(), created.getProductId(),
					created.getProductName(), created.getEventName(), created.getNoOfParticipants(),
					created.getStartDate(), created.getEndDate(), created.getNotes(), created.getCreatedAt(),
					created.getStatus(), created.getUpdatedAt(), created.getModifiedBy(), created.getCreatedBy(),
					created.getType(),created.getOrgId(),created.getOrgName(),created.getClientId(),created.getClientName());
		}
		System.out.println("addresponse" + addresponse.getBookingId());
		return addresponse;
	}

	@Override
	public EventResponsePayload updateEvent(EventRequestPayload payload) {
		// TODO Auto-generated method stub
		Event eventupdated =null;
		if (payload.getType().equalsIgnoreCase("Event")) {
			eventupdated=modifyEvent(payload);
		}
		if (payload.getType().equalsIgnoreCase("Booking")) {
			eventupdated=modifyBooking(payload);
		}
		
		Event saved=eventrepository.save(eventupdated);
		return buildEventResponsePayload(saved);
	}

	private Event modifyBooking(EventRequestPayload payload) {
		// TODO Auto-generated method stub
		Event eventtobeupdated =eventrepository.findByEventId(payload.getId());
		eventtobeupdated.setProductId(payload.getProductId());
		eventtobeupdated.setProductName(payload.getProductName());
//		eventtobeupdated.setEventName(payload.getEventName());
		eventtobeupdated.setNoOfParticipants(payload.getNoOfParticipants());
//		eventtobeupdated.setStartDate(payload.getStartDate());
//		eventtobeupdated.setEndDate(payload.getEndDate());
		eventtobeupdated.setUpdatedAt(new Date());
		eventtobeupdated.setModifiedBy(payload.getModifiedBy());
		eventtobeupdated.setNotes(payload.getNotes());
		return eventtobeupdated;
	}

	private Event modifyEvent(EventRequestPayload payload) {
		// TODO Auto-generated method stub
		Event eventtobeupdated =eventrepository.findByEventId(payload.getId());
		eventtobeupdated.setOrgId(payload.getOrgId());
		eventtobeupdated.setOrgName(payload.getOrgName());
		//eventtobeupdated.setClientId(payload.getClientId());
		eventtobeupdated.setClientName(payload.getClientName());
		eventtobeupdated.setProductId(payload.getProductId());
		eventtobeupdated.setProductName(payload.getProductName());
		//eventtobeupdated.setEventName(payload.getEventName());
		//eventtobeupdated.setStartDate(payload.getStartDate());
		//eventtobeupdated.setEndDate(payload.getEndDate());
		eventtobeupdated.setNoOfParticipants(payload.getNoOfParticipants());
		eventtobeupdated.setNotes(payload.getNotes());
		eventtobeupdated.setSessions(payload.getSessions());
		eventtobeupdated.setModifiedBy(payload.getModifiedBy());
		//eventtobeupdated.setTollPass(payload.getTollPass());		
		return eventtobeupdated;
	}

	@Override
	public EventResponsePayload getEvent(String id) {
		// TODO Auto-generated method stub
		Event result = eventrepository.findByEventId(id);
		return buildEventResponsePayload(result);
	}

	@Override
	public List<EventResponsePayload> getBookingPending() {
		// TODO Auto-generated method stub
		System.out.println("Coming here");
		List<EventResponsePayload> payload = new ArrayList<EventResponsePayload>();
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("status").is("Pending"));
		List<Event> list = mongoOps.find(query1, Event.class);
		System.out.println("list" + list.size());
		for (Event e : list) {
			System.out.println("list" + e.getEventId());
			payload.add(buildEventResponsePayload(e));
		}
		return payload;
	}

	@Override
	public List<EventResponsePayload> getBookingApproved() {
		// TODO Auto-generated method stub
		List<EventResponsePayload> payload = new ArrayList<EventResponsePayload>();
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("status").is("Approved"));
		List<Event> list = mongoOps.find(query1, Event.class);
		for (Event e : list) {

			payload.add(buildEventResponsePayload(e));
		}
		return payload;
	}

	@Override
	public List<EventResponsePayload> getBookingCanceled() {
		// TODO Auto-generated method stub
		List<EventResponsePayload> payload = new ArrayList<EventResponsePayload>();
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("status").is("Canceled"));
		List<Event> list = mongoOps.find(query1, Event.class);
		for (Event e : list) {
			payload.add(buildEventResponsePayload(e));
		}
		return payload;
	}

	@Override
	public List<EventResponsePayload> getBookingRejected() {
		// TODO Auto-generated method stub
		List<EventResponsePayload> payload = new ArrayList<EventResponsePayload>();
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("status").is("Rejected"));
		List<Event> list = mongoOps.find(query1, Event.class);
		for (Event e : list) {
			payload.add(buildEventResponsePayload(e));
		}
		return payload;
	}

	@Override
	public EventResponsePayload updateBookingAction(String id, String action) {
		// TODO Auto-generated method stub
		Event eventtobeupdated =eventrepository.findByEventId(id);
		eventtobeupdated.setStatus(action);
		Event updatedaction=eventrepository.save(eventtobeupdated);
		return buildEventResponsePayload(updatedaction);
	}

	@Override
	public List<EventResponsePayload> getAllBooking() {
		// TODO Auto-generated method stub
		List<EventResponsePayload> payload = new ArrayList<EventResponsePayload>();
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("type").is("Booking"));
		List<Event> list = mongoOps.find(query1, Event.class);
		for (Event e : list) {

			payload.add(buildEventResponsePayload(e));
		}
		return payload;
		
	}

	@Override
	public List<EventResponsePayload> getAllEvent() {
		// TODO Auto-generated method stub
		List<EventResponsePayload> payload = new ArrayList<EventResponsePayload>();
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("type").is("Event"));
		List<Event> list = mongoOps.find(query1, Event.class);
		for (Event e : list) {

			payload.add(buildEventResponsePayload(e));
		}
		return payload;
	}

}
