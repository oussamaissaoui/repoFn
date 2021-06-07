package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Location;
import tn.esprit.spring.entities.SellAd;
import tn.esprit.spring.services.SellService;



//@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT})

@RestController
@RequestMapping("/Achat")
public class SellAdController {
	@Autowired
	private SellService sellservice;
	
	//implementer les validators pour les request param du paging en cas de valeurs illogiques ex="-1" !!!!!!!!!
	// utiliser aussi (Pageable page) au lieu des param avec une config dans application.properties
	// spring.data.web.pageable.size-parameter=pageSize
	
	@GetMapping("/All")
	public ResponseEntity<Page<SellAd>> getallselladdEntity (
			@RequestParam(required = false, defaultValue = "0" ) int pageNumber, 
			@RequestParam(required = false, defaultValue = "40") int pageSize,
			@RequestParam Optional<String> SortBy
			)
	{
		Page<SellAd> lili= sellservice.findAllSellAds(pageNumber,pageSize,SortBy );
		return new ResponseEntity<>(lili, HttpStatus.OK);
	}
	
	@PostMapping("/Add/{StUser}")
	public ResponseEntity<SellAd> AjouterSellAdd(@RequestBody SellAd sellad,@PathVariable("StUser") long StUser){
		SellAd ajout = sellservice.addSellAnnonce(sellad, StUser);
		return new ResponseEntity<>(ajout, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/findByid/{id}")
	public ResponseEntity<?> rechercheParId(@PathVariable("id") Long id){
		
		SellAd parIdAd = sellservice.findSellAdById(id);
		//if (parIdAd==null ||  !(id instanceof Long)) { return new ResponseEntity<String>(" 'Id' is incorrect", HttpStatus.BAD_REQUEST);}
		
		
		return new ResponseEntity<SellAd>(parIdAd, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAd(@PathVariable("id") Long id, @RequestBody SellAd sellad){
		sellservice.updateSellAd(sellad);
		return new ResponseEntity<String>("Annonce modifiée", HttpStatus.OK);
	}
	
	@DeleteMapping ("/delete/{id}")
	public ResponseEntity<?> supprimerAd(@PathVariable("id") Long id){
		 sellservice.deleteSellAd(id);
		return new ResponseEntity<String>("Item with id " +id+" has been deleted",HttpStatus.OK);
	}
	
	@GetMapping("/find/word/{description}")
	public ResponseEntity<?> rechercheParkeyword(@PathVariable("description") String description){
		
		List<SellAd> parkeyword = sellservice.findBykeywords(description);
		if (parkeyword.isEmpty()) { return new ResponseEntity<String>(" There is no ad containing your critarias", HttpStatus.BAD_REQUEST);}
		
		else {
			return new ResponseEntity<List<SellAd>>(parkeyword, HttpStatus.OK);

		}
	}
	
	@GetMapping("/find/crit/{terrasse}/{balcon}/{assenceur}/{cave}/{piscine}/{vueMer}/{parking}/{garage}")
	public ResponseEntity<?> rechercheParCrit(
			@PathVariable("terrasse") boolean terrasse,
			@PathVariable("balcon") boolean balcon,
			@PathVariable("assenceur") boolean assenceur,
			@PathVariable("cave") boolean cave,
			@PathVariable("piscine") boolean piscine,
			@PathVariable("vueMer") boolean vueMer,
			@PathVariable("parking") boolean parking,
			@PathVariable("garage") boolean garage)
	{
		
		List<SellAd> parcri = sellservice.findByCrit(terrasse, balcon, assenceur, cave, piscine, vueMer, parking, garage);
		if (parcri.isEmpty()) { return new ResponseEntity<String>(" There is no ad containing your critaria", HttpStatus.BAD_REQUEST);}
		
		else {
			return new ResponseEntity<>(parcri, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/find/Main/{surface}/{nbrPieces}/{location}/{prixMin}/{prixMax}")
	public ResponseEntity<?> recherchePrinc (
			@PathVariable("surface") int surface,
			@PathVariable("nbrPieces") int nbrPieces,
			@PathVariable("location") Location location,
			@PathVariable("prixMin") int prixMin,
			@PathVariable("prixMax") int prixMax) {
		
		List<SellAd> princi = sellservice.findprincipal(surface, nbrPieces, location, prixMin, prixMax);
		if (princi.isEmpty()) { return new ResponseEntity<String>(" There is no ad containing your critarias", HttpStatus.BAD_REQUEST);}
		
		else {
			return new ResponseEntity<List<SellAd>>(princi, HttpStatus.OK);

		}
	}
	
	@GetMapping("/recent")
	public ResponseEntity<?> showrecent (){
		
		List<SellAd> rec = sellservice.showbyrecent();
		
		if (rec.isEmpty()) { return new ResponseEntity<String>(" There are no ads", HttpStatus.BAD_REQUEST);}
		
		else {
			return new ResponseEntity<>(rec, HttpStatus.OK);

		}
	}
	
	@GetMapping("/MostViewed")
	public ResponseEntity<?> showrecViewed (){
		
		List<SellAd> vw = sellservice.showbyViews();
		
		if (vw.isEmpty()) { return new ResponseEntity<String>(" There are no ads", HttpStatus.BAD_REQUEST);}
		
		else {
			return new ResponseEntity<>(vw, HttpStatus.OK);

		}
	}
	
	//@PostMapping("/addcomment/{addId}/{commentid}")
	//public ResponseEntity<SellAd> addcommenttoadEntity (@PathVariable("addId") Long addid,
		//@PathVariable("commentid") Long commentid){
		//SellAd sellComment = sellservice.AddcommenttoAd(addid, commentid);
		//return new ResponseEntity<SellAd>(sellComment, HttpStatus.OK);
		
	//}
}
