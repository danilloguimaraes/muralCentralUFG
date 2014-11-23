/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.fabrica.muralufg.central.contato;

import br.ufg.inf.fabrica.muralufg.central.contato.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author GAOliveira
 */

/*Nesta classe é populada várias listas com valores pré-definidos, uma lista para cada classe, ou seja,
uma para contatos, outra para cursos, outra para endereços, outra para orgãos e outra para serviços*/

public class ListaDeDados {
    
     
     public static ArrayList<Contato> ListarContato(){                    //Cria método ListaContato para armazenar os contados em uma lista
          ArrayList<Contato> listcontato = new ArrayList<Contato>();      //Cria lista listacontatos para armazenar os objetos Contato
          
          Contato contato =  new Contato();
          contato.setIdContato(1);
          contato.setNomeContato("Geovane Alves");
          contato.setTelefone("6299213866");
          contato.setFax("6235000021");
          contato.setEmail("Geovane@gmail.com");
          contato.setSkype("Geovane1992");
          listcontato.add(contato);     //Adiciona objeto a lista
          
          Contato contato2 =  new Contato();
          contato2.setIdContato(2);
          contato2.setNomeContato("Wanessa Souza");
          contato2.setTelefone("6299216900");
          contato2.setFax("633511121");
          contato2.setEmail("wanessa@gmail.com");
          contato2.setSkype("wanessa2012");
          listcontato.add(contato2);    //Adiciona objeto a lista
          
            
         
         return listcontato;    //Retorna lista de contatos com os contatos cadastrados
     }
     

     
     public static ArrayList<Curso> ListarCurso(){                        //Cria método ListarCurso para armazenar os cursos em uma lista
          ArrayList<Curso> listcursoo = new ArrayList<Curso>();           //Cria lista listcursoo para armazenar os objetos Curso
          
          Curso curso =  new Curso();
          curso.setIdCurso(1);
          curso.setNomeCurso("Engenharia de Software");
          curso.setIdOrgao(1);
          curso.setIdContato(1);
          listcursoo.add(curso);    //Adiciona objeto a lista
          
          Curso curso2 =  new Curso();
          curso2.setIdCurso(2);
          curso2.setNomeCurso("Matematica");
          curso2.setIdOrgao(2);
          curso2.setIdContato(2);
          listcursoo.add(curso2);   //Adiciona objeto a lista
 
          return listcursoo;       //Retorna lista de cursos com os cursos cadastrados
     }

     
     public static ArrayList<Endereco> ListarEndereco(){                  //Cria método ListarEndereco para armazenar os endereços em uma lista
          ArrayList<Endereco> listendereco = new ArrayList<Endereco>();   //Cria lista listendereco para armazenar os objetos Endereco
          
          Endereco endereco =  new Endereco();
          endereco.setIdEndereco(1);
          endereco.setBairro("Curitiba");
          endereco.setCep("75370000");
          endereco.setNumero("355");
          endereco.setRua("Rua A");
          endereco.setIdContato(1);
          endereco.setIdOrgao(1);
          listendereco.add(endereco);   //Adiciona objeto a lista
          
          Endereco endereco2 =  new Endereco();
          endereco2.setIdEndereco(2);
          endereco2.setBairro("Vila Concordia");
          endereco2.setCep("790010011");
          endereco2.setNumero("001");
          endereco2.setRua("Rua B");
          endereco2.setIdContato(2);
          endereco2.setIdOrgao(2);
          listendereco.add(endereco2);  //Adiciona objeto a lista
     
     return listendereco;       //Retorna lista de endereços com os endereços cadastrados
     }
     
     
     public static ArrayList<Orgao> ListarOrgao(){                        //Cria método ListarOrgao para armazenar os orgãos em uma lista
          ArrayList<Orgao> listorgao = new ArrayList<Orgao>();            //Cria lista listorgao para armazenar os objetos Orgao
          
          Orgao orgao =  new Orgao();
          orgao.setIdOrgao(1);
          orgao.setNome("INF");
          //orgao.setIdservico(1);
          listorgao.add(orgao);     //Adiciona objeto a lista
          
          Orgao orgao2 =  new Orgao();
          orgao2.setIdOrgao(2);
          orgao2.setNome("MAT");
          //orgao.setIdservico(2);
          listorgao.add(orgao2);    //Adiciona objeto a lista
          
     return listorgao;      //Retorna lista de orgãos com os orgãos cadastrados
     }
     
     public static ArrayList<Servico> ListarServico(){                    //Cria método ListarServico para armazenar os serviços em uma lista
          ArrayList<Servico> listservico = new ArrayList<Servico>();      //Cria lista listservico para armazenar os objetos Servico
          
          Servico servico =  new Servico();
          servico.setIdServico(1);
          servico.setDescricao("Servico 1");
          servico.setIdOrgao(1);
          listservico.add(servico);     //Adiciona objeto a lista
          
          Servico servico2 =  new Servico();
          servico2.setIdServico(2);
          servico2.setDescricao("Servico 2");
          servico2.setIdOrgao(2);
          listservico.add(servico2);    //Adiciona objeto a lista
     
     return listservico;     //Retorna lista de serviços com os serviços cadastrados
     }

    
}
