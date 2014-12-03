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

/**
 * Serviço de autorização para realizar determinada ação,
 * conforme o escopo definido, para um dado usuário.
 */
public interface AutorizacaoService {

    /**
     * Verifica se o usuário possui autorização para realizar
     * a ação para o escopo.
     * <p>Vários serviços são oferecidos pela Central, alguns deles
     * exigem autorização, outros não. Aqueles que exigem autorização
     * incluem, por exemplo, (a) auteração de cardápio de restaurante,
     * (b) requisição de envio de mensagem e outros. Cada um destes
     * serviços distintos é definido por uma ação.</p>
     * <p>Além da ação e do usuário, é necessário definir o escopo
     * para que uma autorização possa ser concedida ou não. Por exemplo,
     * um professor tem como escopo, os alunos para os quais ministra
     * aula, no instante em questão. Ou seja, o professor pode enviar
     * mensagens apenas para um subconjunto dos seus alunos. A tentativa
     * de enviar mensagens para funcionários ou para professores ou mesmo
     * outros alunos da UFG, deverá resultar em autorização negada.</p>
     * @param usuario O usuário que requisita a ação.
     * @param acao A ação requisitada por um usuário.
     * @param escopo O escopo da ação.
     * @return {@code true} se e somente se o usuário está
     * autorizado a requisitar a ação para o escopo indicado.
     */
    boolean autoriza(String usuario, String acao, String escopo);
}
