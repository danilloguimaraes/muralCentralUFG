package br.ufg.inf.fabrica.muralufg.central.ouvidoria;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GoogleOuvidoriaRepositoryTest {

    @Mock
    private GoogleOuvidoriaRepository mockOuvidoria;

    @Test
    public void buscaNaoRespondidosComData() {
        DateFormat formatter = new SimpleDateFormat("MMddyy");
        Date dataAnterior = null;
        Date data1 = null;
        Date data2 = null;
        Date data3 = null;
        try {
            data1 = formatter.parse("21052014");
            data2 = formatter.parse("22052014");
            data3 = formatter.parse("23052014");
            dataAnterior = formatter.parse("20052014");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(new Assunto("conteudo", data1, "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", data2, "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", data3, "fonte"));
        List<Assunto> listaRetorno = null;
        try {
            when(mockOuvidoria.buscaNaoRespondidos(dataAnterior)).thenReturn(listaRetornoMock);
            listaRetorno = mockOuvidoria.buscaNaoRespondidos(dataAnterior);

        } catch (OuvidoriaRepositoryException e) {
            e.printStackTrace();
        }


        assertEquals(listaRetorno, listaRetornoMock);

    }


    @Test
    public void buscaNaoRespondidosComDataEIndice() {

        DateFormat formatter = new SimpleDateFormat("MMddyy");
        Date dataAnterior = null;
        Date data1 = null;
        Date data2 = null;
        try {
            data1 = formatter.parse("21052014");
            data2 = formatter.parse("22052014");
            dataAnterior = formatter.parse("20052014");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(new Assunto("conteudo", data1, "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", data2, "fonte"));
        List<Assunto> listaRetorno = null;


        try {
            when(mockOuvidoria.buscaNaoRespondidos(dataAnterior, 2)).thenReturn(listaRetornoMock);
            listaRetorno = mockOuvidoria.buscaNaoRespondidos(dataAnterior, 2);

        } catch (OuvidoriaRepositoryException e) {
            e.printStackTrace();
        }


        assertEquals(listaRetorno, listaRetornoMock);

    }


    @Test
    public void buscaRespondidosComData() {
        DateFormat formatter = new SimpleDateFormat("MMddyy");
        Date dataAnterior = null;
        Date data1 = null;
        Date data2 = null;
        Date data3 = null;
        try {
            data1 = formatter.parse("21052014");
            data2 = formatter.parse("22052014");
            data3 = formatter.parse("23052014");
            dataAnterior = formatter.parse("20052014");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Assunto assunto1 = new Assunto("conteudo", data1, "fonte");
        Assunto assunto2 = new Assunto("conteudo", data2, "fonte");
        Assunto assunto3 = new Assunto("conteudo", data3, "fonte");

        assunto1.foiRespondido();
        assunto2.foiRespondido();
        assunto3.foiRespondido();

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(assunto1);
        listaRetornoMock.add(assunto2);
        listaRetornoMock.add(assunto2);
        List<Assunto> listaRetorno = null;

        try {
            when(mockOuvidoria.buscaRespondidos(dataAnterior)).thenReturn(listaRetornoMock);
            listaRetorno = mockOuvidoria.buscaRespondidos(dataAnterior);

        } catch (OuvidoriaRepositoryException e) {
            e.printStackTrace();
        }

        assertEquals(listaRetorno, listaRetornoMock);

    }


    @Test
    public void buscaRespondidosComDataEIndice() {

        DateFormat formatter = new SimpleDateFormat("MMddyy");
        Date dataAnterior = null;
        Date data1 = null;
        Date data2 = null;
        try {
            data1 = formatter.parse("21052014");
            data2 = formatter.parse("22052014");
            dataAnterior = formatter.parse("20052014");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Assunto assunto1 = new Assunto("conteudo", data1, "fonte");
        Assunto assunto2 = new Assunto("conteudo", data2, "fonte");

        assunto1.foiRespondido();
        assunto2.foiRespondido();

        List<Assunto> listaRetornoMock = new ArrayList<>();
        listaRetornoMock.add(new Assunto("conteudo", data1, "fonte"));
        listaRetornoMock.add(new Assunto("conteudo", data2, "fonte"));
        List<Assunto> listaRetorno = null;


        try {
            when(mockOuvidoria.buscaRespondidos(dataAnterior, 2)).thenReturn(listaRetornoMock);
            listaRetorno = mockOuvidoria.buscaRespondidos(dataAnterior, 2);

        } catch (OuvidoriaRepositoryException e) {
            e.printStackTrace();
        }

        assertEquals(listaRetorno, listaRetornoMock);

    }




}
