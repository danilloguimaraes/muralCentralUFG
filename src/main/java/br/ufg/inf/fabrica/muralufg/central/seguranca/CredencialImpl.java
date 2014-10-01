package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class CredencialImpl implements CredencialService {
	
	static final String SYSTEM_PATH = System.getProperty("user.dir") + System.getProperty("file.separator");
	File arquivo;

	public boolean insere(String usuario, String senha) {
		arquivo = new File(getCaminhoArquivo(usuario)); 
		
		if (arquivo.exists()) {
			return false;
		} else {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		try (FileWriter fileWriter = new FileWriter(arquivo)){
			
			byte[] salt = gerarSalt();
			byte[] senhaEncriptada = getSenhaEncriptada(senha, salt);
			
			fileWriter.write(toHexString(salt));
			fileWriter.write(toHexString(senhaEncriptada));
			
			fileWriter.flush();
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Algoritmo não encontrado." + e.getMessage());
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean autenticar(String usuario, String senha) {
		if (!isUsuarioExiste(usuario))
			return false;
		
		try {
			String credencialTexto = recuperaCredencial(usuario);
			byte[] salt = toByteArray(credencialTexto.substring(0, 16));
			byte[] senhaEncriptadaSalva = toByteArray(credencialTexto.substring(16));
			
			byte[] senhaEncriptada = getSenhaEncriptada(senha, salt);
			
			return Arrays.equals(senhaEncriptadaSalva, senhaEncriptada);
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private String toHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}
	
	private byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}

	public void invalidar(String usuario) {
		if(isUsuarioExiste(usuario))
			arquivo.delete();
	}
	
	private String recuperaCredencial(String usuario) throws IOException {
		FileReader fileReader = new FileReader(arquivo);
		BufferedReader reader = new BufferedReader(fileReader);
		String credencialTexto = "";
		
		
		while(reader.ready()) {
			credencialTexto += reader.readLine();
		}
		
		reader.close();
			
		return credencialTexto;
	}
	
	private boolean isUsuarioExiste(String usuario) {
		arquivo = new File(getCaminhoArquivo(usuario));
		
		return arquivo.exists();
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
	private byte[] getSenhaEncriptada(String senhaPlana, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
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
	
	/**
	 * 
	 * @param usuario
	 * @return caminho do arquivo que será criado
	 */
	private String getCaminhoArquivo(String usuario) {
		return SYSTEM_PATH + usuario + ".bin";
	}
}
