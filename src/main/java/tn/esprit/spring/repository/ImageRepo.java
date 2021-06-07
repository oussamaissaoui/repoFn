package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ImageRepo extends JpaRepository<tn.esprit.spring.entities.imgDesc, Long> {
	

}
