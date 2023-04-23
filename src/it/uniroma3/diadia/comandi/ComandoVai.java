package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;

public class ComandoVai implements Comando {

	private String direzione ;




	/*
	 * esecuzione del comando
	 * 
	 */

	@Override 
	public void esegui(Partita partita ) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if ( this.direzione == null) {
			partita.getIO().mostraMessaggio("Dove vuoi andare? Devi Specificare una direzione");

			return ;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		
		
		if ( prossimaStanza == null) {
			partita.getIO().mostraMessaggio("Direzione non valida");
			return;
		}
		if ( prossimaStanza == stanzaCorrente) {
			partita.getIO().mostraMessaggio("Non puoi andare in quella direzione!");		}

	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         	else {
			partita.setStanzaCorrente(prossimaStanza);
		}

		//		if ( partita.getStanzaCorrente()!= partita.getLabirinto().getStanzaVincente()) {
		//			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		//		}
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);

	}



	@Override
	public void setParametro(String parametro) {
		this.direzione  = parametro;

	}
}
