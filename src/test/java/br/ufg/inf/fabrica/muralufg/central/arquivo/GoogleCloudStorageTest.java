package br.ufg.inf.fabrica.muralufg.central.arquivo;

import com.google.appengine.tools.cloudstorage.GcsFilename;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.mockito.Mockito.*;

public class GoogleCloudStorageTest {

    private ArquivoRepository arquivoRepository;
    private String bucket = "ufg-tests";
    private String projectId = "able-decorator-747";
    private String applicationName = "mural-ufg-tests";
    private String accountId = "";
    private String keyPath = "";

    @Mock
    private ArquivoCloudManager cloudManager;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        arquivoRepository = new ArquivoRepositoryCloudStorage(bucket, projectId, applicationName, accountId, keyPath, cloudManager);
    }

    @Test
    public void testaSeEnviaArquivo() throws IOException {

        Arquivo arquivo = new Arquivo("id-arquivo", "plain/text");
        InputStream conteudo = criaArquivoParaTest();
        GcsFilename filename = new GcsFilename(bucket,arquivo.getId());
        arquivoRepository.persiste(arquivo, conteudo);

        verify(cloudManager).sendFile(filename,arquivo.getMimeType(),conteudo);
    }

    private InputStream criaArquivoParaTest() throws IOException {
        File file = new File("Arquivodetest.txt");
        InputStream conteudo = null;
        file.createNewFile();
        conteudo = new FileInputStream(file);

        return conteudo;
    }

    @Test
    public void testRecupera() throws Exception {

        String idArquivo = "id-arquivo";
        GcsFilename filename = new GcsFilename(bucket,idArquivo);
        when(cloudManager.downloadFile(filename)).thenReturn(criaArquivoParaTest());

        InputStream inputStream = arquivoRepository.recupera(idArquivo);
        Assert.assertNotNull("O Arquivo retornado é nulo",inputStream);

        verify(cloudManager).downloadFile(filename);
    }
}