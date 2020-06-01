/**
 * 
 */
package com.simtuitive.core.service.abstracts;

import java.text.ParseException;
import java.util.List;

import com.simtuitive.core.controller.requestpayload.EventRequestPayload;
import com.simtuitive.core.controller.responsepayload.EventResponsePayload;

/**
 * @author Veeramani N S
 *
 */
public interface IEventService {
	
	public EventResponsePayload addEvent(EventRequestPayload payload) throws ParseException;	
	public EventResponsePayload updateEvent(EventRequestPayload payload);
	public EventResponsePayload getEvent(String id);
	public List<EventResponsePayload> getBookingPending();
	public List<EventResponsePayload> getBookingApproved();
	public List<EventResponsePayload> getBookingCanceled();
	public List<EventResponsePayload> getBookingRejected();
	public EventResponsePayload updateBookingAction(String id,String action);
	

}
