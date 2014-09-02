package br.ufg.inf.fabrica.muralufg.central.autenticacao;

import java.security.NoSuchAlgorithmException;

public class Credencial {
	
	private EncriptaSenhaService encriptaSenhaService = new EncriptaSenhaService();
	private String usuario;
	private String senha;
	
	public Credencial(String usuario, String senha) {
		try {
			byte[] salt = encriptaSenhaService.gerarSalt();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public byte[] getSenha(String usuario) {
		
		
		return null;
	}

}
