package com.simtuitive.core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simtuitive.core.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByuserEmail(String email);	
		
	public User findByUserId(String userId);
	
	public List<User> findByRole(String role);

}
