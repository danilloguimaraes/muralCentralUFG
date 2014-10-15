/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.fabrica.muralufg.central.oportunidade.dao;

import br.ufg.inf.fabrica.muralufg.central.oportunidade.Oportunidade;
import java.util.Date;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luiz
 */
public class OportunidadeDAOTest {
    
    public OportunidadeDAOTest() {
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
     * Test of buscarOportunidadesVigentes method, of class OportunidadeDAO.
     */
    //Ainda não implementado
    @Test
    public void testBuscarOportunidadesVigentes() {
        System.out.println("buscarOportunidadesVigentes - ainda não implementado");
        OportunidadeDAO instance = new OportunidadeDAO();
        Set<Oportunidade> expResult = null;
        Set<Oportunidade> result = instance.buscarOportunidadesVigentes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionar method, of class OportunidadeDAO.
     */
    @Test
    public void testAdicionar() {
        System.out.println("adicionar");
        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setId(3);
        oportunidade.setDescricao("Oportunidade de Estagio no LabTime.");
        oportunidade.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidade.setDataTermino(new Date(System.currentTimeMillis()));
        
        OportunidadeDAO instance = new OportunidadeDAO();
        boolean result = instance.adicionar(oportunidade);
        assertTrue(result);
    }
    
}
