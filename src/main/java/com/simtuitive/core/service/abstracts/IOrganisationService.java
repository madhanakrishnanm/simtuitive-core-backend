package com.simtuitive.core.service.abstracts;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.validator.internal.util.ConcurrentReferenceHashMap.Option;
import org.springframework.data.domain.Page;

import com.simtuitive.core.controller.requestpayload.OrganisationRequestPayload;
import com.simtuitive.core.controller.responsepayload.OrganisationResponsePayload;
import com.simtuitive.core.model.Organisation;

public interface IOrganisationService {

	public OrganisationResponsePayload addOrganisation(OrganisationRequestPayload payload);

	public OrganisationResponsePayload updateOrganisation(OrganisationRequestPayload payload);

	public OrganisationResponsePayload getOrganisation(String Id);

	public List<OrganisationResponsePayload> findAll(List<Organisation> organisations);
	
	public Page<Organisation>getAll(Optional<String>pageno, Optional<String> query, Optional<String> location, Optional<String> industry,Optional<String> name);
	
	public List<String>findAllOrganisationName(Optional<String> query);
	
	public List<String>findAllOrganisationLocation(Optional<String> query);
	
	public List<String>findAllOrganisationIndustry(Optional<String> query);
	
	public OrganisationResponsePayload deleteOrganisation(String Id);
	
	public boolean getExistsOrganisation(String name);
}
