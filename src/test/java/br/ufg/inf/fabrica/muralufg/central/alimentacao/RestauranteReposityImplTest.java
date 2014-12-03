package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste da implementação da interface {@link RestauranteRepository}
 *
 * @author Fabrica de Software - INF/UFG
 */
public class RestauranteReposityImplTest extends ConfiguracaoBase {
    
    // <editor-fold defaultstate="collapsed" desc="CONFIGURAÇÕES DOS TESTES">
    /**
     * Construtor da classe de testes.
     */
    public RestauranteReposityImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Método executado antes da execução de QUALQUER método de teste.
     */
    @Before
    public void setUp() {
    }

    /**
     * Método executado depois da execução de QUALQUER método de teste.
     */
    @After
    public void tearDown() {
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="TESTES">
    @Test(expected = IllegalArgumentException.class)
    public void testAdicioneRestauranteExcecao() {
        restIpml.adiciona(null);
    }

    @Test
    public void testAdicionaRestuarante() {
        Restaurante r = new Restaurante();
        r.setNome("Test");

        int tListaAntesInsercao = 0;
        restIpml.adiciona(r);
        int tListaDepoisInsercao = 0;

        assertTrue(tListaAntesInsercao < tListaDepoisInsercao);
    }

    @Test
    public void testRemocaoRestauranteFalha() {
        Boolean resultado = restIpml.remover(null);

        assertFalse(resultado);
    }

    @Test
    public void testRemocaoRestauranteSucesso() {
        Restaurante itemRemocao = restIpml.getRepoRestaurantes().get(new Random().nextInt(5));

        Boolean resultado = restIpml.remover(itemRemocao);

        assertTrue(resultado);
    }

    @Test
    public void testAtualizacaoRestaurante() {
        int indice = new Random().nextInt(50);
        Restaurante itemAtualizacao = restIpml.getRepoRestaurantes().get(indice);

        itemAtualizacao.setCampus("TestFixture");
        itemAtualizacao.setNome("TestFixture@");

        restIpml.atualizar(itemAtualizacao);

        assertTrue(restIpml.getRepoRestaurantes().get(indice).equals(itemAtualizacao));
    }

    @Test
    public void testAdicionaPrato() {
        int indice = new Random().nextInt(50);
        Restaurante itemAtualizacao = restIpml.getRepoRestaurantes().get(indice);
        int quantidadePratos = restIpml.getListaPratos(itemAtualizacao).size();

        restIpml.adicionaPrato(ObtenhaPratoAleatorio(), itemAtualizacao);

        assertTrue(quantidadePratos != restIpml.getListaPratos(itemAtualizacao).size());
    }

    @Test
    public void testObtemPrato() {
        int indice = new Random().nextInt(50);
        Restaurante itemBusca = restIpml.getRepoRestaurantes().get(indice);

        for (Map.Entry<Restaurante, ArrayList<Prato>> elemento : restIpml.getRepoCardapio().entrySet()) {
            if (elemento.getKey().equals(itemBusca)) {
                for (Prato prato : elemento.getValue()) {
                    dataDisponivel = prato.getDiaEmQueEstaDisponivel();
                    break;
                }
            }
        }

        List<Prato> listaDePratos = restIpml.obtemPrato(itemBusca, dataDisponivel);

        assertTrue(!listaDePratos.isEmpty());
    }

    @Test
    public void testGetImagem() {
        byte[] resultado = restIpml.getImagem(imagemId.toString());

        assertTrue(resultado != null);
    }

    @Test
    public void testGetMymeTypeFalha() {
        String resultado = restIpml.getMimeType("");

        assertTrue(resultado.equals(""));
    }

    @Test
    public void testGetMymeTypeSucesso() {
        String resultado = restIpml.getMimeType(imagemId.toString());

        assertTrue(!resultado.equals(""));
    }

    // </editor-fold>
}
