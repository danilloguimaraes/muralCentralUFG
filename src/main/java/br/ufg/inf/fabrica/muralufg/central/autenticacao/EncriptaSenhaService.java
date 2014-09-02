package br.ufg.inf.fabrica.muralufg.central.autenticacao;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Classe responsável por encriptar a senha usando métodos seguros,
 * sugeridos pela NIST (National Institute of Standards and Technology)
 * 
 * <p>
 * Fontes de referência:<br>
 * http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
 * http://blog.jerryorr.com/2012/05/secure-password-storage-lots-of-donts.html
 * </p>
 * 
 * @author Jerry Orr
 * @author Douglas Japiassu
 * 
 */
public class EncriptaSenhaService {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = gerarSalt();
		byte[] senhaEncriptada = getSenhaEncriptada("teste", salt);
		autenticar("teste", senhaEncriptada, salt);
	}

	/**
	 * Método responsável por autenticar a senha plana, encriptando ela usando o mesmo salt
	 * utilizado na senha original e depois comparando as duas.
	 * 
	 * @param senhaPlana
	 * @param senhaEncriptada
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static boolean autenticar(String senhaPlana, byte[] senhaEncriptada, byte[] salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		// Encripta a senha plana usando o mesmo "salt" que foi usado para encriptar a senha original
		byte[] senhaPlanaEncriptada = getSenhaEncriptada(senhaPlana, salt);

		boolean isAutenticacaoOk = Arrays.equals(senhaEncriptada, senhaPlanaEncriptada);

		return isAutenticacaoOk;
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
	public static byte[] getSenhaEncriptada(String senhaPlana, byte[] salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
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
	public static byte[] gerarSalt() throws NoSuchAlgorithmException {
		//gera um número criptografado aleatório utilizando o algoritmo SHA1PRNG
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		
		// O salt deverá ter 8 bytes (64 bits) - recomendado por RSA PKCS5
		byte[] salt = new byte[8];
		secureRandom.nextBytes(salt);

		return salt;
	}
	
	public void criarUsuario(String usuario, String senha) {
		
	}
}
