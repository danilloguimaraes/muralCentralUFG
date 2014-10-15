package br.ufg.inf.fabrica.muralufg.central.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Fornece a identificação da Central UFG (inclui a versão da API de acesso aos
 * serviços).
 */
public class ConfiguracaoRepresentationURL {
	private String url;
	private String keyURL;

	public ConfiguracaoRepresentationURL(String url, String keyURL) {
		this.keyURL = keyURL;
		this.url = url;
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
	public void setKeyURL(String nome) {
		this.keyURL = nome;
	}
}
