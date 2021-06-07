package tn.esprit.spring.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;



public interface AbonnementService {
	public List<Object> MesAbonnes(Long abonne_id);
	public List<Object>Abonnements(Long Abonnement_id);
	public Integer nombreAbonnes(Long abonne_id);
	
	public Map<String,Abonnement>  AbonnementService(long abonne_id, long Abonnement_id);

	public Long getAbonnes(Long abonne_id,Long abonnement_id);
	
    


}
