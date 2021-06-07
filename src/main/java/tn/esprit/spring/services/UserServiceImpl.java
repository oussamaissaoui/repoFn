package tn.esprit.spring.services;


import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.Notification;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.specification.ConnectedUserSpec;
import tn.esprit.spring.util.JwtUtil;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	ConnectedUserRepository userRepository;

	@Autowired
	CryptWithSHA256 cryptaWithSHA256;

	@Autowired
	NotificationServeur notificationServeur;

	@Autowired
	Filter filter;

	@Autowired
	JwtUtil javautil;
	
	
	@Autowired
	ConnectedUserSpec spec;

	@Autowired
	SmsService smsService;

	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	public Map<String, ConnectedUser> ajouterClient(ConnectedUser client, String password) throws Exception {

		Role role = null;

		Map<String, ConnectedUser> result = new HashMap<>();

		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // verification
																						// mail

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(client.getEmail());

		boolean verifey = false;

		for (ConnectedUser us : users) {

			if (client.getEmail().equals(us.getEmail())) {

				verifey = true;

				result.put("Email exits deja dans la base de donner", null);

			}
			
			if (!matcher.matches()) {
				
				verifey=true;

				result.put("Invalid email", null);

			}

			if (client.getUserName().equals(us.getUserName())) {

				verifey = true;

				result.put("Username exits deja dans la base de donner", null);

			}
			

		}
		
		if (!checkString(password)) {

			verifey = true;

			result.put("Password have 8 caracter upper lower and number", null);

		}

		if (!verifey) {

			smsService.sendSMS(client);
			
			client.setRole(role.Client);

			String Pass = cryptaWithSHA256.cryptWithSHA256(password);

			client.setPassword(Pass);

			userRepository.save(client);

			result.put("Success add client have Registred ", client);

			return result;

		}

		return result;

	}
	
	

	public ConnectedUser ajouterAdmin(ConnectedUser admin) {

		Role role = null;

		admin.setRole(role.Admin);

		userRepository.save(admin);

		return admin;

	}

	public String authentification(String email, String password) throws Exception {

		Role role = null;

		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		int verifyemail = 0;
		int verifypassword = 0;

		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // verification
																			// mail

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);

		for (ConnectedUser user : users) {

			if (user.getEmail().equals(email)) {
				verifyemail++;

				if (user.getPassword().equals(cryptaWithSHA256.cryptWithSHA256(password))) {

					verifypassword++;

					if (user.getRole() == role.Admin) {

						try {
							authenticationManager.authenticate(
									new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
						} catch (Exception ex) {

							throw new Exception("inavalid username/password ");
						}

						return (javautil.generateToken(user.getUserName()));

					} else if (user.getRole() == role.Client) {

						if (user.isBlock() == true) {

							return user.getDescriptionBlock() + "Try to contact with admin !!";

						} else {

							try {
								authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
										user.getUserName(), user.getPassword()));
							} catch (Exception ex) {
								throw new Exception("inavalid username/password");
							}

							return (javautil.generateToken(user.getUserName()));
						}

					} else if (user.getRole() == role.Visitor) {

						return ("Welcome agent");
					}

				} else { // else pa rapport satuts du password qui est incorrect

					if (user.getRole() == role.Client) {

						int nbre = user.getNbre();

						user.setNbre(nbre + 1);

						userRepository.save(user);

						if (user.getNbre() == 3) {

							user.setBlock(true);

							user.setDescriptionBlock("security problem");

							userRepository.save(user);

							return ("Votre compte est blocke security problem");

						} else if (user.getNbre() >= 3) {

							user.setNbre(3);

							userRepository.save(user);

							return ("Votre compte est blocke security problem");

						} else {
							return ("Password incorrect");

						}

					}

				}
			} else {

				if (!matcher.matches()) {

					return "Invalid email";

				}

			}
		}
		if (verifyemail == 0) {

			return ("Email not found");
		} else {

			return ("");

		}

	}
	
	
	

	private static boolean checkString(String str) { // pour verifier 8 maj et
														// min
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		boolean numberFlag = false;
		if (str.length() > 8) {
			for (int i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (Character.isDigit(ch)) {
					numberFlag = true;
				} else if (Character.isUpperCase(ch)) {
					capitalFlag = true;
				} else if (Character.isLowerCase(ch)) {
					lowerCaseFlag = true;
				}
				if (numberFlag && capitalFlag && lowerCaseFlag)
					return true;
			}
		}
		return false;
	}

	@Override
	public String forgetPassword(String email) {

		int existe = 0;

		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			return "invalid email";
		}

		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		for (ConnectedUser us : users) {

			if (us.getEmail().equals(email)) {

				notificationServeur.sendNotification(us);

				existe = 1;

				return "We sind you a new password pleaz try to connect with it";

			}
		}
		if (existe == 0) {

			return "This compte n'existe pas you can create an accompte";
		}
		return null;
	}
	
	

	@Override
	public String deblockCompte(String email) {

		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		for (ConnectedUser user : users) {

			System.out.println(user.getEmail());

			if (user.getEmail().equals(email)) {

				if (user.getDescriptionBlock().equals("security problem")) {

					notificationServeur.sendNotification(user);

					user.setDescriptionBlock("nothing");

					user.setNbre(0);

					user.setBlock(false);

					userRepository.save(user);

					return "we send you a new password pleaz chek your email";

				} else {

					return "this is not a security problem";
				}

			}
		}
		return "Email not found";

	}

	@Override
	public String changerPassword(String username, String OldPassword, String password, String newPassword) {

		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		for (ConnectedUser user : users) {

			if (user.getUserName().equals(username)) { // lezmou iddour fel for koll pour trouver userName pas d'interuption with return
				
				if (user.getPassword().equals(cryptaWithSHA256.cryptWithSHA256(OldPassword))) {

					if (newPassword.equals(password)) {

						if (checkString(newPassword)) {

							user.setPassword(cryptaWithSHA256.cryptWithSHA256(newPassword));

							userRepository.save(user);

							return "Password changer with succes";
						} else {

							return "Password have 8 caracter upper lower and number";
						}
					} else {
						return "Password not the same";
					}

				} else {
					return "Password incorrect";
				}
			}
		}
		return "Username does not exist";
	}
	
	

	public User getUserByUsername(String username) { // teb3aa changer mdp pour
														// recuperer utilisateur

		ConnectedUser user = userRepository.findByUserName(username);

		return user;
	}
	
	

	public void SetPhotoByClient(String photo, Long idUser) {

		ConnectedUser user = (ConnectedUser) userRepository.findById(idUser).get();

		user.setPicture(photo);

		userRepository.save(user);
	}

	
	
	public String DeleteConnectUser(long idUser) {

		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		if (users.isEmpty()) {

			return "There is no users in database";
		} else {

			userRepository.deleteById(idUser);

		}

		return "User is removed with success";

	}
	
	public ConnectedUser updateUser(ConnectedUser connectedUser,long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		user.setBlock(connectedUser.isBlock());
		user.setDateNaissance(connectedUser.getDateNaissance());
		user.setFirstName(connectedUser.getFirstName());
		user.setLastName(connectedUser.getLastName());
		user.setPassword(cryptaWithSHA256.cryptWithSHA256(connectedUser.getPassword()));
		user.setUserName(connectedUser.getUserName());
		user.setEmail(connectedUser.getEmail());
		user.setPhoneNumber(connectedUser.getPhoneNumber());
		user.setLieu(connectedUser.getLieu());
		
		userRepository.save(user);
		
		
		return user;
	}
	
	
	public ConnectedUser updateProfileUser(ConnectedUser connectedUser,long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		user.setDateNaissance(connectedUser.getDateNaissance());
		user.setFirstName(connectedUser.getFirstName());
		user.setLastName(connectedUser.getLastName());
		user.setEmail(connectedUser.getEmail());
		user.setPhoneNumber(connectedUser.getPhoneNumber());
		user.setLieu(connectedUser.getLieu());
		
		userRepository.save(user);
		
		
		return user;
	}
	
	
	public void isBlockedUser(long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		user.setBlock(true);
		
		userRepository.save(user);

		
	}
	
	
	public void isConnected(long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		user.setIsConnected(true);
		
		userRepository.save(user);
	}
	

	public void isDeonnected(long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		user.setIsConnected(false);
		
		userRepository.save(user);
	}
	

	
	public long findIdByUserName(String userName){
		
		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		
		 for (ConnectedUser us:users){
			 
			 if(us.getUserName().equals(userName)){
				 
				 return us.getId();
			 }
		 }
		 
		 return 0L;
		
	}
	
	 
	public ConnectedUser getUserByEmail(String email){
		
		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		
		 for (ConnectedUser us:users){
			 
			 if(us.getEmail().equals(email)){
				 
				 return us;
			 }
		 }
		return null;
	}
	
	
	
	public void isdeBlockedUser(long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		user.setBlock(false);
		
		userRepository.save(user);

		
	}
	
	
	
	public ConnectedUser getUserById(long idUser){
		
		ConnectedUser user= userRepository.findById(idUser).get();
		
		return user;
		
	}
	 
	
	public List<ConnectedUser> getAllUsers(){
		
		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		return users;
	}
	
	
	
	/******************Notifcation pour register user service******************/
	
	public String  addUserNotifcation(long idUser){
		
		ConnectedUser user =  userRepository.findById(idUser).get();

		
		Notification notif = new Notification();
		notif.setUser(user);
		
		
		notificationRepository.save(notif);
		
		return "added notif with success";
	
	}
	
	
	public List<ConnectedUser> getAllNotifications(){
		
		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		List<Notification> notif = (List<Notification>) notificationRepository.findAll();
		
		List<ConnectedUser> list = new ArrayList<>();
		
		  for(ConnectedUser us: users){
			  
			   for(Notification ns:notif){ 
				   if(us.getId() == ns.getUser().getId()){
					
					   list.add(us);
					 
					  
				   }
			   }
		  }
		  return list;
	}
	
	
	public String  deleteNotifById(long idNotif){
		
		List<Notification> notifs = (List<Notification>) notificationRepository.findAll();

		if (notifs.isEmpty()) {

			return "There is no notifications in database";
		} else {

			notificationRepository.deleteById(idNotif);

		}

		return "Notification is removed with success";

	}
	
	
	////////////////////////////////Specifications//////////////////////////////////////////////
	
	
	
	public Specification<ConnectedUser> getUserByUsernameSpecifications(String fistname) {  // requette with specifications

		return spec.hasFirstName(fistname);
	}



	public Specification<ConnectedUser> getAllUserByComments(long IdComment) { //requette with specifications

		return spec.getAllUserByComments(IdComment);
	}
	
	
	
	@Override
	public List<Object> fetchAllUserByUserName(String username) { // ena eli mabonnehom

		return userRepository.fetchAllUsersByUserName(username);
	}



}
