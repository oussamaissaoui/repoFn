
package tn.esprit.spring.controller;

	import java.util.List;
	import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.core.io.Resource;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
	import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
	import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.Storageservice;

	 

	@RestController

	@CrossOrigin("*")
	public class RestcontrolleurUpload {

		private static final Logger l= LogManager.getLogger(RestcontrolleurUpload.class);
			String password;
			
		 	
		 	  @Autowired
		 	  UserRepository employerrepository;
		 	  
			  @Autowired
			  
			  Storageservice storageService ;
			  
			  
		
			  @PostMapping("/upload")
			  public void uploadFile(@RequestParam("file") MultipartFile file) {
			    String message = "";
			    try {
			      storageService.save(file);
			      
			    /*  message = "Uploaded the file successfully: " + file.getOriginalFilename();
			      
			      
			      User em = new User("fff", "hhj", "hjhkj");
			    
			  
			      em.setPicture(file.getOriginalFilename());*/
			      
			    //  employerrepository.save(em);
			    
			    } catch (Exception e) {
			      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			     
			    }
			  }
			  
			
			}