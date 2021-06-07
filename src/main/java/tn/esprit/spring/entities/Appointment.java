package tn.esprit.spring.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.protobuf.TextFormat.ParseException;

import io.jsonwebtoken.io.IOException;
import tn.esprit.spring.audit.AuditTable;


@Entity
@Table(name = "Appointment")

@EntityListeners(AuditingEntityListener.class)
public class Appointment extends AuditTable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idAppointement; 
	

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	private Date dateAppointement;
	private boolean Visibility;
	private String state;
	private String Attendance;
	private boolean purchased;
	private String justification;
	
	
	private String files; //dossier necessaire
	
	
	@JoinColumn(name="user_id")
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	ConnectedUser user;
	 
	@JoinColumn(name="ad_id")
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	Add ad;


	
	
	public Add getAd() {
		return ad;
	}

	public void setAd(Add ad) {
		this.ad = ad;
	}

	public Appointment() {
		super();
		
	}

	public Appointment(Long idAppointement, Date dateAppointement, boolean visibility, String state, 
			String attendance, boolean purchased, String justification, String files,
			ConnectedUser user) {
		super();
		this.idAppointement = idAppointement;
		this.dateAppointement = dateAppointement;
		Visibility = visibility;
		this.state = state;
		Attendance = attendance;
		this.purchased = purchased;
		this.justification = justification;
		
		this.files = files;
		this.user = user;
	}

	public Long getIdAppointement() {
		return idAppointement;
	}

	public void setIdAppointement(Long idAppointement) {
		this.idAppointement = idAppointement;
	}

	public Date getDateAppointement() {
		return dateAppointement;
	}

	public void setDateAppointement(Date dateAppointement) {
		this.dateAppointement = dateAppointement;
	}

	public boolean isVisibility() {
		return Visibility;
	}

	public void setVisibility(boolean visibility) {
		Visibility = visibility;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public String getAttendance() {
		return Attendance;
	}

	public void setAttendance(String attendance) {
		Attendance = attendance;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public ConnectedUser getUser() {
		return user;
	}
	

	public void setUser(ConnectedUser user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Appointment [idAppointement=" + idAppointement + ", dateAppointement=" + dateAppointement
				+ ", Visibility=" + Visibility + ", state=" + state  + ", Attendance=" + Attendance
				+ ", purchased=" + purchased + ", justification=" + justification 
				+ ", files=" + files + "]";
	}

	
	
	
	

	
	
	
	

}