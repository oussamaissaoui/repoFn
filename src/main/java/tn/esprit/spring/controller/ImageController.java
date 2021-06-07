package tn.esprit.spring.controller;

import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.imgDesc;
import tn.esprit.spring.services.ImageService;



//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/upload1/{AdId}")
	ResponseEntity<?> imageUpload (@RequestParam("file") MultipartFile file,@PathVariable ("AdId") Long AdId){
		try {
			imgDesc FileName = imageService.Store(file, AdId);
			return new ResponseEntity<imgDesc>(FileName, HttpStatus.OK);
		} catch (IOException e) {
			//throw new RuntimeException("Un problème est apparu");
			return new ResponseEntity<String>("erreur", HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	@GetMapping("/find/{id}")
	ResponseEntity<imgDesc> recherche (@PathVariable("id") Long id) {
		imgDesc Fal = imageService.find(id);
		return new ResponseEntity<imgDesc>(Fal, HttpStatus.OK);
		
		
	}





}
