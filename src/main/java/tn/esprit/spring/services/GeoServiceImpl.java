package tn.esprit.spring.services;

//import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import tn.esprit.spring.entities.GeoIP;


@Service
public class GeoServiceImpl //implements GeoIPLocationService 
{

	private DatabaseReader dbReader;

	//@Autowired
	//GeoIpRepository geoRepository;
/*
	public GeoServiceImpl() throws IOException {

		File database = new File("src/main/resources/your-mmdb-location");

		dbReader = new DatabaseReader.Builder(database).build();
	}

	public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception {

		InetAddress IpAddress = InetAddress.getByName(ip);

		CityResponse response = dbReader.city(IpAddress);

		String cityName = response.getCity().getName();

		String latitude = response.getLocation().getLatitude().toString();

		String longitude = response.getLocation().getLongitude().toString();

		return new GeoIP(ip, cityName, latitude, longitude);
	}

	public GeoIP findIpAddressByCityName(String city) throws IOException, GeoIp2Exception {

		List<GeoIP> geopip = (List<GeoIP>) geoRepository.findAll();

		boolean verifey = false;

		for (GeoIP geo : geopip) {

			if (geo.getCity().equals(city)) { // lezem token n est pa expirer
												// n3eeeeees

				verifey = true;

				GeoIP g = geoRepository.findByCityNameGeoIp(city);

				return this.getLocation(geo.getIpAddress());

			}
		}

		if (!verifey) {


			return null;

		}

		return null;
	}*/

}