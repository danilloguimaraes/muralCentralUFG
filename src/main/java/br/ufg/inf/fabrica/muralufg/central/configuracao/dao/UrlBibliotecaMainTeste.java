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

package br.ufg.inf.fabrica.muralufg.central.configuracao.dao;

import java.util.Scanner;
import br.ufg.inf.fabrica.muralufg.central.api.ConfiguracaoRepresentationUrlBiblioteca;

/*
* Para testar o DAO usando o Google Datastore localmente
*
* 1 - Baixar o arquivo http://storage.googleapis.com/gcd/tools/gcd-v1beta2-rev1-2.1.1.zip
* 2 - Descompactar o arquivo em uma pasta
* 3 - Executar na linha de comando:
*       gcd-v1beta2-rev1-2.1.1/gcd.exe create my-dataset
*       gcd-v1beta2-rev1-2.1.1/gcd.sh start my-dataset
* 4 - Criar duas variáveis de ambiente:
*       DATASTORE_HOST=http://localhost:8080
*       DATASTORE_DATASET=my-dataset
* */

public class UrlBibliotecaMainTeste {
	public static void main(String[] args) {
		
		int opcao;
		String chave = "0";
		String valor = "www.ufg.br";
		do{
			Scanner sc = new Scanner(System.in);
			System.out.println("opcao:\n"
					+ "1-Salvar\n"
					+ "2-Recuperar\n"
					+ "0-Sair\n");
			opcao = sc.nextInt();
			
			switch(opcao){
			case 1:
				salvarDado(chave, valor);
				break;
			case 2:
				recuperarDado(chave);
				break;
			case 0:
				break;
			default:
				System.out.println("digite certo");
			}
		}while(opcao!=0);
		

    }

	/**
	 * Recupera a URL
	 * @param chave
	 */
	public static void recuperarDado(String chave) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Salva os dados da URL (chave, valor)
	 * @param chave
	 * @param valor
	 */
	public static void salvarDado(String chave, String valor) {
		ConfiguracaoRepresentationUrlBiblioteca configRepresURL = new ConfiguracaoRepresentationUrlBiblioteca(chave, valor);
		configRepresURL.defineValor(chave, valor);
	}
}
