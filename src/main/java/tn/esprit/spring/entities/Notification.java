package tn.esprit.spring.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Notification {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idnotif;
	
	@JoinColumn(name="user_idN")
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	ConnectedUser user;

     

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Notification(long idnotif, ConnectedUser user) {
		super();
		this.idnotif = idnotif;
		this.user = user;
	}


	public long getIdnotif() {
		return idnotif;
	}


	public void setIdnotif(long idnotif) {
		this.idnotif = idnotif;
	}


	public ConnectedUser getUser() {
		return user;
	}


	public void setUser(ConnectedUser user) {
		this.user = user;
	}


	public Notification(ConnectedUser user) {
		super();
		this.user = user;
	}


	
	
	

}
