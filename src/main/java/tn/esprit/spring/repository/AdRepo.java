package tn.esprit.spring.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Add;


@Repository
@Primary
public interface AdRepo extends JpaRepository<Add, Long>{

}
