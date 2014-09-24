package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Credencialmpl implements CredencialService {
	
	static final String PATH = "/home/alunoinf/credencial.bin";
	File arquivo = new File(PATH);

	public boolean insere(String usuario, String senha) throws IOException {
		if (isUsuarioExistente(usuario)) {
			return false;
		}
		
		FileOutputStream fos;
		BufferedOutputStream bos;
		try {
			fos = new FileOutputStream(arquivo);
			bos = new BufferedOutputStream(fos);
			
			bos.write(usuario.getBytes());
			bos.flush();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean autenticar(String usuario, String senha) {
		// TODO Auto-generated method stub
		return false;
	}

	public void invalidar(String usuario) {
		// TODO Auto-generated method stub

	}
	
	private String recuperaCredencial() throws IOException {
		if (arquivo.exists()) {
			FileReader fileReader = new FileReader(arquivo);
			BufferedReader reader = new BufferedReader(fileReader);
			String credencialTexto = "";
			
			while(reader.ready()) {
				credencialTexto += reader.readLine();
			}
			
			reader.close();
			
			return credencialTexto;
		}
		
		arquivo.createNewFile();
		return "";
	}
	
	private boolean isUsuarioExistente(String usuario) throws IOException {
		String credencialTexto = recuperaCredencial();
		String credencial[] = credencialTexto.split("|");
		
		if (credencial[1] == "usuario")
			return true;
		
		return false;
	}
	
	/**
	 * Método responsável por encriptar a senha
	 * 
	 * @param senhaPlana
	 * @param salt o mesmo usado para encriptar a senha original
	 * @return senha encriptada usando o salt fornecido e algoritmos de hash PBKDF2 e SHA1
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private byte[] getSenhaEncriptada(String senhaPlana) throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Gera o SALT
		byte[] salt = gerarSalt();
		
		
		// Utilizaremos PBKDF2 com SHA-1 como algoritmo de encriptação
		String algoritmoEncriptacao = "PBKDF2WithHmacSHA1";

		// tamanho da chave de saída será 160, conforme o algoritmo SHA-1
		int tamanhoChaveSaida = 160;

		// define o número de iterações que teremos para gerar o hash
		// mínimo aconselhado de 1000
		int numeroIteracoes = 20000;

		KeySpec keySpec = new PBEKeySpec(senhaPlana.toCharArray(), salt, numeroIteracoes, tamanhoChaveSaida);

		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algoritmoEncriptacao);

		return secretKeyFactory.generateSecret(keySpec).getEncoded();
	}

	/**
	 * @return sequência de bits gerado aleatoriamente e que é único para cada usuário, para ser adicionado à senha do usuário como parte do HASH.
	 * @throws NoSuchAlgorithmException
	 */
	private byte[] gerarSalt() throws NoSuchAlgorithmException {
		//gera um número criptografado aleatório utilizando o algoritmo SHA1PRNG
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		
		// O salt deverá ter 8 bytes (64 bits) - recomendado por RSA PKCS5
		byte[] salt = new byte[8];
		secureRandom.nextBytes(salt);

		return salt;
	}
}
