package br.ufg.inf.fabrica.muralufg.central.identificacao;

import br.ufg.inf.fabrica.muralufg.central.api.CentralIdentificacao;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Identificação da Central. Simplesmente expõe
 * valores configurados em central-configuracao.yml.
 */
@Path("/identificacao")
@Produces(MediaType.APPLICATION_JSON)
public class IdentificacaoResource {
    private final AtomicLong counter;
    private final String nome;
    private final String versao;

    public IdentificacaoResource(String nome, String versao) {
        this.counter = new AtomicLong();
        this.nome = nome;
        this.versao = versao;
    }

    @GET
    @Timed
    public CentralIdentificacao fornecaIdentificacao() {
        return new CentralIdentificacao(counter.incrementAndGet(), nome, versao);
    }
}
