/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.fabrica.muralufg.central.contato;

/**
 *
 * @author GAOliveira
 */
//Classes necessárias para uso de Banco de dados // 

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

//Início da classe de conexão// 

public class ConexaoMysql { 
	public static String status = "Não conectou..."; 
	
	//Método Construtor da Classe// 
	
public ConexaoMysql() { 

} 
	
//Método de Conexão// 
	
public static java.sql.Connection getConexaoMySQL() { 
        Connection connection = null; //atributo do tipo 
		
  try { 
		
// Carregando o JDBC Driver padrão 
		
    String driverName = "com.mysql.jdbc.Driver"; 
    Class.forName(driverName); 
		
// Configurando a conexão com um banco de dados// 
		
	String serverName = "186.202.152.239"; //caminho do servidor do BD String 
		
	String mydatabase ="usemoveis2"; //nome do seu banco de dados 
		
	String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
        
        String username = "usemoveis2"; //nome de um usuário de seu BD 
		
	String password = "teste123"; //sua senha de acesso 
		
	connection = DriverManager.getConnection(url, username, password); 
		
//Testa a conexão// 
		
    if (connection != null) { 
	status = ("STATUS--->Conectado com sucesso!"); 
	} 
    else { 
	status = ("STATUS--->Não foi possivel realizar conexão"); 
	} return connection; } catch (ClassNotFoundException e) { 
					
//Driver não encontrado 
					
	System.out.println("O driver expecificado nao foi encontrado."); 
					
	return null; 
					
		} catch (SQLException e) { 
					
//Não conseguindo se conectar ao banco 
					
                    System.out.println("Nao foi possivel conectar ao Banco de Dados."); 
					
                    return null;
                        } 
 } 
//Método que retorna o status da conexão// 
					

public static String statusConection() { 
					
    return status; 
					
} 
					
//Método que fecha a conexão// 
					
public static boolean FecharConexao() { 
					
    try { 
	ConexaoMysql.getConexaoMySQL().close(); return true; 
	} catch (SQLException e) { 
									
            return false; 
	} 
} 
									
//Método que reinicia a conexão// 
									
public static java.sql.Connection ReiniciarConexao() { 
    FecharConexao(); 
	return ConexaoMysql.getConexaoMySQL(); 
} 
}
