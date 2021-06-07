package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.audit.AuditTable;

@Entity
@Table(name = "T_COMMENT")
public class Comment extends AuditTable<String>  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdComment;

	private String DescriptionComment;
	private int NumberLikes;

	private Boolean IsBlocked;

	@JoinColumn(name="ad_idC")
	@JsonIgnore
	@ManyToOne
	private Add ads;

	@JoinColumn(name="user_idC")
	@JsonIgnore
	@ManyToOne(/* cascade = CascadeType.ALL */)
	ConnectedUser userId;

	@JsonIgnore
	@OneToMany(/* cascade = CascadeType.ALL, */ mappedBy = "commentaire")
	private Set<Reclamation> reclamations;


	public Long getIdComment() {
		return IdComment;
	}

	public ConnectedUser getUserId() {
		return userId;
	}

	public void setUserId(ConnectedUser userId) {
		this.userId = userId;
	}

	public Set<Reclamation> getReclamations() {
		return reclamations;
	}

	public void setReclamations(Set<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public void setIdComment(Long idComment) {
		IdComment = idComment;
	}

	public String getDescriptionComment() {
		return DescriptionComment;
	}

	public void setDescriptionComment(String descriptionComment) {
		DescriptionComment = descriptionComment;
	}

	public int getNumberLikes() {
		return NumberLikes;
	}

	public void setNumberLikes(int numberLikes) {
		NumberLikes = numberLikes;
	}

	public Boolean getIsBlocked() {
		return IsBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		IsBlocked = isBlocked;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String descriptionComment) {
		super();
		DescriptionComment = descriptionComment;
	}

	public Add getAds() {
		return ads;
	}

	public void setAds(Add ads) {
		ads = ads;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
