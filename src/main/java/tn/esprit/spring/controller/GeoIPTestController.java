package tn.esprit.spring.controller;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import org.springframework.web.bind.annotation.RestController;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entities.GeoIP;
import tn.esprit.spring.services.GeoIPLocationService;
import tn.esprit.spring.services.GeoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;



@RestController
@CrossOrigin(origins="*", allowedHeaders = "*" )
public class GeoIPTestController {
	/*@Autowired
	tn.esprit.spring.services.GeoIPLocationService GeoIPLocationService;
	    

	   public GeoIPTestController() throws IOException {
	    	GeoIPLocationService = new  GeoServiceImpl();
	    }
	   
	   //http://localhost:8085/GeoIPTest?City=Bizerte
	    
	    @PostMapping("/GeoIPTest/{city}")
	    public GeoIP getLocation(@PathVariable("city") String city) throws Exception {
	    	
	        tn.esprit.spring.services.GeoIPLocationService GeoIPlocationService= new GeoServiceImpl();
	        
	        return GeoIPLocationService.findIpAddressByCityName(city);
	    }
	    */
	
	    
}
