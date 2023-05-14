package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public  class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private LinkedList<Attrezzo> attrezzi;
	
	private HashMap<String,Stanza> stanzeAdiacenti;
	private int numeroStanzeAdiacenti;
	private TreeSet<String> direzioni ;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;

		this.attrezzi = new LinkedList<>(); // lista di attrezzi contenuti in una stanza
		this.stanzeAdiacenti = new HashMap<>(); // array list che mantiene le stanze adiacenti di una stanza 
		this.direzioni = new TreeSet<>();  // set di direzioni 
		//		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		//		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		//		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;

		if(this.stanzeAdiacenti.containsKey(direzione)) {
			this.stanzeAdiacenti.put(direzione, stanza);
			aggiornato= true;
		}

		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni.add(direzione);
				this.stanzeAdiacenti.put(direzione, stanza);
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		Iterator<String> it = this.direzioni.iterator();
		while ( it.hasNext()) {
			String direzioneCercata = it.next();
			if ( direzioneCercata.equals(direzione)) {
				stanza = this.stanzeAdiacenti.get(direzioneCercata);
			}
		}
		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public LinkedList<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if ( this.attrezzi.size()>= NUMERO_MASSIMO_ATTREZZI) return false;
		return this.attrezzi.add(attrezzo);
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if ( attrezzo != null) {
				risultato.append(attrezzo.toString()+" ");
			}

		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if ( attrezzo != null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}

		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if( attrezzo!= null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
			}
		}
		
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		// iterare e  cercare fino a che non trovo l'attrezzo
		boolean eliminato = false; 
		Iterator<Attrezzo> it  = this.attrezzi.iterator();
		while(it.hasNext()) {
			Attrezzo attrezzoDaRimuovere =(Attrezzo)  it.next();
			if (attrezzoDaRimuovere.getNome().equals(attrezzo.getNome())) {
				it.remove();
				eliminato = true;
			}
		}
		return eliminato;
	}




	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];

		return this.direzioni.toArray(direzioni);
	}

	public int getNumeroStanzeAdiacenti() {
		return this.numeroStanzeAdiacenti;
	}

	public HashMap<String,Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}




}