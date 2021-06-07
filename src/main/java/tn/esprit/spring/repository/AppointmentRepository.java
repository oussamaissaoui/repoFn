package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Appointment;

import tn.esprit.spring.entities.ConnectedUser;


public interface AppointmentRepository extends CrudRepository<Appointment,Long>{
	

   
    
	
}
