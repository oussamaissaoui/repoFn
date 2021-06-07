package tn.esprit.spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.SellAd;
import tn.esprit.spring.services.SellService;




@WebFilter(urlPatterns = "/Achat/findByid/*")
//@Component
public class SellFilter implements Filter {

	@Autowired
	private SellService sellserv;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
		
		String a= httpRequest.getRequestURI().substring(16);
		System.out.println("URL est : "+ req);
		System.out.println("ID Annonce est : "+a);
		Long aa = Long.parseLong(a);
		
		
		
		
        chain.doFilter(request, response);
        
        int status = ((HttpServletResponse) response).getStatus();
        System.out.println("STATUT annonce est : " + status);
        if (status==200)
        {
        
       SellAd changer= sellserv.findSellAdById(aa);
        changer.IncreVNumber();
       sellserv.addSellAnnonce2(changer);
        System.out.println("Nombre de vues est : " +changer.getViewsNumber());
        }
        
        else System.out.println("erreur");
                
        
        
		
	}

}
