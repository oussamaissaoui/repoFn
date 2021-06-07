package tn.esprit.spring.EventListner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;


public class Eve extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private int onsurface;
	private int onnbrPieces;
	private String onlocationString;
	private int onprixMin;
	private int onprixMax;

	public Eve(Object source, int intersurface, int internbrPieces, String interlocationString, int interprixMin, int interprixMax) {
		super(source);
		// TODO Auto-generated constructor stub
		
		this.onsurface=intersurface;
		this.onnbrPieces=internbrPieces;
		this.onlocationString=interlocationString;
		this.onprixMin=interprixMin;
		this.onprixMax=interprixMax;
	}

	

	public int getOnsurface() {
		return onsurface;
	}

	public void setOnsurface(int onsurface) {
		this.onsurface = onsurface;
	}

	public int getOnnbrPieces() {
		return onnbrPieces;
	}

	public void setOnnbrPieces(int onnbrPieces) {
		this.onnbrPieces = onnbrPieces;
	}

	public String getOnlocationString() {
		return onlocationString;
	}

	public void setOnlocationString(String onlocationString) {
		this.onlocationString = onlocationString;
	}

	public int getOnprixMin() {
		return onprixMin;
	}

	public void setOnprixMin(int onprixMin) {
		this.onprixMin = onprixMin;
	}

	public int getOnprixMax() {
		return onprixMax;
	}

	public void setOnprixMax(int onprixMax) {
		this.onprixMax = onprixMax;
	}
	
	

}
