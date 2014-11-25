/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.muralufg.central.oportunidade.dao;

import br.ufg.inf.fabrica.muralufg.central.oportunidade.Oportunidade;
import br.ufg.inf.fabrica.muralufg.central.oportunidade.OportunidadeRepositoryDatastore;
import java.util.Date;
import java.util.Set;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luiz Henrique
 */
public class OportunidadeRepositoryDatastoreTest {

    public OportunidadeRepositoryDatastoreTest() {
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
        System.out.println("buscarOportunidadesVigentes");
        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setId(36);
        oportunidade.setDescricao("Oportunidade de Estagio no CERCOMP/UFG.");
        oportunidade.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidade.setDataFim(new Date(System.currentTimeMillis()));

        OportunidadeRepositoryDatastore instance = new OportunidadeRepositoryDatastore();
        instance.adicionar(oportunidade);
        Set<Oportunidade> oportunidadesVigentes = instance.vigentes();
        assertEquals(1, oportunidadesVigentes.size());
    }

    /**
     * Test of adicionar method, of class OportunidadeDAO.
     */
    @Test
    public void testAdicionar() {
        System.out.println("inserir");
        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setId(36);
        oportunidade.setDescricao("Oportunidade de Estagio no CERCOMP/UFG.");
        oportunidade.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidade.setDataFim(new Date(System.currentTimeMillis()));

        OportunidadeRepositoryDatastore instance = new OportunidadeRepositoryDatastore();
        instance.adicionar(oportunidade);
        Set<Oportunidade> oportunidadesVigentes = instance.vigentes();
        assertEquals(1, oportunidadesVigentes.size());
    }

}
