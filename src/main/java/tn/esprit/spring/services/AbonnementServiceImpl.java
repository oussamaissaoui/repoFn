package tn.esprit.spring.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.specification.ConnectedUserSpec;

@Service
public class AbonnementServiceImpl implements AbonnementService {

	@Autowired
	AbonnementRepository abonnementRepository;
	@Autowired
	ConnectedUserRepository userRepo;
	
	@Override
	public List<Object> MesAbonnes(Long abonne_id) { // houmaa eli 3amlelhom
														// abonner

		return abonnementRepository.MesAbonnes(abonne_id);
	}

	@Override
	public Long getAbonnes(Long abonne_id, Long abonnement_id) { // houmaa eli
																	// 3amlelhom
																	// abonner

		return abonnementRepository.getAbonnes(abonne_id, abonnement_id);
	}

	@Override
	public List<Object> Abonnements(Long Abonnement_id) { // ena eli mabonnehom

		return abonnementRepository.Abonnements(Abonnement_id);
	}

	@Override
	public Integer nombreAbonnes(Long abonne_id) {

		return abonnementRepository.nombreAbonnes(abonne_id);
	}

	
	@Override
	public Map<String, Abonnement> AbonnementService(long abonne_id, long Abonnement_id) {

		List<ConnectedUser> user = (List<ConnectedUser>) userRepo.findAll();
		List<Abonnement> Abonnements = (List<Abonnement>) abonnementRepository.findAll();

		Abonnement abonnement = new Abonnement();

		Map<String, Abonnement> result = new HashMap<String, Abonnement>();

		Long id = null;

		id = abonnementRepository.getAbonnes(abonne_id, Abonnement_id);

		if (id == null) {

			abonnement.setAbonnes(userRepo.findById(abonne_id).get());
			abonnement.setAbonnements(userRepo.findById(Abonnement_id).get());

			abonnementRepository.save(abonnement);

			result.put("Abonnement with success  ", null);

			return result;

		} else {

			result.put("Abonnement exists in database  ", null);

			return result;

		}

	}



}
