
package tn.esprit.spring.services;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.UserRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
    @Autowired
     ConnectedUserRepository repository;
    

    @Override
    public UserDetails loadUserByUsername(String username)  {
    	  ConnectedUser  user = repository.findByUserName(username);
 
    	  try{
    		  
    		  if(user != null){
    			  
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
 
    		  }
    		  
    	  }catch(Exception e){
    		  
    		  System.out.println("Database is empty user is null");
    	  }
        
        return null;
    }
}
