/*
 * Autor: Bruno Rodrigues Franco
 * Data: 19/11/2014
 */
package br.ufg.inf.fabrica.muralufg.central.seguranca;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class AutenticacaoServiceTest.
 */
public class AutenticacaoServiceTest {
	
	/** Endereco do servidor. */
	private static String ENDERECO_SERVIDOR;
	
	/**
	 * Carrega configuracoes iniciais para os testes.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Teste autentica usuario.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testeAutenticaUsuario() throws IOException{
		boolean autenticaUsuario = new AutenticacaoServiceImpl(ENDERECO_SERVIDOR).autenticaUsuario("bruno_rodrigues_franco", "bruno_rodrigues_franco");
		assertEquals(autenticaUsuario, true);
	}
	
}
