package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.Notification;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.repository.ReclamationRepository;

import tn.esprit.spring.repository.UserRepository;

@Service
public class ReclamationServiceImpl implements ReclamationService {

	@Autowired
	ReclamationRepository reclamationRepository;

	@Autowired
	MailService mailservice;

	@Autowired
	ConnectedUserRepository userRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	Filter filter;

	@Autowired
	CommentRepository commentrepository;
	
	@Autowired
    NotificationRepository notificationRepository ; 
	
	private static final Logger l = LogManager.getLogger(ReclamationServiceImpl.class);

	@Override
	public void addReclamation(Reclamation r) {
		reclamationRepository.save(r);
	}

	@Override
	public List<Reclamation> retrieveAllReclamations() {
		return (List<Reclamation>) reclamationRepository.findAll();
	}

	@Override
	public void deleteReclamation(long id) {
		reclamationRepository.deleteById((int) id);
	}

	@Override
	public void ajouterReclamation(Reclamation reclamation) {

		reclamationRepository.save(reclamation);

	}

	@Override
	public Reclamation findReclamation(String id) {
		Reclamation reclama = new Reclamation();
		Long j = Long.parseLong(id);
		List<Reclamation> reclamations = (List<Reclamation>) reclamationRepository.findAll();
		for (Reclamation reclamation : reclamations) {
			if (reclamation.getId() == (j)) {

				reclama = reclamation;
			}
		}
		return reclama;
	}

	@Override
	public List<Reclamation> afficherReclamation() {
		List<Reclamation> Reclamations = (List<Reclamation>) reclamationRepository.findAll();
		for (Reclamation reclamation : Reclamations) {

		}
		return Reclamations;
	}

	@Override
	public void affecterreclamationUser(String idReclamation, String IdUser) {
		ConnectedUser UserManageEntity = userRepository.findById(Long.parseLong(IdUser)).get();
		Reclamation ReclamationmanagerEntity = reclamationRepository.findById((int) Long.parseLong(idReclamation))
				.get();
		ReclamationmanagerEntity.setUserId(UserManageEntity);

		reclamationRepository.save(ReclamationmanagerEntity);

	}

	public String AddReclamationAndBlokUserWhenCommentsContainsBadWords(String iduser, String idComment) {

		int x = 0;

		List<Reclamation> Reclamations = (List<Reclamation>) reclamationRepository.findAll();

		ConnectedUser user = userRepository.findById(Long.parseLong(iduser)).get();

		Comment comment = commentrepository.findById(Long.parseLong(idComment)).get();

		String output = filter.getCensoredText(comment.getDescriptionComment());

		if (output.contains("*")) {

			l.info("badwordsss   " + output);

			Reclamation reclamation = new Reclamation("user post in comments bad words !! ", user, comment);
			
			
			reclamationRepository.save(reclamation);
		
			
			for (Reclamation recc : Reclamations) {

				if (recc.getUserId().getId() == Long.parseLong(iduser)
						&& recc.getCommentaire().getIdComment() == Long.parseLong(idComment)) {

					x = x + 1;

					l.info("xxxxxxx   " + x);

				}
			}

			ConnectedUser blockeduser = userRepository.findById(Long.parseLong(iduser)).get();

			Comment blockedComment = commentrepository.findById(Long.parseLong(idComment)).get();

			if (x >= 3) {

				blockeduser.setBlock(true);

				blockeduser.setDescriptionBlock("security problem");

				mailservice.sendEmail(blockeduser.getEmail(), "your account", blockeduser.getUserName());

				blockedComment.setIsBlocked(true);

				blockedComment.setDescriptionComment(output);
			    
				
				commentrepository.save(blockedComment);

				userRepository.save(blockeduser);

				return "User is bloqued  and comment bloqued too";

			}

			return "Nombre de tentative  " + x;

		} else {

			l.info("Comment not contains bad words !!");

			return "Comment not contains bad words ";
		}

	}
	
	
   /*public Map<String, String> getAllReclamationsByNotifAndUser(){
		
		/*List<Reclamation> reclms = (List<Reclamation>) reclamationRepository.findAll();

		List<Notification> notif = (List<Notification>) notificationRepository.findAll();
		
		List<ConnectedUser> users = (List<ConnectedUser>) userRepository.findAll();

		
		Map<String, String> list = new HashMap<String, String>();
		
		  for(Reclamation rc: reclms){
			  
			   for(Notification ns:notif){ 
				   for(ConnectedUser us : users){
				    
				   if(rc.getId() == ns.getReclamation().getId() && 
						   us.getId() == ns.getUser().getId() ){
					
			
					   list.put(us.getUserName(),us.getPicture());
					 
					  
				   }
			     }
			   }
		  }
		  return list;
	}
	*/

}
