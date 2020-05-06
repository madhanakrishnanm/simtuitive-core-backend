package com.simtuitive.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simtuitive.core.model.WhiteListIP;

@Repository
public interface WhiteListRespository extends MongoRepository<WhiteListIP, String> {

	public WhiteListIP findWhiteListIPByipAddress(String ipAddress);
}