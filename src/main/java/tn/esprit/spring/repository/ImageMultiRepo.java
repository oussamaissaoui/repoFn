package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ImgMulti;

@Repository
public interface ImageMultiRepo extends JpaRepository<ImgMulti, Long> {

}
