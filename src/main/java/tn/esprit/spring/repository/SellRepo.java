package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Location;
import tn.esprit.spring.entities.SellAd;


@Repository
public interface SellRepo extends AbstractAdRepo<SellAd>{

	SellAd findSellAdById(Long id);
	
	@Query(" select u from SellAd u where u.description like %:description% or u.title like %:description%  ORDER by id desc")
	List<SellAd> findByKeyword(@Param("description") String description);

	@Query(" select u FROM SellAd u where u.autresCriteres.terrasse=:terrasse AND u.autresCriteres.balcon=:balcon AND u.autresCriteres.assenceur=:assenceur AND u.autresCriteres.cave=:cave AND u.autresCriteres.piscine=:piscine AND u.autresCriteres.vueMer=:vueMer AND u.autresCriteres.parking=:parking AND u.autresCriteres.garage=:garage")
	List<SellAd> findByAutresCrit(
			@Param("terrasse") boolean terrasse,
			@Param("balcon") boolean balcon,
			@Param("assenceur") boolean assenceur,
			@Param("cave") boolean cave,
			@Param("piscine") boolean piscine,
			@Param("vueMer") boolean vueMer,
			@Param("parking") boolean parking,
			@Param("garage") boolean garage);
	
	@Query(" select u FROM SellAd u where u.surface <= :surface AND u.nbrPieces <= :nbrPieces AND u.adresse.location=:location AND u.prix between :prixMin and :prixMax")
	List<SellAd> findPrincipal(
			@Param("surface") int surface,
			@Param("nbrPieces") int nbrPieces,
			@Param("location") Location location,
			@Param("prixMin") int prixMin,
			@Param("prixMax") int prixMax
			);
	
	//select u.date FROM SellAd u
	@Query( " SELECT u from SellAd u order by u.date desc " )
	List<SellAd> recentAd();

	@Query( " SELECT u from SellAd u order by u.viewsNumber desc " )
	List<SellAd> MostViewedAd();

}
