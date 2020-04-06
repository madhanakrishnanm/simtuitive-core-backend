package com.simtuitive.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simtuitive.core.model.Permissions;

@Repository
public interface PermissionsRepository extends MongoRepository<Permissions, String> {

}