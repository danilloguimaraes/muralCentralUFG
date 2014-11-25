package br.ufg.inf.fabrica.muralufg.central.ouvidoria;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ImpOuvidoriaRepositoryTest {

    @Mock
    private ImpOuvidoriaRepository mockOuvidoria;

    @Test
    public void buscaNaoRespondidosComData() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMyyyy");
        DateTime data = formatter.parseDateTime("20052014");

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("21052014"), "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("22052014"), "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("23052014"), "fonte"));

        when(mockOuvidoria.buscaNaoRespondidos(data)).thenReturn(listaRetornoMock);

        List<Assunto> listaRetorno = mockOuvidoria.buscaNaoRespondidos(data);

        assertEquals(listaRetorno, listaRetornoMock);

    }


    @Test
    public void buscaNaoRespondidosComDataEIndice() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMyyyy");
        DateTime data = formatter.parseDateTime("20052014");

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("21052014"), "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("22052014"), "fonte"));


        when(mockOuvidoria.buscaNaoRespondidos(data, 2)).thenReturn(listaRetornoMock);

        List<Assunto> listaRetorno = mockOuvidoria.buscaNaoRespondidos(data, 2);

        assertEquals(listaRetorno, listaRetornoMock);

    }


    @Test
    public void buscaRespondidosComData() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMyyyy");
        DateTime data = formatter.parseDateTime("20052014");

        Assunto assunto1 = new Assunto("conteudo", new DateTime("21052014"), "fonte");
        Assunto assunto2 = new Assunto("conteudo", new DateTime("22052014"), "fonte");
        Assunto assunto3 = new Assunto("conteudo", new DateTime("23052014"), "fonte");

        assunto1.foiRespondido();
        assunto2.foiRespondido();
        assunto3.foiRespondido();

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(assunto1);
        listaRetornoMock.add(assunto2);
        listaRetornoMock.add(assunto2);

        when(mockOuvidoria.buscaRespondidos(data)).thenReturn(listaRetornoMock);

        List<Assunto> listaRetorno = mockOuvidoria.buscaRespondidos(data);

        assertEquals(listaRetorno, listaRetornoMock);

    }


    @Test
    public void buscaRespondidosComDataEIndice() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMyyyy");
        DateTime data = formatter.parseDateTime("20052014");

        Assunto assunto1 = new Assunto("conteudo", new DateTime("21052014"), "fonte");
        Assunto assunto2 = new Assunto("conteudo", new DateTime("22052014"), "fonte");

        assunto1.foiRespondido();
        assunto2.foiRespondido();

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("21052014"), "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", new DateTime("22052014"), "fonte"));


        when(mockOuvidoria.buscaRespondidos(data, 2)).thenReturn(listaRetornoMock);

        List<Assunto> listaRetorno = mockOuvidoria.buscaRespondidos(data, 2);

        assertEquals(listaRetorno, listaRetornoMock);

    }

}
