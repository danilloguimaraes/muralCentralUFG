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

import static br.ufg.inf.fabrica.muralufg.central.contato.ConexaoMysql.FecharConexao;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Pessoa ou serviço que representa algum órgão da UFG e com o
 * qual contato é desejado.
 */
public class ContatoMain {
    
    public static java.sql.Connection ReiniciarConexao() { 
    FecharConexao(); 
    return ConexaoMysql.getConexaoMySQL(); 
} 

    /**
     * O nome do contato, por exemplo, nome da pessoa física
     * que oferece/representa algum serviço oferecido pela UFG.
     * Por exemplo, o nome de uma coordenadora de curso, ou o
     * nome de um técnico responsável por um laboratório,
     * ou diretor do CEGEF, dentre várias outras opções.
     */


    public static void main(String[] args) throws SQLException {
      
        Connection conn = ConexaoMysql.getConexaoMySQL();

        
        System.out.println(ConexaoMysql.statusConection() + "\n \n ");
        
        
        Statement stm = conn.createStatement();
        
       int menu = 0;   
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
                    String valor = "%" + sc.nextLine() + "%";

                    //Executa consulta pelo valor digitado
                    ResultSet rs = stm.executeQuery("select curso.nomeCurso, orgao.nome, servico.descricao, contato.nomeContato, contato.telefone, contato.email, contato.fax, contato.skype, endereco.bairro, endereco.cep, endereco.numero, endereco.rua from curso inner join orgao on orgao.idorgao = curso.idorgao inner join servico on servico.idorgao = orgao.idorgao inner join contato on contato.idservico = servico.idservico inner join endereco on endereco.idcontato = contato.idcontato where curso.nomeCurso like '"+valor+"' or orgao.nome like '"+valor+"' or servico.descricao like '"+valor+"' or contato.nomeContato like '"+valor+"' or contato.telefone like '"+valor+"' or contato.email like '"+valor+"' or contato.fax like '"+valor+"' or contato.skype like '"+valor+"' or endereco.bairro like '"+valor+"' or endereco.cep like '"+valor+"' or endereco.numero like '"+valor+"' or endereco.rua like '"+valor+"'");
 

                    //enquanto tiver tuplas retornadas ele não sai do laço while  
                    while(rs.next()){  
                       System.out.println(rs.getString("curso.nomeCurso") +" - "+ rs.getString("orgao.nome") +" - "+ rs.getString("servico.descricao") +" - "+ rs.getString("contato.nomeContato") +" - "+ rs.getString("contato.telefone")  +" - "+ rs.getString("contato.email") +" - "+ rs.getString("contato.fax") +" - "+ rs.getString("contato.skype") +" - "+ rs.getString("endereco.bairro") +" - "+ rs.getString("endereco.cep") +" - "+ rs.getString("endereco.numero") +" - "+ rs.getString("endereco.rua"));
                    } 
                    
                 
                }
                else if(menu == 2){
                    out.close();
                }
        }

        
    }
    
}
