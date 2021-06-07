package tn.esprit.spring.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import tn.esprit.spring.audit.AuditorAwareImpl;
import tn.esprit.spring.filter.JwtFilter;
import tn.esprit.spring.services.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


    
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        
       
    }
    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
  
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
  
    
    	http.cors();
    	http.csrf().disable();
    	
   http.authorizeRequests()
   .antMatchers("/authentification/**","/add-user/**",
    			"/upload","/setPhoto/**","/getUser/**",
    			"/changer-Password/**","/forget-Password/**","/debloquer-Compte/**","/GeoIPTest/**","/getAllUsers"
    			,"/getUserById/**" ,"/updateUser/**","/getUsersByEmail/**","/adduserNotif/**","/getUserByNotification",
    			"/bad-words/**","/getAllReclamationsByNotifAndUser","/getAllEmailReceived",
    			
    			"/add-Comment/**"  ,"/add-Appointment/**",
    			
    			
    			"/Achat/**","/image/**","/imgMulti/**")
    			
		   /**************ousemaaaaaaaaaaaaaaaaaaaaaaaaa***********************/
    			
		
   
        .permitAll()
        .anyRequest()
        .authenticated()
       .and().exceptionHandling().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
   
   		http.addFilterBefore(jwtFilter,  UsernamePasswordAuthenticationFilter.class);
        

    }
    
    @Bean
    
    public AuditorAware<String> auditorAware(){
    	
    	return new AuditorAwareImpl();
    }
  
}
