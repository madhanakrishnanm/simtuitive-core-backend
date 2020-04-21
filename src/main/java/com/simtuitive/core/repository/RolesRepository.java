package com.simtuitive.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simtuitive.core.model.Roles;

public interface RolesRepository extends MongoRepository<Roles, String> {

	public Roles findByRoleid(String roleid);
	
	public Roles findByRolename(String rolename);

	
}
