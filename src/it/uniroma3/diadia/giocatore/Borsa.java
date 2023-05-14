package it.uniroma3.diadia.giocatore;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNomeAttrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPesoAttrezzo;


public class Borsa  {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private HashMap<String,Attrezzo> attrezzi;
	int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}





	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>(); 
		this.numeroAttrezzi = 0;
	}





	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if ( this.attrezzi.containsKey(attrezzo.getNome())) return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}




	public int getPesoMax() {
		return pesoMax;
	}



	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if ( this.attrezzi.containsKey(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);
			return a;
		}

		return a ;

	}



	public int getPeso() {
		int peso = 0;
		for ( String nomeAttrezzo : this.attrezzi.keySet()) {
			peso += this.attrezzi.get(nomeAttrezzo).getPeso();
		}
		return peso;
	}



	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}



	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}



	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if ( this.attrezzi.containsKey(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);
			this.attrezzi.remove(nomeAttrezzo);
			return a;
		}
		return a;
	}



	public LinkedList<Attrezzo> getContenutoOrdinatoPerPeso(){
		LinkedList<Attrezzo> attrezziOrdinati = new LinkedList<>();
		for ( String nomeAttrezzo : this.attrezzi.keySet()) {
			attrezziOrdinati.add(this.attrezzi.get(nomeAttrezzo));
		}
		ComparatorePerPeso comp = new ComparatorePerPeso(); 
		Collections.sort(attrezziOrdinati,comp);
		return attrezziOrdinati;
	}

	public TreeSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNomeAttrezzo cmp = new ComparatorePerNomeAttrezzo();
		TreeSet<Attrezzo> attrezziOrdinati = new TreeSet<>(cmp);
		for ( String nomeAttrezzo : this.attrezzi.keySet()) {
			attrezziOrdinati.add(this.attrezzi.get(nomeAttrezzo));
		}

		return attrezziOrdinati;
	}
	public TreeSet<Attrezzo> getSetOrdinatoPerPeso(){
		ComparatorePerPesoAttrezzo cmp = new ComparatorePerPesoAttrezzo();
		TreeSet<Attrezzo> attrezziOrdinati = new TreeSet<>(cmp);
		for ( String nomeAttrezzo : this.attrezzi.keySet()) {
			attrezziOrdinati.add(this.attrezzi.get(nomeAttrezzo));
		}

		return attrezziOrdinati;
	}


	public Map<Integer, HashSet<Attrezzo>> getAttrezziRaggruppatiPerPeso(){
		HashMap<Integer, HashSet<Attrezzo>> peso2Attrezzi = new HashMap<>();


		/* Scorro tutti i nomi degli attrezzi contenuti nella mappa nome attrezzo - riferimento attrezzo*/
		for( String nomeAttrezzo : this.attrezzi.keySet()) {

			// estraggo le due variabili locali per facilitare la lettura del codice
			Attrezzo attrezzoDaInserire = this.attrezzi.get(nomeAttrezzo); // dato il nome dell'attrezzo , ottengo il valore dalla mappa nomeAttrezzo-Attrezzo
			int pesoAttrezzo = attrezzoDaInserire.getPeso(); // e il peso dell'attrezzo , che sarà la chiave della mappa peso2Attrezzi

			/*Il peso è gia presente nella mappa come chiave , posso accedervi e aggiungere il corrispettivo attrezzo come set */
			if( peso2Attrezzi.containsKey(pesoAttrezzo) ) {

				// aggiungo l'attrezzo alla collezione , prendo il set mappato dal peso dell'attrezzo e lo inserisco 
				peso2Attrezzi.get(pesoAttrezzo).add(attrezzoDaInserire); 				
			}

			/*Il peso dell'attrezzo non è presente come chiave nella mappa*/
			else {
				HashSet<Attrezzo> nuovaCollezione = new HashSet<>(); // creo un nuovo hash set
				nuovaCollezione.add(attrezzoDaInserire); // aggiungo l'attrezzo da inserire al set
				peso2Attrezzi.put(pesoAttrezzo, nuovaCollezione); // aggiungo alla mappa:  il peso dell'attrezzo come chiave e il set come valore

			}
		}
		return peso2Attrezzi;

	}

	public String stampaSet(Set<Attrezzo> setAttrezzi) {
		// {}
		StringBuilder stampa = new StringBuilder();

		stampa.append("{");

		Iterator<Attrezzo> it = setAttrezzi.iterator();

		while ( it.hasNext()) {
			Attrezzo attrezzo = it.next();
			if ( it.hasNext()) {
				stampa.append(attrezzo.getNome() + ", ");
			}
			else {
				stampa.append(attrezzo.getNome());
			}

		}

		stampa.append("}");
		return stampa.toString();
	}


	public String stampaList(List<Attrezzo> listAttrezzi) {
		//[]
		StringBuilder stampa = new StringBuilder();
		stampa.append("[");
		Iterator<Attrezzo> it = listAttrezzi.iterator();
		while(it.hasNext()) {
			Attrezzo attrezzo = it.next();
			if (it.hasNext()) {
				stampa.append(attrezzo.getNome()+ ", ");
			}
			else {
				stampa.append(attrezzo.getNome());
			}
		}

		stampa.append("]");
		return stampa.toString();

	}

	public String stampaMap(Map<Integer, HashSet<Attrezzo>> mapAttrezzi) {
		//( chiave , {set}) 
		StringBuilder stampa = new StringBuilder();
		for ( Integer peso : mapAttrezzi.keySet()) {
			stampa.append("(");
			stampa.append(peso+", ");
			stampa.append(stampaSet(mapAttrezzi.get(peso)));
			stampa.append(")");

		}
		return stampa.toString();

	}




	public HashMap<String, Attrezzo> getAttrezzi() {
		return attrezzi;
	}





	public void setAttrezzi(HashMap<String, Attrezzo> attrezzi) {
		this.attrezzi = attrezzi;
	}






	public String toString() {
		StringBuilder s = new StringBuilder();
		if (this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (String nomeAttrezzo : this.attrezzi.keySet()) { 
				Attrezzo a = this.attrezzi.get(nomeAttrezzo);
				s.append(a.toString()+" ");
			}
		}
		else 
			s.append("Borsa vuota");
		return s.toString();
	}

}
