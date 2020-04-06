package com.simtuitive.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.model.User;
import com.simtuitive.core.repository.UserRepository;
import com.simtuitive.core.service.abstracts.IUserService;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Get User By Email
	@Override
	public User getUser(String email) {
		return userrepository.findByuserEmail(email);
	}

	@Override
	public User addUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User buildUserNeedsToBeCreated = null;
		if (UserRequestPayload.getUserType().equalsIgnoreCase("ADMIN")||UserRequestPayload.getUserType().equalsIgnoreCase("Super Admin") ) {
			buildUserNeedsToBeCreated = buildAdminUser(UserRequestPayload);
		}
		if (UserRequestPayload.getUserType().equalsIgnoreCase("CLIENT")) {
			buildUserNeedsToBeCreated = buildClientUser(UserRequestPayload);
		}
		if (UserRequestPayload.getUserType().equalsIgnoreCase("Learner")) {
			buildUserNeedsToBeCreated = buildLearnerUser(UserRequestPayload);
		}
		User savedUser = userrepository.save(buildUserNeedsToBeCreated);
		return savedUser;
	}

	@Override
	public User updateUser(UserRequestPayload UserRequestPayload) {
		// TODO Auto-generated method stub
		User updateuser = modifyAdminUser(UserRequestPayload);
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

		User user = new User(payload.getUserName(), payload.getUserEmail(),
				passwordEncoder.encode(payload.getPassword()), 1L, payload.getPermissions(),payload.getUserType());
		return user;

	}

	private User modifyAdminUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByuserEmail(payload.getUserEmail());
		existinguser.setUserName(payload.getUserName());
		existinguser.setUserEmail(payload.getUserEmail());
		return existinguser;

	}

	private User buildClientUser(UserRequestPayload payload) {

		User user = new User(payload.getUserName(), payload.getUserEmail(), payload.getClientOrgname(),
				passwordEncoder.encode(payload.getPassword()), 1L, payload.getClientDealOwner(), new Date(),
				payload.getClientLocation(), payload.getClientGst(), payload.getClientPan(),
				payload.getPermissions(),payload.getUserType());
		return user;

	}

	private User modifyClientUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByuserEmail(payload.getUserEmail());
		existinguser.setUserName(payload.getUserName());
		existinguser.setUserEmail(payload.getUserEmail());
		existinguser.setClientOrgname(payload.getClientOrgname());
		existinguser.setClientDealOwner(payload.getClientDealOwner());
		existinguser.setClientLocation(payload.getClientLocation());
		existinguser.setClientGst(payload.getClientGst());
		existinguser.setClientPan(payload.getClientPan());
		existinguser.setClientSpoc(payload.getClientSpoc());
		return existinguser;

	}

	private User buildLearnerUser(UserRequestPayload payload) {

		User user = new User(payload.getUserName(), payload.getUserEmail(), payload.getClientOrgname(),
				passwordEncoder.encode(payload.getPassword()), 1L, new Date(), payload.getSimEventName(),
				payload.getSmeAssigned(), payload.getNoOfMilestone(), payload.getNoOfMilestoneAttended(),
				payload.getNoOfMilestoneCompleted(), payload.getPermissions(),payload.getUserType());
		return user;

	}

	private User modifyLearnerUser(UserRequestPayload payload) {
		User existinguser = userrepository.findByuserEmail(payload.getUserEmail());
		existinguser.setUserName(payload.getUserName());
		existinguser.setUserEmail(payload.getUserEmail());
		existinguser.setClientOrgname(payload.getClientOrgname());
		existinguser.setSimEventName(payload.getSimEventName());
		existinguser.setSmeAssigned(payload.getSmeAssigned());
		existinguser.setNoOfMilestone(payload.getNoOfMilestone());
		existinguser.setNoOfMilestoneAttended(payload.getNoOfMilestoneAttended());
		existinguser.setNoOfMilestoneCompleted(payload.getNoOfMilestoneCompleted());
		return existinguser;

	}

}
