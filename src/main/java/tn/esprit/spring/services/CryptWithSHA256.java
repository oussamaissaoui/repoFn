package tn.esprit.spring.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;


@Service
public class CryptWithSHA256 {
	   private static MessageDigest md;

	   public static String cryptWithSHA256(String pass){
	    try {
	        md = MessageDigest.getInstance("SHA-256");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	      
	    }
	        return null;


	   }
	}