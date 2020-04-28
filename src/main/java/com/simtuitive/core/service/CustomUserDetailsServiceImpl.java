/**
 * 
 */
package com.simtuitive.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simtuitive.core.model.User;
import com.simtuitive.core.repository.UserRepository;
import com.simtuitive.core.model.Permissions;
import com.simtuitive.core.service.abstracts.IUserService;

/**
 * @author Veeramani N S
 *
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userservice;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User dbUser;
		dbUser = userservice.findByuserEmail(email);

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		GrantedAuthority authority = new SimpleGrantedAuthority(dbUser.getRole());
		grantedAuthorities.add(authority);

		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
				dbUser.getUserEmail(), dbUser.getPassword(), grantedAuthorities);
		return user;

	}

	public String getUserDetails(String email) throws UsernameNotFoundException {

		User dbUser;
		dbUser = userservice.findByuserEmail(email);
		return dbUser.getRole();
	}

}
