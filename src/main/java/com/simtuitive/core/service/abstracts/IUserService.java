package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.model.User;
import com.simtuitive.core.model.Permissions;

public interface IUserService {

	public User addUser(UserRequestPayload UserRequestPayload);

	public User updateUser(UserRequestPayload UserRequestPayload);

	public User getUser(String email);

	public User changePasswordUser(UserRequestPayload UserRequestPayload);

	public List<User> getAllUser(String userType);

	public List<User> deleteUser(String email);

	public User getUserDetails(UserRequestPayload UserRequestPayload);
	
	public User getUserDetailsById(String id);

	public boolean samePasswordOrNOr(UserRequestPayload UserRequestPayload);

	public Long getActiveUser(Long status);	
	
	public List<Permissions>buildRolePermission(String roleid);
	

}
