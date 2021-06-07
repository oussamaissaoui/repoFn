package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class GeoIP implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private long id;
	
	
		public GeoIP(long id, String ipAddress, String city, String latitude, String longitude) {
		super();
		this.id = id;
		IpAddress = ipAddress;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}

		private String IpAddress;
	    private String city;
	    
	    @Transient
	    private String latitude;
	    
	    @Transient
	    private String longitude;
		
	    public GeoIP() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
		public GeoIP(String ipAddress, String city, String latitude, String longitude) {
			super();
			IpAddress = ipAddress;
			this.city = city;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public GeoIP( String city,String latitude, String longitude) {
			super();
		
			this.city = city;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public String getIpAddress() {
			return IpAddress;
		}

		public void setIpAddress(String ipAddress) {
			IpAddress = ipAddress;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		@Override
		public String toString() {
			return "GeoIP [IpAddress=" + IpAddress + ", city=" + city + ", latitude=" + latitude + ", longitude="
					+ longitude + "]";
		}

		
}
