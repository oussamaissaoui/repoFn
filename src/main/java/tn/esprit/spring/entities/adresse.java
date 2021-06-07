package tn.esprit.spring.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable         //// changer abstract class to class 3adiya sinon nested exception cannot find constructor
@AttributeOverrides({@AttributeOverride(name = "numTel", column = @Column(name="Adress_numTel")),
		@AttributeOverride(name = "id", column = @Column(name="Adress_id"))})

public class adresse {
	private Long id;
	private int numTel;
	private String rue;
	private int codePostal;
	private String GpsLongitude;
	private String GpsLatitude;
	
	@Enumerated(value = EnumType.STRING)
	private Location location;

	public adresse() {
	
	}

	public adresse(Long id, int numTel, String rue, int codePostal, String GpsLongitude,String GpsLatitude, Location location) {
		super();
		this.id = id;
		this.numTel = numTel;
		this.rue = rue;
		this.codePostal = codePostal;
		this.GpsLongitude = GpsLongitude;
		this.GpsLatitude = GpsLatitude;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	

	public String getGpsLongitude() {
		return GpsLongitude;
	}

	public void setGpsLongitude(String gpsLongitude) {
		GpsLongitude = gpsLongitude;
	}

	public String getGpsLatitude() {
		return GpsLatitude;
	}

	public void setGpsLatitude(String gpsLatitude) {
		GpsLatitude = gpsLatitude;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "adresse [id=" + id + ", numTel=" + numTel + ", rue=" + rue + ", codePostal=" + codePostal
				+ ", GpsLongitude=" + GpsLongitude + ", GpsLatitude=" + GpsLatitude + ", location=" + location + "]";
	}

}
