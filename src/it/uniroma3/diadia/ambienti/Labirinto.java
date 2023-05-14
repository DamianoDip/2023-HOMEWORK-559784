package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String,Stanza> stanze;
	private List<Stanza> elencoStanze;
	private Set<Attrezzo> attrezziLabirinto;
	
	public Labirinto() {
		stanze = new HashMap<>();
		elencoStanze = new ArrayList<>();
		attrezziLabirinto = new HashSet<>();
		
	}


	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave",1);

		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBuia("Atrio", "lanterna");
		Stanza aulaN11 = new StanzaBloccata("AulaN11", "est", "chiave");
		Stanza aulaN10 = new StanzaBase("Aula N10");
		Stanza laboratorio = new StanzaBase("Laboratorio Campus");
		Stanza biblioteca = new StanzaBase("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}
	// stanza corrente è atrio , che ha attrezzo osso , stanza vincente è biblioteca , direzione nord , stanza a sud di atr
	//atrio è N10 che ha lanterna

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public void addStanza(Stanza stanza) {
		this.stanze.put(stanza.getNome(), stanza);
		this.elencoStanze.add(stanza);
	}
	
	public Stanza getStanza(String nomeStanza) {
		return this.stanze.get(nomeStanza);
		
	}


	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	public boolean addAttrezzo( Attrezzo nuovoAttrezzo) {
		 return this.attrezziLabirinto.add(nuovoAttrezzo);
	}


	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}


	public Map<String, Stanza> getStanze() {
		return stanze;
	}


	public void setStanze(Map<String, Stanza> stanze) {
		this.stanze = stanze;
	}


	public List<Stanza> getElencoStanze() {
		return elencoStanze;
	}


	public void setElencoStanze(List<Stanza> elencoStanze) {
		this.elencoStanze = elencoStanze;
	}


	public Set<Attrezzo> getAttrezziLabirinto() {
		return attrezziLabirinto;
	}
	
	


}
