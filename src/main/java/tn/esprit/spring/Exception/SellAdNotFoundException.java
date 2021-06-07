package tn.esprit.spring.Exception;

import java.text.MessageFormat;

public class SellAdNotFoundException extends RuntimeException {
	
	public SellAdNotFoundException (Long id) {
		super(MessageFormat.format("THE ID ''{0}'' that you entered is not a valid id",id));
	}

}
