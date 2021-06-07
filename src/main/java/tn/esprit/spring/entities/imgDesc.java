package tn.esprit.spring.entities;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;



@Entity
public class imgDesc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String Imagename;
	private String imageType;
	
	@Lob
	private byte[] data;

	@ManyToOne
	@JoinColumn(name = "img_id")
	private Add ad;


	public imgDesc(String imagename, String imageType, byte[] data) {
		
		Imagename = imagename;
		this.imageType = imageType;
		this.data = data;
	}
	
	

	public imgDesc() {
		
	}



	@Override
	public String toString() {
		return "imgDesc []";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagename() {
		return Imagename;
	}

	public void setImagename(String imagename) {
		Imagename = imagename;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Add getAd() {
		return ad;
	}

	public void setAd(Add ad) {
		this.ad = ad;
	}



	public imgDesc(Long id, String imagename, String imageType, byte[] data, Add ad) {
		super();
		this.id = id;
		Imagename = imagename;
		this.imageType = imageType;
		this.data = data;
		this.ad = ad;
	}

	
	
}
