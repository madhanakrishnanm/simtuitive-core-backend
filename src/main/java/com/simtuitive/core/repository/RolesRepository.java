package com.simtuitive.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.simtuitive.core.model.Roles;

public interface RolesRepository extends MongoRepository<Roles, String> {

	public Roles findByRoleId(String roleid);
	
	public Roles findByRoleName(String rolename);	
	
	public boolean existsByRoleName(String rolename);
	
	public Page<Roles> findAllByStatus(Long status, Pageable pageable);
}
