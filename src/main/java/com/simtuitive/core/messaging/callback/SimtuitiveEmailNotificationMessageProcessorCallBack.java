package com.simtuitive.core.messaging.callback;

import com.simtuitive.core.messaging.service.IConsumerCallback;
/**
 * On Message Arrival On Topic Name {SimtuitiveEmailNotification}, This Consumer CallBack
 * is designed to process the message.
 * @author Surath Patnaik
 *
 */
public class SimtuitiveEmailNotificationMessageProcessorCallBack implements IConsumerCallback {
	//private static final Logger LOGGER = LoggerFactory.getLogger(SimtuitiveEmailNotificationMessageProcessorCallBack.class);
	
	/**
	 * Message Is Consumed Here. Hence Process The Message
	 */
	 @Override
	  public boolean message(String topicName, String messagePayload) {
	   // LOGGER.info("FulfilmentOpsMessageProcessorCallBack - topic : " , topicName);
	   // LOGGER.info("Consumed Message : " + messagePayload);
	    System.out.println("Message Arrived in SimtuitiveEmailNotificationMessageProcessorCallBack ########  " + messagePayload);
	    return true;
	  }

}
