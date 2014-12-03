/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste da implementação da interface {@link RestauranteRepository}
 *
 * @author Iasmim Ribeiro
 */
public class TesteRestauranteReposityImpl {

    // <editor-fold defaultstate="collapsed" desc="CONSTANTES">
    /**
     * Constante horas.
     */
    private final String HORAS = "HORAS";

    /**
     * Constante dias.
     */
    private final String DIAS = "DIAS";

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="VARIÁVEIS">
    /**
     * Formas de pagamento aceitas no restaurante.
     */
    private List<String> formasPagamento;

    /**
     * Data em que o prato está diponível.
     */
    private Date dataDisponivel;

    /**
     * {@link UUID} da imagem.
     */
    private UUID imagemId;

    /**
     * Referência da classe a ser testada.
     */
    RestauranteRepositoryImpl restIpml;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="CONFIGURAÇÕES DOS TESTES">
    /**
     * Construtor da classe de testes.
     */
    public TesteRestauranteReposityImpl() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Método executado antes da execução de QUALQUER método de teste.
     */
    @Before
    public void setUp() {
        /* Formas de pagamento */
        formasPagamento = new ArrayList<>();
        formasPagamento.add("CARTÃO DE CRÉDITO");
        formasPagamento.add("DINHEIRO");
        formasPagamento.add("CHEQUE");

        restIpml = new RestauranteRepositoryImpl();
        restIpml.setRepoRestaurantes(obtenhaListaDeRestaurante());
        restIpml.setRepoCardapio(obtenhaCardapio(restIpml));
    }

    /**
     * Método executado depois da execução de QUALQUER método de teste.
     */
    @After
    public void tearDown() {
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="TESTES">
    @Test
    public void testObtemRestauranteNull() {
        List<Restaurante> resultado = restIpml.obtem(null);
        assertNull(resultado);
    }

    @Test
    public void testListaObtemRestaurante() {
        List<Restaurante> resultado = restIpml.obtem(new Restaurante());
        assertTrue(!resultado.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdicioneRestauranteExcecao() {
        restIpml.adiciona(null);
    }

    @Test
    public void testAdicionaRestuarante() {
        Restaurante r = new Restaurante();
        r.setNome("Test");

        int tListaAntesInsercao = restIpml.getRepoRestaurantes().size();
        restIpml.adiciona(r);
        int tListaDepoisInsercao = restIpml.getRepoRestaurantes().size();

        assertTrue(tListaAntesInsercao < tListaDepoisInsercao);
    }

    @Test
    public void testRemocaoRestauranteFalha() {
        Boolean resultado = restIpml.remover(null);

        assertFalse(resultado);
    }

    @Test
    public void testRemocaoRestauranteSucesso() {
        Restaurante itemRemocao = restIpml.getRepoRestaurantes().get(new Random().nextInt(5));

        Boolean resultado = restIpml.remover(itemRemocao);

        assertTrue(resultado);
    }

    @Test
    public void testAtualizacaoRestaurante() {
        int indice = new Random().nextInt(50);
        Restaurante itemAtualizacao = restIpml.getRepoRestaurantes().get(indice);

        itemAtualizacao.setCampus("TestFixture");
        itemAtualizacao.setNome("TestFixture@");

        restIpml.atualizar(itemAtualizacao);

        assertTrue(restIpml.getRepoRestaurantes().get(indice).equals(itemAtualizacao));
    }

    @Test
    public void testAdicionaPrato() {
        int indice = new Random().nextInt(50);
        Restaurante itemAtualizacao = restIpml.getRepoRestaurantes().get(indice);
        int quantidadePratos = restIpml.getListaPratos(itemAtualizacao).size();

        restIpml.adicionaPrato(ObtenhaPratoAleatorio(), itemAtualizacao);

        assertTrue(quantidadePratos != restIpml.getListaPratos(itemAtualizacao).size());
    }

    @Test
    public void testObtemPrato() {
        int indice = new Random().nextInt(50);
        Restaurante itemBusca = restIpml.getRepoRestaurantes().get(indice);

        for (Map.Entry<Restaurante, ArrayList<Prato>> elemento : restIpml.getRepoCardapio().entrySet()) {
            if(elemento.getKey().equals(itemBusca)){
                for (Prato prato : elemento.getValue()) {
                    dataDisponivel = prato.getDiaEmQueEstaDisponivel();
                    break;
                }
            }
        }
        
        List<Prato> listaDePratos = restIpml.obtemPrato(itemBusca, dataDisponivel);

        assertTrue(!listaDePratos.isEmpty());
    }

    @Test
    public void testGetImagem() {
        byte[] resultado = restIpml.getImagem(imagemId.toString());

        assertTrue(resultado != null);
    }

    @Test
    public void testGetMymeTypeFalha() {
        String resultado = restIpml.getMimeType("");

        assertTrue(resultado.equals(""));
    }

    @Test
    public void testGetMymeTypeSucesso() {
        String resultado = restIpml.getMimeType(imagemId.toString());

        assertTrue(!resultado.equals(""));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÉTODOS PRIVADOS">
    /**
     * Obtém uma data randômica.
     *
     * @param tipoDeAdicao Adição de horas ou dias.
     * @return Nova data.
     */
    private Date obtenhaDataAleatoria(String tipoDeAdicao) {
        Date dateTime = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dateTime);
        calendario.add(tipoDeAdicao.equals(HORAS)
                ? Calendar.HOUR
                : Calendar.DAY_OF_MONTH, new Random().nextInt(12));
        dateTime = calendario.getTime();

        return dateTime;
    }

    /**
     * Obtém uma lista de restaurantes.
     *
     * @return {@link ArrayList<>} do tipo {@link Restaurante}.
     */
    private ArrayList<Restaurante> obtenhaListaDeRestaurante() {
        ArrayList<Restaurante> lista = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Restaurante r1 = new Restaurante();
            r1.setCampus("Campus " + i);
            r1.setFimHorario(obtenhaDataAleatoria(HORAS));
            r1.setFormaPagamento(formasPagamento);
            r1.setId(String.valueOf(new Random().nextInt(999)));
            r1.setInicioHorario(obtenhaDataAleatoria(HORAS));
            r1.setNome("Restaurante " + i);

            lista.add(r1);
        }

        return lista;
    }

    /**
     * Obtém um cardápio, utilizando os resturantes já inseridos.
     *
     * @param restImpl Referência para o objeto com o repoitóio de cardápio.
     * @return Dicionário com a chave {@link Restaurante} e o valor {@link
     * ArrayList<Prato>}.
     */
    private HashMap<Restaurante, ArrayList<Prato>> obtenhaCardapio(
            RestauranteRepositoryImpl restImpl) {
        HashMap<Restaurante, ArrayList<Prato>> cardapio = new HashMap<>();

        for (Restaurante elemento : restImpl.getRepoRestaurantes()) {
            cardapio.put(elemento, obtenhaListaDePratos());
        }
        
        return cardapio;
    }

    /**
     * Obtém uma lista randômica de pratos.
     *
     * @return {@link ArrayList<Prato>}.
     */
    private ArrayList<Prato> obtenhaListaDePratos() {
        ArrayList<Prato> lista = new ArrayList<>();

        int indice = new Random().nextInt(50);

        for (int i = 0; i < indice; i++) {
            lista.add(ObtenhaPratoAleatorio());
        }

        return lista;
    }

    /**
     * Obtém um único prato.
     *
     * @return {@link Prato}.
     */
    private Prato ObtenhaPratoAleatorio() {
        List<String> listaMimeType = new ArrayList<>();
        listaMimeType.add("image/bmp");
        listaMimeType.add("image/x-windows-bmp");
        listaMimeType.add("image/gif");
        listaMimeType.add("image/jpeg");

        Prato p = new Prato();
        p.setDescricao("Prato " + new Random().nextInt(999));
        p.setDiaEmQueEstaDisponivel(obtenhaDataAleatoria(DIAS));
        p.setMimeTypeImage(listaMimeType.get(new Random().nextInt(listaMimeType.size())));
        p.setPrecoEmReais(new Random().nextDouble());
        imagemId = UUID.randomUUID();
        p.setImagemId(imagemId.toString());

        return p;
    }

    // </editor-fold>
}
