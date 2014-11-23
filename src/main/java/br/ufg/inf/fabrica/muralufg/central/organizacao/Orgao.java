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
 * Representa um órgão da UFG.
 */
public class Orgao {
    private String id;
    private String nome;
    private Set<Tecnico> tecnicos;
    private Set<Docente> docentes;
    private Set<Curso> cursos;

    /**
     * Cria uma instância de Orgao.
     * @param id Identificador único do órgão.
     * @param nome Nome do órgão.
     * @param tecnicos Conjunto de técnicos do órgão.
     * @param docentes Conjunto de docentes do órgão.
     * @param cursos Conjunto de cursos do órgão.
     */
    public Orgao(String id, String nome, Set<Tecnico> tecnicos, Set<Docente> docentes, Set<Curso> cursos) {
        this.id = id;
        this.nome = nome;
        this.tecnicos = tecnicos;
        this.docentes = docentes;
        this.cursos = cursos;
    }

    /**
     * Obtém o identificador único do órgão.
     * @return Sequência de caracteres que corresponde ao identificador do órgão.
     */
    public String getId() {
        return id;
    }

    /**
     * Define identificador único do órgão.
     * @param id Sequência de caracteres que corresponde ao identificador do órgão.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o nome do órgão.
     * @return Sequência de caracteres que corresponde ao nome do órgão.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do órgão.
     * @param nome Sequência de caracteres que corresponde ao nome do órgão.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o conjunto de técnicos do órgão.
     * @return Conjunto de técnicos que são funcionários do órgão.
     */
    public Set<Tecnico> getTecnicos() {
        return tecnicos;
    }

    /**
     * Define o conjunto de técnicos do órgão.
     * @param tecnicos Conjunto de técnicos que são funcionários do órgão.
     */
    public void setTecnicos(Set<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    /**
     * Obtém o conjunto de docentes do órgão.
     * @return Conjunto de docentes que são funcionários do órgão.
     */
    public Set<Docente> getDocentes() {
        return docentes;
    }

    /**
     * Define o conjunto de docentes do órgão.
     * @param docentes Conjunto de docentes que são funcionários do órgão.
     */
    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

    /**
     * Obtém o conjunto de cursos oferecidos pelo órgão.
     * @return Conjunto de cursos do órgão.
     */
    public Set<Curso> getCursos() {
        return cursos;
    }

    /**
     * Define o conjunto de cursos oferecidos pelo órgão.
     * @param cursos CConjunto de cursos do órgão.
     */
    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}
