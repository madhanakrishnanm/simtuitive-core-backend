package com.simtuitive.core.service.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.controller.responsepayload.PermissionsResponsePayload;
import com.simtuitive.core.model.Permissions;

public interface IPermissionService {
	public PermissionsResponsePayload create(PermissionsRequestPayload userrole);

	// Update Role
	public PermissionsResponsePayload update(PermissionsRequestPayload userrole);

	// GetAll Roles
	public List<PermissionsResponsePayload> findAll(List<Permissions> permlist);
	
	public Page<Permissions> getall(Optional<String> pageno,Optional<String> query,Optional<String> type,Optional<String> name);
	
	public PermissionsResponsePayload get(String permissionId);
	
	public PermissionsResponsePayload delete(String permissionId);

	public Long countofPermission();
	
	public boolean permissionExistsByName(String name);
	
	public boolean permissionExistsByType(String type);
	
	public List<String> getPermissionTypeAll(Optional<String> query);
	
	public List<String> getPermissionNameAll(Optional<String> query);
}
