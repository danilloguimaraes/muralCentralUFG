package br.ufg.mural.ufg.model;

import java.util.List;

/**
 * Mensagem a ser enviada para os dispositivos indicados.
 * 
 * texto: conteudo da mensagem
 * 
 * regIds: os dispositivos para receber a mensagem
 * 
 * tempoDeVida: tempo que a mensagem deve durar enquanto não for entregue para o
 * usuário
 * 
 */
public class Mensagem {

	private String texto;
	private Integer tempoDeVida;
	private List<String> regIds;

	// Contrutor criado para parser Json
	public Mensagem() {
	}

	public Mensagem(String texto, List<String> regIds, Integer tempoDeVida) {
		this.texto = texto;
		this.regIds = regIds;
		this.tempoDeVida = tempoDeVida;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<String> getRegIds() {
		return regIds;
	}

	public void setRegIds(List<String> regIds) {
		this.regIds = regIds;
	}

	public Integer getTempoDeVida() {
		return tempoDeVida;
	}

	public void setTempoDeVida(Integer tempoDeVida) {
		this.tempoDeVida = tempoDeVida;
	}
}
