package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.controller.responsepayload.RolesResponsePayload;
import com.simtuitive.core.model.Roles;

public interface IRolesService {

	public RolesResponsePayload addRole(RolesRequestPayload payload);

	public RolesResponsePayload updateRole(RolesRequestPayload payload);

	public RolesResponsePayload getRole(String roleid);
	
	public Roles getRoleId(String role);

	public RolesResponsePayload deleteRole(String roleid);

	public List<RolesResponsePayload> getall();
}
