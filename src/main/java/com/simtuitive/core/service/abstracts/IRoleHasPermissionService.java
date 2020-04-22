package com.simtuitive.core.service.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.RoleHasPermissionRequestPayload;
import com.simtuitive.core.model.RoleHasPermission;

public interface IRoleHasPermissionService {

	public RoleHasPermission addRolePermission(RoleHasPermissionRequestPayload payload);

	public RoleHasPermission updateRolePermission(RoleHasPermissionRequestPayload payload);

	public RoleHasPermission getPermission(String roleid);

	public List<RoleHasPermission> getpermissionByid(String roleid);
	
	public List<RoleHasPermission> getrolesByid(String permissionid);

}
