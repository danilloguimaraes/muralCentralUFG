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

/*Esta é a classe orgao, a qual possui vinculo com a classe Servico realizado pelo idServico*/

public class Orgao {
    
   private int idOrgao;
   private String nome;
   private int idServico;
   
   

    public int getIdservico() {
        return idServico;
    }

    public void setIdservico(int idservico) {
        this.idServico = idservico;
    }



    public int getIdOrgao() {
        return idOrgao;
    }

    public void setIdOrgao(int idOrgao) {
        this.idOrgao = idOrgao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
