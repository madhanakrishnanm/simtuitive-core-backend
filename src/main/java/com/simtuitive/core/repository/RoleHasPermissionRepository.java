package com.simtuitive.core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simtuitive.core.model.RoleHasPermission;

public interface RoleHasPermissionRepository extends MongoRepository<RoleHasPermission, String>{

	public List<RoleHasPermission> findByRoleid(String roleid);
	public List<RoleHasPermission> findByPermissionid(String permissionid);
	
	public void deleteByPermissionid(String permissionid);
	
}
