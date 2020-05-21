package com.simtuitive.core.service.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.simtuitive.core.controller.requestpayload.LicenseRequestPayload;
import com.simtuitive.core.controller.responsepayload.LicenseResponsePayload;
import com.simtuitive.core.model.License;

public interface ILicenseService {

	public LicenseResponsePayload addLicense(LicenseRequestPayload payload);
	public LicenseResponsePayload updateLicense(LicenseRequestPayload payload);
	public LicenseResponsePayload getLicense(String Id);
	public LicenseResponsePayload deleteLicense(String Id);
	public Page<License>getAll(Optional<String>pageno, Optional<String> query, Optional<String> product, Optional<String> orgName,Optional<String> status);
	public List<LicenseResponsePayload>findAll(List<License>licenselist);
}
