package tn.esprit.spring.EventListner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import tn.esprit.spring.repository.SellRepo;



@Component
public class EcentListnr implements ApplicationListener<Eve> {
	
	@Autowired
	private SellRepo sellrepo;
	
	//@Autowired
	//private RunnableTask rntsk;
	
	@Autowired
	private RunnableTask rntsk;
	
	@Autowired
	private TaskScheduler tsk;

	@Override
	public void onApplicationEvent(Eve event) {
		// TODO Auto-generated method stub
		
		int surfa= event.getOnsurface();
		int nbrpiece= event.getOnnbrPieces();
		String locString = event.getOnlocationString();
		int prMin= event.getOnprixMin();
		int prMax=event.getOnprixMax();
		
		
		rntsk.setLocation(locString);
		rntsk.setNbrpieces(nbrpiece);
		rntsk.setSurface(surfa);
		rntsk.setPrixmin(prMin);
		rntsk.setPrixmax(prMax);
		
		tsk.scheduleAtFixedRate(rntsk, 10000L);
		
	}

}
