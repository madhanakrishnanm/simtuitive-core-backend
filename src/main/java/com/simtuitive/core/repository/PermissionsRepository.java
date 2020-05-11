package com.simtuitive.core.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.simtuitive.core.model.Permissions;

@Repository
public interface PermissionsRepository extends MongoRepository<Permissions, String> {

	
	public Permissions findBypermissionId(String permissionId);
	
	public boolean existsByName(String name);
	
	public boolean existsByType(String type);
	
}