package com.simtuitive.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simtuitive.core.model.License;

public interface LicenseRepository extends MongoRepository<License, String>{

	public License findByLicenseId(Long licenseId);
}
