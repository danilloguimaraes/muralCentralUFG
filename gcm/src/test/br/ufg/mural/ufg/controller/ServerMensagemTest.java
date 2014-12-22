package test.br.ufg.mural.ufg.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ufg.mural.ufg.controller.ServerMensagem;
import br.ufg.mural.ufg.model.LogMensagem;
import br.ufg.mural.ufg.model.Mensagem;
import br.ufg.mural.ufg.model.ServerStatus;

/**
 * Teste da classe @see{ServerMensagem}
 */
@RunWith(MockitoJUnitRunner.class)
public class ServerMensagemTest {

	@Mock
	private ServerMensagem mockServerMensagem;

	@Test
	public void getlogMensagem() {
		LogMensagem logMensagemMock = new LogMensagem();

		int mensagemId = 1;

		Mockito.when(mockServerMensagem.getLogMensagem(mensagemId)).thenReturn(
				logMensagemMock);

		LogMensagem logMensagemRetorno = mockServerMensagem
				.getLogMensagem(mensagemId);

		assertEquals(logMensagemRetorno, logMensagemMock);
	}

	@Test
	public void enviarMensagem() {
		LogMensagem logMensagemMock = new LogMensagem();

		Mensagem mensagem = new Mensagem("Texto Mock", new ArrayList<String>(),
				99);

		Mockito.when(mockServerMensagem.enviarMensagem(mensagem)).thenReturn(
				logMensagemMock);

		LogMensagem logMensagemRetorno = mockServerMensagem
				.enviarMensagem(mensagem);

		assertEquals(logMensagemRetorno, logMensagemMock);
	}

	@Test
	public void serverStatusFuncionando() {
		ServerStatus serverStatusMock = new ServerStatus(ServerStatus.OK, true);

		Mockito.when(mockServerMensagem.serverStatus()).thenReturn(
				serverStatusMock);

		ServerStatus serverStatusRetorno = mockServerMensagem.serverStatus();

		assertEquals(serverStatusRetorno, serverStatusMock);
	}

	@Test
	public void serverStatusOffline() {
		ServerStatus serverStatusMock = new ServerStatus(ServerStatus.ERROR,
				false);

		Mockito.when(mockServerMensagem.serverStatus()).thenReturn(
				serverStatusMock);

		ServerStatus serverStatusRetorno = mockServerMensagem.serverStatus();

		assertEquals(serverStatusRetorno, serverStatusMock);
	}

}
