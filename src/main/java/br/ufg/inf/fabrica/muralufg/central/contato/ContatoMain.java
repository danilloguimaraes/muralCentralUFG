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

package br.ufg.inf.fabrica.muralufg.central.contato;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Pessoa ou serviço que representa algum órgão da UFG e com o
 * qual contato é desejado.
 */
public class ContatoMain {
    

    /**
     * O nome do contato, por exemplo, nome da pessoa física
     * que oferece/representa algum serviço oferecido pela UFG.
     * Por exemplo, o nome de uma coordenadora de curso, ou o
     * nome de um técnico responsável por um laboratório,
     * ou diretor do CEGEF, dentre várias outras opções.
     */


    public static void main(String[] args) throws SQLException{
        
        String orgao1 = null;
        String servico1 = null;
        String contato1 = null;
        String endereco1 = null;
        String curso1 = null;
        int contatoid;
        int orgaoid = 0;
        int servicoid;
        int cursoid = 0;
        int enderecoid;
        String concatena;   //Variável para armazenar os registros vinculados
        int menu = 0;       //Variável do menu de opções.
       
        //Aqui é realizada as chamadas das classes necessárias para a construção da busca.
        Contato contato =  new Contato();
        Curso curso =  new Curso();
        Endereco endereco =  new Endereco();
        Orgao orgao =  new Orgao();
        Servico servico =  new Servico();
        ListaDeDados listaDeDados = new ListaDeDados();
        

                    ListaDeDados.ListarContato();
                    ListaDeDados.ListarCurso();
                    ListaDeDados.ListarOrgao();
                    ListaDeDados.ListarServico();
                    ListaDeDados.ListarEndereco();
                    
                    List<String> listContatos = new ArrayList<String>();    //Cria lista para armazenar os registros vinculados em forma de string.
                    
              /*Neste bloco é realizada a logica para fazer o vinculo entre os os registros de cada classe.
                Para cada contato é verificado qual é seu endereço, pelo idContato que é cadastrado na classe Endereco,
                achando o encereço é verificado qual o curso vinculado ao contato, pelo idContato cadastrado na classe Curso,
                e assim sucessivamente até o último vinculo.*/
                    
              for (Contato con : ListaDeDados.ListarContato()) { 
                        contatoid = con.getIdContato();
                        contato1 = con.getNomeContato() + " - " + con.getTelefone() + " - " + con.getFax() + " - " + con.getEmail() + " - " + con.getSkype();
                        
                        for (Endereco end : ListaDeDados.ListarEndereco()) {

                            if(end.getIdContato() == contatoid){
                                enderecoid = end.getIdEndereco();
                                endereco1 = end.getRua() + " - " + end.getNumero() + " - " + end.getBairro() + " - " + end.getCep();

                            }     
                               for (Curso cur : ListaDeDados.ListarCurso()) { 
                                   
                                   if(cur.getIdContato() == contatoid){
                                       cursoid = cur.getIdOrgao();
                                       curso1 = cur.getNomeCurso();
                                            }

                                        for (Orgao org : ListaDeDados.ListarOrgao()) { 

                                            if(org.getIdOrgao() == cursoid){
                                                orgaoid = org.getIdOrgao();
                                                orgao1 = org.getNome();
                                            }

                                            for (Servico ser : ListaDeDados.ListarServico()) { 

                                                if(ser.getIdOrgao() == orgaoid){
                                                    servico1 = ser.getDescricao();

                                                }
    
                                            }   
                                        }
                                }
                            
                        }
                        //Aqui é transformado todos os registros vinculados em uma única string.
                        concatena = contato1 + " - " + endereco1 + " - " + curso1 + " - " + orgao1 + " - " + servico1;
                        //Após criar a string, a mesma é adicionada a lista listContatos
                        listContatos.add(concatena);
                        
                }
        /*Aqui é criado um while para a escolha de uma opção no menu, 1 para digitar a palavra a ser consultada 
          e 2 para sair do programa.*/
        while(menu != 2){
            
            System.out.println(" 1 - Consultar \n 2 - Sair \n -->");
            Scanner men = new Scanner(System.in);
             menu = men.nextInt();
             
             if (menu != 1 && menu != 2){
                 System.out.println("Valor digitado é inválido!");
            
             }
       
                if(menu == 1){
                    
                    System.out.println("Digite a palavra a ser consultada: \n");
                    Scanner sc = new Scanner(System.in);
                    String valor = sc.nextLine();
                    
                     for(String str : listContatos) {  //Faz uma busca em toda a lista em busca da palavra digitada acima.
 
                        if(str.contains(valor)){       //Se a palavra digitada for encontrada, o registro que a possui é retornado.
                            System.out.println(str);
                        }
                         
                     }
                }
                
                else if(menu == 2){
                    out.close();
                }
                                
        }  
    }
}


