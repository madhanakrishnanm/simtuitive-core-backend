package com.simtuitive.core.service.abstracts;

import java.util.List;
import java.util.Map;

import com.simtuitive.core.controller.requestpayload.OrganisationRequestPayload;
import com.simtuitive.core.controller.responsepayload.OrganisationResponsePayload;
import com.simtuitive.core.model.Organisation;

public interface IOrganisationService {

	public OrganisationResponsePayload addOrganisation(OrganisationRequestPayload payload);

	public OrganisationResponsePayload updateOrganisation(OrganisationRequestPayload payload);

	public OrganisationResponsePayload getOrganisation(String Id);

	public List<OrganisationResponsePayload> findAll();
	
	public Map<String,String>findAllOrganisationName();
}
