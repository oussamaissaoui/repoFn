package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


//@JsonIdentityInfo(generator= ObjectIdGenerators.PropretyGenerator.Class, property="id")
//au lieu de @JsonBackReference + JsonManagedReference pour l'infinite recursion 
//@MappedSuperclass

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Add implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE) // auto no marche pas etant une classe abstraire with inhertiance
	@Column(name = "Add_id",insertable=false, updatable=false)
	protected Long id;
	
	@Column(name = "title", length = 100, nullable = false)
	protected String title;
	
	
	// verifier si length fonctionne pour type int car par défaut pour string !!!!!!!!
	@Column(name = "phoneNumber", length = 8, nullable = false) 
	protected int phoneNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	protected Date date ; // a verifier !!!! ( ajout ne constructeur pour date
	
	protected Boolean favorite;
	
	protected int viewsNumber; 
	
	//methode pour le filtre pour augmenter viewsNumber
	public void IncreVNumber() {
		viewsNumber++;
	}
	
	protected String description;
	
	protected int surface;
	protected int nbrPieces;
	protected int Prixm2;
	protected int prixEstim;
	
	
	@OneToMany(mappedBy = "ads",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@JoinColumn(name="user_idAd")
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	ConnectedUser user;

	@OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "ad",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Appointment> Appointment;
	
	
	//@JsonIgnore
	@OneToMany(mappedBy = "ad", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	protected Set<imgDesc> imgdesc;
	
	@OneToMany(mappedBy = "add",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	protected Set<ImgMulti> imgmulti;
	
	
	
	
	
	public Set<ImgMulti> getImgmulti() {
		return imgmulti;
	}


	public void setImgmulti(Set<ImgMulti> imgmulti) {
		this.imgmulti = imgmulti;
	}


	public int getPrixEstim() {
		return prixEstim;
	}


	public void setPrixEstim(int prixEstim) {
		this.prixEstim = prixEstim;
	}


	@PrePersist
	void FixPrixEstime() {
		
		String inter = getAdresse().getLocation().label;
		int estim = Integer.parseInt(inter);
		setPrixm2(estim);
		
		Estimer();
	}
	
	
	void Estimer () {
		
		int iterim = getPrixm2();
	int pieceestim = getNbrPieces();
	 if (pieceestim>2) {iterim= iterim*2;};
	 int surfestim=getSurface();
	 if (surfestim>=150) {iterim= iterim*2*surfestim;};
	 boolean critpiscine = getAutresCriteres().isGarage();
	 if (critpiscine=true) {iterim=iterim*3;};
	 setPrixEstim(iterim);
		
	}
	
	@Embedded
	protected adresse adresse;
	
	@Embedded
	protected autresCriteres autresCriteres;
	
 public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public int getPhoneNumber() {
return phoneNumber;
}

public void setPhoneNumber(int phoneNumber) {
this.phoneNumber = phoneNumber;
}

public Date getDate() {
return date;
}

public void setDate(Date date) {
this.date = date;
}

public Boolean getFavorite() {
return favorite;
}

public void setFavorite(Boolean favorite) {
this.favorite = favorite;
}

public int getViewsNumber() {
return viewsNumber;
}

public void setViewsNumber(int viewsNumber) {
this.viewsNumber = viewsNumber;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public int getSurface() {
return surface;
}

public void setSurface(int surface) {
this.surface = surface;
}

public int getNbrPieces() {
return nbrPieces;
}

public void setNbrPieces(int nbrPieces) {
this.nbrPieces = nbrPieces;
}

public adresse getAdresse() {
return adresse;
}

public void setAdresse(adresse adresse) {
this.adresse = adresse;
}


public Set<imgDesc> getImgdesc() {
return imgdesc;
}

public void setImgdesc(Set<imgDesc> imgdesc) {
this.imgdesc = imgdesc;
}




public autresCriteres getAutresCriteres() {
return autresCriteres;
}


public void setAutresCriteres(autresCriteres autresCriteres) {
this.autresCriteres = autresCriteres;
}






public int getPrixm2() {
return Prixm2;
}


public void setPrixm2(int prixm2) {
Prixm2 = prixm2;
}


public List<Comment> getComments() {
	return comments;
}


public void setComments(List<Comment> comments) {
	this.comments = comments;
}


public ConnectedUser getUser() {
	return user;
}


public void setUser(ConnectedUser user) {
	this.user = user;
}


public Set<Appointment> getAppointment() {
	return Appointment;
}


public void setAppointment(Set<Appointment> appointment) {
	Appointment = appointment;
}


	
	

}
