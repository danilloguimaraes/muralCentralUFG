/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.testng.Assert.*;

/**
 *
 * @author Iasmim
 */
public class RestauranteRepositoryImplNGTest {
    
    public RestauranteRepositoryImplNGTest() {
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Teste de método obtem, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testObtem() {
        System.out.println("obtem");
        Restaurante filtro = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        List expResult = null;
        List result = instance.obtem(filtro);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método adiciona, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testAdiciona() {
        System.out.println("adiciona");
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.adiciona(restaurante);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método remover, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testRemover() {
        System.out.println("remover");
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.remover(restaurante);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método atualizar, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testAtualizar() {
        System.out.println("atualizar");
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.atualizar(restaurante);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método adicionaPrato, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testAdicionaPrato() {
        System.out.println("adicionaPrato");
        Prato prato = null;
        Restaurante restaurante = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.adicionaPrato(prato, restaurante);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método obtemPrato, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testObtemPrato() {
        System.out.println("obtemPrato");
        Restaurante restaurante = null;
        Date dia = null;
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        List expResult = null;
        List result = instance.obtemPrato(restaurante, dia);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método getImagem, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testGetImagem() {
        System.out.println("getImagem");
        String imagemId = "";
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        byte[] expResult = null;
        byte[] result = instance.getImagem(imagemId);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método getMimeType, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testGetMimeType() {
        System.out.println("getMimeType");
        String imagemId = "";
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        String expResult = "";
        String result = instance.getMimeType(imagemId);
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método obtenhaListaDeRestaurante, da classe RestauranteRepositoryImpl.
     */
    @org.testng.annotations.Test
    public void testObtenhaListaDeRestaurante() {
        System.out.println("obtenhaListaDeRestaurante");
        RestauranteRepositoryImpl instance = new RestauranteRepositoryImpl();
        ArrayList expResult = null;
        ArrayList result = instance.obtenhaListaDeRestaurante();
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }
    
}
