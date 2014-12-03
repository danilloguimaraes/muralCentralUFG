package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes da classe {@link Restaurante }.
 *
 * @author Fabrica de Software - INF/UFG
 */
public class RestauranteTest extends ConfiguracaoBase {

    // <editor-fold defaultstate="collapsed" desc="CONFIGURAÇÕES DOS TESTES">
    public RestauranteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

      // </editor-fold>
    
    @Test
    public void testObtemRestauranteNull() {
        List<Restaurante> resultado = restIpml.obtem(null);
        assertNull(resultado);
    }

    @Test
    public void testListaObtemRestaurante() {
        List<Restaurante> resultado = restIpml.obtem(new Restaurante());
        assertTrue(!resultado.isEmpty());
    }

}
