package com.simtuitive.core.messaging.callback;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simtuitive.core.messaging.service.IConsumerCallback;
import com.simtuitive.core.model.EmailNotificationMeta;
import com.simtuitive.core.service.EmailServiceImpl;
import com.simtuitive.core.service.abstracts.IEmailService;
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
		 ObjectMapper objectMapper = new ObjectMapper();
		 EmailNotificationMeta meta=new EmailNotificationMeta();
		 try {
			meta=objectMapper.readValue(messagePayload.toString(), EmailNotificationMeta.class);			
			String[] getto = Arrays.copyOf(meta.getTo(),
					meta.getTo().length,
					String[].class);
			String[] getcc = Arrays.copyOf(meta.getCc(),
					meta.getCc().length,
					String[].class);			
			String to=Arrays.toString(getto);
			String cc=Arrays.toString(getcc);
			if(to.isEmpty() || to==null) {
				System.out.println("empty1");
			}
			if(cc.isEmpty() || cc==null) {
				System.out.println("empty1cc");
			}
			if(meta.getSubject().isEmpty() || meta.getSubject()==null) {
				System.out.println("getSubject");
			}
			if(meta.getText().isEmpty() || meta.getText()==null) {
				System.out.println("getText");
			}			
			EmailServiceImpl emailservice= new EmailServiceImpl();
			emailservice.triggerMessageClient(getto,getcc, meta.getSubject(), meta.getText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	    System.out.println("Message Arrived in SimtuitiveEmailNotificationMessageProcessorCallBack ########  " + messagePayload);
	    
	    
	    return true;
	  }

}
