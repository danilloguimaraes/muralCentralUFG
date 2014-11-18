package br.ufg.inf.fabrica.muralufg.central.seguranca;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Classe para teste de autorização.
 * @author italogustavomirandamelo
 *
 */
public class AutorizacaoTest {

	
	@Test
	public void testAutorizaEnvioIdsPermitidos() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		String escopo = "10,11,12,21,22,23,31,32,33";

		Assert.assertTrue(autorizacaoService.autoriza("1", "ENVIAR_MENSAGEM", escopo));
	}
	

	@Test
	public void testAutorizaEnvioIdNaoPermitido() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		String escopo = "35";

		Assert.assertFalse(autorizacaoService.autoriza("1", "ENVIAR_MENSAGEM", escopo));
	}
	
	
	@Test
	public void testAutorizaEnvioIdDestinatariosNull() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		Assert.assertFalse(autorizacaoService.autoriza("1", "ENVIAR_MENSAGEM", null));
	}
	
	
	@Test
	public void testAutorizaEnvioIdDestinatariosVazio() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		Assert.assertFalse(autorizacaoService.autoriza("1", "ENVIAR_MENSAGEM", ""));
	}
	
	@Test
	public void testAutorizaEnvioIdDestinatariosInvalidos() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		Assert.assertFalse(autorizacaoService.autoriza("1", "ENVIAR_MENSAGEM", "a,b,c"));
	}
	
	
	@Test
	public void testAutorizaEnvioIdRemetenteNull() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		String escopo = "35";

		Assert.assertFalse(autorizacaoService.autoriza(null, "ENVIAR_MENSAGEM", escopo));
	}
	
	
	@Test
	public void testAutorizaEnvioIdRemetenteVazio() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		String escopo = "35";

		Assert.assertFalse(autorizacaoService.autoriza("", "ENVIAR_MENSAGEM", escopo));
	}
	
	
	@Test
	public void testAutorizaEnvioIdRemetenteInvalido() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		String escopo = "35";

		Assert.assertFalse(autorizacaoService.autoriza("a", "ENVIAR_MENSAGEM", escopo));
	}
	
	
	@Test
	public void testAutorizaAcaoInvalida() {

		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();

		Assert.assertFalse(autorizacaoService.autoriza("", "ACAO_INVALIDA", ""));
	}
	
	@Test
	public void testAutorizaAutorizacaoCancelar() {
		
		AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock();
		
		Assert.assertTrue(autorizacaoService.isUsuarioPodeCancelar(1l, autorizacaoService.getMensagemCancelar()));
		
		
	}
}
