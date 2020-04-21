package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.model.Permissions;

public interface IPermissionService {
	public Permissions create(PermissionsRequestPayload userrole);

	// Update Role
	public Permissions update(PermissionsRequestPayload userrole);

	// GetAll Roles
	public List<Permissions> findAll();
	
	public Permissions get(String permissionId);

}
