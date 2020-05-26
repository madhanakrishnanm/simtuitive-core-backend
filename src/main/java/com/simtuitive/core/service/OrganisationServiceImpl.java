package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
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

	@Autowired
	private MongoOperations mongoOps;

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
		Organisation client = organisationrepository.findByOrganizationId(Id);
		return buildOrganisationResponsePayload(client);
	}

	@Override
	public List<OrganisationResponsePayload> findAll(List<Organisation> organisations) {
		// TODO Auto-generated method stub
		List<OrganisationResponsePayload> result = new ArrayList<OrganisationResponsePayload>();

		for (Organisation org : organisations) {

			OrganisationResponsePayload value = buildOrganisationResponsePayload(org);
			result.add(value);

		}
		return result;
	}

	@Override
	public List<String> findAllOrganisationName(Optional<String> query) {
		// TODO Auto-generated method stub
		List<String> organizationName = new ArrayList<String>();
		if (query.isPresent()) {
			Query query1 = new Query();
			new Criteria();
			query1.addCriteria(Criteria.where("organizationName").regex(query.orElse(""), "i"));
			System.out.println("query" + query1.toString());
			System.out.println(
					"MongoQuery new " + mongoOps.findDistinct(query1, "organizationName", Organisation.class, String.class));
			organizationName = mongoOps.findDistinct(query1, "organizationName", Organisation.class, String.class);

		} else {
			organizationName = mongoOps.findDistinct("organizationName", Organisation.class, String.class);
		}
		return organizationName;
	}

	@Override
	public OrganisationResponsePayload deleteOrganisation(String Id) {
		// TODO Auto-generated method stub
		Organisation client = organisationrepository.findByOrganizationId(Id);
		client.setStatus("inactive");
		Organisation deleted = organisationrepository.save(client);
		return buildOrganisationResponsePayload(deleted);
	}

	@Override
	public Page<Organisation> getAll(Optional<String> pageno, Optional<String> query, Optional<String> location,
			Optional<String> industry, Optional<String> name) {
		// TODO Auto-generated method stub		
		int countofquery=0;
		int pagenumber = Integer.parseInt(pageno.orElse("0"));
		final Pageable pageable = PageRequest.of(pagenumber, 5, Sort.by("organizationId").ascending());
		Criteria org1 = null, location1 = null, industry1 = null, namecr = null, industrycr = null, locationcr1 = null;
		Query query1 = new Query();
		System.out.println("Check for query");
		System.out.println("query101"+query1.toString());
		if (query != null&&query.isPresent()&&!query.get().equalsIgnoreCase("null")) {
			new Criteria();
			org1 = Criteria.where("organizationName").regex(query.orElse(""), "i");
			new Criteria();
			location1 = Criteria.where("location").regex(query.orElse(""), "i");
			new Criteria();
			industry1 = Criteria.where("industry").regex(query.orElse(""), "i");
			countofquery++;
			query1.addCriteria(new Criteria().orOperator(org1, location1, industry1));
		}
		
		System.out.println("querybeforFilter" + query1.toString());
		if (location.isPresent()&&location!=null&&!location.get().equalsIgnoreCase("null")) {
			new Criteria();
			locationcr1 = Criteria.where("location").is(location.get());
			query1.addCriteria(locationcr1);
			countofquery++;
		}
		if (industry.isPresent()&&industry!=null&&!industry.get().equalsIgnoreCase("null")) {
			new Criteria();
			industrycr = Criteria.where("industry").is(industry.get());
			query1.addCriteria(industrycr);
			countofquery++;
		}
		if (name.isPresent()&&name!=null&&!name.get().equalsIgnoreCase("null")) {
			new Criteria();
			namecr = Criteria.where("organizationName").is(name.get());
			query1.addCriteria(namecr);
			countofquery++;
		}
		if(countofquery!=0) {
			long count = mongoOps.count(query1, Organisation.class);
			query1.with(pageable);
			System.out.println("count"+count);
			System.out.println("Organisation query" + query1.toString());		
			List<Organisation> orgresult = mongoOps.find(query1, Organisation.class);		
			Page<Organisation> result = new PageImpl<Organisation>(orgresult, pageable, count);
			return result;
		}else {
			query1.with(pageable);				
			List<Organisation> orgresult = mongoOps.find(query1, Organisation.class);
			int total=orgresult.size();	
			System.out.println("Count"+total);
			Page<Organisation> result = new PageImpl<Organisation>(orgresult, pageable, Long.valueOf(total));
			return result;
		}
		
	}

	@Override
	public boolean getExistsOrganisation(String name) {
		// TODO Auto-generated method stub
		return organisationrepository.existsByOrganizationName(name);
	}

	@Override
	public List<String> findAllOrganisationLocation(Optional<String> query) {
		// TODO Auto-generated method stub
		List<String> orglocation = new ArrayList<String>();
		if (query.isPresent()&&query!=null) {
			Query query1 = new Query();
			new Criteria();
			query1.addCriteria(Criteria.where("location").regex(query.orElse(""), "i"));
			System.out.println("query" + query1.toString());
			System.out.println(
					"MongoQuery new " + mongoOps.findDistinct(query1, "location", Organisation.class, String.class));
			orglocation = mongoOps.findDistinct(query1, "location", Organisation.class, String.class);
		} else {
			orglocation = mongoOps.findDistinct("location", Organisation.class, String.class);
		}
		return orglocation;
	}

	@Override
	public List<String> findAllOrganisationIndustry(Optional<String> query) {
		// TODO Auto-generated method stub
		List<String> industry = new ArrayList<String>();
		if (query.isPresent()&&query!=null) {
			Query query1 = new Query();
			new Criteria();
			query1.addCriteria(Criteria.where("industry").regex(query.orElse(""), "i"));
			System.out.println("query" + query1.toString());
			System.out.println(
					"MongoQuery new " + mongoOps.findDistinct(query1, "industry", Organisation.class, String.class));
			industry = mongoOps.findDistinct(query1, "industry", Organisation.class, String.class);
		} else {
			industry = mongoOps.findDistinct("industry", Organisation.class, String.class);
		}
		return industry;
	}

	@Override
	public Map<String, String> getAllOrgIdName() {
		// TODO Auto-generated method stub
		Map<String,String> result =new HashMap<String, String>();
		List<Organisation> orglist=organisationrepository.findAll();
		for(Organisation org:orglist) {
			result.put(org.getOrganizationId(), org.getOrganizationName());
		}
		return result;
	}

}
