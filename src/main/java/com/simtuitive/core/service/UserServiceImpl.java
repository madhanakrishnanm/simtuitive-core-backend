package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SortAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.controller.responsepayload.UserResponsePayload;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.model.User;
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
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleHasPermissionRepository roleHasPermissionRepository;

	@Autowired
	private PermissionsRepository permissionsRepository;
	@Autowired
	private RolesRepository roleRepository;

	// Get User By Email
	@Override
	public UserResponsePayload getUser(String email) {
		UserResponsePayload returnpayload = null;
		User user = userrepository.findByuserEmail(email);
		returnpayload = buildPayloadbyUser(user);
		return returnpayload;
	}

	private UserResponsePayload buildPayloadbyUser(User user) {
		// TODO Auto-generated method stub
		UserResponsePayload payload = null;
		if (user.getRole().equalsIgnoreCase("ADMIN") || user.getRole().equalsIgnoreCase("Super Admin")) {
			payload = new UserResponsePayload(user.getUserId(), user.getUserName(), user.getUserEmail(),
					null, user.getStatus(), user.getPermissions(), user.getRole());
		}
		if (user.getRole().equalsIgnoreCase("CLIENT")) {
			payload=new UserResponsePayload(user.getUserName(), user.getUserEmail(), user.getClientOrgname(),
					null, 1L, user.getClientDealOwner(), user.getCreatedDate(),
					user.getClientGst(), user.getClientPan(), user.getPermissions(), user.getRole());
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
	public List<UserResponsePayload> getAllUser(String userType) {
		List<UserResponsePayload>result=new ArrayList<UserResponsePayload>();
		List<User> userlist=userrepository.findByRole(userType);
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
			for(User user:userlist) {				
				if(user.getStatus()==1L) {
					UserResponsePayload payload=buildPayloadbyUser(user);
					result.add(payload);
				}
			}
		}
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
		Roles role = roleRepository.findByRoleName(payload.getRole());
		List<Permissions> permissionlist = buildRolePermission(role.getRoleId());
		System.out.println("List" + permissionlist.toString());
		User user = new User(payload.getName(), payload.getEmail(), passwordEncoder.encode(payload.getPassword()), 1L,
				permissionlist, payload.getRole());
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
		List<Permissions> permissionlist = buildRolePermission(role.getRoleId());
		User user = new User(payload.getName(), payload.getEmail(), payload.getClientOrgname(),
				passwordEncoder.encode(payload.getPassword()), 1L, payload.getClientDealOwner(), new Date(),
				payload.getClientGst(), payload.getClientPan(), permissionlist, payload.getRole());
		return user;

	}

	private User modifyClientUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByUserId(payload.getUserId());
		existinguser.setUserName(payload.getName());
		existinguser.setUserEmail(payload.getEmail());
		existinguser.setClientOrgname(payload.getClientOrgname());
		existinguser.setClientDealOwner(payload.getClientDealOwner());
		existinguser.setClientGst(payload.getClientGst());
		existinguser.setClientPan(payload.getClientPan());
		return existinguser;

	}

	private User buildLearnerUser(UserRequestPayload payload) {

		User user = new User(payload.getName(), payload.getEmail(), payload.getClientOrgname(),
				passwordEncoder.encode(payload.getPassword()), 1L, new Date(), payload.getSimEventName(),
				payload.getSmeAssigned(), payload.getNoOfMilestone(), payload.getNoOfMilestoneAttended(),
				payload.getNoOfMilestoneCompleted(), payload.getPermissions(), payload.getUserType());
		return user;

	}

	private User modifyLearnerUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByuserEmail(payload.getEmail());
		existinguser.setUserName(payload.getName());
		existinguser.setUserEmail(payload.getEmail());
		existinguser.setClientOrgname(payload.getClientOrgname());
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
			permissionlist.add(per);
			permissionlist.sort(new SortbyRank());
		}
		return permissionlist;
	}

	

	

}
