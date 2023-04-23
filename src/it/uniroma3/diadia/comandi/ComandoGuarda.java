package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.StanzaBuia;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub

			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
