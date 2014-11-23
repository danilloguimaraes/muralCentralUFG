package br.ufg.inf.fabrica.muralufg.central;

import br.ufg.inf.fabrica.muralufg.central.identificacao.IdentificacaoHealthCheck;
import br.ufg.inf.fabrica.muralufg.central.identificacao.IdentificacaoResource;
import br.ufg.inf.fabrica.muralufg.central.upload.UploadResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Ponto de Entrada da Central. O processamento
 * executado pela Central (componente) inicia-se
 * por esta classe. A configuração é fornecida por
 * outra classe {@link br.ufg.inf.fabrica.muralufg.central.CentralConfiguration}.
 * @see br.ufg.inf.fabrica.muralufg.central.CentralConfiguration
 */
    public class CentralApplication extends Application<CentralConfiguration> {

    /**
     * Ponto de entrada da aplicação.
     * @param args Argumentos fornecidos.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new CentralApplication().run(args);
    }

    @Override
    public String getName() {
        return "Central UFG";
    }

    @Override
    public void initialize(Bootstrap<CentralConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CentralConfiguration configuracao,
                    Environment environment) {

        final IdentificacaoResource versaoResource = new IdentificacaoResource(
                configuracao.getNome(),
                configuracao.getVersao(),
                configuracao.getRecursos()
        );

        environment.jersey().register(versaoResource);

        final UploadResource ur = new UploadResource();
        environment.jersey().register(ur);

        final IdentificacaoHealthCheck healthCheck =
                new IdentificacaoHealthCheck(configuracao);
        environment.healthChecks().register("identificacao", healthCheck);
    }
}
