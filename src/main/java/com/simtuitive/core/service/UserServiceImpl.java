package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.RoleHasPermission;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.model.User;
import com.simtuitive.core.repository.PermissionsRepository;
import com.simtuitive.core.repository.RoleHasPermissionRepository;
import com.simtuitive.core.repository.RolesRepository;
import com.simtuitive.core.repository.UserRepository;
import com.simtuitive.core.service.abstracts.IUserService;

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
	public User getUser(String email) {
		return userrepository.findByuserEmail(email);
	}

	@Override
	public User addUser(UserRequestPayload UserRequestPayload) {
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
		return savedUser;
	}

	@Override
	public User updateUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User updateuser = null;
		if (UserRequestPayload.getRole().equalsIgnoreCase("ADMIN")
				|| UserRequestPayload.getRole().equalsIgnoreCase("Super Admin")) {
			updateuser = modifyAdminUser(UserRequestPayload);
		}
		if (UserRequestPayload.getRole().equalsIgnoreCase("CLIENT")) {
			updateuser = modifyClientUser(UserRequestPayload);
		}
		return userrepository.save(updateuser);
	}

	@Override
	public User changePasswordUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUser(String userType) {

		return userrepository.findByRole(userType);
	}

	@Override
	public List<User> deleteUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserDetails(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		return userrepository.findByUserId(UserRequestPayload.getUserId());
	}

	@Override
	public boolean samePasswordOrNOr(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long getActiveUser(Long status) {
		// TODO Auto-generated method stub
		return null;
	}

	private User buildAdminUser(UserRequestPayload payload) {
		List<Permissions> permissionlist = buildRolePermission(payload);
		System.out.println("List" + permissionlist.toString());
		User user = new User(payload.getName(), payload.getEmail(),
				passwordEncoder.encode(payload.getPassword()), 1L, permissionlist, payload.getRole());
		return user;
	}

	private List<Permissions> buildRolePermission(UserRequestPayload payload) {
		List<Permissions> permissionlist = new ArrayList<Permissions>();
		Roles role = roleRepository.findByRolename(payload.getRole());
		System.out.println("veera role" + role.toString());
		List<RoleHasPermission> haspermission = roleHasPermissionRepository.findByRoleid(role.getRoleid());
		for (RoleHasPermission permission : haspermission) {
			System.out.println("permission" + permission.getPermissionid());
			Permissions per = permissionsRepository.findBypermissionId(permission.getPermissionid());
			permissionlist.add(per);
		}
		return permissionlist;
	}

	private User modifyAdminUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByUserId(payload.getUserId());
		existinguser.setUserName(payload.getName());
		existinguser.setUserEmail(payload.getEmail());		
		return existinguser;

	}

	private User buildClientUser(UserRequestPayload payload) {
		List<Permissions> permissionlist = buildRolePermission(payload);
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

}
