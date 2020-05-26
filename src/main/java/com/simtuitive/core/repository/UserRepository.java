package com.simtuitive.core.repository;

import java.util.List;

import javax.management.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simtuitive.core.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByuserEmail(String email);	
		
	public User findByUserId(String userId);	
	
	public Page<User> findByRoleAndStatus(String role, Pageable pageable,Long status);	

	public Page<User> findByuserEmail(String firstname, Pageable pageable);
	
	public List<User> findByRoleAndStatus(String role,Long status);

	public Long countByRole(String role);
	
	public boolean existsByuserEmail(String email);
	
	public List<User>findByOrgName(String orgName);
	
	public Long countByRoleAndStatus(String role,Long status);
	
	public Page<User> findByStatus(org.springframework.data.mongodb.core.query.Query query,String role, Pageable pageable);
}
