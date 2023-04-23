package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	String attrezzoLuminoso;
	
	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
			}
	
	
	@Override
	public String getDescrizione() {
		if( this.hasAttrezzo(attrezzoLuminoso)) {
			return this.toString();
		}
		else {
			return "qui c'è buio pesto...";
		}
	}
	

}
