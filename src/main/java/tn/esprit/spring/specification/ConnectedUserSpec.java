package tn.esprit.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;



import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.ConnectedUser;


@Component
public class ConnectedUserSpec {     

	
	public static Specification<ConnectedUser> hasFirstName(String firstname){ // rechercher avancer b like 
		
		return(root,query,builder)-> (firstname == null || firstname.equals("")) ? null :
				
			 builder.like(builder.lower(root.<String>get("FirstName")), "%" + firstname.toLowerCase() + "%"); // lower ??
		
	}
	
	
	public static Specification<ConnectedUser> getAllUserByComments(Long IdComment){ 
 
  return(root,query,builder)->{
			
	  
			if(IdComment == null){
				return null;
			}
			
			 Join<ConnectedUser, Comment> users= root.join("comments",JoinType.INNER); // jai pas compris alech hatina comments
			return builder.and(builder.equal(users.get("IdComment"), IdComment));
		};
	}
	
	
	
}