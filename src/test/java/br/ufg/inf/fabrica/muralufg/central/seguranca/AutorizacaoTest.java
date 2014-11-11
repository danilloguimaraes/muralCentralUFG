package br.ufg.inf.fabrica.muralufg.central.seguranca;

import org.junit.Assert;
import org.junit.Test;

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
}
