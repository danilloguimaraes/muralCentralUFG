package br.ufg.inf.fabrica.muralufg.central.configuracao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ufg.inf.fabrica.muralufg.central.configuracao.ConfiguracaoRepresentationUrlBiblioteca;

public class ConfiguracaoRepresentationUrlBibliotecaTest {
	@Test
    public void testFornecidaCoincideComCriada() throws Exception {
        ConfiguracaoRepresentationUrlBiblioteca config = new ConfiguracaoRepresentationUrlBiblioteca();
        assertEquals("www.bc.ufg.br", config.getUrl());
    }
}
