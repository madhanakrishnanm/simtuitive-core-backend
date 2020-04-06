package com.simtuitive.core.oauth2;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.simtuitive.core.service.abstracts.IUserService;

public class CustomPermissionEvaluator implements PermissionEvaluator {

	// private Logger log =
	// LoggerFactory.getLogger(CustomPermissionEvaluator.class);
	@Autowired
	private IUserService userDetailsService;

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		
		///Surat sir will guide on this
//		if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
//			return false;
//		}

//		User user = userDetailsService.getUser(targetType);
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
//		List<UserRole> roles=user.getRoles();
//		for (UserRole role : roles){
//			if(role.getAuthority()=="ADMIN") {
//				return true;
//			}
//		}
//		String principalLogin = userDetails.getUsername();
//
//		if (Objects.equals(user.getUserEmail(), principalLogin)) {
//			return true;
//		}

		return true;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		// TODO Auto-generated method stub
		return true;
	}

}
