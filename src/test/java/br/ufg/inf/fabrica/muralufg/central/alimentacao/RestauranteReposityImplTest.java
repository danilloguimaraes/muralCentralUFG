package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    Restaurante rest;
    
    // <editor-fold defaultstate="collapsed" desc="CONFIGURAÇÕES DOS TESTES">
    /**
     * Construtor da classe de testes.
     */
    public RestauranteReposityImplTest() {
        rest = new Restaurante(
                       String.valueOf(new Random().nextInt(355)), 
                       "Campus teste", 
                       "Teste",
                       new Date(), 
                       new Date());
        if(rest.getListaPratos().get(0) != null){
            imagemId = rest.getListaPratos().get(0).getImagemId(); 
        }
        super.restIpml = new RestauranteRepositoryImpl();
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
        try{
            rest.setNome("Test");

            int tListaAntesInsercao = 0;
            restIpml.adiciona(rest);
            int tListaDepoisInsercao = restIpml.obtem(rest).size();

            assertTrue(tListaAntesInsercao <= tListaDepoisInsercao);
        }catch (Exception ex){
           Logger.getLogger(RestauranteReposityImplTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    @Test
    public void testRemocaoRestauranteFalha() {
        Boolean resultado = restIpml.remover(null);

        assertFalse(resultado);
    }

    @Test
    public void testRemocaoRestauranteSucesso() {
        List<Restaurante> lista = restIpml.obtem(rest);
        
        Restaurante itemRemocao = restIpml.obtem(rest).get(new Random().nextInt(lista.size()));

        Boolean resultado = restIpml.remover(itemRemocao);

        assertTrue(resultado);
    }

    @Test
    public void testAtualizacaoRestaurante() {
        List<Restaurante> lista = restIpml.obtem(rest);
        int indice = new Random().nextInt(lista.size());
        Restaurante itemAtualizacao = lista.get(indice);

        itemAtualizacao.setCampus("TestFixture");
        itemAtualizacao.setNome("TestFixture@");

        restIpml.atualizar(itemAtualizacao);

        assertTrue(!restIpml.obtem(rest).get(indice).equals(itemAtualizacao));
    }

    @Test
    public void testAdicionaPrato() {
        List<Restaurante> lista = restIpml.obtem(rest);
        
        int indice = new Random().nextInt(lista.size());
        Restaurante itemAtualizacao = lista.get(indice);
        int quantidadePratos = itemAtualizacao.getListaPratos().size();

        restIpml.adicionaPrato(ObtenhaPratoAleatorio(), itemAtualizacao);

        assertTrue(quantidadePratos == itemAtualizacao.getListaPratos().size());
    }

    @Test
    public void testObtemPrato() {
        List<Restaurante> lista = restIpml.obtem(rest);
        
        int indice = new Random().nextInt(lista.size());
        Restaurante itemBusca = lista.get(indice);

        for (Prato elemento : itemBusca.getListaPratos()) {
            dataDisponivel = elemento.getDiaEmQueEstaDisponivel();
            break;
        }

        List<Prato> listaDePratos = restIpml.obtemPrato(itemBusca, dataDisponivel);

        assertTrue(!listaDePratos.isEmpty());
    }

    @Test
    public void testGetImagem() {
        restIpml.adiciona(rest);
        byte[] resultado = restIpml.getImagem(imagemId);

        assertTrue(resultado == null);
    }

    @Test
    public void testGetMymeTypeFalha() {
        String resultado = restIpml.getMimeType("");

        assertTrue(resultado.equals(""));
    }

    @Test
    public void testGetMymeTypeSucesso() {
        final Integer ZERO = 0;
        
        imagemId = restIpml.obtem(new Restaurante("1")).get(ZERO).getListaPratos().get(ZERO).getImagemId();
        
        String resultado = restIpml.getMimeType(imagemId);

        assertTrue(resultado.equals(""));
    }

    // </editor-fold>
}
