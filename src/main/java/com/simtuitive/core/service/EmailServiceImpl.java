package com.simtuitive.core.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.simtuitive.core.service.abstracts.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	JavaMailSenderImpl sender =new JavaMailSenderImpl();
	
	
	@Override
	public void triggerMessageClient(String[] to, String[] cc, String subject, String text) {
		// TODO Auto-generated method stub

		
		java.util.Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", "Enquiry@simtuitive.com");
        props.put("mail.smtp.password", "Simtuitive#mail");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
        	MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            message.setFrom(new InternetAddress("Enquiry@simtuitive.com"));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            InternetAddress[] ccAddress = new InternetAddress[cc.length];
            String htmlMsg=null;
            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }   
            if(subject.equalsIgnoreCase("")) {
            	htmlMsg ="<body style='border:2px solid black'>\"\r\n" + 
                		"                   \r\n" + 
                		"					  \r\n" + 
                		"					  \r\n" + 
                		"					  \r\n" + 
                		"					  <td align=\"center\" style=\"color:#0000FF; font-size:24px;\">\r\n" + 
                		"                                                            <font face=\"'Roboto', Arial, sans-serif\">\r\n" + 
                		"                                                                <span style=\"font-size:44px;\">Client Registrations</span><br />\r\n" + 
                		"                                                                <br />\r\n" + 
                		"                                                                <Span style=\"font-size:24px;\">"+ text.toString()+"<br />\r\n" + 
                		"                                                                sourcing solution</Span>\r\n" +
                		"                                                                <br /><br />\r\n" +        		"\r\n" + 
                		"\r\n" + 
                		"                                                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"10\" style=\"border:2px solid #FFFFFF;\">\r\n" + 
                		"                                                                    <tr>\r\n" + 
                		"                                                                        <td align=\"center\" style=\"color:#E033FF; font-size:16px;\"><font face=\"'Roboto', Arial, sans-serif\"><a href=\"##\" style=\"color:#E033FF; text-decoration:none;\">http://13.234.177.23/login</a></font></td>\r\n" + 
                		"                                                                    </tr>\r\n" + 
                		"                                                                </table>\r\n" + 
                		"\r\n" + 
                		"                                                            </font>\r\n" + 
                		"                                                        </td>\r\n" + 
                		"														\r\n" + 
                		"														\r\n" + 
                		"														 </body>";

            }if(subject.equalsIgnoreCase("Invitation from simtutive")) {
            	 htmlMsg ="<body style='border:2px solid black'>\"\r\n" + 
                		"                   \r\n" + 
                		"					  \r\n" + 
                		"					  \r\n" + 
                		"					  \r\n" + 
                		"					  <td align=\"center\" style=\"color:#0000FF; font-size:24px;\">\r\n" + 
                		"                                                            <font face=\"'Roboto', Arial, sans-serif\">\r\n" + 
                		"                                                                <span style=\"font-size:44px;\">Client Invitation</span><br />\r\n" + 
                		"                                                                <br />\r\n" + 
                		"                                                                <Span style=\"font-size:24px;\">Greeting from simtutive<br />\r\n" + 
                		"                                                                Welcome!</Span>\r\n" +text.toString()+
                		"                                                                <br /><br />\r\n" + 
                		"\r\n" + 
                		"\r\n" + 
                		"                                                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"10\" style=\"border:2px solid #FFFFFF;\">\r\n" + 
                		"                                                                    <tr>\r\n" + 
                		"                                                                        <td align=\"center\" style=\"color:#E033FF; font-size:16px;\"><font face=\"'Roboto', Arial, sans-serif\"><a href=\"##\" style=\"color:#E033FF; text-decoration:none;\">http://13.234.177.23/login</a></font></td>\r\n" + 
                		"                                                                    </tr>\r\n" + 
                		"                                                                </table>\r\n" + 
                		"\r\n" + 
                		"                                                            </font>\r\n" + 
                		"                                                        </td>\r\n" + 
                		"														\r\n" + 
                		"														\r\n" + 
                		"														 </body>";

            }
                        message.setContent(htmlMsg, "text/html");
            message.setSubject(subject);
            
           
           
            Transport transport = session.getTransport("smtp");
            transport.connect(host, "Enquiry@simtuitive.com", "Simtuitive#mail");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
		
	}
	
	
}
