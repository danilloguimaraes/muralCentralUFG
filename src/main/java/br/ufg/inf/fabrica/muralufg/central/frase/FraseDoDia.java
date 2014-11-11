package br.ufg.inf.fabrica.muralufg.central.frase;

import java.util.Date;

public class FraseDoDia {
	
	private int id;
	private Date data;
	private String frase;
	private String autor;
	
	public FraseDoDia(int id, Date data, String frase, String autor) {
		super();
		this.id = id;
		this.data = data;
		this.frase = frase;
		this.autor = autor;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

}
