package br.ufg.inf.fabrica.muralufg.central.evento;

import java.util.Calendar;
import java.util.Date;

public class EventoTest {
	public static void main(String[] args) {
		Evento ev1 = new Evento();
		ev1.setId(1l);
		ev1.setNome("Evento Teste");
		ev1.setDataInicio(new Date());
		ev1.setDataFim(new Date());
		ev1.setHoraEvento("19:15");
		EventoDao dao = new EventoDao();
		dao.salvar(ev1);
	}
}
