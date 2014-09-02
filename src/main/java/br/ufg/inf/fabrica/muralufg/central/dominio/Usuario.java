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

package br.ufg.inf.fabrica.muralufg.central.dominio;

import java.util.Date;

/**
 * Representa informação veiculada/divulgada pelo
 * Mural UFG.
 */
public class Usuario {

    private String id;

    /**
     * Obtém o identificador único do usuário.
     * @return O identificador único (guid) do
     * usuário.
     * @see #setId(String)
     */
    public String getId() {
        return id;
    }

    /**
     * Define o identificador único (guid) do
     * usuário.
     * @param id O identificador único do usuário.
     * @see #getId()
     */
    public void setId(String id) {
        this.id = id;
    }

    private String login;

    /**
     * Obtém o login do usuário.
     * @return O login do usuário.
     * @see #setLogin(String)
     */
    public String getLogin() {
		return login;
	}

    /**
     * Define o login do usuário.
     * @param login O login do usuário.
     * @see #getLogin()
     */
	public void setLogin(String login) {
		this.login = login;
	}

    /**
     * Data de criação do usuário.
     */
    private Date dataCriacao;

    /**
     * Obtém a data de criação do usuário.
     * @return Data em que o usuário foi criado
     * @see #setDataCriacao(java.util.Date)
     */
    public Date getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Define a data em que o usuário foi criado.
     * @param dataCriacao Data em que mensagem
     *                    é recebida pelo Mural UFG.
     * @see #getDataCriacao()
     */
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    private String salt;
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	private String senhaEncriptada;
	
	public String getSenhaEncriptada() {
		return senhaEncriptada;
	}

	public void setSenhaEncriptada(String senhaEncriptada) {
		this.senhaEncriptada = senhaEncriptada;
	}


}
