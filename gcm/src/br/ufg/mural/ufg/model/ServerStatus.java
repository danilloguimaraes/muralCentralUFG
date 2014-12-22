package br.ufg.mural.ufg.model;

/**
 * Para indicar o estado do servidor para o envio de mensagens
 */
public class ServerStatus {
	
	public static final String OK = "Servidor funcionando corretamente, envie mensagem para seus dispositivos.";
	public static final String ERROR = "Servidor não está funcionando corretamente.";

	String mensagem;
	boolean funcionando;

	public ServerStatus() {
	}

	public ServerStatus(String mensagem, boolean funcionando) {
		super();
		this.mensagem = mensagem;
		this.funcionando = funcionando;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isFuncionando() {
		return funcionando;
	}

	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}

}
