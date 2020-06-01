package com.simtuitive.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simtuitive.core.model.Event;

public interface EventRepository extends MongoRepository<Event, String>{

	public  Event findByEventId(String id);
	
}