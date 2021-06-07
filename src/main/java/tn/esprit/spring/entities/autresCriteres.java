package tn.esprit.spring.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable 
@AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name="crit_id")))
public class autresCriteres {
	

	private Long id;
	
	private boolean terrasse;
	private boolean balcon;
	private boolean assenceur;
	private boolean cave;
	private boolean piscine;
	private boolean vueMer;
	private boolean parking;
	private boolean garage;
	
public autresCriteres() {
		super();
	}

public autresCriteres(Long id, boolean terrasse, boolean balcon, boolean assenceur, boolean cave, boolean piscine,
		boolean vueMer, boolean parking, boolean garage) {
	super();
	this.id = id;
	this.terrasse = terrasse;
	this.balcon = balcon;
	this.assenceur = assenceur;
	this.cave = cave;
	this.piscine = piscine;
	this.vueMer = vueMer;
	this.parking = parking;
	this.garage = garage;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public boolean isTerrasse() {
	return terrasse;
}

public void setTerrasse(boolean terrasse) {
	this.terrasse = terrasse;
}

public boolean isBalcon() {
	return balcon;
}

public void setBalcon(boolean balcon) {
	this.balcon = balcon;
}

public boolean isAssenceur() {
	return assenceur;
}

public void setAssenceur(boolean assenceur) {
	this.assenceur = assenceur;
}

public boolean isCave() {
	return cave;
}

public void setCave(boolean cave) {
	this.cave = cave;
}

public boolean isPiscine() {
	return piscine;
}

public void setPiscine(boolean piscine) {
	this.piscine = piscine;
}

public boolean isVueMer() {
	return vueMer;
}

public void setVueMer(boolean vueMer) {
	this.vueMer = vueMer;
}

public boolean isParking() {
	return parking;
}

public void setParking(boolean parking) {
	this.parking = parking;
}

public boolean isGarage() {
	return garage;
}

public void setGarage(boolean garage) {
	this.garage = garage;
}

@Override
public String toString() {
	return "autresCriteres [id=" + id + ", terrasse=" + terrasse + ", balcon=" + balcon + ", assenceur=" + assenceur
			+ ", cave=" + cave + ", piscine=" + piscine + ", vueMer=" + vueMer + ", parking=" + parking + ", garage="
			+ garage + "]";
}



}
