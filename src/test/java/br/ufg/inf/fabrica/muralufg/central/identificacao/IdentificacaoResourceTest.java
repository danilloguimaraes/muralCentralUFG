package br.ufg.inf.fabrica.muralufg.central.identificacao;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class IdentificacaoResourceTest {

    @Test
    public void testFornecidaCoincideComCriada() throws Exception {
        IdentificacaoResource ir = new IdentificacaoResource("a", "v");
        assertEquals("a", ir.fornecaIdentificacao().getNome());
        assertEquals("v", ir.fornecaIdentificacao().getVersao());
    }
}