package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	private IOConsole ioconsole;

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		this.ioconsole = new IOConsole();
		String istruzione;
		ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do		
			istruzione = ioconsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**    
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if ( istruzione == "") return false;
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		
		else {
			ioconsole.mostraMessaggio("Comando Sconosciuto");
		}
		if (this.partita.vinta()) {
			ioconsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioconsole.mostraMessaggio(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioconsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioconsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		ioconsole.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		
	}

	
	/**
	 * Comando "Prendi". Permette al giocatore di prendere un attrezzo presente nella stana
	 */
	
	private void prendi (String nomeAttrezzo) {
		Attrezzo attrezzo;
		if(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) { // se l'attrezzo è presente nella stanza
			
			// prendo il riferimento dell'attrezzo
			attrezzo = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo); 
			
			// elimino l'attrezzo dalla stanza
			this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);  
			
			// aggiungo l'attrezzo alla borsa
			this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo); 
			
			ioconsole.mostraMessaggio("Attrezzo"+ nomeAttrezzo + "inserito nella borsa" );
			ioconsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
			
		}
		else {
			ioconsole.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella stanza");
		}
	}
	
	/**
	 * Comando "Posa". Permette al giocatore di posare un attrezzo contenuto nella borsa , depositandolo nella stanza corrente
	 */
	
	private void posa (String nomeAttrezzo ) {
		Attrezzo attrezzo ;
		if ( this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			//salvo il riferimento
			attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			
			//rimuovo dalla borsa
			
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			
			// aggiungo alla stanza
			this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			
			// stampo lo stato della stanza
			ioconsole.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
			
		}
		else {
			ioconsole.mostraMessaggio( nomeAttrezzo +"  non si trova in borsa ");
		}
		
		
	}
	
	
	/**
	 * 
	 * 
	 
	 * Comando "Fine".
	 */
	private void fine() {
		ioconsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}