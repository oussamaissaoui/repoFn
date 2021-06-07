package tn.esprit.spring.entities;

public enum Location {
	ARIANA("400"),
	BEN_AROUS("260"),
	BIZERTE("200"),
	BEJA("100"),
	GABES("75"),
	GAFSA("80"),
	JENDOUBA("90"),
	KAIROUAN("100"),
	KASSERINE("50"),
	KEBILLI("70"),
	MANOUBA("300"),
	KEF("100"),
	MEHDIA("150"),
	MONASTIR("200"),
	MEDNINE("100"),
	NABEUL("250"),
	SFAX("180"),
	SIDI_BOUZID("80"),
	SILIANA("80"),
	SOUSSE("320"),
	TATAOUINE("100"),
	TOZEUR("100"),
	TUNIS("400"),
	ZAGHOUAN("150");

	
	public final String label;
	
	private Location(String label) {
		
		this.label=label;
	}
	
	
	

}
