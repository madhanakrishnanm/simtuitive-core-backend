/**
 * 
 */
package com.simtuitive.core.service.abstracts;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.simtuitive.core.controller.requestpayload.EventRequestPayload;
import com.simtuitive.core.controller.responsepayload.EventResponsePayload;

/**
 * @author Veeramani N S
 *
 */
public interface IEventService {
	
	public EventResponsePayload addEvent(EventRequestPayload payload) throws ParseException;	
	public EventResponsePayload updateEvent(EventRequestPayload payload) throws ParseException;
	public EventResponsePayload getEvent(String id);
	public List<EventResponsePayload> getBookingPending();
	public List<EventResponsePayload> getBookingApproved();
	public List<EventResponsePayload> getBookingCanceled();
	public List<EventResponsePayload> getBookingRejected();
	public EventResponsePayload updateBookingAction(String id,String action);
	public List<EventResponsePayload> getAllBooking(Optional<String>  status);
	public List<EventResponsePayload> getAllEvent(Optional<String>  status);
	
}
