package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.GeoIP;
import tn.esprit.spring.entities.User;

public interface GeoIpRepository extends JpaRepository<GeoIP, Long> {



	@Query("  from GeoIP where city = :city ")

	public GeoIP findByCityNameGeoIp(@Param("city") String city);
	
}
