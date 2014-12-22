package br.ufg.mural.ufg.data;

import java.util.ArrayList;
import java.util.List;

import br.ufg.mural.ufg.model.LogMensagem;

/**
 * Implementação de persistencia para objeto {@code LogMensagem}
 * 
 */
public class DataStoreLogMensagem implements LogMensagemRepository {

	private static List<LogMensagem> LOGMENSAGENS = new ArrayList<LogMensagem>();

	@Override
	public boolean salvar(LogMensagem logMensagem) {
		LOGMENSAGENS.add(logMensagem);
		return true;
	}

	@Override
	public LogMensagem recuperar(long mensagemId) {
		for (LogMensagem logMensagem : LOGMENSAGENS) {
			if (logMensagem.getId() == mensagemId) {
				return logMensagem;
			}
		}
		return null;
	}

	@Override
	public boolean remover(long id) {
		for (LogMensagem logMensagem : LOGMENSAGENS) {
			if (logMensagem.getId() == id) {
				LOGMENSAGENS.remove(logMensagem);
			}
		}
		return true;
	}

}
