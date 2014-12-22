package br.ufg.inf.fabrica.muralufg.central.despertador;

import java.util.Observable;
import java.util.Observer;

public class Observador implements Observer {

	
	private String id ="";
	
	@Override
	public void update(Observable o, Object arg) {
		// implementar oq fazer quando despertar
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
