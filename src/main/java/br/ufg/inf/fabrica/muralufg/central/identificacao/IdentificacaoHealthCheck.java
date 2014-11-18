package br.ufg.inf.fabrica.muralufg.central.identificacao;

import br.ufg.inf.fabrica.muralufg.central.CentralConfiguration;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;

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
        if (versao == null || versao.isEmpty()) {
            return Result.unhealthy("identificação não inclui versão.");
        }
        
        // Recupera URL da biblioteca
        String url = configuracao.getURL();
        if (url == null || url.isEmpty()) {
            return Result.unhealthy("identificação não possui nome.");
        }
        String keyURL = configuracao.getKeyURL();
        if (keyURL == null || keyURL.isEmpty()) {
            return Result.unhealthy("identificação não inclui versão.");
        }

        return Result.healthy();
    }
}
