package com.simtuitive.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.OrganisationRequestPayload;
import com.simtuitive.core.model.Organisation;
import com.simtuitive.core.repository.OrganisationRepository;
import com.simtuitive.core.repository.UserRepository;
import com.simtuitive.core.service.abstracts.IOrganisationService;

@Service
public class OrganisationServiceImpl extends BaseService implements IOrganisationService {
	@Autowired
	private UserRepository userrepository;

	@Autowired
	private OrganisationRepository organisationrepository;

	@Override
	public Organisation addOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub

		Organisation buildOrganisationNeeded = null;
		buildOrganisationNeeded = buildOrganisation(payload);
		Organisation createdOrganisation = organisationrepository.save(buildOrganisationNeeded);
		return createdOrganisation;
	}

	private Organisation buildOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub
		Organisation client = new Organisation(payload.getOrgName(), payload.getLocation(), payload.getIndustry(),
				payload.getClientDealOwnerName(), payload.getClientDealOwnerEmail(), payload.getClientDealOwnerMobile(),
				new Date(), payload.getStatus(), payload.getCreditLimit(), new Date(), payload.getModifiedBy());

		return client;
	}

	@Override
	public Organisation updateOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub
		Organisation clientupdate = modifyOrganisation(payload);
		return organisationrepository.save(clientupdate);
	}

	private Organisation modifyOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub
		Organisation needtobeupdate = getOrganisation(payload.getOrgId());
		needtobeupdate.setOrgName(payload.getOrgName());
		needtobeupdate.setModifiedBy(payload.getModifiedBy());
		needtobeupdate.setClientDealOwnerName(payload.getClientDealOwnerName());
		needtobeupdate.setClientDealOwnerEmail(payload.getClientDealOwnerEmail());
		needtobeupdate.setClientDealOwnerMobile(payload.getClientDealOwnerMobile());
		needtobeupdate.setModifiedDate(new Date());
		needtobeupdate.setIndustry(payload.getIndustry());
		needtobeupdate.setCreditLimit(payload.getCreditLimit());
		needtobeupdate.setStatus(payload.getStatus());
		return needtobeupdate;
	}

	@Override
	public Organisation getOrganisation(String Id) {
		// TODO Auto-generated method stub
		return organisationrepository.findByOrgId(Id);
	}

	@Override
	public List<Organisation> findAll() {
		// TODO Auto-generated method stub
		return organisationrepository.findAll();
	}

}
