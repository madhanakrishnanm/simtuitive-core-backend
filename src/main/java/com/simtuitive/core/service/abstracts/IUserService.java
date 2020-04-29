package com.simtuitive.core.service.abstracts;

import java.util.List;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.controller.responsepayload.UserResponsePayload;
import com.simtuitive.core.model.User;
import com.simtuitive.core.model.Permissions;

public interface IUserService {

	public UserResponsePayload addUser(UserRequestPayload UserRequestPayload);

	public UserResponsePayload updateUser(UserRequestPayload UserRequestPayload);

	public UserResponsePayload getUser(String email);

	public UserResponsePayload changePasswordUser(UserRequestPayload UserRequestPayload);

	public List<UserResponsePayload> getAllUser(String userType);

	public UserResponsePayload deleteUser(String email);

	public UserResponsePayload getUserDetails(UserRequestPayload UserRequestPayload);
	
	public User getUserDetailsById(String id);

	public boolean samePasswordOrNOr(UserRequestPayload UserRequestPayload);
	
	public List<Permissions>buildRolePermission(String roleid);
	

}
