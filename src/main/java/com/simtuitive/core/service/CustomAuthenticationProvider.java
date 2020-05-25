package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.simtuitive.core.globalexception.BadArgumentException;
import com.simtuitive.core.model.User;
import com.simtuitive.core.repository.UserRepository;

@Component

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserRepository userservice;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getPrincipal()+"";
		String password = authentication.getCredentials()+"";
		
		System.out.println("username"+username+"password"+password);
		
		final User dbUser= userservice.findByuserEmail(username);
		
		if (dbUser == null) {
	        throw new BadCredentialsException("user not exist");
	    }
		if (!encoder.matches(password, dbUser.getPassword())) {
	        throw new BadCredentialsException("Password wrong");
	    }
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		GrantedAuthority authority = new SimpleGrantedAuthority(dbUser.getRole());
		grantedAuthorities.add(authority);
		
		return new UsernamePasswordAuthenticationToken(username, dbUser.getPassword(),grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
