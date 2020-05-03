package com.simtuitive.core.messaging.callback;

import org.apache.commons.lang3.StringUtils;

import com.simtuitive.core.messaging.service.IConsumerStateCallback;

public class ConsumerStateFailureCallback implements IConsumerStateCallback {
	//private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerStateFailureCallback.class);
	public void onConsumerStateFailure(String topicName, Exception ex){
		if(StringUtils.isNotBlank(topicName)){
			//LOGGER.info("ConsumerStateFailureCallback, consumer is not in a appropriate state for the - topic : " + topicName);
		    System.out.println("Consumer for this topic is in broken state ########  " + topicName);
		}else{
			//LOGGER.info("ConsumerStateFailureCallback, consumer is not in a appropriate state for the - topic : " );
		    System.out.println("Consumer for this topic is in broken state ########  ");
		}
	}

}
