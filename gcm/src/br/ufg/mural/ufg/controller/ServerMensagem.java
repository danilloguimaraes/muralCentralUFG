package br.ufg.mural.ufg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufg.mural.ufg.data.DataStoreLogMensagem;
import br.ufg.mural.ufg.model.FalhaEnvio;
import br.ufg.mural.ufg.model.LogMensagem;
import br.ufg.mural.ufg.model.Mensagem;
import br.ufg.mural.ufg.model.ServerStatus;
import br.ufg.mural.ufg.util.Configuracao;
import br.ufg.mural.ufg.util.Configuracao.ConfiguracaoExeption;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Controlador responsável por enviar mensagem, verificar status do resvidor e
 * recuperar log das mensagens enviadas.
 */
public class ServerMensagem {

	private final Logger mLogger = Logger.getLogger(getClass().getName());

	private DataStoreLogMensagem mStoreLogMensagem;

	public ServerMensagem() {
		mStoreLogMensagem = new DataStoreLogMensagem();
	}

	public LogMensagem enviarMensagem(Mensagem mensagem) {
		LogMensagem logMensagem = new LogMensagem();

		// Carrega o arquivo de configuração
		Configuracao conf;
		try {
			conf = Configuracao.getInstance();
		} catch (ConfiguracaoExeption e) {
			mLogger.log(Level.SEVERE, "Erro no arquivo de configuração", e);
			logMensagem
					.setMessageError(LogMensagem.ERRO_CONF_SERVIDOR);
			return logMensagem;
		}

		// Preparando a mensagem para envio
		Builder messageBuilder = new Message.Builder();
		messageBuilder.addData(conf.getDataKey(), mensagem.getTexto());
		messageBuilder.timeToLive(mensagem.getTempoDeVida());
		Message message = messageBuilder.build();

		// Enviar a mensagem
		MulticastResult multicastResult;
		try {
			Sender sender = new Sender(conf.getApiKey());
			multicastResult = sender.send(message, mensagem.getRegIds(), 5);
		} catch (IOException e) {
			mLogger.log(Level.SEVERE, "Erro ao enviar mensagem", e);
			logMensagem.setMessageError(LogMensagem.ERRO_ENVIAR_MENSAGEM);
			return logMensagem;
		}

		// Analizar os resultados do envio para gerar o log da Mensagem
		// Identificar apenas as mensagens que não foram enviadas e salvar no
		// log

		logMensagem.setId(multicastResult.getMulticastId());

		List<FalhaEnvio> falhaEnvios = verificarFalhaEnvio(
				multicastResult.getResults(), mensagem.getRegIds());

		if (falhaEnvios.isEmpty()) {
			// Mensagem enviada para todos os detinatários com sucesso
			logMensagem.setSucesso(true);
			logMensagem.setFalha(false);
		} else {
			// Houve a falha no envio para algum regId
			logMensagem.setFalha(true);
			logMensagem.setSucesso(true);
			logMensagem.setFalhaEnvio(falhaEnvios);
		}

		salvarLogMensagem(logMensagem);

		return logMensagem;
	}

	/**
	 * Verificar as ocorrencia de falhas no envio de mensagens para os
	 * dispositivos
	 * 
	 * @param results
	 *            resultados do envio
	 * @param devices
	 *            reg ids dos dispositivos
	 * @return lista de contendo os dispositivos o status da falha
	 */
	private List<FalhaEnvio> verificarFalhaEnvio(List<Result> results,
			List<String> devices) {
		List<FalhaEnvio> falhaEnvio = new ArrayList<FalhaEnvio>();

		for (int i = 0; i < devices.size(); i++) {
			String regId = devices.get(i);
			Result result = results.get(i);
			String messageId = result.getMessageId();

			if (messageId != null) {
				mLogger.fine("Mensagem enviada com sucesso para o id: " + regId
						+ "; messageId = " + messageId);

				String canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null) {
					// o mesmo dispositivo tem mais de um ID registrado
					mLogger.info("canonicalRegId " + canonicalRegId);
					falhaEnvio.add(new FalhaEnvio(regId,
							FalhaEnvio.ERROR_INVALID_REGISTRATION));
				}
			} else {
				String error = result.getErrorCodeName();
				if (error != null
						&& error.equals(Constants.ERROR_NOT_REGISTERED)) {
					// Aplicativo foi removido do dispositivo
					mLogger.info("Dispositivo nao registrado: " + regId);

					falhaEnvio.add(new FalhaEnvio(regId,
							FalhaEnvio.ERROR_NOT_REGISTERED));
				} else {
					mLogger.severe("Erro ao enviar mensagem " + regId + ": "
							+ error);
					falhaEnvio.add(new FalhaEnvio(regId,
							FalhaEnvio.ERROR_NOT_SEND_MESSAGE));
				}
			}
		}

		return falhaEnvio;
	}

	/**
	 * Salvar o log da mensagem
	 * 
	 * @param logMensagem
	 *            log a ser salvo no repositório
	 */
	private void salvarLogMensagem(LogMensagem logMensagem) {
		mStoreLogMensagem.salvar(logMensagem);
	}

	/**
	 * Recupear log da mensagem no repositorio
	 * 
	 * @param mensagemId
	 *            identificador do log
	 * @return {@link LogMensagem} da mensagem selecionada
	 */
	public LogMensagem getLogMensagem(long mensagemId) {
		return mStoreLogMensagem.recuperar(mensagemId);
	}

	/**
	 * Status do servidor, verifica se é capaz de enviar mensagem
	 * 
	 * @return {@link ServerStatus}
	 */
	public ServerStatus serverStatus() {
		boolean status = true;

		// Verificar configurações do servidor
		try {
			Configuracao.getInstance();
		} catch (ConfiguracaoExeption e) {
			status = false;
		}

		// Testar envio para o GCM
		List<String> testRedId = new ArrayList<String>();
		testRedId.add("IdTestServerStatus");

		LogMensagem logMensagem = enviarMensagem(new Mensagem("ServerStatus",
				testRedId, 3600));
		if (!logMensagem.isSucesso()) {
			status = false;
		}

		//
		ServerStatus serverStatus = new ServerStatus();
		serverStatus.setFuncionando(status);

		if (serverStatus.isFuncionando()) {
			serverStatus
					.setMensagem(ServerStatus.OK);
		} else {
			serverStatus
					.setMensagem(ServerStatus.ERROR);
		}

		return serverStatus;
	}

}