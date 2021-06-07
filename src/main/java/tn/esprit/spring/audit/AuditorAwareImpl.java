package tn.esprit.spring.audit;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import tn.esprit.spring.services.UserServiceImpl;

public class AuditorAwareImpl  implements AuditorAware<String> {

	private static final Logger logger = Logger.getLogger(AuditorAwareImpl.class);


	
 public Optional<String> getCurrentAuditor() {

  String  name= SecurityContextHolder.getContext().getAuthentication().getName();
  
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  
 logger.info("nameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee    :"  +name);


  return   Optional.ofNullable(name);
 }
}