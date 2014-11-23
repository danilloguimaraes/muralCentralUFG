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

/*Esta é a classe de endereço, a qual possui vinculo com a classe Contato e Orgao realizado pelo idContato e idOrgao*/

public class Endereco {
    
   private int idEndereco;
   private String bairro;
   private String cep;
   private String numero;
   private String rua;
   private int idContato;
   private int idOrgao;


    public int getIdContato() {
        return idContato;
    }
    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }
    
    public int getIdOrgao() {
        return idOrgao;
    }

    public void setIdOrgao(int idOrgao) {
        this.idOrgao = idOrgao;
    }
   

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
    
    
}
