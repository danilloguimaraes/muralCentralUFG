package br.ufg.inf.fabrica.muralufg.central.arquivo;

import com.google.api.client.util.ByteStreams;
import com.google.api.services.storage.Storage;

import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.stream.Stream;

import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

import java.io.IOException;
import java.nio.channels.Channels;

/**
 * Created by Vinicius on 20/10/2014.
 * Implementa as funções descritas na interface ArquivoRepository, fazendo uso do serviço do google:
 * Gooogle Cloud Storage
 */
public class GoogleCloudStorage implements ArquivoRepository {

    private Properties properties;
    private Storage storage;

    private String bucket;
    private String projectId;
    private String applicationName;
    private String accountId;
    private String keyPath;

    //provedor de serviços para o google cloud storage
    private final GcsService gcsService =
            GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());

    public GoogleCloudStorage(String bucket, String projectId, String applicationName, String accountId, String keyPath) {
        this.bucket = bucket;
        this.projectId = projectId;
        this.applicationName = applicationName;
        this.accountId = accountId;
        this.keyPath = keyPath;
    }

    /**
     * Envia um arquivo ao Google Cloud Storage por meio da Java Client Library
     * @param arquivo Detalhes do arquivo a ser persistido.
     * @param conteudo {@link Stream} do qual o conteúdo do
     */
    @Override
    public void persiste(Arquivo arquivo, InputStream conteudo) {

        //o nome do arquivo é baseado no bucket e no id
        GcsFilename filename = new GcsFilename(bucket,arquivo.getId());

        GcsOutputChannel outputChannel = null;
        OutputStream outputStream;

        try {
            outputChannel = gcsService.createOrReplace(filename,
                    new GcsFileOptions.Builder().mimeType(arquivo.getMimeType()).build());

            outputStream = Channels.newOutputStream(outputChannel);

            //escreve os dados do inputStream no output
            ByteStreams.copy(conteudo,outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public InputStream recupera(String arquivoId) {

        GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(new GcsFilename(bucket,arquivoId), 0, 1024 * 1024);

        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(Channels.newInputStream(readChannel));

            return objectInputStream;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
