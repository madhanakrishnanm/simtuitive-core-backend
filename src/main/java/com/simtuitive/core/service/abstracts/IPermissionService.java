package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.controller.responsepayload.PermissionsResponsePayload;
import com.simtuitive.core.model.Permissions;

public interface IPermissionService {
	public PermissionsResponsePayload create(PermissionsRequestPayload userrole);

	// Update Role
	public PermissionsResponsePayload update(PermissionsRequestPayload userrole);

	// GetAll Roles
	public List<PermissionsResponsePayload> findAll();
	
	public PermissionsResponsePayload get(String permissionId);
	
	public PermissionsResponsePayload delete(String permissionId);

}
