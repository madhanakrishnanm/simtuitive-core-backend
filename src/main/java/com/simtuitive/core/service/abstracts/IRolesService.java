package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.model.Roles;

public interface IRolesService {

	public Roles addRole(RolesRequestPayload payload);

	public Roles updateRole(RolesRequestPayload payload);

	public Roles getRole(String roleid);
	
	public Roles getRoleId(String role);

	public void deleteRole(String roleid);

	public List<Roles> getall();
}
