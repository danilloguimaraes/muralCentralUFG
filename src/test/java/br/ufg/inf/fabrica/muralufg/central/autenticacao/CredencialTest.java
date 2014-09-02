package br.ufg.inf.fabrica.muralufg.central.autenticacao;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

public class CredencialTest {
	
	private final String senhaPlana = "senhaPlanaParaSerEncriptada";
	private byte[] senhaEncriptada;
	private byte[] salt;
	
	@Test
	public void testeEncriptarAutenticarSenha() throws NoSuchAlgorithmException, InvalidKeySpecException {
		EncriptaSenhaService credencial = new EncriptaSenhaService();
		
		salt = credencial.gerarSalt();
		senhaEncriptada = credencial.getSenhaEncriptada(senhaPlana, salt);
		
		assertTrue(credencial.autenticar(senhaPlana, senhaEncriptada, salt));
	}

}
