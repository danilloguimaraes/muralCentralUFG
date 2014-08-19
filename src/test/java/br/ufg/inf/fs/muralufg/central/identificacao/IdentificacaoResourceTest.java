package br.ufg.inf.fs.muralufg.central.identificacao;

import static org.junit.Assert.*;

public class IdentificacaoResourceTest {

    @org.junit.Test
    public void testFornecidaCoincideComCriada() throws Exception {
        IdentificacaoResource ir = new IdentificacaoResource("a", "v");
        assertEquals("a", ir.fornecaIdentificacao().getNome());
        assertEquals("v", ir.fornecaIdentificacao().getVersao());
    }
}