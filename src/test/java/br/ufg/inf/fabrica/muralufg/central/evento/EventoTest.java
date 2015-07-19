package br.ufg.inf.fabrica.muralufg.central.evento;

import br.ufg.inf.fabrica.muralufg.central.evento.Evento;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventoTest {

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
     * Teste do método filtraEventoPorRaio
     */
    @Test
    public void testFiltraEventoPorRaio() {
        System.out.println("filtraEventoPorRaio");
        Evento evento = new Evento();
        evento.setId(1);
        evento.setNomeEvento("Palestra JAVA");
        evento.setDescricao("Palestra JAVA - Realizado pelo professor Fábio "
                + "Lucena - Local: INF");
        evento.setDataInicio(new DateTime(System.currentTimeMillis()));
        evento.setDataFim(new DateTime("16/12/2014"));
        evento.setHoraEvento("16:45");

        EventoRepositoryDataStore instance = new EventoRepositoryDataStore();
        instance.adiciona(evento);
        List<Evento> eventoPorRaio = instance.filtraEventoPorRaio(2);
        assertEquals(1, eventoPorRaio.size());
    }
    
    /**
     * Teste do método filtraEventoPorDiaRaio
     */
    @Test
    public void testFiltraEventoPorDiaRaio() {
        System.out.println("filtraEventoPorDiaRaio");
        Evento evento = new Evento();
        evento.setId(1);
        evento.setNomeEvento("Palestra JAVA");
        evento.setDescricao("Palestra JAVA - Realizado pelo professor Fábio "
                + "Lucena - Local: INF");
        evento.setDataInicio(new DateTime("14/12/2014"));
        evento.setDataFim(new DateTime("16/12/2014"));
        evento.setHoraEvento("16:45");

        EventoRepositoryDataStore instance = new EventoRepositoryDataStore();
        instance.adiciona(evento);
        List<Evento> eventoPorRaio = instance.filtraEventoPorRaio(2);
        assertEquals(1, eventoPorRaio.size());
    }

    /**
     * Teste do método adiciona
     */
    @Test
    public void testAdiciona() {
        System.out.println("insere");
        Evento evento = new Evento();
        evento.setId(1);
        evento.setNomeEvento("Palestra JAVA");
        evento.setDescricao("Palestra JAVA - Realizado pelo professor Fábio "
                + "Lucena - Local: INF");
        evento.setDataInicio(new DateTime("13/12/2014"));
        evento.setDataFim(new DateTime("16/12/2014"));
        evento.setHoraEvento("16:45");
                
        EventoRepositoryDataStore instance = new EventoRepositoryDataStore();
        instance.adiciona(evento);
    }
}
