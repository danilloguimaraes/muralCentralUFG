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

package br.ufg.inf.fabrica.muralufg.central.organizacao;

import java.util.Date;
import java.util.Set;

/**
 * Representa uma turma.
 */
public class Turma {
    private String id;
    private Date semestre;
    private Set<Docente> docentes;
    private Set<Aluno> matriculados;

    /**
     * Cria uma instância de Turma.
     * @param id Identificador único da turma.
     * @param semestre Semestre da turma.
     * @param docentes Conjunto de docentes que são responsáveis da turma.
     * @param matriculados Conjunto de alunos que estão matriculados na turma.
     */
    public Turma(String id, Date semestre, Set<Docente> docentes, Set<Aluno> matriculados) {
        this.id = id;
        this.semestre = semestre;
        this.docentes = docentes;
        this.matriculados = matriculados;
    }

    /**
     * Obtém o identificador único da turma.
     * @return Sequência de caracteres que corresponde ao identificador da turma.
     */
    public String getId() {
        return id;
    }

    /**
     * Define identificador único da turma.
     * @param id Sequência de caracteres que corresponde ao identificador da turma.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o semestre da disciplina.
     * @return Data que indica o semestre da disciplina.
     */
    public Date getSemestre() {
        return semestre;
    }

    /**
     * Define o semestre da disciplina.
     * @param semestre Data que indica o semestre da disciplina.
     */
    public void setSemestre(Date semestre) {
        this.semestre = semestre;
    }

    /**
     * Obtém o conjunto de docentes responsáveis da turma.
     * @return Conjunto de docentes da turma.
     */
    public Set<Docente> getDocentes() {
        return docentes;
    }

    /**
     * Define o conjunto de docentes responsáveis da turma.
     * @param docentes Conjunto de docentes da turma.
     */
    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

    /**
     * Obtém o conjunto de alunos matriculados na turma.
     * @return Conjunto de alunos matriculados na turma.
     */
    public Set<Aluno> getMatriculados() {
        return matriculados;
    }

    /**
     * Define o conjunto de alunos matriculados na turma.
     * @param matriculados Conjunto de alunos matriculados na turma.
     */
    public void setMatriculados(Set<Aluno> matriculados) {
        this.matriculados = matriculados;
    }
}
