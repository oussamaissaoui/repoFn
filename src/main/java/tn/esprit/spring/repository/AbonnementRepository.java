package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Abonnement;

import tn.esprit.spring.entities.User;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional


	@Query("SELECT COUNT(p.Abonnes.id) FROM Abonnement p where p.Abonnes.id =: Abonnes")

	public Integer nombreAbonnes(@Param("Abonnes") Long Abonnes);

	@Query(value = "SELECT idF FROM Abonnement p WHERE p.Abonnes.id=:Abonnes and  p.Abonnements.id=:Abonnements ")

	public Long getAbonnes(@Param("Abonnes") Long Abonnes, @Param("Abonnements") Long Abonnements);

	@Query(value = "SELECT count(*) FROM ConnectedUser")
	public int retrieveUsers();

	@Query("SELECT p.Abonnements FROM Abonnement p  join p.Abonnes where  p.Abonnes.id=:Abonnes  ")
	public List<Object> MesAbonnes(@Param("Abonnes") Long Abonnes);

	@Query("SELECT p.Abonnes FROM Abonnement p  join p.Abonnements where  p.Abonnements.id=:Abonnements ")
	public List<Object> Abonnements(@Param("Abonnements") Long Abonnements);

}
