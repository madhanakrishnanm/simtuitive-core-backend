package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.OrganisationRequestPayload;
import com.simtuitive.core.model.Organisation;

public interface IOrganisationService {

	public Organisation addOrganisation(OrganisationRequestPayload payload);

	public Organisation updateOrganisation(OrganisationRequestPayload payload);

	public Organisation getOrganisation(String Id);

	public List<Organisation> findAll();
}
