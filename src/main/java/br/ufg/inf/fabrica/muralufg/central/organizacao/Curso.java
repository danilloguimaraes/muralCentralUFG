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

import java.util.Set;

/**
 * Representa um curso oferecido pela UFG (graduação ou
 * pós-graduação).
 */
public class Curso {
    private String id;
    private String nome;
    private Set<Disciplina> disciplinas;
    private Set<Docente> docentes;
    private Orgao orgao;

    /**
     * Cria uma instância de Curso.
     * @param id Identificador único do curso.
     * @param nome Nome do curso.
     * @param disciplinas Conjunto de disciplinas do curso.
     * @param docentes Conjunto de docentes do curso.
     * @param orgao Órgão que ofecere o curso.
     */
    public Curso(String id, String nome, Set<Disciplina> disciplinas, Set<Docente> docentes, Orgao orgao) {
        this.id = id;
        this.nome = nome;
        this.disciplinas = disciplinas;
        this.docentes = docentes;
        this.orgao = orgao;
    }

    /**
     * Obtém o identificador único do curso.
     * @return Sequência de caracteres que corresponde ao identificador do curso.
     */
    public String getId() {
        return id;
    }

    /**
     * Define identificador único do curso.
     * @param id Sequência de caracteres que corresponde ao identificador do curso.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o nome do curso.
     * @return Sequência de caracteres que corresponde ao nome do curso.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do curso.
     * @param nome Sequência de caracteres que corresponde ao nome do curso.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o conjunto de disciplinas integrantes do curso.
     * @return Conjunto de disciplinas que fazem parte do curso.
     */
    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    /**
     * Define o conjunto de disciplinas integrantes do curso.
     * @param disciplinas Conjunto de disciplinas que fazem parte do curso.
     */
    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    /**
     * Obtém o conjunto de docentes que desenvolvem atividades no curso.
     * @return Conjunto de docentes do curso.
     */
    public Set<Docente> getDocentes() {
        return docentes;
    }

    /**
     * Define o conjunto de docentes que desenvolvem atividades no curso.
     * @param docentes Conjunto de docentes do curso.
     */
    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

    /**
     * Obtém o órgão que oferece o curso.
     * @return Órgão que oferece o curso.
     */
    public Orgao getOrgao() {
        return orgao;
    }

    /**
     * Define o órgão que oferece o curso.
     * @param orgao Órgão que oferece o curso.
     */
    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
}
