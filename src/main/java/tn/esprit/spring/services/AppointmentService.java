package tn.esprit.spring.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entities.Appointment;


public interface AppointmentService {
	
	List<Appointment> retrieveAllAppointment();
	
	public  String addAppointment(Appointment a, long idUser) throws IOException, GeoIp2Exception;

	public String deleteAppointment(long id);
	
	public String ConfrmerAppointment(Date date , long idUser);

	public String CancledAppointment(Date date,long idUser);
	
	public List<Appointment> getApp();
	
	public Appointment getAppointementById(long idApp);
	
	public String updateAppointment(Appointment a, long idApp);
	
	public void isVisibility(long idApp);
	
	public void isNotVisibility(long idApp);
	
	public void isPurchase(long idApp);
	
	public void isNotPurchase(long idApp);
	
	

	


}
