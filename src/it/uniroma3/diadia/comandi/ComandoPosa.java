package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{

	private String nomeAttrezzo;
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo ;
		if ( partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			//salvo il riferimento
			attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);

			//rimuovo dalla borsa

			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);

			// aggiungo alla stanza
			partita.getStanzaCorrente().addAttrezzo(attrezzo);

			// stampo lo stato della stanza
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());

		}
		else {
			partita.getIO().mostraMessaggio( nomeAttrezzo +"  non si trova in borsa ");
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
