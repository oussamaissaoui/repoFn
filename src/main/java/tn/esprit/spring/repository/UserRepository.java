package tn.esprit.spring.repository;


import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;


@NoRepositoryBean
//@Repository
public interface  UserRepository<T, Long extends Serializable> extends JpaRepository<T, Long>{ // type generique bech eli bech heritiih meyouhelech kima list

	
	//T findByUserName(String username);


	@Query("from ConnectedUser user fetch all properties where user.userName=:username ") 
	public List<Object>  fetchAllUsersByUserName (@Param("username") String username);
}
