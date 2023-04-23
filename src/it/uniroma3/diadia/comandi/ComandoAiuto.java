package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};


	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			partita.getIO().mostraMessaggio(elencoComandi[i]+" ");
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

}