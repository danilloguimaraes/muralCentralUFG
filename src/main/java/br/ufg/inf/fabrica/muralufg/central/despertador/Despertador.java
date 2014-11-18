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

package br.ufg.inf.fabrica.muralufg.central.despertador;

import java.util.Date;
import java.util.Observer;

/**
 * Oferece serviço para "despertar" em instantes de tempo
 * agendados previamente.
 *
 * <p>O despertador recebe requisições de agendamento e as
 * sinaliza no instante de tempo correspondente. Um agendamento
 * representa a "intenção" de ser avisado no instante de tempo
 * em questão. Um agendamento inclui um instante de tempo e um
 * identificador único.
 * </p>
 * <p>Quando o instante de tempo correspondente a um agendamento
 * é atingido, o despertador sinaliza esta ocorrência, neste
 * instante de tempo. O identificador único do agendamento
 * é fornecida ao sinalizar a ocorrência.</p>
 * <p>A sinalização é enviada para observadores.
 * ({@link java.util.Observer}). Todos os observadores cadastrados
 * receberão a sinalização de um agendamento, via método
 * {@link Observer#update(java.util.Observable, Object)} no qual o
 * primeiro argumento será {@code null} e o segundo argumento será
 * o identificador único do agendamento).</p>
 * <p>Ao enviar a mensagem indicada acima, o despertador considera
 * sua tarefa realizada de forma satisfatória.</p>
 */
public abstract class Despertador {

    /**
     * Requisita o início das operações do despertador.
     * <p>Observe que, após interrupção de operação, os interessados ou
     * observadores deverão novamente se cadastrar junto ao despertador
     * ou, caso contrário, não receberão a sinalização que aguardam.
     * Este método permite que o início das ações do despertador
     * sejam postergadas até que interessados tenham tido a
     * oportunidade necessária para registrar interesse nos serviços
     * do despertador.</p>
     * @return
     */
    public abstract boolean inicie();

    /**
     * Agenda o despertador para enviar a mensagem {@link Runnable#run()}
     * ao objeto fornecido no instante de tempo indicado.
     * @param identificador Identificação única do agendamento.
     * @param instante Instante de tempo em que o despertador deverá
     *                 enviar a mensagem ao objeto fornecido.
     * @return O valor {@code true} caso o agendamento foi feito de forma
     * satisfatória ou o valor {@code false}, caso contrário.
     * @throws java.lang.IllegalArgumentException se o identificador fornecido
     * ou o instante fornecido seja {@code null}. Esta exceção também será
     * gerada se o instante de tempo fornecido for anterior ou igual ao
     * instante corrente.
     * @see #remove(String)
     */
    public abstract boolean desperteEm(String identificador, Date instante);

    /**
     * Remove um agendamento prévio.
     * @param identificador O identificador único do agendamento a ser
     *                      removido. Este identificador é obtido do
     *                      retorno do método
     *                      {@link #desperteEm(String, java.util.Date)}.
     * @return {@code true} se o agendamento foi removido de forma satisfatória
     * ou {@code false}, caso contrário.
     * @throws java.lang.IllegalArgumentException Se o {@code identificador} for
     * {@code null} ou vazio.
     * @see #desperteEm(String, java.util.Date)
     */
    public abstract boolean remove(String identificador);

    /**
     * Cadastra um {@link Observer} para receber sinalizações de ocorrências
     * de agendamentos.
     * <p>Quando o instante de tempo correspondente a um agendamento for
     * atingido, o método {@link Observer#update(java.util.Observable, Object)}
     * será chamado, ondo o primeiro argumento será {@code null} e o segundo
     * o identificador do agendamento em questão. Observe que esta mensagem
     * será enviada para todos os observadores cadastrados.</p>
     * @param observador Objeto que será avisado da ocorrência de um
     *                   agendamento.
     * @return
     */
    public abstract boolean adicionaObservador(Observer observador);

    /**
     * Obtém instância de {@link Despertador}. Este objeto é um
     * <i>singleton</i>.
     * @return Objeto que implementa {@link Despertador}.
     */
    public static Despertador getInstance() {
        return null;
    }
}
