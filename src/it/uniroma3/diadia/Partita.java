package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {




	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private IO io ;
	
	public Partita(IO io){
		labirinto = new Labirinto();
		labirinto.creaStanze();
		stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
		giocatore = new Giocatore();
		this.io = io;

	}
	public Partita(IO io, Labirinto labirinto){
		this.labirinto = labirinto;
		stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
		giocatore = new Giocatore();
		this.io = io;

	}

	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return getStanzaCorrente()== this.getLabirinto().getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Giocatore getGiocatore () {
		return this.giocatore;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public IO getIO() {
		return this.io;
	}


	
}
