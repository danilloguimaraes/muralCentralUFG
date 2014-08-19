package br.ufg.inf.fs.muralufg.central;

import br.ufg.inf.fs.muralufg.central.identificacao.IdentificacaoHealthCheck;
import br.ufg.inf.fs.muralufg.central.identificacao.IdentificacaoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CentralApplication extends Application<CentralConfiguration> {

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
                configuracao.getVersao()
        );

        environment.jersey().register(versaoResource);

        final IdentificacaoHealthCheck healthCheck =
                new IdentificacaoHealthCheck(configuracao);
        environment.healthChecks().register("identificacao", healthCheck);
        environment.jersey().register(versaoResource);
    }
}
