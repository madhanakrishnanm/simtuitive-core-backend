package com.simtuitive.core.oauth2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

public class SimtuitiveTokenServices extends DefaultTokenServices{
	@Override
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {

//	    System.out.println("authentication values"+authentication.getDetails().toString());
	    final Map<String, Object> tafInfo = new HashMap<>();
	    tafInfo.put("key_super_key",UUID.randomUUID().toString());		    	    
	    authentication.setDetails(tafInfo);
	    System.out.println("SimtuitiveTokenServices::createAccessToken::key_super_key::"+authentication.getDetails().toString());
	    return super.createAccessToken(authentication);
	}
}
