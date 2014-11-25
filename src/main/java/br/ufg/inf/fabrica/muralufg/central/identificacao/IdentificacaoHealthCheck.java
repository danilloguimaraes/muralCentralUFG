package br.ufg.inf.fabrica.muralufg.central.identificacao;

import br.ufg.inf.fabrica.muralufg.central.CentralConfiguration;
import com.codahale.metrics.health.HealthCheck;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class IdentificacaoHealthCheck extends HealthCheck {
    private final CentralConfiguration configuracao;
    Logger logger = LoggerFactory.getLogger(IdentificacaoHealthCheck.class);

    public IdentificacaoHealthCheck(CentralConfiguration template) {
        this.configuracao = template;
    }

    @Override
    protected Result check() throws Exception {
        String nome = configuracao.getNome();
        logger.debug("Método check. nome: " + nome);
        if (nome == null || nome.isEmpty()) {
            return Result.unhealthy("identificação não possui nome.");
        }

        String versao = configuracao.getVersao();
        logger.debug("Método check. versao: " + versao);
        if (versao == null || versao.isEmpty()) {
            return Result.unhealthy("identificação não inclui versão.");
        }

        return Result.healthy();
    }
}
