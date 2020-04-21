/**
 * 
 */
package com.simtuitive.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.repository.PermissionsRepository;
import com.simtuitive.core.service.abstracts.IPermissionService;


/**
 * @author Veeramani N S
 *
 */
@Service
public class PermissionsServiceImpl implements IPermissionService {

	@Autowired
	private PermissionsRepository permissionrepository;

	// Create Role
	@Override
	public Permissions create(PermissionsRequestPayload userrole) {
		Permissions userroletobecreate = buildPermisssion(userrole);
		return permissionrepository.save(userroletobecreate);
	}

	// Update Role
	@Override
	public Permissions update(PermissionsRequestPayload userrole) {
		Permissions userroleupdate = modifyUserRole(userrole);
		return permissionrepository.save(userroleupdate);
	}

	// GetAll Roles
	@Override
	public List<Permissions> findAll() {
		return permissionrepository.findAll();
	}

	private Permissions buildPermisssion(PermissionsRequestPayload payload) {				
		Permissions userrole = new Permissions(payload.getName(), payload.getType(), payload.getDescription(), new Date(), new Date());
		return userrole;
	}

	private Permissions modifyUserRole(PermissionsRequestPayload payload) {
		Permissions userroleupdate = permissionrepository.findBypermissionId(payload.getPermissionId());		
		return userroleupdate;
	}

	@Override
	public Permissions get(String permissionId) {
		// TODO Auto-generated method stub
		return permissionrepository.findBypermissionId(permissionId);
	}
}
