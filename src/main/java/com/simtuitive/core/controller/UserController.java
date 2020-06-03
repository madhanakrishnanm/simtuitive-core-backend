/**
 * 
 */
package com.simtuitive.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.simtuitive.core.common.Constants;
import com.simtuitive.core.config.RedisConfiguration;
import com.simtuitive.core.controller.productmgmt.api.JsonApiWrapper;
import com.simtuitive.core.controller.productmgmt.api.Link;
import com.simtuitive.core.controller.productmgmt.api.PaginationResponse;
import com.simtuitive.core.controller.requestpayload.UserRequestPayload;
import com.simtuitive.core.controller.responsepayload.UserResponsePayload;
import com.simtuitive.core.globalexception.BadArgumentException;
import com.simtuitive.core.globalexception.ResourceNotFoundException;
import com.simtuitive.core.globalexception.UserServiceException;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.model.ProductUsers;
import com.simtuitive.core.model.Roles;
import com.simtuitive.core.model.User;
import com.simtuitive.core.service.CustomUserDetailsServiceImpl;
import com.simtuitive.core.service.UserServiceImpl;
import com.simtuitive.core.service.abstracts.IPermissionService;
import com.simtuitive.core.service.abstracts.IRoleHasPermissionService;
import com.simtuitive.core.service.abstracts.IRolesService;
import com.simtuitive.core.service.abstracts.IUserService;
import com.simtuitive.core.util.TokenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Veeramani N S
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {

	@Value("${secret}")
	private String secret;

	@Autowired
	RedisConfiguration redis;
	@Autowired
	private IUserService userservice;

	@Autowired
	private IRoleHasPermissionService rolehasservice;

	@Autowired
	private IRolesService roleservice;

	@Autowired
	private IPermissionService permissionservice;

	@Autowired
	private UserServiceImpl userimpl;

	@Autowired
	private CustomUserDetailsServiceImpl customuserdetail;

	// Create User
//	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates a Admin user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/add-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> createUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {		
		String tmp = builder.path(Constants.PATH_CREATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_CREATE_ADMIN_DETAIL);
		checkArguments(userpayload);
		checkArguments(userpayload.getEmail());
		checkExistingUser(userpayload.getEmail(), l1.getHref());
		UserResponsePayload userResponse = null;
		userResponse = userservice.addUser(userpayload);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}

	public void checkExistingUser(String email, String url) {
		if (email.isEmpty()) {
			throw new BadArgumentException("User email not entered");
		} else {
			boolean userResponse = userservice.userExist(email);
			System.out.println("Existing check::"+userResponse);
			if (userResponse) {
				throw new BadArgumentException("User Already exist");
			}
		}
	}

	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Creates a Admin user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })

	@RequestMapping(value = "/oauth/validation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> login(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.getRequestDispatcher("/oauth/token").forward(request, response);	
		UserResponsePayload userResponse = null;
		CheckSession(request);
		//userResponse = userservice.addUser(userpayload);
		String tmp = builder.path(Constants.PATH_CREATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_CREATE_ADMIN_DETAIL);
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}
	// update

	private void CheckSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String clientToken = parseJwt(request);
		Map<?, ?> newtoken = (Map<?, ?>) redis.redisTemplate().opsForHash().get(clientToken,
				clientToken);
		if(newtoken!=null) {
			String truetoken = (String) newtoken.get(Constants.STR_AUTH_TOKEN);//
			System.out.println("truetoken::" + truetoken);
			String username = (String) newtoken.get(Constants.STR_AUTH_EMAIL);
			String initrole = customuserdetail.getUserDetails(username);
			String sessionkey=username+initrole;
			Map<?, ?> sessioninfo = (Map<?, ?>) redis.redisTemplate().opsForHash().get(sessionkey, sessionkey);
			Long time = (Long) newtoken.get("expirationTime");
			Long sessiontime=(Long) sessioninfo.get("sessionCreatedTime");
			Long sessiontime1=(Long) sessioninfo.get("sessionExpiryTime");
			Date expirationTime = new java.util.Date(time);
			Date sessionexpiretime= new java.util.Date(sessiontime1);
			System.out.println("welcome sessionexpiretime"+sessionexpiretime);		
			Date sessioncreatedtime = new java.util.Date(sessiontime);
			System.out.println("welcome sessioncreatedtime"+sessioncreatedtime);
			Date now = new Date();
			System.out.println("now"+now);
			if (truetoken.matches(clientToken)) {
				if (now.after(expirationTime)) {
					System.out.println("cominghere");
					throw new BadCredentialsException("Token expired");//
				} else {
					if(now.after(sessioncreatedtime)&&now.before(sessionexpiretime)) {
						System.out.println("coming session here");						
					}else {
						throw new BadCredentialsException("session Time out by token");						
					}						
				}
			}
			else {
				throw new BadCredentialsException("Invalid Token "+clientToken);
			}
		}else {
			throw new BadCredentialsException("Invalid Token "+clientToken);
		}
		
	}

	@ResponseStatus(HttpStatus.IM_USED)
	@ApiOperation(value = " Updates a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/update-user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> updateAdminUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
		UserResponsePayload userResponse = userservice.updateUser(userpayload);
		String tmp = builder.path(Constants.PATH_UPDATE_ADMIN).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_UPDATE_ADMIN_DETAIL);

		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));

	}

//	@ResponseStatus(HttpStatus.IM_USED)
//	@ApiOperation(value = " Changes password of a user ", response = User.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Successful Creation of User Data.", response = JsonApiWrapper.class),
//			@ApiResponse(code = 401, message = "Not authorized!"),
//			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
//			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
//			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
//			@ApiResponse(code = 500, message = "Internal server error") })
//	@RequestMapping(value = "/changepassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public JsonApiWrapper<UserResponsePayload> changePasswordUser(@ApiIgnore UriComponentsBuilder builder,
//			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
//		String tmp = builder.path(Constants.PATH_PASSWORD_CHANGE).build().toString();
//		boolean same = userservice.samePasswordOrNOr(userpayload);
//		if (same) {
//			UserResponsePayload userResponse = userservice.getUserDetails(userpayload);
//			Link l2 = new Link(tmp, Constants.PASSWORD_UPDATE_FAILURE);
//			return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l2));
//		} else {
//			UserResponsePayload userResponse = userservice.changePasswordUser(userpayload);
//			Link l1 = new Link(tmp, Constants.PASSWORD_UPDATED_SUCCESS);
//			return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
//		}
//	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Map<String, Map<String, Long>>> getSample(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		Map<String, Map<String, Long>> userresponse = generateCounts();
		String tmp = builder.path(Constants.PATH_GET_DASHBOARD).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_DASHBOARD);
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-super-dashboard", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<Map<String, Long>> getAdmin(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		Map<String, Long> userresponse = getsuperadmincounts();
		String tmp = builder.path(Constants.PATH_GET_DASHBOARD).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_DASHBOARD);
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}

	private Map<String, Long> getsuperadmincounts() {
		// TODO Auto-generated method stub
		Map<String, Long> result = new HashMap<String, Long>();
		result.put("Admin", userservice.countofAdmin("Admin"));
		result.put("Role", roleservice.countofRole());
		result.put("Permission", permissionservice.countofPermission());
		return result;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/product-users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<ProductUsers>> getProductCount(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		List<ProductUsers> userresponse = generateProductCounts();
		String tmp = builder.path(Constants.PATH_GET_DASHBOARD).build().toString();
		Link l1 = new Link(tmp, Constants.LINK_GET_DASHBOARD);
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}

	private List<ProductUsers> generateProductCounts() {
		// TODO Auto-generated method stub
		List<ProductUsers> result = new ArrayList<ProductUsers>();
		ProductUsers prod1 = new ProductUsers("Business Ethics", 765L, 734L);
		result.add(prod1);
		ProductUsers prod2 = new ProductUsers("Data Visualization", 5333L, 5334L);
		result.add(prod2);
		ProductUsers prod3 = new ProductUsers("Business Acumen", 3334L, 653L);
		result.add(prod3);
		ProductUsers prod4 = new ProductUsers("Business Finance", 789L, 764L);
		result.add(prod4);
		ProductUsers prod5 = new ProductUsers("Agile Project Management", 700L, 833L);
		result.add(prod5);
		System.out.println("Result list"+result.toString());
		return result;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> getUser(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response) {
		UserResponsePayload userResponse = userservice.getUser(request.getUserPrincipal().getName());
		System.out.println("coming here controller");
		Roles userrole = roleservice.getRoleId(userResponse.getRole());
		userResponse.setPassword(null);
		List<Permissions> permissionlist = userservice.buildRolePermission(userrole.getRoleId());
		userResponse.setPermissions(permissionlist);
		String tmp = builder.path("/get").build().toString();
		Link l1 = new Link(tmp, " User Detail get");
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get a user ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-user-id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> getUserById(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response) {
		UserResponsePayload userResponse = userservice.getUserDetails(userpayload);
		Roles userrole = roleservice.getRoleId(userResponse.getRole());
		userResponse.setPassword(null);
		List<Permissions> permissionlist = userservice.buildRolePermission(userrole.getRoleId());
		userResponse.setPermissions(permissionlist);
		userResponse.setPassword(null);
		String tmp = builder.path("/get").build().toString();
		Link l1 = new Link(tmp, " User Detail get");
		return new JsonApiWrapper<>(userResponse, getSelfLink(request), Arrays.asList(l1));
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get all users ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful getAll of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-users-by-role", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<UserResponsePayload>> getAllUser(@ApiIgnore UriComponentsBuilder builder,
			@RequestParam("role") String userType, @RequestParam("pageNo") Optional<String> pageno,
			 @RequestParam("query") Optional<String> query,@RequestParam("name") Optional<String> name,@RequestParam("orgName") Optional<String> orgname,HttpServletRequest request, HttpServletResponse response) {
		Page<User> userresponse = userservice.getAllUserByPaginationApplied(userType, pageno,query,name,orgname);		
		List<UserResponsePayload> result = createResponse(userresponse.getContent(), userType);
		String tmp = builder.path("/getAll").build().toString();
		Link l1 = new Link(tmp, " User Detail getAll");		
		System.out.println("user count"+userresponse.getTotalElements());		
		PaginationResponse page = new PaginationResponse((int)((Long)userresponse.getTotalElements()).intValue(),
				userresponse.getTotalPages(), userresponse.getSize(), userresponse.getPageable().getPageNumber());
		return new JsonApiWrapper<>(result, getSelfLink(request), Arrays.asList(l1), page);

	}

	private List<UserResponsePayload> createResponse(List<User> userresponse, String userType) {
		List<UserResponsePayload> result = new ArrayList<UserResponsePayload>();
		List<User> userList = userresponse;
		if (userType.equalsIgnoreCase("Admin") || userType.equalsIgnoreCase("Super Admin")) {
			for (User user : userList) {
				UserResponsePayload payload = userimpl.buildPayloadbyUser(user);
				result.add(payload);
			}
		}
		if (userType.equalsIgnoreCase("CLIENT")) {
			for (User user : userList) {
				UserResponsePayload payload = userimpl.buildPayloadbyUser(user);
				result.add(payload);
			}
		}
		return result; // TODO Auto-generated method stub

	}
//	@ResponseStatus(HttpStatus.ACCEPTED)
//	@ApiOperation(value = " Get all users ", response = User.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Successful getAll of User Data.", response = JsonApiWrapper.class),
//			@ApiResponse(code = 401, message = "Not authorized!"),
//			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
//			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
//			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
//			@ApiResponse(code = 500, message = "Internal server error") })
//	@RequestMapping(value = "/get-pagination", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public JsonApiWrapper<Page<User>> getAll(@ApiIgnore UriComponentsBuilder builder,
//			@RequestParam("pageno") int pageno,	 HttpServletRequest request, HttpServletResponse response) {
//		Page<User> userresponse = userservice.getAllUserByPaginationApplied(pageno);
//		String tmp = builder.path("/getAll").build().toString();
//		Link l1 = new Link(tmp, " User Detail getAll");
//		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
//
//	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/delete-id", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> deleteUserByEmail(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody UserRequestPayload userpayload, HttpServletRequest request, HttpServletResponse response)
			throws UserServiceException, ResourceNotFoundException {
		checkArguments(userpayload);
		UserResponsePayload userresponse = userservice.deleteUser(userpayload.getUserId());
		String tmp = builder.path("/delete").build().toString();
		Link l1 = new Link(tmp, " User Detail Delete");
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get all users ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful getAll of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-admin-users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<UserResponsePayload>> getAllAdminUser(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response) {
		List<UserResponsePayload> result =userservice.getAllAdminUser("Admin");		
		String tmp = builder.path("/getAll").build().toString();
		Link l1 = new Link(tmp, " User Detail getAll");			
		return new JsonApiWrapper<>(result, getSelfLink(request), Arrays.asList(l1));

	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = " Get all users ", response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful getAll of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/get-client-org", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<List<String>> getAllOrgClient(@ApiIgnore UriComponentsBuilder builder,
			@RequestParam("orgName") String orgName,HttpServletRequest request, HttpServletResponse response) {
		List<String> result =userservice.getOrgUsers(orgName);		
		String tmp = builder.path("/getAll").build().toString();
		Link l1 = new Link(tmp, " User Detail getAll");			
		return new JsonApiWrapper<>(result, getSelfLink(request), Arrays.asList(l1));

	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful delete of User Data.", response = JsonApiWrapper.class),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Not authorized to perform this action."),
			@ApiResponse(code = 404, message = "Invalid userId or userRoleId."),
			@ApiResponse(code = 404, message = "Operation cannot be performed now."),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonApiWrapper<UserResponsePayload> logoutPage(@ApiIgnore UriComponentsBuilder builder,
			HttpServletRequest request, HttpServletResponse response) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("request controller logout"+auth.getName());
		System.out.println("request controller logout"+request.getHeader(Constants.STR_AUTH_AUTHORIZATION));
		String username = null;
		if (auth != null) {
			String clientToken = parseJwt(request);
			if (clientToken!=null) {
				String clientTrueToken = TokenUtil.getToken(clientToken);
				Map<?, ?> newtoken = (Map<?, ?>) redis.redisTemplate().opsForHash().get(clientTrueToken,
						clientTrueToken);
				username = (String) newtoken.get(Constants.STR_AUTH_EMAIL);
				System.out.println("username" + username);
				String initrole = customuserdetail.getUserDetails(username);
				redis.redisTemplate().opsForHash().delete(clientTrueToken, clientTrueToken);
				String key = username + initrole;
				System.out.println("key" + key);
				deleteredis(key);

			}

			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		UserResponsePayload logoutuser = userservice.getUser(username);
		UserRequestPayload payload = new UserRequestPayload();
		payload.setUserId(logoutuser.getUserId());
		payload.setRole(logoutuser.getRole());
		UserResponsePayload userresponse = userservice.updateLastLoginUser(payload);
		String tmp = builder.path("/logout").build().toString();
		Link l1 = new Link(tmp, " User Detail Delete");
		return new JsonApiWrapper<>(userresponse, getSelfLink(request), Arrays.asList(l1));
	}

	private void deleteredis(String key) {
		// TODO Auto-generated method stub
		Map<?, ?> sessionifo = (Map<?, ?>) redis.redisTemplate().opsForHash().get(key, key);
		String username = (String) sessionifo.get("emailId");
		String role = (String) sessionifo.get("role");
		String key1 = username + role;
		System.out.println("username" + username);
		System.out.println("role" + role);
		System.out.println("key1" + key1);
		redis.redisTemplate().opsForValue().getOperations().delete(key1);
	}

	private Map<String, Map<String, Long>> generateCounts() {
		Map<String, Long> retailUserscount = new HashMap<String, Long>();
		retailUserscount.put("currentMonth", 20005L);
		retailUserscount.put("pastMonth", 20000L);
		Map<String, Long> enterpriseUserscount = new HashMap<String, Long>();
		enterpriseUserscount.put("currentMonth", 244L);
		enterpriseUserscount.put("pastMonth", 255L);
		Map<String, Long> usersOnlineNowcount = new HashMap<String, Long>();
		usersOnlineNowcount.put("yesterday", 202L);
		usersOnlineNowcount.put("today", 234L);
		Map<String, Map<String, Long>> userresponse = new HashMap<String, Map<String, Long>>();
		userresponse.put("retailUsers", retailUserscount);
		userresponse.put("enterpriseUsers", enterpriseUserscount);
		userresponse.put("usersOnlineNow", usersOnlineNowcount);
		return userresponse;
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(Constants.STR_AUTH_AUTHORIZATION);

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(Constants.STR_AUTH_LOGOUT_BEARER)) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}
