package com.simtuitive.core.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.LicenseRequestPayload;
import com.simtuitive.core.controller.responsepayload.LicenseResponsePayload;
import com.simtuitive.core.model.License;
import com.simtuitive.core.repository.LicenseRepository;
import com.simtuitive.core.service.abstracts.ILicenseService;
@Service
public class LicenseServiceImpl extends BaseService implements ILicenseService{

	
	@Autowired
	private LicenseRepository licensrepository;
	
	@Autowired
	private MongoOperations mongoOps;
	
	
	@Override
	public LicenseResponsePayload addLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		License buildLicenseNeeded=null;
		buildLicenseNeeded=buildLicense(payload);					
		List<Long>licensidsgen=mongoOps.findDistinct("licenseId", License.class, Long.class);		
		
		if(licensidsgen.isEmpty()) {
			buildLicenseNeeded.setLicenseId(100000L);
		}else {
			Long id=Collections.max(licensidsgen);
			buildLicenseNeeded.setLicenseId(id+1);
		}		
		License createdLicense=licensrepository.save(buildLicenseNeeded);
		LicenseResponsePayload result=buildLicenseResponsePayload(createdLicense);
		return result;
	}

	private LicenseResponsePayload buildLicenseResponsePayload(License createdLicense) {
		// TODO Auto-generated method stub
		LicenseResponsePayload addresponse= new LicenseResponsePayload(createdLicense.getLicenseId(), createdLicense.getOrgName(), createdLicense.getOrgId(), createdLicense.getProductName(), createdLicense.getProductId(), createdLicense.getPaymentStatus(), createdLicense.getCreditLimit(), createdLicense.getNarration(), createdLicense.getSellingPrice(), createdLicense.getDealSize(), createdLicense.getNumberOfLicense(), createdLicense.getCreatedAt(), createdLicense.getModifiedAt(), createdLicense.getCreatedBy(), createdLicense.getModifiedBy(),createdLicense.getStatus());
		return addresponse;
	}

	private License buildLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		License license= new License(payload.getOrganization(), payload.getOrgId(), payload.getProductName(), payload.getProductId(), payload.getPaymentStatus(), payload.getCreditLimit(), payload.getNarration(), payload.getSellingPrice(), payload.getDealSize(), payload.getNumberOfLicense(), new Date(), new Date(), payload.getCreatedBy(), payload.getModifiedBy(),"active");
		return license;
	}

	@Override
	public LicenseResponsePayload updateLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		System.out.println("changes coming");
		License licenseupdate=modifyLicense(payload);
		License licenseupdated=licensrepository.save(licenseupdate);
		return buildLicenseResponsePayload(licenseupdated);
	}

	private License modifyLicense(LicenseRequestPayload payload) {
		// TODO Auto-generated method stub
		License licenseupdateneeded=licensrepository.findByLicenseId(payload.getLicenseId());
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
	public LicenseResponsePayload getLicense(Long Id) {
		// TODO Auto-generated method stub
		License license=licensrepository.findByLicenseId(Id);
		return buildLicenseResponsePayload(license);
	}

	@Override
	public LicenseResponsePayload deleteLicense(Long Id) {
		// TODO Auto-generated method stub
		License license=licensrepository.findByLicenseId(Id);
		license.setStatus("in_active");
		License licensedelete=licensrepository.save(license);
		return buildLicenseResponsePayload(licensedelete);
	}

	@Override
	public Page<License> getAll(Optional<String> pageno, Optional<String> query, Optional<String> product,
			Optional<String> orgName, Optional<String> status) {
		// TODO Auto-generated method stub
		return null;
	}

}
