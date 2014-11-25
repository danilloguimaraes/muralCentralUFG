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

import com.google.appengine.tools.cloudstorage.GcsFilename;
import java.io.IOException;
import java.io.InputStream;

/**
 * Gerenciamento de arquivos armazenados na nuvem do google.
 * (Google Cloud Storage)
 *
 * Classe que deve fazer a manipulação direta dos arquivo,
 * envio e recuperação dos mesmos.
 * As classes que implementarem esta interface, devem
 * encapsular as rotinas de envio e download de arquivo.
 */
public interface ArquivoCloudManager {

    /**
     * Envia um arquivo para
     * @param gcsFilename nome do arquivo seguindo os padrões do GCS.
     * @param mimeType mime type do arquivo a ser enviado.
     * @throws IOException caso ocorra algum erro na transação.
     */
    public void sendFile(GcsFilename gcsFilename, String mimeType, InputStream conteudo) throws IOException;

    /**
     * Executa dowload de um arquivo no GCS.
     * @param gcsFilename Nome do arquivo a ser baixado.
     * @return InputStream com o conteúdo do arquivo.
     * @throws IOException Caso o arquivo não seja encontrado ou algum outro problema seja detectado.
     */
    public InputStream downloadFile(GcsFilename gcsFilename) throws IOException;

}
