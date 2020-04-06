package com.simtuitive.core.service.abstracts;

import com.simtuitive.core.model.WhiteListIP;

public interface IAddressService {

	public WhiteListIP getAddress(String ipaddress);
}
