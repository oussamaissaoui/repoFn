package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Abonnement implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	
	private Long idF;  
	
	
 	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL) 
	ConnectedUser Abonnes;
	
 	
 	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL) 
	ConnectedUser Abonnements;
	
	public Abonnement(Long id, User Abonnes, User Abonnements) {
		super();
		this.idF = id;
		Abonnes = Abonnes;
		Abonnements = Abonnements;
	}
	public Abonnement() {
		super();
	
	}
	
	public Long getId() {
		return idF;
	}
	public void setId(Long id) {
		this.idF = id;
	}

	
	public Long getIdF() {
		return idF;
	}
	public void setIdF(Long idF) {
		this.idF = idF;
	}
	public ConnectedUser getAbonnes() {
		return Abonnes;
	}
	public void setAbonnes(ConnectedUser abonnes) {
		Abonnes = abonnes;
	}
	public ConnectedUser getAbonnements() {
		return Abonnements;
	}
	public void setAbonnements(ConnectedUser abonnements) {
		Abonnements = abonnements;
	}
	
	

	
	
}
