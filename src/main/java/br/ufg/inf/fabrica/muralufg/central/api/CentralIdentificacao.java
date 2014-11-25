package br.ufg.inf.fabrica.muralufg.central.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Fornece a identificação da Central UFG
 * (inclui a versão da API de acesso aos serviços).
 */
public class CentralIdentificacao {
    private String nome;
    private String versao;
    private String recursos;

    public CentralIdentificacao(String nome, String versao, String recursos) {
        this.nome = nome;
        this.versao = versao;
        this.recursos = recursos;
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
    public String getNome() {
        return nome;
    }

    @JsonProperty
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty
    public String getRecursos() {
        return recursos;
    }
    @JsonProperty
    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }
}