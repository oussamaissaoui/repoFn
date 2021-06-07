package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.util.MailSSLSocketFactory;

import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;



@Service

public class MailService implements IMailService{

	
	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(String maill,String descriptionmail , String usermail) throws MailException {

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(maill);
		mail.setSubject("Warning!!");
		mail.setText("Dear Client :"+usermail+" Your comment below :"+descriptionmail+ " has been blocked!! If you will repeat another one you will be blocked as well ;)");

		javaMailSender.send(mail);
		
	}

		public void sendEmail2(String maill, String usermail) throws MailException {

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(maill);
		mail.setSubject("Block Mail");
		mail.setText("Dear Client :"+usermail+" You have been blocked !!!");

		javaMailSender.send(mail);
		
	}

	
	
	
	public void sendEmailWithAttachment(ConnectedUser user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}
	
	
	
	 public   Map<String,List<Object>> receiveEmail(String host, String storeType,  String username, String password) throws Exception
	  {
		 
          Map<String, List<Object>> result= new HashMap();
          List<Object> list= new ArrayList<>();
          
	      try
	      {
	    	  
	    	  
	          Properties properties = new Properties();  
	          
	          MailSSLSocketFactory sf = new MailSSLSocketFactory();
	          sf.setTrustAllHosts(true); 
	          properties.put("mail.imap.ssl.trust", "*");
	          properties.put("mail.imap.ssl.socketFactory", sf);
	          properties.put("mail.imap.com", host);  
	          properties.put("mail.imap.starttls.enable","true");
	          properties.put("mail.imap.auth", "true");  // If you need to authenticate

	          // Use the following if you need SSL
	          properties.put("mail.imap.socketFactory.port", 993);
	          properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	          properties.put("mail.imap.socketFactory.fallback", "false");

	          Session emailSession = Session.getDefaultInstance(properties);  
	          emailSession.setDebug(true);

	          //2) create the IMAP store object and connect with the Imap server  
	          IMAPStore emailStore = (IMAPStore) emailSession.getStore(storeType);

	          emailStore.connect(host, username, password);  

	          //3) create the folder object and open it  
	          Folder emailFolder = emailStore.getFolder("INBOX");  
	          emailFolder.open(Folder.READ_ONLY);  

	          //4) retrieve the messages from the folder in an array and print it  
	          Message[] messages = emailFolder.getMessages();  
	          
	          
	          for (int i = messages.length-1; i >messages.length-30 ; i--) 
	          {
	              Message message = messages[i];  
	              MimeMessage m = new MimeMessage(emailSession);
	            
	              System.out.println("---------------------------------");  
	              System.out.println("Email Number " + (i + 1));  
	              System.out.println("date sendeeeet " + message.getSentDate()); 
	             
	              List<Object> listEmail = new ArrayList(); 

	              listEmail.add(message.getFrom()[0]);
	              listEmail.add(message.getSentDate());
	              
	              
	              if (message.getSubject() == null){
	            	 
	              
	              result.put("nothing", listEmail);
	              
	              }else{
	            	  
		              result.put(message.getSubject(),listEmail);

	              }
	           
	          }  
	          
	        

	          //5) close the store and folder objects  
	          emailFolder.close(false);  
	          emailStore.close();  

	      } 
	      catch (NoSuchProviderException e) {e.printStackTrace();}   
	      catch (MessagingException e) {e.printStackTrace();}  
	      
	 	 return result;
	  }
	 

}
