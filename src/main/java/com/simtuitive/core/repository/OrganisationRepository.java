package com.simtuitive.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simtuitive.core.model.Organisation;

public interface OrganisationRepository extends MongoRepository<Organisation, String> {	
	
	public Organisation findByOrganizationId(String organizationId);
	
	
}
