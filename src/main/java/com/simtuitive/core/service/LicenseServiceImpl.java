package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.BsonRegularExpression;
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

import com.simtuitive.core.controller.requestpayload.LicenseRequestPayload;
import com.simtuitive.core.controller.responsepayload.LicenseResponsePayload;
import com.simtuitive.core.model.License;
import com.simtuitive.core.repository.LicenseRepository;
import com.simtuitive.core.service.abstracts.ILicenseService;

@Service
public class LicenseServiceImpl extends BaseService implements ILicenseService {

	@Autowired
	private LicenseRepository licensrepository;

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public LicenseResponsePayload addLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		License buildLicenseNeeded = null;
		buildLicenseNeeded = buildLicense(payload);
		List<String> licensidsgen = mongoOps.findDistinct("licenseId", License.class, String.class);
		List<Long> newcollected = new ArrayList<Long>();
		for (String s : licensidsgen) {
			newcollected.add(Long.valueOf(s));
		}

		if (licensidsgen.isEmpty()) {
			Long idvalue = 100000L;
			buildLicenseNeeded.setLicenseId(Long.toString(idvalue));
		} else {
			Long value = Collections.max(newcollected) + 1;
			buildLicenseNeeded.setLicenseId(value.toString());
		}
		License createdLicense = licensrepository.save(buildLicenseNeeded);
		LicenseResponsePayload result = buildLicenseResponsePayload(createdLicense);
		return result;
	}

	private LicenseResponsePayload buildLicenseResponsePayload(License createdLicense) {
		// TODO Auto-generated method stub
		LicenseResponsePayload addresponse = new LicenseResponsePayload(createdLicense.getLicenseId(),
				createdLicense.getOrgName(), createdLicense.getOrgId(), createdLicense.getProductName(),
				createdLicense.getProductId(), createdLicense.getPaymentStatus(), createdLicense.getCreditLimit(),
				createdLicense.getNarration(), createdLicense.getSellingPrice(), createdLicense.getDealSize(),
				createdLicense.getNumberOfLicense(), createdLicense.getCreatedAt(), createdLicense.getModifiedAt(),
				createdLicense.getCreatedBy(), createdLicense.getModifiedBy(), createdLicense.getStatus());
		return addresponse;
	}

	private License buildLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		License license = new License(payload.getOrganization(), payload.getOrgId(), payload.getProductName(),
				payload.getProductId(), payload.getPaymentStatus(), payload.getCreditLimit(), payload.getNarration(),
				payload.getSellingPrice(), payload.getDealSize(), payload.getNumberOfLicense(), new Date(), new Date(),
				payload.getCreatedBy(), payload.getModifiedBy(), "active");
		return license;
	}

	@Override
	public LicenseResponsePayload updateLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		System.out.println("changes coming");
		License licenseupdate = modifyLicense(payload);
		License licenseupdated = licensrepository.save(licenseupdate);
		return buildLicenseResponsePayload(licenseupdated);
	}

	private License modifyLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		License licenseupdateneeded = licensrepository.findByLicenseId(payload.getLicenseId());
		System.out.println("id");
		licenseupdateneeded.setCreditLimit(payload.getCreditLimit());
		licenseupdateneeded.setDealSize(payload.getDealSize());
		licenseupdateneeded.setModifiedAt(new Date());
		licenseupdateneeded.setModifiedBy(payload.getModifiedBy());
		licenseupdateneeded.setNarration(payload.getNarration());
		licenseupdateneeded.setNumberOfLicense(payload.getNumberOfLicense());
		licenseupdateneeded.setOrgId(payload.getOrgId());
		licenseupdateneeded.setOrgName(payload.getOrganization());
		licenseupdateneeded.setPaymentStatus(payload.getPaymentStatus());
		licenseupdateneeded.setProductId(payload.getProductId());
		licenseupdateneeded.setProductName(payload.getProductName());
		licenseupdateneeded.setSellingPrice(payload.getSellingPrice());
		return licenseupdateneeded;
	}

	@Override
	public LicenseResponsePayload getLicense(String Id) {
		// TODO Auto-generated method stub
		License license = licensrepository.findByLicenseId(Id);
		return buildLicenseResponsePayload(license);
	}

	@Override
	public LicenseResponsePayload deleteLicense(String Id) {
		// TODO Auto-generated method stub
		License license = licensrepository.findByLicenseId(Id);
		license.setStatus("in_active");
		License licensedelete = licensrepository.save(license);
		return buildLicenseResponsePayload(licensedelete);
	}

	@Override
	public Page<License> getAll(Optional<String> pageno, Optional<String> query, Optional<String> product,
			Optional<String> orgName, Optional<String> status) {
		// TODO Auto-generated method stub
		int pagenumber = Integer.parseInt(pageno.orElse("0"));
		final Pageable pageable = PageRequest.of(pagenumber, 5, Sort.by("licenseId").ascending());
		Query query1 = new Query();
		if (query != null && query.isPresent() && !query.get().equalsIgnoreCase("null")) {
			new Criteria();
			Criteria nar1 = Criteria.where("narration").regex(query.orElse(""), "i");
			new Criteria();
			Criteria prod = Criteria.where("productName").regex(query.orElse(""), "i");
			new Criteria();
			Criteria orgname = Criteria.where("orgName").regex(query.orElse(""), "i");
			new Criteria();
			Criteria licensid = Criteria.where("licenseId").regex(query.orElse(""), "i");
			query1.addCriteria(new Criteria().orOperator(nar1, prod, orgname, licensid));
		}
		if (product.isPresent() && product != null && !product.get().equalsIgnoreCase("null")) {
			new Criteria();
			Criteria productcr = Criteria.where("productName").is(product.get());
			query1.addCriteria(productcr);

		}
		if (orgName.isPresent() && orgName != null && !orgName.get().equalsIgnoreCase("null")) {
			new Criteria();
			Criteria orgNamecr = Criteria.where("orgName").is(orgName.get());
			query1.addCriteria(orgNamecr);

		}
		if (status.isPresent() && status != null && !status.get().equalsIgnoreCase("null")) {
			new Criteria();
			Criteria statuscr = Criteria.where("paymentStatus").is(status.get());
			query1.addCriteria(statuscr);

		}
		new Criteria();
		System.out.println("License query" + query1.toString());
		query1.addCriteria(Criteria.where("status").is("active"));
		long count = mongoOps.count(query1, License.class);
		query1.with(pageable);
		List<License> licresult = mongoOps.find(query1, License.class);
		Page<License> result = new PageImpl<License>(licresult, pageable, count);

		return result;
	}

	@Override
	public List<LicenseResponsePayload> findAll(List<License> licenselist) {
		// TODO Auto-generated method stub
		List<LicenseResponsePayload> result = new ArrayList<LicenseResponsePayload>();
		for (License lic : licenselist) {
			LicenseResponsePayload value = buildLicenseResponsePayload(lic);
			result.add(value);
		}
		return result;
	}

}
