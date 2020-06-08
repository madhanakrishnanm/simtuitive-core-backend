package com.simtuitive.core.service.abstracts;


public interface IEmailService {

	

	public void triggerMessageClient(String[] to, String[] cc, String subject, String text);

	
}
