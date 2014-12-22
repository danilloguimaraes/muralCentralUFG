package br.ufg.mural.ufg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Para cada mensagem enviada, será salvo um log, contendo as informações do
 * envio
 * 
 * sucesso: se a mensagem foi enviada sem problemas
 * 
 * falha: status se a mensagem falhou
 * 
 * falhas: itens que falharam no envio da mensagem
 */
public class LogMensagem implements Serializable {

	public static final String ERRO_CONF_SERVIDOR = "Erro ao carregar as configurações do servidor";
	public static final String ERRO_ENVIAR_MENSAGEM = "Erro ao enviar mensagem";

	private static final long serialVersionUID = -6850085995333686437L;

	private long id;

	private boolean sucesso;

	private boolean falha;

	private List<FalhaEnvio> falhas;

	private String messageError;

	public LogMensagem() {
		this.sucesso = false;
		this.falha = true;
		this.falhas = new ArrayList<FalhaEnvio>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return true mensagem enviada para todos os destinatários false caso
	 *         contrário
	 */
	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	/**
	 * Lista dos redIds que falharam
	 * 
	 * @return lista contendo as falhas
	 */
	public List<FalhaEnvio> getFalhas() {
		return falhas;
	}

	public void setFalhaEnvio(List<FalhaEnvio> falhas) {
		this.falhas = falhas;
	}

	/**
	 * Status caso tenha ocorrido uma falha
	 * 
	 * @return
	 */
	public boolean isFalha() {
		return falha;
	}

	public void setFalha(boolean falha) {
		this.falha = falha;
	}

	public void setMessageError(String messageError) {
		this.falha = true;
		this.sucesso = false;
		this.messageError = messageError;
	}

	public String getMessageError() {
		return messageError;
	}
}
