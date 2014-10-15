package br.ufg.inf.fabrica.muralufg.central.evento;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class EventoTest {
	@Test
	public void testAdicionaEvento() throws Exception {
		Evento ev1 = new Evento();
		ev1.setId(1l);
		ev1.setNome("Evento Teste");
		ev1.setDataInicio(new Date());
		ev1.setDataFim(new Date());
		ev1.setHoraEvento("19:15");
		EventoResource rsc = new EventoResource();
		rsc.adiciona(ev1);
	}
}
