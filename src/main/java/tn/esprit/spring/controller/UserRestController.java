package tn.esprit.spring.controller;


import java.security.Principal;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.Role;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.AppointmentService;
import tn.esprit.spring.services.CustomUserDetailsService;
import tn.esprit.spring.services.IMailService;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.services.ReclamationService;
import tn.esprit.spring.services.Storageservice;
import tn.esprit.spring.specification.ConnectedUserSpec;
import tn.esprit.spring.util.JwtUtil;

@RestController

@CrossOrigin(origins="*", allowedHeaders = "*" )


public class UserRestController {
	
	@Autowired
	
	IUserService iuserService;
	
 
	@Autowired
	
	 ReclamationService  reclamationservice ;
	
	@Autowired
	
	  AppointmentService apoitmentService ;
	
	@Autowired
	
	 ConnectedUserRepository connectUserRepository ;
	
	@Autowired
	
	 IMailService imailService ;
	
	private static final Logger logger = Logger.getLogger(UserRestController.class);
	
	


	@PostMapping("/add-user/{password}")
	@ResponseBody
	
	public Map<String, ConnectedUser>  Add(@RequestBody ConnectedUser  client,@PathVariable("password") String password) throws Exception{
		
	
		return iuserService.ajouterClient(client, password);
	}
	
	
	
	/*@PostMapping("/add-admin")
	@ResponseBody
	
	public User AddAdmin(@RequestBody User  admin) throws Exception{
		
		return iuserService.ajouterAdmin(admin);
	}*/
	  


	@PostMapping("/authentification/{email}/{password}")
	@ResponseBody
	
	public String Authentification(@PathVariable("email") String email, @PathVariable("password") String password) throws Exception{
		
		return iuserService.authentification(email, password);
	}
	

	@PostMapping("/forget-Password/{email}")
	@ResponseBody
	public String forgetPassword(@PathVariable("email") String email){
		
		return iuserService.forgetPassword(email);
	}
	
 
	@PostMapping("/changer-Password/{username}/{OldPassword}/{password}/{newPassword}")
	@ResponseBody
	public String changerPassword(@PathVariable("username") String username,
			@PathVariable("OldPassword") String OldPassword,@PathVariable("password") String password,
			@PathVariable("newPassword")  String newPassword){
		
		
		return iuserService.changerPassword(username, OldPassword, password, newPassword);
	}
	
	
	@GetMapping("/getUser/{username}")
	@ResponseBody
	public User getUserByUsername(@PathVariable("username") String username){
		
		
		return iuserService.getUserByUsername(username);
	}
	

	@PostMapping("/debloquer-Compte/{email}")
	@ResponseBody
	public String deblockCompte(@PathVariable("email") String email){
		
		return iuserService.deblockCompte(email);
		
	}

	@PostMapping("/setPhoto/{idUser}/{photo}")
	@ResponseBody
	public void SetPhotoByClient(@PathVariable("idUser") Long idUser,@PathVariable("photo")String photo){
		
		 iuserService.SetPhotoByClient(photo, idUser);
		
	}
	
	@DeleteMapping("/delete-user/{idUser}")
	@ResponseBody
	public String DeleteUser(@PathVariable("idUser") Long idUser){
		
		return iuserService.DeleteConnectUser(idUser);
	}
	
	
	
	@GetMapping("/getUserByFirstName/{firstname}")
	@ResponseBody
	public List<ConnectedUser> getAllUserByFirstnameWithSpecification(@PathVariable("firstname") String firstname){
		
		Specification<ConnectedUser> specifications=Specification.where(iuserService.getUserByUsernameSpecifications(firstname));
		
		return connectUserRepository.findAll(specifications);
		
	}
	
	@GetMapping("/blockedUser/{idUser}")
	@ResponseBody
	public void blockedUser(@PathVariable("idUser") long idUser){
		
		iuserService.isBlockedUser(idUser);
		
	}
	
	@GetMapping("/deblockedUser/{idUser}")
	@ResponseBody
	public void debblockedUser(@PathVariable("idUser") long idUser){
		
		iuserService.isdeBlockedUser(idUser);
		
	}
	
	
	@GetMapping("/getAllUserByComments/{id_comment}") // tout va recuperer liste user eli enty lawajtha bel idcomment
	@ResponseBody
	public List<ConnectedUser> getAllUserByCommentsSpecification(@PathVariable("id_comment")  long id_comment){
		
       Specification<ConnectedUser> specifications=Specification.where(ConnectedUserSpec.getAllUserByComments(id_comment));
		
		return connectUserRepository.findAll(specifications);
		
		
	}
	
	

	@GetMapping("/fetchAllUsersByUserName/{username}")
	@ResponseBody
	public List<Object>fetchAllUsersByUserName(@PathVariable("username") String username){
		
		return connectUserRepository.fetchAllUsersByUserName(username);
		
	}
	
	

	@DeleteMapping("/deleteUserById/{idUser}")
	@ResponseBody
	public String DeleteConnectUser(@PathVariable("idUser")  long idUser){
		
		return iuserService.DeleteConnectUser(idUser);
	}
	
	
	@GetMapping("/getUserById/{idUser}")
	@ResponseBody
	public ConnectedUser getUserById (@PathVariable("idUser")  long idUser){
		
		return iuserService.getUserById(idUser);
	}
	
	
	@PutMapping("/updateUser/{idUser}")
	@ResponseBody
	public ConnectedUser updateUser(@RequestBody ConnectedUser user ,@PathVariable("idUser")  long idUser){
		
		return iuserService.updateUser(user, idUser);
	}
	

	@GetMapping("/getAllUsers")
	@ResponseBody
	public List<ConnectedUser> getAllUsers(){
		
		return iuserService.getAllUsers();
	}
	
	
	@GetMapping("/getUsersByEmail/{email}")
	@ResponseBody
	public ConnectedUser getUserByEmail(@PathVariable("email")  String email){
		
		return iuserService.getUserByEmail(email);
		
	}
	
	@GetMapping("/isConnected/{idUser}")
	@ResponseBody
	public void isConnected(@PathVariable("idUser")  long idUser){
		
		iuserService.isConnected(idUser);
	}
	
	
	@GetMapping("/isDonnected/{idUser}")
	@ResponseBody
	public void isDeonnected(@PathVariable("idUser")  long idUser){
		
		iuserService.isDeonnected(idUser);
		
	}
	
	@GetMapping("/findIdByUserName/{userName}")
	@ResponseBody
	public long findIdByUserName(@PathVariable("userName")  String userName){
		
		return iuserService.findIdByUserName(userName);
	}

	
	@PutMapping("/updateProfileUser/{idUser}")
	@ResponseBody
	public ConnectedUser updateProfileUser(@RequestBody ConnectedUser user ,@PathVariable("idUser")  long idUser){
		
		return iuserService.updateProfileUser(user, idUser);

	}


	@GetMapping("/adduserNotif/{idUser}")
	@ResponseBody
	public String  addUserNotifcation(@PathVariable("idUser")  long idUser){
		
		return iuserService.addUserNotifcation(idUser);
		
	}
	
	@GetMapping("/getUserByNotification")
	@ResponseBody
	public List<ConnectedUser> getAllNotifications(){
		
		return iuserService.getAllNotifications();
				
	}
	
	@DeleteMapping("/deleteNotifById/{idNotif}")
	@ResponseBody
	public String deleteAllNotif(@PathVariable("idNotif")  long idNotif){
		
		return iuserService.deleteNotifById(idNotif);
	}
	
	
	
	@GetMapping("/getAllEmailReceived")
	@ResponseBody
	public Map<String,List<Object>> receiveEails() throws Exception{
		
		return imailService.receiveEmail( "imap.gmail.com", "imap", "youssef.ayari1@esprit.tn", "Ayarinhomessinho12");
				
	}
}


