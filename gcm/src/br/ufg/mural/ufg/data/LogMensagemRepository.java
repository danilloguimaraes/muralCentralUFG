package br.ufg.mural.ufg.data;

import br.ufg.mural.ufg.model.LogMensagem;

public interface LogMensagemRepository {
	
	/**
	 * 
	 * @param logMensagem
	 */
	public boolean salvar(LogMensagem logMensagem);
	
	/**
	 * 
	 * @param mensagemId
	 * @return
	 */
	public LogMensagem recuperar(long mensagemId);
	
	/**
	 * 
	 * @param id
	 */
	public boolean remover(long id);	
}
