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

package br.ufg.inf.fs.muralufg.central.dominio;

import java.util.Date;
import java.util.List;

/**
 * Serviço de acesso a mensagens divulgadas/publicadas no Mural UFG.
 */
public interface MensagemRepository {

    /**
     * Recupera a mensagem cujo identificador é fornecido.
     * @param id O identificador da mensagem a ser recuperada.
     * @return A {@link br.ufg.inf.fs.mural.dominio.Mensagem} cujo
     * identificador é aquele fornecido.
     * @see #getPorPeriodo(java.util.Date, java.util.Date)
     */
    public Mensagem getPorId(String id);

    /**
     * Recupera, em ordem cronologógica, as mensagens recebidas
     * pelo Mural UFG no período fornecido. Toda mensagem retornada
     * tem data de criação contida no período indicado.
     * @param aPartirDe Data a partir da qual, inclusive, mensagens
     *                  serão consideradas.
     * @param fim Data após a qual mensagens recebidas pelo Mural
     *            não serão retornadas pelo presente método.
     * @return Mensagens, em ordem cronológica, cujas datas de criação
     * estão no período indicado.
     * @see #getPorId(String)
     */
    public List<Mensagem> getPorPeriodo(Date aPartirDe, Date fim);
}
