package tn.esprit.spring.services;

import java.io.IOException;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entities.GeoIP;



public interface GeoIPLocationService {
	
	public GeoIP getLocation(String ip)throws IOException, GeoIp2Exception ;
	
	 public GeoIP findIpAddressByCityName(String city) throws IOException, GeoIp2Exception;
	 
	 
}
