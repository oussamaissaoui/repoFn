package tn.esprit.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ImgMulti {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String ImageUrl;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "imgMulti_id")
	private Add add;




	public ImgMulti() {
	
	}




	public ImgMulti(String imageUrl) {
		
		ImageUrl = imageUrl;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getImageUrl() {
		return ImageUrl;
	}




	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}




	public Add getAdd() {
		return add;
	}




	public void setAdd(Add add) {
		this.add = add;
	}
	
	
	

}
