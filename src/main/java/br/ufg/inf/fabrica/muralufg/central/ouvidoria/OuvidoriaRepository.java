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

package br.ufg.inf.fabrica.muralufg.central.ouvidoria;

import com.google.api.services.datastore.client.Datastore;

import java.util.Date;
import java.util.List;

/**
 * Manutenção das informações de ouvidoria.
 */
public interface OuvidoriaRepository {


    /**
     * Realiza a conexão com o Google Cloud Datastore.
     * @return Datastore, com a conexão já configurada.
     */
    void conectar();


    /**
     * Acrescenta ao repositório o assunto.
     * @param assunto Assunto a ser inserido no repositório.
     *
     * @return {@code true} se e somente se o assunto foi
     * inserido de forma satisfatória.
     */
    void insere(Assunto assunto);


    /**
     * Recupera os assuntos submetidos à ouvidoria, ainda
     * sem resposta, a partir da data indicada.
     * @param desde Data a partir da qual assuntos serão considerados.
     *
     * @return Lista, em ordem cronológica crescente, do mais antido
     * para o mais recente dos assuntos submetidos à ouvidoria e para
     * os quais não existe resposta. No máximo 100 assuntos são retornados.
     *
     */
    List<Assunto> buscaNaoRespondidos(Date desde);

    /**
     * Recupera os assuntos submetidos à ouvidoria, ainda
     * sem resposta, a partir da data indicada.
     * @param desde Data a partir da qual assuntos serão considerados.
     * @param aPartirDe Ordem do assunto a partir da qual os resultados
     *                  serão produzidos. Ou seja, no máximo, os 100
     *                  assuntos seguintes, conforme esta ordem.
     *
     * @return Lista, em ordem cronológica crescente, do mais antigo
     * para o mais recente dos assuntos submetidos à ouvidoria e para
     * os quais não existe resposta. No máximo 100 assuntos são retornados.
     *
     * @see #naoRespondidos(java.util.Date)
     */
    List<Assunto> buscaNaoRespondidos(Date desde, int aPartirDe);

    /**
     * Recupera os assuntos submetidos à ouvidoria, que já foram respondidos,
     * a partir da data indicada
     * @param desde Data a partir da qual os assuntos serão considerados.
     *
     * @return Lista, em ordem cronológica crescente, do mais antigo
     * para o mais recente dos assuntos submetidos à ouvidoria e para
     * os quais não existe resposta. No máximo 100 assuntos são retornados.
     */
    List<Assunto> buscaRespondidos(Date desde);


    /**
     * Recupera os assuntos submetidos à ouvidoria, que já foram respondidos,
     * a partir da data indicada
     * @param desde Data a partir da qual os assuntos serão considerados.
     * @param aPartirDe Ordem do assunto a partir da qual os resultados
     *                  serão produzidos. Ou seja, no máximo, os 100
     *                  assuntos seguintes, conforme esta ordem.
     *
     * @return Lista, em ordem cronológica crescente, do mais antigo
     * para o mais recente dos assuntos submetidos à ouvidoria e para
     * os quais não existe resposta. No máximo 100 assuntos são retornados.
     */
    List<Assunto> buscaRespondidos(Date desde, int aPartirDe);

    /**
     * Recupera os assuntos submetidos a ouvidoria, que contenham
     * a palavra chave que é passada como parâmetro.
     * @param palavraChave uma palavra chave qualquer.
     *
     * @return Lista, com os Assuntos que contenham a palavra chave informada.
     */
    List<Assunto> buscaPalavraChave(String palavraChave);





}
