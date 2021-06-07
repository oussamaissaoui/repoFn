package tn.esprit.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.Exception.SellAdNotFoundException;
import tn.esprit.spring.entities.SellAd;
import tn.esprit.spring.entities.imgDesc;

@Service
public class ImageService {
	
	@Autowired
	private tn.esprit.spring.repository.ImageRepo imageRepo;
	@Autowired
	private SellService sellService;
	
	public imgDesc Store (MultipartFile File, Long AdId) throws MultipartException, IOException {
		
		String fileName = org.springframework.util.StringUtils.cleanPath(File.getOriginalFilename());
			imgDesc imageFileDesc = new imgDesc(fileName,File.getContentType(), File.getBytes());
		
		// file.getBytes doit avoir traitement d'exception IOException
		// validation du Size dans application.properties
		if (File != null && File.getContentType() != null && !File.getContentType().toLowerCase().startsWith("image"))
		{throw new MultipartException("Le document que vous ajoutez n'est pas une image");}
		
		SellAd sellinter = sellService.findSellAdById(AdId);
		imageFileDesc.setAd(sellinter);
		
		
		return imageRepo.save(imageFileDesc);
		
	}
	
	public imgDesc find (Long id) {
		return imageRepo.findById(id).orElseThrow(()-> new SellAdNotFoundException(id));
	}
	
	//public imgDesc addImageToId (Long AdId,  ) retirée étant donné qu'une image ne peux pas exister toute seule sans Annonces
	

}
