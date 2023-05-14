package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePerNomeAttrezzo implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		// TODO Auto-generated method stub
		return a1.getNome().compareTo(a2.getNome());
	}

}
