package tn.esprit.spring.filter;
import org.apache.log4j.Logger;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.JwksVerificationKeyResolver;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.CustomUserDetailsService;

import tn.esprit.spring.util.JwtUtil;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	

	private static final Logger logger = Logger.getLogger(JwtFilter.class);

	
    @Autowired
     JwtUtil jwtUtil;
    
    @Autowired
   CustomUserDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

       String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            
            logger.info("tokeeen "    + token);
           
    		
    		
    		
				try {
					userName = jwtUtil.extractUsername(token);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
		
            
            logger.info("usernaame "    + userName);
            
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);
            
          
         
				try {
					if (jwtUtil.validateToken(token, userDetails)) {
					
						
						
					    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					    		
					            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					    
					    logger.info("getauthorotiiies "    + userDetails.getAuthorities());
					    
					    usernamePasswordAuthenticationToken
					            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
		
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    
      }
    }
 
    
