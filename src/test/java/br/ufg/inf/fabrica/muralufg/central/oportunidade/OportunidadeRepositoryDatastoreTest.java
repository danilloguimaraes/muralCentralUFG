/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.muralufg.central.oportunidade;

import br.ufg.inf.fabrica.muralufg.central.oportunidade.CentralException;
import br.ufg.inf.fabrica.muralufg.central.oportunidade.Oportunidade;
import br.ufg.inf.fabrica.muralufg.central.oportunidade.OportunidadeRepositoryDatastore;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 
 * Classe de teste da implementação do repositório OportunidadeRepository utilizando Google Datastore
 */
public class OportunidadeRepositoryDatastoreTest {

    private static OportunidadeRepositoryDatastore mockedOportunidadeRepository;
    private static Oportunidade oportunidadeA;
    private static Oportunidade oportunidadeB;

    public OportunidadeRepositoryDatastoreTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Método responsável pela configuração inicial do teste utilizando mocks
     * @throws CentralException 
     */
    @Before
    public void setUp() throws CentralException {
        //Criação do Mock da classe OportunidadeRepositoryDatasore
        mockedOportunidadeRepository = mock(OportunidadeRepositoryDatastore.class);

        //Criação de algumas instâncias de Oportunidade
        oportunidadeA.setId(1);
        oportunidadeA.setDescricao("Oportunidade de Estagio no CERCOMP/UFG.");
        oportunidadeA.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidadeA.setDataFim(new Date(System.currentTimeMillis()));

        oportunidadeB.setId(2);
        oportunidadeB.setDescricao("Oportunidade de Estagio no CIAR/UFG.");
        oportunidadeB.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidadeB.setDataFim(new Date(System.currentTimeMillis()));

        //Stubbing the methods of mocked BookDAL with mocked data.
        when(mockedOportunidadeRepository.vigentes()).thenReturn(new HashSet(Arrays.asList(oportunidadeA, oportunidadeB)));
        when(mockedOportunidadeRepository.adicionar(oportunidadeA)).thenReturn(oportunidadeA.getId());
    }

    @After
    public void tearDown() {
    }

    /**
     * Teste do método buscarOportunidadesVigentes method, da classe
     * OportunidadeRepositoryDatasore
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarOportunidadesVigentes() throws Exception {
        Set<Oportunidade> oportunidadesVigentes = mockedOportunidadeRepository.vigentes();
        assertEquals(2, oportunidadesVigentes.size());
    }

    /**
     * Teste do método adicionar, da classe OportunidadeRepositoryDatasore
     * @throws java.lang.Exception
     */
    @Test
    public void testAdicionar() throws Exception {
        long id = mockedOportunidadeRepository.adicionar(oportunidadeA);
        assertNotNull(id);
        assertEquals(oportunidadeA.getId(), id);
    }

}
