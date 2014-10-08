/*
 * ====================================================================
 * Licença da Fábrica de Software (UFG)
 *
 * Copyright (c) 2014 Fábrica de Software
 * Instituto de Informática (Universidade Federal de Goiás)
 * Todos os direitos reservados.
 *
 * Redistribuição e uso, seja dos fontes ou do formato binário
 * correspondente, com ou sem modificação, são permitidos desde que
 * as seguintes condições sejam atendidas:
 *
 * 1. Redistribuição do código fonte deve conter esta nota, em sua
 *    totalidade, ou seja, a nota de copyright acima, as condições
 *    e a observação sobre garantia abaixo.
 *
 * 2. Redistribuição no formato binário deve reproduzir o conteúdo
 *    desta nota, em sua totalidade, ou seja, o copyright acima,
 *    esta lista de condições e a observação abaixo na documentação
 *    e/ou materiais fornecidos com a distribuição.
 *
 * 3. A documentação fornecida com a redistribuição,
 *    qualquer que seja esta, deve incluir o seguinte
 *    texto, entre aspas:
 *       "Este produto inclui software desenvolvido pela Fábrica
 *       de Software do Instituto de Informática (UFG)."
 *
 * 4. Os nomes Fábrica de Software, Instituto de Informática e UFG
 *    não podem ser empregados para endoçar ou promover produtos
 *    derivados do presente software sem a explícita permissão
 *    por escrito.
 *
 * 5. Produtos derivados deste software não podem ser chamados
 *    "Fábrica de Software", "Instituto de Informática", "UFG",
 *    "Universidade Federal de Goiás" ou contê-los em seus nomes,
 *    sem permissão por escrito.
 *
 * ESTE SOFTWARE É FORNECIDO "COMO ESTÁ". NENHUMA GARANTIA É FORNECIDA,
 * EXPLÍCITA OU NÃO. NÃO SE AFIRMA QUE O PRESENTE SOFTWARE
 * É ADEQUADO PARA QUALQUER QUE SEJA O USO. DE FATO, CABE AO
 * INTERESSADO E/OU USUÁRIO DO PRESENTE SOFTWARE, IMEDIATO OU NÃO,
 * ESTA AVALIAÇÃO E A CONSEQUÊNCIA QUE O USO DELE OCASIONAR. QUALQUER
 * DANO QUE DESTE SOFTWARE DERIVAR DEVE SER ATRIBUÍDO AO INTERESSADO
 * E/OU USUÁRIO DESTE SOFTWARE.
 * ====================================================================
 *
 * Este software é resultado do trabalho de voluntários, estudantes e
 * professores, ao realizar atividades no âmbito da Fábrica de Software
 * do Instituto de Informática (UFG). Consulte <http://fs.inf.ufg.br>
 * para detalhes.
 */


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
	
	/**
	 * @param usuario Nome do usuário
	 * @return 	{@code true} se o arquivo contendo os dados do usuário existe
	 * 			{@code false} se não existir esse arquivo com os dados do usuário	
	 */
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
	 * @param usuario Nome do usuário
	 * @return caminho do arquivo que será criado
	 */
	private String getCaminhoArquivo(String usuario) {
		String usuarioHexBinary = toHexString(usuario.getBytes());
		return SYSTEM_PATH + usuarioHexBinary + ".bin";
	}
}
