package br.ufg.inf.fabrica.muralufg.central.proxy;

import java.util.Map;

/**
 * Created by kyriosdata on 10/7/14.
 */
public class ProxyService {
	private RMTCService rmtc;
	private int antiguidadeEmSegundos;
	private int totalConfortavelDeRespostas;
	private int totalThreadConsumidores;

	/**
	 * Respostas produzidas pela RMTC e "guardadas" pelo serviço de "proxy" da
	 * Central até que o Cliente da Central faça a requisição por resposta já
	 * disponível.
	 */
	private Map<String, Resposta> respostas;

	/**
	 * Lista de pedidos submetidos pelo Cliente da Central e que estão
	 * aguardando para serem executados.
	 */
	public String getResposta(String guid) {
		Resposta resposta = respostas.get(guid);
		return resposta.getResposta();
	}

	/**
	 * Inicia <i>thread</i> (ou tarefa) responsável por continuamente, conforme
	 * período previamente configurado, "limpar" o depósito de respostas
	 * daquelas "mais antigas".
	 */
	public void iniciarLimpezaDeAntigos() throws UnsupportedOperationException {
		// Método não implementado.
	}

	public void iniciarConsumidores() throws UnsupportedOperationException {
		// Método não implementado.
	}

	public void executarRequisicaoRmtc() throws UnsupportedOperationException {
		/*
		 * while (true) { try { Pedido pedido = pedidos.take();
		 * 
		 * Execute aqui o pedido recuperado! String respostaRmtc =
		 * rmtc.getLinha();
		 * 
		 * 
		 * } catch (InterruptedException ie) {
		 * 
		 * } }
		 */
	}

	public void limpaAntigas() {
		for (int i = 0; i < respostas.size(); i++) {
			// masoq!
		}
	}

	public RMTCService getRmtc() {
		return rmtc;
	}

	public void setRmtc(RMTCService rmtc) {
		this.rmtc = rmtc;
	}

	public int getAntiguidadeEmSegundos() {
		return antiguidadeEmSegundos;
	}

	public void setAntiguidadeEmSegundos(int antiguidadeEmSegundos) {
		this.antiguidadeEmSegundos = antiguidadeEmSegundos;
	}

	public int getTotalConfortavelDeRespostas() {
		return totalConfortavelDeRespostas;
	}

	public void setTotalConfortavelDeRespostas(int totalConfortavelDeRespostas) {
		this.totalConfortavelDeRespostas = totalConfortavelDeRespostas;
	}

	public int getTotalThreadConsumidores() {
		return totalThreadConsumidores;
	}

	public void setTotalThreadConsumidores(int totalThreadConsumidores) {
		this.totalThreadConsumidores = totalThreadConsumidores;
	}

	public Map<String, Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(Map<String, Resposta> respostas) {
		this.respostas = respostas;
	}
}
