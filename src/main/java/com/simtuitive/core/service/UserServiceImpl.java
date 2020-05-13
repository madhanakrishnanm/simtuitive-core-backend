package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.jaxb.SortAdapter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.controller.responsepayload.UserResponsePayload;
import com.simtuitive.core.model.Organisation;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.model.User;
import com.simtuitive.core.repository.OrganisationRepository;
import com.simtuitive.core.repository.PermissionsRepository;
import com.simtuitive.core.repository.RoleHasPermissionRepository;
import com.simtuitive.core.repository.RolesRepository;
import com.simtuitive.core.repository.UserRepository;
import com.simtuitive.core.service.abstracts.IUserService;
import com.simtuitive.core.util.SortbyRank;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	private UserRepository userrepository;
	
	@Autowired 
	private MongoOperations mongoOps;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleHasPermissionRepository roleHasPermissionRepository;

	@Autowired
	private PermissionsRepository permissionsRepository;
	@Autowired
	private RolesRepository roleRepository;
	@Autowired
	private OrganisationRepository orgrepository;

	// Get User By Email
	@Override
	public UserResponsePayload getUser(String email) {
		UserResponsePayload returnpayload = null;
		User user = userrepository.findByuserEmail(email);
		System.out.println("user"+user.getUserId());
		returnpayload = buildPayloadbyUser(user);
		return returnpayload;
	}

	public UserResponsePayload buildPayloadbyUser(User user) {
		// TODO Auto-generated method stub
		UserResponsePayload payload = null;
		if (user.getRole().equalsIgnoreCase("ADMIN") || user.getRole().equalsIgnoreCase("Super Admin")) {
			payload = new UserResponsePayload(user.getUserId(), user.getUserName(), user.getUserEmail(),
					null, user.getStatus(), user.getPermissions(), user.getRole(),user.getLastLoggedIn());
		}
		if (user.getRole().equalsIgnoreCase("CLIENT")) {
			System.out.println("welcome issue"+user.getOrgId());
			Organisation org=orgrepository.findByOrganizationId(user.getOrgId());
			System.out.println("welcome issue");
			System.out.println("organisaton"+org.getOrganizationId());
			payload=new UserResponsePayload(user.getUserId(),user.getUserName(), user.getUserEmail(), user.getOrgId(),
					null, user.getStatus(), user.getCreatedDate(),
					user.getClientGst(), user.getClientPan(),user.getPermissions(), user.getRole(),org.getOrganizationName(),user.getLastLoggedIn());
		}		
		return payload;
	}
	
	

	@Override
	public UserResponsePayload addUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User buildUserNeedsToBeCreated = null;
		if (UserRequestPayload.getRole().equalsIgnoreCase("ADMIN")
				|| UserRequestPayload.getRole().equalsIgnoreCase("Super Admin")) {
			buildUserNeedsToBeCreated = buildAdminUser(UserRequestPayload);
		}
		if (UserRequestPayload.getRole().equalsIgnoreCase("CLIENT")) {
			System.out.println("welcome client");
			buildUserNeedsToBeCreated = buildClientUser(UserRequestPayload);
		}
		if (UserRequestPayload.getRole().equalsIgnoreCase("Learner")) {
			buildUserNeedsToBeCreated = buildLearnerUser(UserRequestPayload);
		}
		User savedUser = userrepository.save(buildUserNeedsToBeCreated);
		UserResponsePayload newuser= buildPayloadbyUser(savedUser);
		return newuser;
	}

	@Override
	public UserResponsePayload updateUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User updateuser = null;
		if (UserRequestPayload.getRole().equalsIgnoreCase("ADMIN")
				|| UserRequestPayload.getRole().equalsIgnoreCase("Super Admin")) {
			updateuser = modifyAdminUser(UserRequestPayload);
		}
		if (UserRequestPayload.getRole().equalsIgnoreCase("CLIENT")) {
			updateuser = modifyClientUser(UserRequestPayload);
		}
		User savedUser =userrepository.save(updateuser);
		UserResponsePayload updateUser= buildPayloadbyUser(savedUser);
		return updateUser;
	}

	@Override
	public UserResponsePayload changePasswordUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponsePayload> getAllUser(String userType,int pageno) {
		List<UserResponsePayload>result=new ArrayList<UserResponsePayload>();
		final Pageable pageable = PageRequest.of(pageno, 5,Sort.by("userId").ascending());
	
		Query query = new Query();
		query.with(pageable);		
		
		Page<User> pageuserlist=(Page<User>) userrepository.findByRoleAndStatus(userType,pageable,1L);
		
		List<User>userlist=pageuserlist.getContent();
		System.out.println("userlist"+userlist.toString());
		if (userType.equalsIgnoreCase("ADMIN")
				|| userType.equalsIgnoreCase("Super Admin")) {
			for(User user:userlist) {
				
				if(user.getStatus()==1L) {
					UserResponsePayload payload=buildPayloadbyUser(user);
					result.add(payload);
				}
				
			}
		}
		if (userType.equalsIgnoreCase("CLIENT")) {
			System.out.println("checking coming");
			for(User user:userlist) {				
				if(user.getStatus()==1L) {
					System.out.println("checking"+user.getUserId());
					UserResponsePayload payload=buildPayloadbyUser(user);
					result.add(payload);
				}
			}
		}
		return result;
	}

	
	@Override
	public Page<User> getAllUserByPaginationApplied(String userType,Optional<String> pageno) {
		int pagenumber=Integer.parseInt(pageno.orElse("0"));
		final Pageable pageable = PageRequest.of(pagenumber, 5,Sort.by("userId").ascending());		
		Query query = new Query();
		query.with(pageable);
		query.addCriteria(new Criteria().orOperator(Criteria.where("userName").regex("test").andOperator(Criteria.where("role").is(userType).andOperator(Criteria.where("status").is(1L)))));		
		//parameter rqueired to construct pageable			
		List<User>result1=mongoOps.find(query, User.class);
		long count = mongoOps.count(query, User.class);		
		Page<User> result=new PageImpl<User>(result1 , pageable, count);		
		return result;
		
	}
	@Override
	public UserResponsePayload deleteUser (String userId) {
		// TODO Auto-generated method stub
		User savedUser =userrepository.findByUserId(userId);
		savedUser.setStatus(2L);
		User updated=userrepository.save(savedUser);
		return buildPayloadbyUser(updated);
	}

	@Override
	public UserResponsePayload getUserDetails(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User user=userrepository.findByUserId(UserRequestPayload.getUserId());
		return buildPayloadbyUser(user);
	}

	@Override
	public boolean samePasswordOrNOr(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		return false;
	}	

	private User buildAdminUser(UserRequestPayload payload) {
		System.out.println("welcome add user");
		Roles role = roleRepository.findByRoleName(payload.getRole());
		List<Permissions> permissionlist = buildRolePermission(role.getRoleId());
		System.out.println("List" + permissionlist.toString());
		User user = new User(payload.getName(), payload.getEmail(), passwordEncoder.encode(payload.getPassword()), 1L,
				permissionlist, payload.getRole(),new Date());
		return user;
	}

	private User modifyAdminUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByUserId(payload.getUserId());
		existinguser.setUserName(payload.getName());
		existinguser.setUserEmail(payload.getEmail());
		return existinguser;

	}

	private User buildClientUser(UserRequestPayload payload) {
		Roles role = roleRepository.findByRoleName(payload.getRole());
		System.out.println("role"+role.toString());
		List<Permissions> permissionlist = buildRolePermission(role.getRoleId());
		System.out.println("role"+permissionlist.toString());
		User user = new User(payload.getName(), payload.getEmail(), payload.getOrganisationId(),
				passwordEncoder.encode(payload.getPassword()), 1L, new Date(),
				payload.getGst(), payload.getPan(), permissionlist, payload.getRole(),new Date());
		return user;

	}

	private User modifyClientUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByUserId(payload.getUserId());
		existinguser.setUserName(payload.getName());
		existinguser.setUserEmail(payload.getEmail());
		existinguser.setOrgId(payload.getOrganisationId());		
		existinguser.setClientGst(payload.getGst());
		existinguser.setClientPan(payload.getPan());
		return existinguser;

	}

	private User buildLearnerUser(UserRequestPayload payload) {

		User user = new User(payload.getName(), payload.getEmail(), payload.getOrganisationId(),
				passwordEncoder.encode(payload.getPassword()), 1L, new Date(), payload.getSimEventName(),
				payload.getSmeAssigned(), payload.getNoOfMilestone(), payload.getNoOfMilestoneAttended(),
				payload.getNoOfMilestoneCompleted(), payload.getPermissions(), payload.getUserType());
		return user;

	}

	private User modifyLearnerUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByuserEmail(payload.getEmail());
		existinguser.setUserName(payload.getName());
		existinguser.setUserEmail(payload.getEmail());
		existinguser.setOrgId(payload.getOrganisationId());
		existinguser.setSimEventName(payload.getSimEventName());
		existinguser.setSmeAssigned(payload.getSmeAssigned());
		existinguser.setNoOfMilestone(payload.getNoOfMilestone());
		existinguser.setNoOfMilestoneAttended(payload.getNoOfMilestoneAttended());
		existinguser.setNoOfMilestoneCompleted(payload.getNoOfMilestoneCompleted());
		return existinguser;

	}

	@Override
	public User getUserDetailsById(String id) {
		// TODO Auto-generated method stub
		return userrepository.findByUserId(id);
	}

	@Override
	public List<Permissions> buildRolePermission(String roleid) {
		// TODO Auto-generated method stub
		List<Permissions> permissionlist = new ArrayList<Permissions>();
		Roles role = roleRepository.findByRoleId((roleid));		
		List<RoleHasPermission> haspermission = roleHasPermissionRepository.findByRoleid(role.getRoleId());
		for (RoleHasPermission permission : haspermission) {		
			Permissions per = permissionsRepository.findBypermissionId(permission.getPermissionid());
			if(per.getStatus()==1L) {
				permissionlist.add(per);	
			}
			permissionlist.sort(new SortbyRank());
		}
		return permissionlist;
	}

	@Override
	public UserResponsePayload updateLastLoginUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User updateuser = null;
		updateuser=updateLogoutUser(UserRequestPayload);
		UserResponsePayload payload= buildPayloadbyUser(updateuser);
		return payload;
	}
	
	private User updateLogoutUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByUserId(payload.getUserId());
		existinguser.setLastLoggedIn(new Date());
		return existinguser;

	}

	@Override
	public Long countofAdmin(String role) {
		// TODO Auto-generated method stub
		return userrepository.countByRole(role);
	}

	@Override
	public boolean userExist(String email) {
		// TODO Auto-generated method stub
		return userrepository.existsByuserEmail(email);
	}

	@Override
	public Long countByRoleAndStatus(String role, Long status) {
		// TODO Auto-generated method stub
		return userrepository.countByRoleAndStatus(role, status);
	}

	

	

}
