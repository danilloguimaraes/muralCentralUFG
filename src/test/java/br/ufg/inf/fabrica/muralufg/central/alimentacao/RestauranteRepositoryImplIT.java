/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iasmim
 */
public class RestauranteRepositoryImplIT {
    
    public RestauranteRepositoryImplIT() {
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

    /**
     * Test of obtem method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testObtem() {
        System.out.println("obtem");
        Restaurante filtro = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        List<Restaurante> expResult = null;
        List<Restaurante> result = instance.obtem(filtro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adiciona method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testAdiciona() {
        System.out.println("adiciona");
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.adiciona(restaurante);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remover method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testRemover() {
        System.out.println("remover");
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.remover(restaurante);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizar method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testAtualizar() {
        System.out.println("atualizar");
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.atualizar(restaurante);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionaPrato method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testAdicionaPrato() {
        System.out.println("adicionaPrato");
        Prato prato = null;
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.adicionaPrato(prato, restaurante);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtemPrato method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testObtemPrato() {
        System.out.println("obtemPrato");
        Restaurante restaurante = null;
        Date dia = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        List<Prato> expResult = null;
        List<Prato> result = instance.obtemPrato(restaurante, dia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImagem method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testGetImagem() {
        System.out.println("getImagem");
        String imagemId = "";
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        byte[] expResult = null;
        byte[] result = instance.getImagem(imagemId);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMimeType method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testGetMimeType() {
        System.out.println("getMimeType");
        String imagemId = "";
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        String expResult = "";
        String result = instance.getMimeType(imagemId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenhaListaDeRestaurante method, of class RestauranteRepositoryImpl.
     */
    @Test
    public void testObtenhaListaDeRestaurante() {
        System.out.println("obtenhaListaDeRestaurante");
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        ArrayList<Restaurante> expResult = null;
        ArrayList<Restaurante> result = instance.obtenhaListaDeRestaurante();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
