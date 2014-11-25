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

import br.ufg.inf.fabrica.muralufg.central.seguranca.Usuario;

import java.util.Set;

/**
 * Representa um docente, lotado em um dado órgão.
 */
public class Docente extends Usuario {
    private String id;
    private String nome;
    private Set<Turma> turmas;
    private Orgao orgao;

    /**
     * Cria uma instância de Docente.
     * @param id Identificador único do docente.
     * @param nome Nome do docente.
     * @param turmas Conjunto de turmas em que o docente é responsável.
     * @param orgao Órgão em que o docente está lotado.
     */
    public Docente(String id, String nome, Set<Turma> turmas, Orgao orgao) {
        this.id = id;
        this.nome = nome;
        this.turmas = turmas;
        this.orgao = orgao;
    }

    /**
     * Obtém o identificador único do docente.
     * @return Sequência de caracteres que corresponde ao identificador do docente.
     */
    public String getId() {
        return id;
    }

    /**
     * Define identificador único do docente.
     * @param id Sequência de caracteres que corresponde ao identificador do docente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o nome do docente.
     * @return Sequência de caracteres que corresponde ao nome do docente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define nome do docente.
     * @param nome Sequência de caracteres que corresponde ao nome do docente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o conjunto de turmas em que o docente é responsável.
     * @return Conjunto de turmas do docente.
     */
    public Set<Turma> getTurmas() {
        return turmas;
    }

    /**
     * Define o conjunto de turmas em que o docente é responsável.
     * @param turmas Conjunto de turmas do docente.
     */
    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

    /**
     * Obtém o órgão em que o docente está lotado.
     * @return Órgão em que o docente é integrante.
     */
    public Orgao getOrgao() {
        return orgao;
    }

    /**
     * Define o órgão em que o docente está lotado.
     * @param orgao Órgão em que o docente é integrante.
     */
    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
}
