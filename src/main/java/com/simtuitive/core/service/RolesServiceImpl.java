package com.simtuitive.core.service;

import java.util.Date;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.repository.RolesRepository;
import com.simtuitive.core.service.abstracts.IRolesService;
@Service
public class RolesServiceImpl extends BaseService implements IRolesService{

	
	@Autowired
	private RolesRepository rolesrepository;
	@Override
	public Roles addRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		Roles rolesneedtoadd=buildRole(payload);
		return rolesrepository.save(rolesneedtoadd);
	}

	private Roles buildRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		Roles role= new Roles(payload.getRolename(), payload.getDescription(), new Date(), new Date());
		return role;
	}

	@Override
	public Roles updateRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		
		Roles rolesneedtomodify=modifyRole(payload);
		return rolesrepository.save(rolesneedtomodify);
	}

	private Roles modifyRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		Roles role=getRole(payload.getRoleid());
		role.setRolename(payload.getRolename());
		role.setDescription(payload.getDescription());
		return role;
	}

	@Override
	public Roles getRole(String roleid) {
		// TODO Auto-generated method stub
		return rolesrepository.findByRoleid(roleid);
	}

	@Override
	public void deleteRole(String roleid) {
		// TODO Auto-generated method stub
		Roles role=getRole(roleid);
		rolesrepository.delete(role);
	}

	@Override
	public List<Roles> getall() {
		// TODO Auto-generated method stub
		return rolesrepository.findAll();
	}

}
