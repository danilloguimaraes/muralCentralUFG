package br.ufg.mural.ufg.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configurações da aplicação contidas no arquivo de configuração.
 */
public class Configuracao {
	String PROPERTIES_FILE = "app.properties";
	String api_key;
	String data_key;

	public static Configuracao getInstance() throws ConfiguracaoExeption {
		return new Configuracao();
	}

	private Configuracao() throws ConfiguracaoExeption {
		// Lendo o arquivo
		InputStream app_properties_stream = this.getClass()
				.getResourceAsStream("/" + PROPERTIES_FILE);

		// Carregar as propriedades do arquivo
		Properties app_properties = new Properties();
		try {
			app_properties.load(app_properties_stream);
		} catch (IOException e) {
			throw new ConfiguracaoExeption(String.format(
					"Arquivo de configuração ( $s ) ausente", PROPERTIES_FILE));
		}

		// Api Key, necessária para a autenticação no GCM
		this.api_key = app_properties.getProperty("api_key");
		if (getApiKey() == null) {
			throw new ConfiguracaoExeption(
					"api_key, ausente no arquivo de configuracao");
		}

		// Parametro da mensagem, que serve para identificar o dado que está
		// sendo enviado ao dispositivo
		this.data_key = app_properties.getProperty("data_key");
		if (getDataKey() == null) {
			throw new ConfiguracaoExeption(
					"data_key, ausente no arquivo de configuracao");
		}
	}

	public String getApiKey() {
		return api_key;
	}

	public String getDataKey() {
		return data_key;
	}

	public class ConfiguracaoExeption extends Exception {
		private static final long serialVersionUID = 5995214349587660327L;

		public ConfiguracaoExeption(String mensagem) {
			super(mensagem);
		}
	}

}
