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

package br.ufg.inf.fabrica.muralufg.central.evento;

import java.util.Date;
import java.util.List;

/**
 * Manutenção de eventos.
 */
public interface EventoRepository {

    /**
     * Adiciona o evento ao repositório.
     * @param evento O evento a ser adicionado.
     * @return {@code true} se e somente se o evento
     * foi adicionado de forma satisfatória.
     */
    boolean adiciona(Evento evento);

    /**
     * Identifica eventos do repositório cuja realização
     * encontra-se no "raio" de tempo, em dias, com base
     * no dia corrente.
     * @param raioEmDias Número de dias passados e futuros
     *                   perfazendo um período no qual qualquer
     *                   realização de evento que há interseção
     *                   com ele será fornecido na resposta.
     * @return Eventos, em ordem cronológica, cuja realização
     * coincide com o período determinado pela data corrente e
     * o "raio" fornecidos.
     *
     * @see #proximos(java.util.Date, int)
     */
    List<Evento> proximos(int raioEmDias);

    /**
     * Identifica eventos cuja realização coincide com o período
     * definido pela data e os dias indicados, tanto anteriores
     * quanto posteriores à data.
     * @param data Data que define período relevante para a busca de
     *             eventos.
     * @param raioEmDias Número de dias, tanto anteriores quanto
     *                   posteriores à data indicada.
     * @return Eventos cuja realização coincide com o período definido
     * pela data e dias anteriores/posteriores fornecidos.
     *
     * @see #adiciona(Evento)
     */
    List<Evento> proximos(Date data, int raioEmDias);
}
