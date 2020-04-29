/**
 * 
 */
package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.controller.responsepayload.PermissionsResponsePayload;
import com.simtuitive.core.controller.responsepayload.RolesResponsePayload;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.repository.PermissionsRepository;
import com.simtuitive.core.repository.RoleHasPermissionRepository;
import com.simtuitive.core.repository.RolesRepository;
import com.simtuitive.core.service.abstracts.IPermissionService;

/**
 * @author Veeramani N S
 *
 */
@Service
public class PermissionsServiceImpl implements IPermissionService {

	@Autowired
	private PermissionsRepository permissionrepository;

	@Autowired
	private RoleHasPermissionRepository rolehaspermissionrepository;

	@Autowired
	private RolesRepository rolerepository;

	// Create Role
	@Override
	public PermissionsResponsePayload create(PermissionsRequestPayload userrole) {

		Permissions userroletobecreate = buildPermisssion(userrole);
		Permissions created = permissionrepository.save(userroletobecreate);
		List<String> roleids = userrole.getRoleIds();
		for (String role : roleids) {
			RoleHasPermission permission = new RoleHasPermission(role, created.getPermissionId());
			rolehaspermissionrepository.save(permission);
		}
		PermissionsResponsePayload payload = buildPermissionsResponsePayload(created);
		return payload;
	}

	private PermissionsResponsePayload buildPermissionsResponsePayload(Permissions created) {
		// TODO Auto-generated method stub
		List<Roles> mapping = new ArrayList<Roles>();
		List<RoleHasPermission> permissions = rolehaspermissionrepository.findByPermissionid(created.getPermissionId());
		List<Roles> roleresponse = new ArrayList<>();
		Roles rolepermission = null;
		for (RoleHasPermission per : permissions) {
			rolepermission = rolerepository.findByRoleId(per.getRoleid());
			roleresponse.add(rolepermission);
		}
		for (Roles role : roleresponse) {
			role.setModifiedOn(null);
			role.setDescription(null);
			role.setStatus(null);
			role.setCreatedOn(null);
			mapping.add(role);
		}
		PermissionsResponsePayload payload = new PermissionsResponsePayload(created.getPermissionId(),
				created.getName(), created.getType(), created.getDescription(), created.getCreatedOn(),
				created.getModifiedOn(), created.getStatus(), created.getRank(), mapping);
		return payload;
	}

	// Update Role
	@Override
	public PermissionsResponsePayload update(PermissionsRequestPayload userrole) {
		Permissions userroleupdate = modifyUserRole(userrole);
		Permissions updated = permissionrepository.save(userroleupdate);
		List<String> roleids = userrole.getRoleIds();
		List<RoleHasPermission> oldlist = rolehaspermissionrepository.findByPermissionid(userrole.getPermissionId());

		for (RoleHasPermission needtodelete : oldlist) {
			rolehaspermissionrepository.deleteByPermissionid(needtodelete.getPermissionid());
			System.out.println("coming here");
		}
		for (String role : roleids) {
			RoleHasPermission permission = new RoleHasPermission(role, userrole.getPermissionId());
			rolehaspermissionrepository.save(permission);
		}

		PermissionsResponsePayload payload = buildPermissionsResponsePayload(updated);
		return payload;
	}

	// GetAll Roles
	@Override
	public List<PermissionsResponsePayload> findAll() {
		List<Permissions> permlist = permissionrepository.findAll();
		List<PermissionsResponsePayload> result = new ArrayList<PermissionsResponsePayload>();
		for (Permissions perm : permlist) {
			if (perm.getStatus() == 1L) {
				PermissionsResponsePayload payload = buildPermissionsResponsePayload(perm);
				result.add(payload);
			}
		}
		return result;
	}

	private Permissions buildPermisssion(PermissionsRequestPayload payload) {
		Permissions userrole = new Permissions(payload.getName(), payload.getType(), payload.getDescription(),
				new Date(), new Date(), payload.getRank(), 1L);
		return userrole;
	}

	private Permissions modifyUserRole(PermissionsRequestPayload payload) {
		Permissions userroleupdate = permissionrepository.findBypermissionId(payload.getPermissionId());
		userroleupdate.setModifiedOn(new Date());
		userroleupdate.setName(payload.getName());
		userroleupdate.setRank(payload.getRank());
		userroleupdate.setDescription(payload.getDescription());
		userroleupdate.setType(payload.getType());
		return userroleupdate;
	}

	@Override
	public PermissionsResponsePayload get(String permissionId) {
		// TODO Auto-generated method stub
		Permissions get = permissionrepository.findBypermissionId(permissionId);
		PermissionsResponsePayload payload = buildPermissionsResponsePayload(get);
		return payload;
	}

	@Override
	public PermissionsResponsePayload delete(String permissionId) {
		// TODO Auto-generated method stub
		Permissions get = permissionrepository.findBypermissionId(permissionId);
		get.setStatus(2L);
		permissionrepository.save(get);
		PermissionsResponsePayload payload = buildPermissionsResponsePayload(get);
		return payload;
	}

}
