package br.ufg.inf.fabrica.muralufg.central.evento;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class EventoResourceTest {

	@Test
	public void testAdicionaEvento() throws Exception {
		Evento ev1 = new Evento();
		ev1.setId(2l);
		ev1.setNome("Evento Teste");
		ev1.setDataInicio(new Date());
		ev1.setDataFim(new Date());
		ev1.setHoraEvento("20:15");
		EventoResource rsc = new EventoResource();
		boolean adiciona = rsc.adiciona(ev1);
		assertEquals(adiciona, true);
	}

}
