package test.br.ufg.mural.ufg.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ufg.mural.ufg.controller.ServerMensagem;
import br.ufg.mural.ufg.data.DataStoreLogMensagem;
import br.ufg.mural.ufg.data.LogMensagemRepository;
import br.ufg.mural.ufg.model.LogMensagem;
import br.ufg.mural.ufg.model.Mensagem;
import br.ufg.mural.ufg.model.ServerStatus;

/**
 * Teste da classe @see{DataStoreLogMensagem}
 */
@RunWith(MockitoJUnitRunner.class)
public class DataStoreLogMensagemTest {

	@Mock
	private DataStoreLogMensagem mockDataStoreLogMensagem;

	@Test
	public void salvarOk() {
		LogMensagem logMensagemMock = new LogMensagem();

		Mockito.when(mockDataStoreLogMensagem.salvar(logMensagemMock))
				.thenReturn(true);

		boolean logMesagemSalvar = mockDataStoreLogMensagem
				.salvar(logMensagemMock);

		assertEquals(logMesagemSalvar, true);
	}

	@Test
	public void salvarError() {
		LogMensagem logMensagemMock = new LogMensagem();

		Mockito.when(mockDataStoreLogMensagem.salvar(logMensagemMock))
				.thenReturn(false);

		boolean logMesagemSalvar = mockDataStoreLogMensagem
				.salvar(logMensagemMock);

		assertEquals(logMesagemSalvar, false);
	}
	
	@Test
	public void recuperarLogMensagem() {
		LogMensagem logMensagemMock = new LogMensagem();
		
		int mensagemId = 1;
		Mockito.when(mockDataStoreLogMensagem.recuperar(mensagemId))
				.thenReturn(logMensagemMock);

		LogMensagem logMesagemRetorno = mockDataStoreLogMensagem
				.recuperar(mensagemId);

		assertEquals(logMesagemRetorno, logMensagemMock);
	}
	
	@Test
	public void recuperarNull() {
		LogMensagem logMensagemMockNull = null;
		
		int mensagemId = 1;
		Mockito.when(mockDataStoreLogMensagem.recuperar(mensagemId))
				.thenReturn(null);

		LogMensagem logMesagemRetorno = mockDataStoreLogMensagem
				.recuperar(mensagemId);

		assertEquals(logMesagemRetorno, logMensagemMockNull);
	}
	
	@Test
	public void removerOk() {
		
		int mensagemId = 1;
		Mockito.when(mockDataStoreLogMensagem.remover(mensagemId))
				.thenReturn(true);

		boolean statusRemover = mockDataStoreLogMensagem
				.remover(mensagemId);

		assertEquals(statusRemover, true);
	}
	
	@Test
	public void removerError() {
		int mensagemId = 1;
		Mockito.when(mockDataStoreLogMensagem.remover(mensagemId))
				.thenReturn(false);

		boolean statusRemover = mockDataStoreLogMensagem
				.remover(mensagemId);

		assertEquals(statusRemover, false);
	}

}
