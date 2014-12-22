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

package br.ufg.inf.fabrica.muralufg.central.mensagem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.ufg.inf.fabrica.muralufg.central.seguranca.Usuario;

/**
 * Representa informação veiculada/divulgada pelo
 * Mural UFG.
 */
public class Mensagem {

    /**
     * Identificador único da mensagem.
     */
    private UUID id;

    /**
     * Informação a ser veiculada pela mensagem.
     * Trata-se exclusivamente de texto, embora o
     * texto possa fazer referência a uma URL onde
     * outras informações podem ser associadas.
     */
    private String conteudo;

    /**
     * Data de criação da mensagem. Não necessariamente
     * é a data em que é enviada (submetida). Trata-se
     * da data em que o Mural UFG tem conhecimento da
     * existência da mensagem.
     */
    private Date dataCriacao;

    /**
     * Imagens associadas à mensagem.
     */
    private List<Imagem> imagens;
    
    /**
     * Usuario remetente da mensagem.
     */
    private Usuario remetente;

    /**
     * Cria uma instância de mensagem.
     *
     * @param id          O identificador único da mensagem.
     * @param conteudo    O conteúdo da mensagem.
     * @param dataCriacao A data de criação da mensagem.
     * @param imagens     O conjunto de imagens associadas à mensagem.
     */
    public Mensagem(String id, String conteudo, Date dataCriacao, List<Imagem> imagens) {
        setId(id);
        setConteudo(conteudo);
        setDataCriacao(dataCriacao);
        setImagens(imagens);
    }
    
    /**
     * Construtor padrão.
     */
    public Mensagem(){
    	
    }

    /**
     * Obtém imagens associadas à mensagem.
     *
     * @return Imagens associadas à mensagem.
     */
    public List<Imagem> getImagens() {
        return Collections.unmodifiableList(imagens);
    }

    /**
     * Define as imagens associadas à mensagem.
     * @param imagens Lista de imagens a serem
     *                associadas à mensagem.
     */
    private void setImagens(List<Imagem> imagens) {
        this.imagens = new ArrayList<Imagem>();

        if (imagens == null) {
            return;
        }

        for (Imagem imagem : imagens) {
            adicionaImagem(imagem);
        }
    }

    /**
     * Associa (adiciona) uma imagem à mensagem.
     * Imagem cujo valor é <c>null</c> ou duplicidade não são
     * admitidos.
     *
     * @param imagem Informações sobre a imagem a ser
     *               adicionada (associada) à mensagem.
     */
    private void adicionaImagem(Imagem imagem) {
        if (imagem == null) {
            throw new IllegalArgumentException("imagem é null");
        }

        if (imagens.contains(imagem)) {
            throw new IllegalArgumentException("imagem já adicionada à mensagem");
        }

        imagens.add(imagem);
    }

    /**
     * Obtém o identificador único da mensagem.
     *
     * @return O identificador único (guid) da
     * mensagem.
     * @see #setId(String)
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Define o identificador único (guid) da
     * mensagem.
     *
     * @param id O identificador único da mensagem.
     * @see #getId()
     */
    private void setId(String id) {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException("id é null ou vazio");
        }

        this.id = UUID.fromString(id);
    }

    /**
     * Recupera o conteúdo da mensagem.
     *
     * @return Sequência que é o conteúdo da mensagem.
     * @see #setConteudo(String)
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Define o conteúdo da mensagem.
     *
     * @param conteudo Sequência que define a informação
     *                 ou conteúdo a ser veiculado pela
     *                 mensagem.
     * @see #getConteudo()
     */
    private void setConteudo(String conteudo) {
        if (conteudo == null || "".equals(conteudo)) {
            throw new IllegalArgumentException("conteúdo é null ou vazio");
        }

        this.conteudo = conteudo;
    }

    /**
     * Obtém a data de criação da mensagem.
     *
     * @return Data em que a mensagem é recebida
     * pelo Mural UFG.
     * @see #setDataCriacao(java.util.Date)
     */
    public Date getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Define a data em que a mensagem torna-se
     * conhecida pelo Mural UFG.
     *
     * @param dataCriacao Data em que mensagem
     *                    é recebida pelo Mural UFG.
     * @see #getDataCriacao()
     */
    private void setDataCriacao(Date dataCriacao) {
        if (dataCriacao == null) {
            throw new IllegalArgumentException("data de criação é null");
        }

        if (new Date().before(dataCriacao)) {
            throw new IllegalArgumentException("data de criação no futuro");
        }

        this.dataCriacao = dataCriacao;
    }

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}
}
