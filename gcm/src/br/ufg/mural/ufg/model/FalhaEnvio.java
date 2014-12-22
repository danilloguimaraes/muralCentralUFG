package br.ufg.mural.ufg.model;

import java.io.Serializable;

/**
 * Classe que representa a falha de envio da mensagem, contendo o regId do
 * dispositivo e o status do erro
 */
public class FalhaEnvio implements Serializable {

	private static final long serialVersionUID = 4004767210587207268L;

	public static final String ERROR_NOT_REGISTERED = "NaoRegistrado";
	public static final String ERROR_NOT_SEND_MESSAGE = "MensagemNaoEnviada";
	public static final String ERROR_INVALID_REGISTRATION = "RegistroInvalido";

	String reg_id;
	String status;

	public FalhaEnvio(String reg_id, String status) {
		this.reg_id = reg_id;
		this.status = status;
	}

	public FalhaEnvio() {
	}

	public String getRegId() {
		return reg_id;
	}

	public void setRegId(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
