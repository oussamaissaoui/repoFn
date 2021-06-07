package tn.esprit.spring.controller;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.services.ReclamationService;


@RestController

@CrossOrigin(origins="*")


public class ReclamationRestController {
	
	@Autowired
	
	ReclamationService  reclamationservice ;
	
	private static final Logger logger = Logger.getLogger(ReclamationRestController.class);
	


	@PostMapping("/bad-words/{iduser}/{idcomment}")
	@ResponseBody
	public String AddReclamationAndBlokUser(@PathVariable("iduser") String iduser , @PathVariable("idcomment")String idComment){
		
		return reclamationservice.AddReclamationAndBlokUserWhenCommentsContainsBadWords(iduser,idComment);
		
	}

	
	@GetMapping("/getAllReclamations")
	@ResponseBody
	public List<Reclamation> getAllReclamations(){
		
		return reclamationservice.retrieveAllReclamations();
		
	}
}
