package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzo ;
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo;
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) { // se l'attrezzo è presente nella stanza
			
			// prendo il riferimento dell'attrezzo
			attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo); 
			
			// elimino l'attrezzo dalla stanza
			partita.getStanzaCorrente().removeAttrezzo(attrezzo);  
			
			// aggiungo l'attrezzo alla borsa
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzo); 
			
			partita.getIO().mostraMessaggio("Attrezzo "+ nomeAttrezzo + " "  +"inserito nella borsa" );
			partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			
		}
		else {
			partita.getIO().mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella stanza");
		}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
		
	}

}
