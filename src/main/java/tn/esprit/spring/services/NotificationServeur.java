package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;


@Service
public class NotificationServeur {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CryptWithSHA256 cryptWitSHA256;
	
	
	public NotificationServeur(JavaMailSender javaMAilSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(ConnectedUser user){
		
		String password="";
		
		for (int i=0;i<10;i++ ) {
			
			password = password+randomCharacter("abcdefjhijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUV");
			
		}
		String randomdigit=randomCharacter("123456789");
		password=insertAtRandom(password, randomdigit);
		
		System.out.println("nchouff "+password);
		
		String randomSymbol=randomCharacter("$£@@@");
		
		password = insertAtRandom(password, randomSymbol);
		
		
		String pass=password;
		
		String cryptePassword=cryptWitSHA256.cryptWithSHA256(pass);
		
		System.out.println("avant base de donne "+cryptePassword);
		
		user.setPassword(cryptePassword);		
		userRepository.save(user);
		
		System.out.println(pass);
		
		
		//sending email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("youssef.ayari1@esprit.tn");
		mail.setSubject(user.getFirstName());
		mail.setText("Welcome to Dari.tn this is your password : " +pass);
		javaMailSender.send(mail);
		System.out.println("apres mail"+user.getPassword());
	}
	
	public static String randomCharacter(String charachter) {
		int n = charachter.length();
		int r = (int)(n*Math.random());
		
		return charachter.substring(r,r+1);
	}
	public static String insertAtRandom(String str,String toInsert) {
		int n = str.length();
		int r=(int)(n * Math.random());
		return str.substring(0,r)+ toInsert + str.substring(r);
	}	
	
	
	
}
