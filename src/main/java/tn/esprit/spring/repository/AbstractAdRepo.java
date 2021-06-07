package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import tn.esprit.spring.entities.Add;


@NoRepositoryBean
public interface AbstractAdRepo <T extends Add> extends JpaRepository<T, Long> {


}
