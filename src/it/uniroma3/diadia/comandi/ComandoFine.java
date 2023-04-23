package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	IOConsole ioconsole = new IOConsole();
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		partita.getIO().mostraMessaggio("Grazie di aver giocato!!");
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

}
