package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tn.esprit.spring.EventListner.Eve;
import tn.esprit.spring.Exception.SellAdNotFoundException;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.Location;
import tn.esprit.spring.entities.SellAd;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.SellRepo;


@Service
public class SellService {
	
	@Autowired
	private SellRepo sellrepo;
	
	@Autowired
	private ConnectedUserRepository UserRepo;
	
	
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	
public SellAd addSellAnnonce2(SellAd sellad) {
		
		
	
		return sellrepo.save(sellad);  // public void walla public sellad + return sellrepo.save !!!!!!!!!!!!!!!!!!!!!
		
	}

	public SellAd addSellAnnonce(SellAd sellad, Long StUser) {
		
		ConnectedUser user= UserRepo.findById(StUser).get();
		sellad.setUser(user);
		return sellrepo.save(sellad);  // public void walla public sellad + return sellrepo.save !!!!!!!!!!!!!!!!!!!!!
		
	}

	public Page<SellAd> findAllSellAds(int pageNumber, int pageSize, Optional<String> sortBy){   // public list<Ad> et non pas public list<SellAdd> !!!!!!!!
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
 		return sellrepo.findAll(page);
 		//, Sort.Direction.DESC, sortBy.orElse("id")
		//return sellrepo.findAll();
	}
	
	public String updateSellAd (SellAd sellad /*Long id*/) {
		/*SellAd item = findSellAdById(id);
		item=sellad;*/
		sellrepo.save(sellad);
		return "Annonce modifiée";
	}
	
	public void deleteSellAd (Long id) {
		// sellrepo.deleteById(id);    // traitement d'exception pour delete by id vs delete does not require exception !!!!!!
		SellAd item = findSellAdById(id);
		sellrepo.delete(item);
	}
	
	public SellAd findSellAdById (Long id){
		return sellrepo.findById(id).orElseThrow(()->
		//new RuntimeException()
		new SellAdNotFoundException(id)
				);
	}
	
	public List<SellAd> findBykeywords(String description ){
		
		 List<SellAd> listadAds = sellrepo.findByKeyword(description);
		 return listadAds;
		
	};
	
	
	public List<SellAd> findByCrit(
			boolean terrasse,boolean balcon,boolean assenceur,boolean cave,
			boolean piscine,boolean vueMer,boolean parking,boolean garage)
	{
		
		 List<SellAd> listadAdscrit = sellrepo.findByAutresCrit(terrasse, balcon, assenceur,
				 cave, piscine, vueMer, parking, garage);
		 return listadAdscrit;
		
	};
	
	public List<SellAd> findprincipal(int surface, int nbrPieces, Location location, int prixMin,int prixMax ){
		
		 List<SellAd> listprin = sellrepo.findPrincipal(surface, nbrPieces, location, prixMin, prixMax);
		 
		 //variables intermédiaires pour l'event Listner
		 int intersurface = surface;
		 int internbrPieces=nbrPieces;
		 String interlocationString = location.toString();
		 int interprixMin=prixMin;
		 int interprixMax=prixMax;
		 
		 if (listprin.isEmpty()) {
			 

				applicationEventPublisher.publishEvent(new Eve(this,intersurface,internbrPieces,interlocationString,
						interprixMin,interprixMax));

				
		}
		 return listprin;
		
	};
	
	public List<SellAd> showbyrecent (){
		List<SellAd> rca = sellrepo.recentAd();
		return rca;
	}
	
	public List<SellAd> showbyViews (){
		List<SellAd> vw = sellrepo.MostViewedAd();
		return vw;
	}
	
	
	
	//public SellAd AddcommenttoAd (Long adId, Long commentid) {
		//SellAd sellAd = findSellAdById(adId);
		//comment comment = commentsService.findById(commentid);
		//sellAd.addcomment(comment);
	//return sellAd;
	//}
	
	
	
	
}
