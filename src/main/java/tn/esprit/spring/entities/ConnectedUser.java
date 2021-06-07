package tn.esprit.spring.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;


@AttributeOverrides({
    @AttributeOverride(name="FirstName", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="LastName", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="id", column=@Column(name="ID_USER"))
})

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ConnectedUser extends User  implements Serializable{
	

	private String lieu;
	
	private Boolean isConnected;
	
	
	public Boolean getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
	private Set<Reclamation> Reclamation;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
	private Set<Comment> comments;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user" )
	private Set<Appointment> Appointment;
	
	@OneToMany( mappedBy="Abonnes" )
	private List<Abonnement> abonnement;
	

	@OneToMany(cascade = CascadeType.REMOVE,mappedBy="user")
	private List<SellAd> ads;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Notification> notifications;

	
	public ConnectedUser(String lieu, Boolean isConnected, Set<tn.esprit.spring.entities.Reclamation> reclamation,
			Set<Comment> comments, Set<tn.esprit.spring.entities.Appointment> appointment, List<Abonnement> abonnement,
			 Set<Notification> notifications) {
		super();
		this.lieu = lieu;
		this.isConnected = isConnected;
		Reclamation = reclamation;
		this.comments = comments;
		Appointment = appointment;
		this.abonnement = abonnement;
		//this.ads = ads;
		this.notifications = notifications;
	}
	public Set<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	/*public List<Ad> getAds() {
		return ads;
	}
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}*/
	public ConnectedUser( Set<tn.esprit.spring.entities.Reclamation> reclamation,
			Set<Comment> comments, Set<tn.esprit.spring.entities.Appointment> appointment) {
		super();
		
		Reclamation = reclamation;
		this.comments = comments;
		Appointment = appointment;
	}
	public Set<Reclamation> getReclamation() {
		return Reclamation;
	}
	public void setReclamation(Set<Reclamation> reclamation) {
		Reclamation = reclamation;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Appointment> getAppointment() {
		return Appointment;
	}
	public void setAppointment(Set<Appointment> appointment) {
		Appointment = appointment;
	}
	public ConnectedUser(String userName, String password, String email, Role role) {
		super(userName, password, email, role);
		// TODO Auto-generated constructor stub
	}
	public ConnectedUser(String userName, String password, String email) {
		super(userName, password, email);
		// TODO Auto-generated constructor stub
	}
	public ConnectedUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public List<Abonnement> getAbonnement() {
		return abonnement;
	}
	public void setAbonnement(List<Abonnement> abonnement) {
		this.abonnement = abonnement;
	}
	
	public ConnectedUser(String lieu, Set<tn.esprit.spring.entities.Reclamation> reclamation, Set<Comment> comments,
			Set<tn.esprit.spring.entities.Appointment> appointment, List<Abonnement> abonnement) {
		super();
		this.lieu = lieu;
		Reclamation = reclamation;
		this.comments = comments;
		Appointment = appointment;
		this.abonnement = abonnement;
	}
	public List<SellAd> getAds() {
		return ads;
	}
	public void setAds(List<SellAd> ads) {
		this.ads = ads;
	}
	
	
	
	


}
