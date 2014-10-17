package br.ufg.inf.fabrica.muralufg.central.seguranca;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

public class AutenticacaoServiceTest {
	
	private static String ENDERECO_SERVIDOR;
	
	@BeforeClass
	public static void carregaConfigTeste() throws IOException{
		Properties propriedades = new Properties();
		String propFileName = "autenticacaoTeste.properties";
		
		InputStream inputStream = AutenticacaoServiceTest.class.getClassLoader().getResourceAsStream(propFileName);
		propriedades.load(inputStream);
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		ENDERECO_SERVIDOR = propriedades.getProperty("enderecoServidor");
	}
	
	@Test
	public void testeAutenticaUsuario() throws IOException{
		boolean autenticaUsuario = new AutenticacaoServiceImpl(ENDERECO_SERVIDOR).autenticaUsuario("bruno_rodrigues_franco", "bruno_rodrigues_franco");
		assertEquals(autenticaUsuario, true);
	}
	
}
