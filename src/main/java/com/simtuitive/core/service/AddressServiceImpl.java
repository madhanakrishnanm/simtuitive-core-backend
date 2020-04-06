package com.simtuitive.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simtuitive.core.model.WhiteListIP;
import com.simtuitive.core.repository.WhiteListRespository;
import com.simtuitive.core.service.abstracts.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	
	@Autowired
	WhiteListRespository whiteListRespository;

	@Override
	public WhiteListIP getAddress(String ipaddress) {
		// TODO Auto-generated method stub
		return whiteListRespository.findWhiteListIPByipAddress(ipaddress);
	}
	
	
	
}
