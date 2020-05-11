package com.simtuitive.core.service.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.controller.responsepayload.RolesResponsePayload;
import com.simtuitive.core.model.Roles;

public interface IRolesService {

	public RolesResponsePayload addRole(RolesRequestPayload payload);

	public RolesResponsePayload updateRole(RolesRequestPayload payload);

	public RolesResponsePayload getRole(String roleid);
	
	public Roles getRoleId(String role);

	public RolesResponsePayload deleteRole(String roleid);

	public Page<Roles> getall(Optional<String> pageno);
	
	public Long countofRole();
	
	public boolean roleExists(String rolename);
}
