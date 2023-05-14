package it.uniroma3.diadia.ambienti;

import java.util.HashMap;

public class StanzaBloccata extends Stanza {


	String direzioneBloccata;
	String attrezzoChiave;



	public StanzaBloccata(String nome, String direzioneBloccata , String attrezzoChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoChiave = attrezzoChiave;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = this;
		if ( !direzione.equals(this.direzioneBloccata) || ( direzione.equals(this.direzioneBloccata) &&  this.hasAttrezzo(attrezzoChiave))) {

			HashMap<String, Stanza> stanzeAdiacenti = this.getStanzeAdiacenti();
			if ( stanzeAdiacenti.containsKey(direzione)) {
				stanza = stanzeAdiacenti.get(direzione);
			}
//			for(int i=0; i<this.getNumeroStanzeAdiacenti(); i++)
//				if( this.getDirezioni()[i]!= null) {
//					
//				if (this.getDirezioni()[i].equals(direzione))
//					
//					stanza = this.getStanzeAdiacenti().get(direzione);
//				}
		}

	
		return stanza;
	}
	
	@Override 
	public String getDescrizione() {
		if( !this.hasAttrezzo(attrezzoChiave)) {
			return "La stanza è bloccata verso "+ this.direzioneBloccata  +" \n" +   this.toString();
			
		}
		return this.toString();
	}



}
