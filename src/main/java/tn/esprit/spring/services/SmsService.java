package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.esprit.spring.entities.ConnectedUser;


@Service
public class SmsService {
	
	@Autowired
	CryptWithSHA256 cryptWitSHA256;
	


	private final static String ACCOUNT_SID = "AC5cc5801aad584e38817e3bb3f04732e4";
	private final static String AUTH_ID = "e7befcd4b5657bb97e502a62803d28ab";
   
	
	
		public void sendSMS(ConnectedUser user){
		
	
			/*String cryptePassword=cryptWitSHA256.cryptWithSHA256(user.getPassword());
	
			String smsSender= "Welcome to Dari.tn this is your password : "+cryptePassword ;
			
			 Twilio.init(ACCOUNT_SID,AUTH_ID);
				
			 Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()),
					 new PhoneNumber("+15867018276"),smsSender).create();*/
			

}
}
