package br.ufg.inf.fabrica.muralufg.central;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Estabelece parâmetros de configuração da Central (componente).
 * @see br.ufg.inf.fabrica.muralufg.central.CentralApplication
 */
public class CentralConfiguration extends Configuration {
    
    @NotEmpty
    private String nome;

    @NotEmpty
    private String versao;
    
    private String url;

    private String keyURL;

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
    
    @JsonProperty
    public String getURL() {
        return url;
    }

    @JsonProperty
    public void setURL(String url) {
        this.url = url;
    }

    @JsonProperty
    public String getKeyURL() {
        return keyURL;
    }

    @JsonProperty
    public void setKeyURL(String keyURL) {
        this.keyURL = keyURL;
    }
}
