package com.simtuitive.core.messaging.callback;

import com.simtuitive.core.messaging.service.ProducerCallback;

public class SimtuitiveEmailNotificationProducerCallBack implements ProducerCallback {
	 //private static final Logger LOGGER = LoggerFactory.getLogger(SimtuitiveEmailNotificationProducerCallBack.class);
	
	@Override
	 public void onSuccess(String topic,String messagePayload){
		 //LOGGER.info("The Publishing Of Message to the topic : " + topic + " is successfull");
		 System.out.println("The Publishing Of Message to the topic : " + topic + " is successfull");
	 }
	 
	 @Override
	 public void onFailure(String topic,String messagePayload, Exception ex){
		 //LOGGER.info("Failure in publishing  message to the topic : " + topic );
		 System.out.println("Failure in publishing  message to the topic : " + topic );
		 
	 }

}
