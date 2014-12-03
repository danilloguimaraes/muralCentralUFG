package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Configuração base compartilhada entre as classes de teste do pacote de
 * alimentação.
 *
 * @author Fabrica de Software - INF/UFG
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class ConfiguracaoBase {

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
    protected List<String> formasPagamento;

    /**
     * Data em que o prato está diponível.
     */
    protected Date dataDisponivel;

    /**
     * {@link String} ID da imagem.
     */
    protected String imagemId;

    /**
     * Referência da classe a ser testada.
     */
    protected RestauranteRepositoryImpl restIpml;

    // </editor-fold>
    
    
    public RestauranteRepositoryImpl getRestIpml() {
        return restIpml;
    }

    public void setRestIpml(RestauranteRepositoryImpl restIpml) {
        this.restIpml = restIpml;
    }
    
    // <editor-fold defaultstate="collapsed" desc="MÉTODOS PRIVADOS">
    /**
     * Obtém uma data randômica.
     *
     * @param tipoDeAdicao Adição de horas ou dias.
     * @return Nova data.
     */
    protected Date obtenhaDataAleatoria(String tipoDeAdicao) {
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
     * Obtém um único prato.
     *
     * @return {@link Prato}.
     */
    protected Prato ObtenhaPratoAleatorio() {
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
        imagemId = UUID.randomUUID().toString();
        p.setImagemId(imagemId);

        return p;
    }

    // </editor-fold>
}
