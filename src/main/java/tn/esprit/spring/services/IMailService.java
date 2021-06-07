package tn.esprit.spring.services;

import java.util.List;
import java.util.Map;

public interface IMailService {
	
	
	 public   Map<String,List<Object>> receiveEmail(String host, String storeType,  String username, String password) throws Exception;


}
