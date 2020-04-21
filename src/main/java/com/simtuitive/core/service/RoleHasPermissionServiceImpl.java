package com.simtuitive.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.RoleHasPermissionRequestPayload;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.repository.RoleHasPermissionRepository;
import com.simtuitive.core.service.abstracts.IRoleHasPermissionService;
@Service
public class RoleHasPermissionServiceImpl extends BaseService implements IRoleHasPermissionService{

	@Autowired
	private RoleHasPermissionRepository repository;
	
	@Override
	public RoleHasPermission addRolePermission(RoleHasPermissionRequestPayload payload) {
		// TODO Auto-generated method stub
		RoleHasPermission permission =new RoleHasPermission(payload.getRoleid(), payload.getPermissionid());
		return repository.save(permission);
	}

	@Override
	public RoleHasPermission updateRolePermission(RoleHasPermissionRequestPayload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleHasPermission getPermission(String roleid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleHasPermission> getpermissionByid(String roleid) {
		// TODO Auto-generated method stub
		return null;
	}

}
