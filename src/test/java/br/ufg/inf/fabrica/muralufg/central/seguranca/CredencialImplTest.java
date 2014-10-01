package br.ufg.inf.fabrica.muralufg.central.seguranca;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import br.ufg.inf.fabrica.muralufg.central.seguranca.CredencialImpl;

public class CredencialImplTest {
	
	@Test
	public void testeEncriptarAutenticarSenha() throws NoSuchAlgorithmException, InvalidKeySpecException {
		CredencialImpl credencialImpl = new CredencialImpl();
		credencialImpl.invalidar("TesteUsuario");

		credencialImpl.insere("TesteUsuario", "testeSenha");
		
		assertTrue(credencialImpl.autenticar("TesteUsuario", "testeSenha"));
		assertFalse(credencialImpl.autenticar("TesteUsuario", "testeSenhaErrada"));
		
		credencialImpl.invalidar("TesteUsuario");
		assertFalse(credencialImpl.autenticar("TesteUsuario", "testeSenha"));
	}

}
