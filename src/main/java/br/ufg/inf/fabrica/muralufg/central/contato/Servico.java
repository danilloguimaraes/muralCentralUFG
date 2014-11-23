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

/*Esta é a servico, a qual possui vinculo com a classe Orgao realizado pelo idOrgao*/

public class Servico {
    
   private int idServico;
   private String descricao;
   private int IdOrgao;



    public int getIdOrgao() {
        return IdOrgao;
    }

    public void setIdOrgao(int IdOrgao) {
        this.IdOrgao = IdOrgao;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
