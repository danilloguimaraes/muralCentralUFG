package br.ufg.inf.fs.muralufg.central.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Fornece a identificação da Central UFG
 * (inclui a versão da API de acesso aos serviços).
 */
public class CentralIdentificacao {
    private long id;
    private String nome;
    private String versao;

    public CentralIdentificacao(long id, String nome, String versao) {
        this.id = id;
        this.nome = nome;
        this.versao = versao;
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
    public long getId() {
        return id;
    }
}