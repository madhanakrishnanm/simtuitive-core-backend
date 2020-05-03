package com.simtuitive.core.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.simtuitive.core.messaging.callback.ConsumerStateFailureCallback;
import com.simtuitive.core.messaging.callback.SimtuitiveEmailNotificationMessageProcessorCallBack;
import com.simtuitive.core.messaging.service.IConsumerCallback;
import com.simtuitive.core.messaging.service.MessagingService;

@Controller
public class InitializationController {
	private static final String NOTIFICATION_TOPIC = "SimtuitiveEmailNotification";
	@Value("${spring.kafka.streams.bootstrap-servers}")
	private String bootstrapServers;
	
	private static MessagingService messagingService;
	
	@PostConstruct
	public void init() throws Exception{		
		//logger.info("Initialing meridian-rest-service");
		//It is the new callback, required from the client.
		ConsumerStateFailureCallback consumerConnFailureCallBack = new ConsumerStateFailureCallback();
		//Contruct CallBack Map
		Map<String, IConsumerCallback> callBackTopicMap = new java.util.HashMap<>();
		SimtuitiveEmailNotificationMessageProcessorCallBack notifictionConsumerCallBack = new SimtuitiveEmailNotificationMessageProcessorCallBack();
		callBackTopicMap.put(NOTIFICATION_TOPIC,notifictionConsumerCallBack); //key is the topic name 
		messagingService =	MessagingService.getMessagingService(bootstrapServers,"Core Service",callBackTopicMap,null,null,consumerConnFailureCallBack);
	}
	
	public static MessagingService getMessagingService(){
        return messagingService;
	}


}
