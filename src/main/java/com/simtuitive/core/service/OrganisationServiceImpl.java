package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.OrganisationRequestPayload;
import com.simtuitive.core.controller.responsepayload.OrganisationResponsePayload;
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
	public OrganisationResponsePayload addOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub

		Organisation buildOrganisationNeeded = null;
		buildOrganisationNeeded = buildOrganisation(payload);
		Organisation createdOrganisation = organisationrepository.save(buildOrganisationNeeded);
		OrganisationResponsePayload result = buildOrganisationResponsePayload(createdOrganisation);
		return result;
	}

	private OrganisationResponsePayload buildOrganisationResponsePayload(Organisation createdOrganisation) {
		// TODO Auto-generated method stub
		OrganisationResponsePayload addresponse = new OrganisationResponsePayload(
				createdOrganisation.getOrganizationName(), createdOrganisation.getOrganizationId(),
				createdOrganisation.getLocation(), createdOrganisation.getIndustry(),
				createdOrganisation.getDealOwnerName(), createdOrganisation.getDealOwnerEmail(),
				createdOrganisation.getDealOwnerMobile(), createdOrganisation.getCreatedAt(),
				createdOrganisation.getStatus(), createdOrganisation.getCreditLimit(),
				createdOrganisation.getUpdatedAt(), createdOrganisation.getModifiedBy());
		return addresponse;
	}

	private Organisation buildOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub
		Organisation client = new Organisation(payload.getName(), payload.getLocation(), payload.getIndustry(),
				payload.getDealOwner(), payload.getDealOwnerEmail(), payload.getDealOwnerMobile(), new Date(),
				payload.getStatus(), payload.getCreditLimit(), new Date(), payload.getModifiedBy());

		return client;
	}

	@Override
	public OrganisationResponsePayload updateOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub
		Organisation clientupdate = modifyOrganisation(payload);
		Organisation clientupdated = organisationrepository.save(clientupdate);
		return buildOrganisationResponsePayload(clientupdated);
	}

	private Organisation modifyOrganisation(OrganisationRequestPayload payload) {
		// TODO Auto-generated method stub
		Organisation needtobeupdate = organisationrepository.findByOrganizationId(payload.getId());
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
	public OrganisationResponsePayload getOrganisation(String Id) {
		// TODO Auto-generated method stub
		Organisation client=organisationrepository.findByOrganizationId(Id);
		return buildOrganisationResponsePayload(client);
	}

	@Override
	public List<OrganisationResponsePayload> findAll(List<Organisation> organisations) {
		// TODO Auto-generated method stub
		List<OrganisationResponsePayload> result=new ArrayList<OrganisationResponsePayload>();
		
		for(Organisation org:organisations) {
			
				OrganisationResponsePayload value=buildOrganisationResponsePayload(org);
				result.add(value);	
			
			
		}
		return result;
	}

	@Override
	public Map<String, String> findAllOrganisationName() {
		// TODO Auto-generated method stub	
		Map<String, String> listorgname = new HashMap<>();
		List<Organisation>organisations=organisationrepository.findAll();
		for (Organisation org : organisations) {
			listorgname.put(org.getOrganizationId(), org.getOrganizationName());
		}
		return listorgname;
	}

	@Override
	public OrganisationResponsePayload deleteOrganisation(String Id) {
		// TODO Auto-generated method stub
		Organisation client=organisationrepository.findByOrganizationId(Id);
		client.setStatus("inactive");
		Organisation deleted=organisationrepository.save(client);
		return buildOrganisationResponsePayload(deleted);
	}

	@Override
	public Page<Organisation> getAll(Optional<String> pageno) {
		// TODO Auto-generated method stub
		int pagenumber=Integer.parseInt(pageno.orElse("0"));
		final Pageable pageable = PageRequest.of(pagenumber, 5,Sort.by("organizationId").ascending());
		
		Query query = new Query();
		query.with(pageable);	
		return organisationrepository.findAll(pageable);
	}

}
