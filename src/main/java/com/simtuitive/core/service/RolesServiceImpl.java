package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.RolesRequestPayload;
import com.simtuitive.core.controller.responsepayload.RolesResponsePayload;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.repository.RolesRepository;
import com.simtuitive.core.service.abstracts.IRolesService;

@Service
public class RolesServiceImpl extends BaseService implements IRolesService {

	@Autowired
	private RolesRepository rolesrepository;

	@Override
	public RolesResponsePayload addRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		Roles rolesneedtoadd = buildRole(payload);
		RolesResponsePayload respayload = buildpayload(rolesrepository.save(rolesneedtoadd));
		return respayload;
	}

	private RolesResponsePayload buildpayload(Roles rolesneedtoadd) {
		// TODO Auto-generated method stub
		RolesResponsePayload payload = new RolesResponsePayload(rolesneedtoadd.getRoleId(),
				rolesneedtoadd.getRoleName(), rolesneedtoadd.getDescription(), rolesneedtoadd.getCreatedOn(),
				rolesneedtoadd.getModifiedOn(), rolesneedtoadd.getStatus());
		return payload;
	}

	private Roles buildRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		System.out.println("Rolename"+payload.getRoleName());
		Roles role = new Roles(payload.getRoleName(), payload.getDescription(), new Date(), new Date(), 1L);
		return role;
	}

	@Override
	public RolesResponsePayload updateRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		Roles rolesneedtomodify = modifyRole(payload);
		RolesResponsePayload respayload = buildpayload(rolesrepository.save(rolesneedtomodify));
		return respayload;
	}

	private Roles modifyRole(RolesRequestPayload payload) {
		// TODO Auto-generated method stub
		Roles role = rolesrepository.findByRoleId(payload.getRoleId());
		role.setRoleName(payload.getRoleName());
		role.setDescription(payload.getDescription());
		return role;
	}

	@Override
	public RolesResponsePayload getRole(String roleid) {
		// TODO Auto-generated method stub
		Roles get = rolesrepository.findByRoleId(roleid);
		return buildpayload(get);
	}

	@Override
	public RolesResponsePayload deleteRole(String roleid) {
		// TODO Auto-generated method stub
		Roles role = rolesrepository.findByRoleId(roleid);
		role.setStatus(2L);
		rolesrepository.save(role);
		return buildpayload(role);
	}

	@Override
	public Page<Roles> getall(Optional<String> pageno) {
		// TODO Auto-generated method stub		
		int pagenumber=Integer.parseInt(pageno.orElse("0"));
		final Pageable pageable = PageRequest.of(pagenumber, 5,Sort.by("roleId").ascending());
		Query query = new Query();
		query.with(pageable);
		Page<Roles> pagerole=rolesrepository.findAll(pageable);
		
		return pagerole;
		
	}
	
	public List<RolesResponsePayload>getPayload(List<Roles> roles){
		List<RolesResponsePayload> result = new ArrayList<RolesResponsePayload>();
		for (Roles role : roles) {
			if (role.getStatus() == 1L) {
				result.add(buildpayload(role));
			}
		}
		return result;
	}

	@Override
	public Roles getRoleId(String role) {
		// TODO Auto-generated method stub
		return rolesrepository.findByRoleName(role);
	}

}
