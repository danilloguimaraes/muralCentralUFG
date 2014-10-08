package br.ufg.inf.fabrica.muralufg.central.seguranca;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import br.ufg.inf.fabrica.muralufg.central.seguranca.CredencialImpl;

public class CredencialServiceTest {
	
	@Test
	public void testeEncriptarAutenticarSenha() throws NoSuchAlgorithmException, InvalidKeySpecException {
		CredencialService credencialService = new CredencialImpl();
		
		credencialService.invalidar("TesteUsuario");
		credencialService.insere("TesteUsuario", "testeSenha");
		
		assertTrue(credencialService.autenticar("TesteUsuario", "testeSenha"));
		assertFalse(credencialService.autenticar("TesteUsuario", "testeSenhaErrada"));
		
		credencialService.invalidar("TesteUsuario");
		assertFalse(credencialService.autenticar("TesteUsuario", "testeSenha"));
	}

}
