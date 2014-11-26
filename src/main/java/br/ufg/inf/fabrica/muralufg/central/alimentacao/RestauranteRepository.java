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
package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.Date;
import java.util.List;

/**
 * Serviços para acesso a informações sobre restaurantes.
 * @author Fabrica de Software - INF/UFG
 */
public interface RestauranteRepository {

    /**
     * Recupera lista de restaurantes que satisfazem os valores do filtro
     * fornecido.
     *
     * @param filtro Restaurante cujos membros definem semelhanças ou não com
     * outros restaurantes. Restaurantes semelhantes são identificados pelo
     * método
     * @return Lista de restaurantes que satisfazem o filtro.
     */
    List<Restaurante> obtem(Restaurante filtro);

    /**
     * Adiciona um restaurante ao repositório. O identificador único do
     * restaurante é atualizado neste processo.
     *
     * @param restaurante O restaurante a ser adicionado.
     * @return {@code true} se e somente se o restaurante foi adicionado de
     * forma satisfatória.
     * @throws java.lang.IllegalArgumentException Se o argumento fornecido é
     * {@code null}, ou o {@link Restaurante#nome} é {@code null} ou vazio.
     */
    boolean adiciona(Restaurante restaurante);

    /**
     * Remove o restaurante do repositório.
     *
     * @param restaurante O restaurante a ser removido, juntamente com o
     * cardápio correspondente.
     * @return {@code true} se e somente se o restaurante foi removido de forma
     * satisfatória.
     */
    boolean remover(Restaurante restaurante);

    /**
     * Atualiza as informações associadas a um restaurante existente.
     *
     * @param restaurante O restaurante cujas informações serão atualizadas.
     * @return {@code true} se e somente se as informações pertinentes ao
     * restaurante foram atualizadas de forma satisfatória.
     */
    boolean atualizar(Restaurante restaurante);

    /**
     * Adiciona o prato ao restaurante.
     *
     * @param prato O prato a ser adicionado.
     * @param restaurante Restaurante para localização do prato.
     * @return {@code true} se e somente se o prato é adicionado de forma
     * satisfatória ao restaurante.
     */
    boolean adicionaPrato(Prato prato, Restaurante restaurante);

    /**
     * Obtém os pratos disponíveis no restaurante no dia indicado.
     *
     * @param restaurante O restaurante no qual os pratos são servidos.
     * @param dia O dia em que os pratos estão disponíveis.
     * @return A lista de pratos disponíveis no resutarante no dia indicado.
     * Retorna uma lista vazia, sem elementos, caso neste dia o restaurante não
     * sirva nenhum prato.
     */
    List<Prato> obtemPrato(Restaurante restaurante, Date dia);

    /**
     * Obtém a imagem.
     *
     * @param imagemId Identificador único da imagem.
     * @return Vetor de bytes que é o conteúdo da imagem.
     */
    byte[] getImagem(String imagemId);

    /**
     * Obtém o mime-type correspondente ao formato da imagem, por exemplo,
     * {@code image/png} para imagem no formato PNG.
     *
     * @param imagemId O identificador único da imagem.
     * @return O mime-type que identifica o formato da imagem.
     */
    String getMimeType(String imagemId);
}
