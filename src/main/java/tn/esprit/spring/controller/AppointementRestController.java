package tn.esprit.spring.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.services.AppointmentService;

@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AppointementRestController {

	@Autowired

	AppointmentService appoitmentService;

	private static final Logger logger = Logger.getLogger(AppointementRestController.class);

	@PostMapping("/canceled-Appointment/{idUser}")
	@ResponseBody
	public String CancledAppointment(@PathVariable("idUser") Long idUser, @RequestBody Appointment ap)
			throws ParseException {

		return appoitmentService.CancledAppointment(ap.getDateAppointement(), idUser);

	}
	
	
	@PostMapping("/confirmed-Appointment/{idUser}")
	@ResponseBody
	public String ConfirmedAppointment(@PathVariable("idUser") Long idUser, @RequestBody Appointment ap)
			throws ParseException {

		return appoitmentService.ConfrmerAppointment(ap.getDateAppointement(), idUser);

	}

	@PostMapping("/add-Appointment/{idUser}")
	@ResponseBody
	public String addAppointment(@RequestBody Appointment a, @PathVariable("idUser") Long idUser) throws IOException, GeoIp2Exception {

		return appoitmentService.addAppointment(a, idUser);
	}
	
	
	@PutMapping("/updateAppointment/{idApp}")
	@ResponseBody
	public String updateAppointment(@RequestBody Appointment a, @PathVariable("idApp") Long idApp) {

		return appoitmentService.updateAppointment(a, idApp);
	}


	@GetMapping("/getallAppoitement")
	@ResponseBody
	public List<Appointment> getApp() {

		return appoitmentService.getApp();
	}


	@GetMapping("/getAppointement/{idApp}")
	@ResponseBody
	public Appointment getAppointementById(@PathVariable("idApp") Long idApp){
		
		
		return appoitmentService.getAppointementById(idApp);
	}
	
	
	@GetMapping("/isVisibility/{idApp}")
	@ResponseBody
	public void isVisibility(@PathVariable("idApp") Long idApp){
		
		appoitmentService.isVisibility(idApp);
		
	}
	
	@GetMapping("/isNotVisibility/{idApp}")
	@ResponseBody
	public void isNotVisibility(@PathVariable("idApp") Long idApp){
		
		appoitmentService.isNotVisibility(idApp);
	}
	
	@GetMapping("/isPurchase/{idApp}")
	@ResponseBody
	public void isPurchase(@PathVariable("idApp") Long idApp){
		
		appoitmentService.isPurchase(idApp);
	}
	
	@GetMapping("/isNotPurchase/{idApp}")
	@ResponseBody
	public void isNotPurchase(@PathVariable("idApp") Long idApp){
		
		appoitmentService.isNotPurchase(idApp);
		
		
	}
	
	@DeleteMapping("/deleteAppById/{idApp}")
	@ResponseBody
	public String deleteAppointementById(@PathVariable("idApp") Long idApp){
		
		return appoitmentService.deleteAppointment(idApp);
		
	}
}
