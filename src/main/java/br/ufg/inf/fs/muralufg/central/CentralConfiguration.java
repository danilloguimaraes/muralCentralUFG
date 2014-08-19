package br.ufg.inf.fs.muralufg.central;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Estabelece parâmetros de configuração da Central.
 */
public class CentralConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String versao;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    @JsonProperty
    public String getNome() {
        return nome;
    }

    @JsonProperty
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty
    public String getVersao() {
        return versao;
    }

    @JsonProperty
    public void setVersao(String versao) {
        this.versao = versao;
    }
}
