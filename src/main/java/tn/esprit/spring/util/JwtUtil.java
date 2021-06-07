package tn.esprit.spring.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.spring.PiedevDariApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.google.api.client.util.Value;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;



@Service
public class JwtUtil {
	


	

	public static final Logger L=LogManager.getLogger(JwtUtil.class);

    private String secret="bF7449XF5lc5iz5sW5Y5suX4d7ok4P7j";
    
  

    public String extractUsername(String token) {
        return 
        		extractClaim(token, Claims::getSubject);
    }


    
    public Date extractExpiration(String token)  {
        
    	return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

	private Claims extractAllClaims(String token)  {
		
	
		
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) throws Exception  {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
    
 

	private String createToken(Map<String, Object> claims, String subject) throws Exception  {
    	
   String token= Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60*60*5))
        		.signWith(SignatureAlgorithm.HS256,secret).compact();
    
    return token;
    }
    
 
    
    public Boolean validateToken(String token, UserDetails userDetails) {
    	
    	
        final String username = extractUsername(token);
        
        try{
        	if(username != null){
        		
                return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

        	}
        }catch(Exception e){
        	
        	L.info("username is null we should add user " + e.getMessage());

        }

    	return null;
    }
    


}

