package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoGuarda implements Comando {

	private String parametro;
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub

		Borsa borsa = partita.getGiocatore().getBorsa();

		if ( parametro== null) {
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			partita.getIO().mostraMessaggio(borsa.toString());
		}
		else {

			if ( parametro.equals("borsa")) {
				partita.getIO().mostraMessaggio(borsa.toString());
			}
			else if ( parametro.equals("borsanome")) {
				partita.getIO().mostraMessaggio(borsa.stampaSet(borsa.getContenutoOrdinatoPerNome()));
			}
			else if (parametro.equals("borsapeso")) {
				partita.getIO().mostraMessaggio(borsa.stampaList(borsa.getContenutoOrdinatoPerPeso()));

			}
			else if (parametro.equals("borsapesomap")) {
				partita.getIO().mostraMessaggio(borsa.stampaMap(borsa.getAttrezziRaggruppatiPerPeso()));
			}

		}
	}
	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		this.parametro = parametro;

	}

}
