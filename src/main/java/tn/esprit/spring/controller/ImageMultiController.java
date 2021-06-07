package tn.esprit.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.services.ImageMultiService;

@RestController
@RequestMapping("/imgMulti")
public class ImageMultiController {
	
	@Autowired
	private ImageMultiService service;
	
	@PostMapping("/upload/{AdId}")
	void imageUpload (@RequestParam("file") MultipartFile file,@PathVariable ("AdId") Long AdId) throws IllegalStateException, IOException {
		service.upload(file, AdId);
	}

}
