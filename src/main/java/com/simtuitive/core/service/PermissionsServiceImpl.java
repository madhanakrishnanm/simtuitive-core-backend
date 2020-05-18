/**
 * 
 */
package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.PermissionsRequestPayload;
import com.simtuitive.core.controller.responsepayload.PermissionsResponsePayload;
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

	@Autowired
	private MongoOperations mongoOps;

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
	public List<PermissionsResponsePayload> findAll(List<Permissions> permlist) {
		List<PermissionsResponsePayload> result = new ArrayList<PermissionsResponsePayload>();
		for (Permissions perm : permlist) {

			PermissionsResponsePayload payload = buildPermissionsResponsePayload(perm);
			result.add(payload);
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

	@Override
	public Page<Permissions> getall(Optional<String> pageno, Optional<String> query, Optional<String> type,
			Optional<String> name,Optional<String> rolename) {
		// TODO Auto-generated method stub
		int pagenumber = Integer.parseInt(pageno.orElse("0"));
		final Pageable pageable = PageRequest.of(pagenumber, 5, Sort.by("rank").ascending());
		Criteria type1 = null, name1 = null, typecr = null, namecr = null,rolecr =null,rolecr1 =null,rolecr2 =null;
		Query query1 = new Query();
		if (query != null && query.isPresent()) {
			new Criteria();
			name1 = Criteria.where("name").regex(query.orElse(""), "i");
			new Criteria();
			type1 = Criteria.where("type").regex(query.orElse(""), "i");			
			Query query2search = new Query();
			new Criteria();
			Criteria rolecrsearch = Criteria.where("roleName").regex(query.orElse(""),"i");
			query2search.addCriteria(rolecrsearch);
			System.out.println("query"+query2search.toString());
			List<Roles>roleuisearch=mongoOps.find(query2search, Roles.class);
			List<String>roleidssearch=new ArrayList<String>();
			for(Roles role:roleuisearch) {
				roleidssearch.add(role.getRoleId());
			}
			//List<String>roleids=mongoOps.findDistinct(query2search, "roleId", Roles.class, String.class);
			System.out.println("query under rolename::"+query2search.toString());
			System.out.println("query under rolename List::"+roleidssearch);
			Query query3search = new Query();
			new Criteria();
			Criteria rolecrsearch1 = Criteria.where("roleid").in(roleidssearch);
			query3search.addCriteria(rolecrsearch1);			
			List<String>permissinidssearch=mongoOps.findDistinct(query3search, "permissionid", RoleHasPermission.class, String.class);
			System.out.println("query under rolename::"+query3search.toString());
			System.out.println("query under rolename List::"+permissinidssearch);			
			new Criteria();
			Criteria rolecrsearch2 = Criteria.where("permissionId").in(permissinidssearch);			
			query1.addCriteria(new Criteria().orOperator(name1, type1,rolecrsearch2));
			
		}
		if (type.isPresent()&&type!=null) {
			new Criteria();
			typecr = Criteria.where("type").is(type.get());
			query1.addCriteria(typecr);
		}
		if (name.isPresent()&&name!=null) {
			new Criteria();
			namecr = Criteria.where("name").is(name.get());
			query1.addCriteria(namecr);
		}
		if(rolename.isPresent()&&rolename!=null) {
			Query query2 = new Query();
			new Criteria();
			rolecr=Criteria.where("roleName").is(rolename.get());
			query2.addCriteria(rolecr);
			System.out.println("query"+query2.toString());
			List<Roles>roleui=mongoOps.find(query2, Roles.class);
			List<String>roleids=new ArrayList<String>();
			for(Roles role:roleui) {
				roleids.add(role.getRoleId());
			}
			//List<String>roleids=mongoOps.findDistinct(query2, "roleId", Roles.class, String.class);
			System.out.println("query under rolename::"+query2.toString());
			System.out.println("query under rolename List::"+roleids);
			Query query3 = new Query();
			new Criteria();
			rolecr1=Criteria.where("roleid").in(roleids);
			query3.addCriteria(rolecr1);			
			List<String>permissinids=mongoOps.findDistinct(query3, "permissionid", RoleHasPermission.class, String.class);
			System.out.println("query under rolename::"+query3.toString());
			System.out.println("query under rolename List::"+permissinids);			
			new Criteria();
			rolecr2=Criteria.where("permissionId").in(permissinids);
			query1.addCriteria(rolecr2);
		}
System.out.println("query"+query1.toString());
		query1.addCriteria(Criteria.where("status").is(1L));
		long count1 = mongoOps.count(query1, Permissions.class);
		System.out.println("totatlCount" + count1);
		query1.with(pageable);
		System.out.println("Permission query::" + query1.toString());
		List<Permissions> roleresult = mongoOps.find(query1, Permissions.class);
		System.out.println("Size" + roleresult.size());
		Page<Permissions> results = new PageImpl<Permissions>(roleresult, pageable, count1);
		return results;
	}

	@Override
	public Long countofPermission() {
		// TODO Auto-generated method stub
		Long count = permissionrepository.count();
		return count;
	}

	@Override
	public boolean permissionExistsByName(String name) {
		// TODO Auto-generated method stub
		return permissionrepository.existsByName(name);
	}

	@Override
	public boolean permissionExistsByType(String type) {
		// TODO Auto-generated method stub
		return permissionrepository.existsByType(type);
	}

	@Override
	public List<String> getPermissionTypeAll(Optional<String> query) {
		// TODO Auto-generated method stub
		List<String> typeall = new ArrayList<String>();
		if (query.isPresent()&&query!=null) {
			Query query1 = new Query();
			new Criteria();
			query1.addCriteria(Criteria.where("type").regex(query.orElse(""), "i"));
			System.out.println("query" + query1.toString());
			System.out.println(
					"MongoQuery new " + mongoOps.findDistinct(query1, "type", Permissions.class, String.class));
			typeall = mongoOps.findDistinct(query1, "type", Permissions.class, String.class);

		} else {
			typeall = mongoOps.findDistinct("type", Permissions.class, String.class);
		}
		return typeall;
	}

	@Override
	public List<String> getPermissionNameAll(Optional<String> query) {
		// TODO Auto-generated method stub
		List<String> nameall = new ArrayList<String>();
		if (query.isPresent()&&query!=null) {
			Query query1 = new Query();
			new Criteria();
			query1.addCriteria(Criteria.where("name").regex(query.orElse(""), "i"));
			System.out.println("query" + query1.toString());
			System.out.println(
					"MongoQuery new " + mongoOps.findDistinct(query1, "name", Permissions.class, String.class));
			nameall = mongoOps.findDistinct(query1, "name", Permissions.class, String.class);
		} else {
			nameall = mongoOps.findDistinct("name", Permissions.class, String.class);
		}

		return nameall;
	}

}
