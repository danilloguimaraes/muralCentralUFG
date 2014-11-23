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

/*Esta é a classe de curso, a qual possui vinculo com a classe Orgao e Contato realizado pelo idOrgao e idContato*/

public class Curso {
     
   private int idCurso;
   private String nomeCurso;
   private int idOrgao;
   private int idContato;

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

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
    
    
    
}
