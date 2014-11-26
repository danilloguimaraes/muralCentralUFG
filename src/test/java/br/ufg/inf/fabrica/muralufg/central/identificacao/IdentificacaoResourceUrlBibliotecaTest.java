package br.ufg.inf.fabrica.muralufg.central.identificacao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IdentificacaoResourceUrlBibliotecaTest {

    @Test
    public void testFornecidaCoincideComCriada() throws Exception {
        IdentificacaoResourceUrlBiblioteca url = new IdentificacaoResourceUrlBiblioteca("a");
        assertEquals("v", url.fornecaIdentificacao().getUrl());
    }
}