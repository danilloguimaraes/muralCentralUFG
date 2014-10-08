package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.io.DataOutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import sun.net.www.protocol.https.HttpsURLConnectionImpl;

public abstract class AutenticacaoUtil {

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

	public static boolean autenticaUsuario(String usuario, String senha) {
		int responseCode = 0;
		try {
			// Dizemos a conexao para confiar em nosso certificado
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0],
					new TrustManager[] { new DefaultTrustManager() },
					new SecureRandom());
			SSLContext.setDefault(ctx);

			// Este é o endereço de nosso servidor de homologação
			String url = "https://cas-homologacao.sistemas.ufg.br/cas/v1/tickets";
			URL obj = new URL(url);
			HttpsURLConnectionImpl con = (HttpsURLConnectionImpl) obj
					.openConnection();

			// Adicionando cabecalho de request
			con.setRequestMethod("POST");
			con.setRequestProperty("Authentication", "Basic: " + usuario + ":"
					+ senha);

			// Informar o nome do usuario do Portal UFGNET e a senha padrao do
			// ambiente simulado (simu123)
			String urlParameters = "username=" + usuario + "&password=" + senha;

			// Enviando requisição POST
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
		} catch (Exception e) {
			System.out.println("Erro na autenticacao do usuario.");
			return false;
		}

		if (responseCode == 200) {
			return true;
		}
		return false;
	}
}
