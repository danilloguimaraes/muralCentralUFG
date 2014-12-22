/*
 * Autor: Bruno Rodrigues Franco
 * Data: 19/11/2014
 */
package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import sun.net.www.protocol.https.HttpsURLConnectionImpl;

/**
 * The Class AutenticacaoServiceImpl.
 */
public class AutenticacaoServiceImpl {

	/** Endereco do servidor. */
	private String ENDERECO_SERVIDOR;

	/**
	 * Instantiates a new autenticacao service impl.
	 *
	 * @param enderecoServidor, o endereco do servidor
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AutenticacaoServiceImpl(String enderecoServidor) throws IOException {
		verificaSsl();
		ENDERECO_SERVIDOR = enderecoServidor;
	}

	/**
	 * Carrega arquivo de propriedades para autenticacao
	 * e verifica ssl.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void verificaSsl() throws IOException {

		Properties propriedades = new Properties();
		String propFileName = "autenticacao.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		propriedades.load(inputStream);
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		final String hostName = propriedades.getProperty("hostname");
		HttpsURLConnection
				.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

					public boolean verify(String hostname,
							javax.net.ssl.SSLSession sslSession) {
						if (hostname.equals(hostName)) {
							return true;
						}
						return false;
					}
				});
	}

	/**
	 * Class DefaultTrustManager que verifica se o cliente
	 * é confiável de acordo com o certificado X509 e também
	 * verifica se o servidor é confiavel.
	 */
	private static class DefaultTrustManager implements X509TrustManager {

		/* (non-Javadoc)
		 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
		 */
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		/* (non-Javadoc)
		 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
		 */
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		/* (non-Javadoc)
		 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
		 */
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	/**
	 * Autentica usuario.
	 *
	 * @param usuario 
	 * @param senha 
	 * @return true, if successful
	 */
	public boolean autenticaUsuario(String usuario, String senha) {
		int responseCode = 0;
		try {
			configuraCertificadoNaConexao();

			HttpsURLConnectionImpl con = abreConexao(ENDERECO_SERVIDOR);

			con.setRequestMethod("POST");

			String urlParameters = "username=" + usuario + "&password=" + senha;

			enviaRequisicaoPost(con, urlParameters);

			responseCode = con.getResponseCode();
		} catch (Exception e) {
			System.out.println("Erro na autenticacao do usuario.");
			return false;
		}

		if (responseCode == 200 || responseCode == 201) {
			return true;
		}
		return false;
	}

	/**
	 * Abre conexao.
	 *
	 * @param url 
	 * @return the https url connection impl
	 * @throws MalformedURLException the malformed url exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static HttpsURLConnectionImpl abreConexao(String url)
			throws MalformedURLException, IOException {
		URL obj = new URL(url);
		HttpsURLConnectionImpl con = (HttpsURLConnectionImpl) obj
				.openConnection();
		return con;
	}

	/**
	 * Envia requisicao post.
	 *
	 * @param con referente a Conexão
	 * @param urlParameters, os parametros da URL
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void enviaRequisicaoPost(HttpsURLConnectionImpl con,
			String urlParameters) throws IOException {
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
	}

	/**
	 * Configura certificado na conexao.
	 *
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyManagementException the key management exception
	 */
	private static void configuraCertificadoNaConexao()
			throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0],
				new TrustManager[] { new DefaultTrustManager() },
				new SecureRandom());
		SSLContext.setDefault(ctx);
	}
}
