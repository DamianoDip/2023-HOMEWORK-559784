package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	Labirinto labirinto;
	
	
	
	
	public LabirintoBuilder () {
		labirinto = new Labirinto();
	}
	
	
	
	public LabirintoBuilder setStanzaVincente(String nomeStanza) {
		Stanza stanzaVincente = new Stanza(nomeStanza);
		this.labirinto.setStanzaVincente(stanzaVincente);
		this.labirinto.addStanza(stanzaVincente);
		return this;
	}
	public LabirintoBuilder setStanzaIniziale(String nomeStanza) {
		Stanza stanzaIniziale = new Stanza(nomeStanza);
		this.labirinto.setStanzaIniziale(stanzaIniziale);
		this.labirinto.addStanza(stanzaIniziale);
		return this;
	}
	
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza daAggiungere = new Stanza(nomeStanza); 
		 this.labirinto.addStanza(daAggiungere);
		return this;
	}
	
	
	
	public LabirintoBuilder addAdiacenza(String nomeStanzaIniziale, String nomeStanzaSuccessiva , String direzioneStanzaSuccessiva) {
		Stanza stanzaDiPartenza = this.labirinto.getStanze().get(nomeStanzaIniziale);
		Stanza stanzaDiArrivo = this.labirinto.getStanze().get(nomeStanzaSuccessiva);
		stanzaDiPartenza.impostaStanzaAdiacente(direzioneStanzaSuccessiva, stanzaDiArrivo);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo , int pesoAttrezzo) {
		if ( this.labirinto.getElencoStanze().size()== 0 ) return null;
		Stanza ultimaAggiunta = this.labirinto.getElencoStanze().get(this.labirinto.getElencoStanze().size()-1);
		Attrezzo nuovoAttrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo); 
		ultimaAggiunta.addAttrezzo(nuovoAttrezzo);
		if ( this.labirinto.addAttrezzo(nuovoAttrezzo) ) return this;
		return null;
		
	}

	
	public Labirinto getLabirinto() {
		return labirinto;
	}
	
	
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
}
