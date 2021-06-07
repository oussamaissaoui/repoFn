package tn.esprit.spring.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.ImgMulti;
import tn.esprit.spring.entities.SellAd;
import tn.esprit.spring.repository.ImageMultiRepo;

@Service
public class ImageMultiService {
	
	@Autowired
	private ImageMultiRepo repo;
	
	@Autowired
	private SellService sellService;
	
	public ImgMulti upload (MultipartFile file, Long AdId) throws IllegalStateException, IOException {
		
		file.transferTo(new File("C:\\Users\\ouss\\Desktop\\zzz\\Dari-Front\\dariTn\\src\\assets\\"+file.getOriginalFilename()));
		
		String Path = "../../assets/"+file.getOriginalFilename();
		ImgMulti im = new ImgMulti(Path);
		
		SellAd sellinter = sellService.findSellAdById(AdId);
		im.setAdd(sellinter);
		return repo.save(im);
		
	}
	
}
