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

package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.Set;

/**
 * Identifica um usuário da Central.
 * <p>Um usuário pode ser tanto quem recebe informações da Central
 * quanto quem envia informações para a Central.</p>
 */
public class Usuario {
    private String username;
    private Set<String> registrationIds;

    /**
     * Construtor padrão de Usuario.
     */
    public Usuario() {
    }

    /**
     * Cria uma instância de Usuario.
     * @param username Identificador do usuário.
     * @param registrationIds Conjunto de identificadores de registro dos
     *                        dispositivos do usuário.
     */
    public Usuario(String username, Set<String> registrationIds) {
        this.username = username;
        this.registrationIds = registrationIds;
    }

    /**
     * Obtém o identificador do usuário.
     * @return Sequência de caracteres que corresponde ao identificador do usuário.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define identificador do usuário.
     * @param username Sequência de caracteres que corresponde ao identificador do usuário.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtém o conjunto de identificadores de registro dos dispositivos do usuário.
     * @return Conjunto dos identificadores de registro dos dispositivos do usuário.
     */
    public Set<String> getRegistrationIds() {
        return registrationIds;
    }

    /**
     * Define o conjunto de identificadores de registro dos dispositivos do usuário.
     * @param registrationIds Conjunto dos identificadores de registro dos dispositivos do usuário.
     */
    public void setRegistrationIds(Set<String> registrationIds) {
        this.registrationIds = registrationIds;
    }
}
