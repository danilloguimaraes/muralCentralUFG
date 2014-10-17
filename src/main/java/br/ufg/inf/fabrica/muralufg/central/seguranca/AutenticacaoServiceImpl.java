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

public class AutenticacaoServiceImpl {

	private String ENDERECO_SERVIDOR;

	public AutenticacaoServiceImpl(String enderecoServidor) throws IOException {
		verificaSsl();
		ENDERECO_SERVIDOR = enderecoServidor;
	}

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

	private static class DefaultTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

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

	private static HttpsURLConnectionImpl abreConexao(String url)
			throws MalformedURLException, IOException {
		URL obj = new URL(url);
		HttpsURLConnectionImpl con = (HttpsURLConnectionImpl) obj
				.openConnection();
		return con;
	}

	private static void enviaRequisicaoPost(HttpsURLConnectionImpl con,
			String urlParameters) throws IOException {
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
	}

	private static void configuraCertificadoNaConexao()
			throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0],
				new TrustManager[] { new DefaultTrustManager() },
				new SecureRandom());
		SSLContext.setDefault(ctx);
	}
}
