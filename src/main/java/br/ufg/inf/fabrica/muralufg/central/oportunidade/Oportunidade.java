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
package br.ufg.inf.fabrica.muralufg.central.oportunidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Classe para criar objetos que representam uma oportunidade de estágio,
 * emprego ou pesquisa, por exemplo. Onde serão contidos, valores e métodos para
 * o mesmo.
 */
public class Oportunidade implements Serializable {

    private long id;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;

    private static final long serialVersionUID = 7845068940066519056L;

    /**
     * Construtor vazio da classe Oportunidade, necessário para que as funções
     * relativas a persistência funcionem normalmente
     */
    public Oportunidade() {
    }

    /**
     * Construtor da classe Oportunidade com todos os seus atributos
     *
     * @param descricao String - Descrição da oportunidade
     * @param dataInicio Date - Data em que a oportunidade se inicia
     * @param dataFim Date - Data em que a oportunidade se encerra
     */
    public Oportunidade(String descricao, Date dataInicio, Date dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    /**
     * Método para retorno do identificador da oportunidade
     *
     * @return Long - identificador
     */
    public long getId() {
        return id;
    }

    /**
     * Método para definir o valor do identificador da oportunidade
     *
     * @param id Long - Identificador único da oportunidade
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Método para retorno da data que a oportunidade se inicia
     *
     * @return Date - Data em que começa
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Método para definir o valor da data em que a oportunidade entra em vigor
     *
     * @param dataInicio Date - Data em que a oportunidade se inicia
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Método para retorno da data que a oportunidade se encerra
     *
     * @return Date - Data em que se encerra
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Método para definir o valor da data em que se encerra a oportunidade
     *
     * @param dataFim Date - Data em que a oportunidade se encerra
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Método para retorno da descrição da oportunidade
     *
     * @return String - Descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método para definir o valor da descrição da oportunidade
     *
     * @param descricao String - Descrição da oportunidade
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Método responsável por retornar um identificador único para o objeto em
     * questão. Para este fim retornamos o identificador do objeto que já possui
     * esse propósito
     *
     * @return int - Identificador único da Oportunidade
     */
    @Override
    public int hashCode() {
        return (int) id;
    }

    /**
     * Método responsável por verificar a igualdade entre objetos do tipo
     * Oportunidade
     *
     * @param obj Object - Objeto a ser comparado com o objeto atual
     * @return boolean - true: Caso o objeto passado como parâmetro seja igual a
     * esse objeto. false: Caso os objetos sejam diferentes.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Oportunidade)) {
            return false;
        }

        final Oportunidade other = (Oportunidade) obj;

        if (this.id != other.id) {
            return false;
        }

        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }

        if (!Objects.equals(this.dataFim, other.dataFim)) {
            return false;
        }

        return Objects.equals(this.descricao, other.descricao);
    }
}
