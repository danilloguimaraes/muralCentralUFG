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

import com.google.api.client.util.ByteStreams;
import com.google.api.services.storage.Storage;
import com.google.appengine.tools.cloudstorage.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.util.Properties;
import java.util.stream.Stream;

public class ArquivoRepositoryCloudStorage implements ArquivoRepository {

    private String bucket;
    private String projectId;
    private String applicationName;
    private String accountId;
    private String keyPath;

    ArquivoCloudManager cloudManager;

    public ArquivoRepositoryCloudStorage(String bucket, String projectId, String applicationName, String accountId, String keyPath, ArquivoCloudManager cloudManager) {
        this.bucket = bucket;
        this.projectId = projectId;
        this.applicationName = applicationName;
        this.accountId = accountId;
        this.keyPath = keyPath;
        this.cloudManager = cloudManager;
    }

    /**
     * Envia um arquivo ao Google Cloud Storage por meio da Java Client Library.
     *
     * @param arquivo  Detalhes do arquivo a ser persistido.
     * @param conteudo {@link Stream} do qual o conteúdo do
     */
    @Override
    public void persiste(Arquivo arquivo, InputStream conteudo) throws IOException {
        //o nome do arquivo é baseado no bucket e no id
        GcsFilename filename = new GcsFilename(bucket, arquivo.getId());
        cloudManager.sendFile(filename,arquivo.getMimeType(),conteudo);
    }

    /***
     * Recupera um arquivo do GCS, por meio de seu ID.
     * @param arquivoId String identificador do arquivo que se deseja obter.
     * @return InputStream
     * @throws IOException Caso não seja possivel recuperar o arquivo.
     */
    @Override
    public InputStream recupera(String arquivoId) throws IOException {
        GcsFilename fileName = new GcsFilename(bucket,arquivoId);
        return cloudManager.downloadFile(fileName);
    }

    @Override
    public Stream conteudo(String arquivoId) {
        return null;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }
}
