package com.simtuitive.core.service.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.controller.responsepayload.UserResponsePayload;
import com.simtuitive.core.model.User;
import com.simtuitive.core.model.Permissions;

public interface IUserService {

	public UserResponsePayload addUser(UserRequestPayload UserRequestPayload);

	public UserResponsePayload updateUser(UserRequestPayload UserRequestPayload);

	public UserResponsePayload getUser(String email);

	public UserResponsePayload changePasswordUser(UserRequestPayload UserRequestPayload);

	public List<UserResponsePayload> getAllAdminUser(String userType);
	 
	public Page<User> getAllUserByPaginationApplied(String userType,Optional<String> pageno,Optional<String> query,Optional<String> name,Optional<String> orgname);
	
	public UserResponsePayload deleteUser(String email);

	public UserResponsePayload getUserDetails(UserRequestPayload UserRequestPayload);
	
	public User getUserDetailsById(String id);

	public boolean samePasswordOrNOr(UserRequestPayload UserRequestPayload);
	
	public List<Permissions>buildRolePermission(String roleid);
	
	public UserResponsePayload updateLastLoginUser(UserRequestPayload UserRequestPayload);
	
	public Long countofAdmin(String role);
	
	public boolean userExist(String email);
	
	public Long countByRoleAndStatus(String role,Long status);

}
