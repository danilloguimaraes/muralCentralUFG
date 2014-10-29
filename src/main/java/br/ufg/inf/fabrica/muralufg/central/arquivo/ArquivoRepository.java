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

package br.ufg.inf.fabrica.muralufg.central.arquivo;

import org.omg.CORBA.portable.OutputStream;

import java.io.InputStream;
import java.util.stream.Stream;

/**
 * Serviços para manipulação de arquivos em meio persistente.
 * <p>Esta interface foi projetada para admitir implementação
 * que faz uso do sistema de arquivos de um sistema operacional
 * como o Windows, ou ainda por serviço remoto de armazenamento.</p>
 *
 * <p>Uma implementação desta interface deve admitir um fluxo
 * clássico onde arquivos são persistidos
 * ({@link #persiste(Arquivo, java.util.stream.Stream)}) e
 * recuperados. A recuperação é dividida em duas partes:
 * (a) recuperação de metainformações ({@link #recupera(String)}) e
 * (b) recuperação do conteúdo ({@link #conteudo(String)}).</p>
 *
 * <p>Observe que não há indicação de diretório, <i>bucket</i> ou
 * outro elemento, por exemplo, credencial exigida para acesso aos
 * serviços oferecidos pela interface. Tais itens são dependentes
 * de cada implementação.</p>
 */
public interface ArquivoRepository {

    /**
     * Persiste o conteúdo do arquivo.
     * @param arquivo Detalhes do arquivo a ser persistido.
     * @param conteudo {@link Stream} do qual o conteúdo do
     *                               arquivo poderá ser recuperado.
     */
    public void persiste(Arquivo arquivo, InputStream conteudo);

    /**
     * Recupera metainformações sobre o arquivo cujo identificador único é
     * fornecido.
     * @param arquivoId String identificador do arquivo que se deseja obter.
     * @return Instância de {@link Arquivo} correspondente ao identificador
     * fornecido.
     */
    public InputStream recupera(String arquivoId);

    /**
     * Obtém {@link Stream} para conteúdo do arquivo.
     * @param arquivoId O identificador único do arquivo.
     * @return {@link Stream} para o conteúdo do arquivo.
     */
    public Stream conteudo(String arquivoId);
}
