package br.ufg.inf.fs.muralufg.central.identificacao;

import br.ufg.inf.fs.muralufg.central.CentralConfiguration;
import com.codahale.metrics.health.HealthCheck;

public class IdentificacaoHealthCheck extends HealthCheck {
    private final CentralConfiguration configuracao;

    public IdentificacaoHealthCheck(CentralConfiguration template) {
        this.configuracao = template;
    }

    @Override
    protected Result check() throws Exception {
        String nome = configuracao.getNome();
        if (nome == null || nome.isEmpty()) {
            return Result.unhealthy("identificação não possui nome.");
        }

        String versao = configuracao.getVersao();
        if (nome == null || nome.isEmpty()) {
            return Result.unhealthy("identificação não inclui versão.");
        }

        return Result.healthy();
    }
}
