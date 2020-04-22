package com.simtuitive.core.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Organisation client = new Organisation(payload.getName(), payload.getLocation(), payload.getIndustry(),
				payload.getDealOwner(), payload.getDealOwnerEmail(), payload.getDealOwnerMobile(),
				new Date(), payload.getStatus(), payload.getCreditLimit(), new Date(),payload.getModifiedBy());

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
		Organisation needtobeupdate = getOrganisation(payload.getId());
		needtobeupdate.setOrganizationName((payload.getName()));
		needtobeupdate.setModifiedBy(payload.getModifiedBy());
		needtobeupdate.setDealOwnerName(payload.getDealOwner());
		needtobeupdate.setDealOwnerEmail(payload.getDealOwnerEmail());
		needtobeupdate.setDealOwnerMobile(payload.getDealOwnerMobile());
		needtobeupdate.setUpdatedAt((new Date()));
		needtobeupdate.setIndustry(payload.getIndustry());
		needtobeupdate.setCreditLimit(payload.getCreditLimit());
		needtobeupdate.setStatus(payload.getStatus());
		return needtobeupdate;
	}

	@Override
	public Organisation getOrganisation(String Id) {
		// TODO Auto-generated method stub
		return organisationrepository.findByOrganizationId(Id);
	}

	@Override
	public List<Organisation> findAll() {
		// TODO Auto-generated method stub
		return organisationrepository.findAll();
	}

	@Override
	public Map<String,String> findAllOrganisationName() {
		// TODO Auto-generated method stub
		List<Organisation>organisations=organisationrepository.findAll();
		Map<String,String>listorgname=new HashMap<>();
		for(Organisation org:organisations) {
			listorgname.put(org.getOrganizationId(), org.getOrganizationName());			
		}
		return listorgname;
	}

}
