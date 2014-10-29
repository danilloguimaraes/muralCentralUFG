package br.ufg.inf.fabrica.muralufg.central.arquivo;

import org.junit.Assert;
import org.junit.Test;
import org.omg.CORBA.portable.OutputStream;

import javax.activation.MimeType;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;



public class GoogleCloudStorageTest {

    private final Arquivo arquivo = new Arquivo("id-test","text/plain");

    @Test
    public void testPersiste() throws Exception {

        String bucket = "ufg-tests";
        String projectId = "able-decorator-747";
        String applicationName = "mural-ufg-tests";
        String accountId = "";
        String keyPath = "";


        GoogleCloudStorage googleCloudStorage = new GoogleCloudStorage(bucket,projectId,applicationName,accountId,keyPath);


        googleCloudStorage.persiste(arquivo,new FileInputStream(""));

        //TODO descobrir a melhor forma de usar o Assert aqui

    }

    @Test
    public void testRecupera() throws Exception {

        String bucket = "ufg-tests";
        String projectId = "able-decorator-747";
        String applicationName = "mural-ufg-tests";
        String accountId = "";
        String keyPath = "";

        String id = "id-test";
        String mimeType = "text/plain";

        GoogleCloudStorage googleCloudStorage = new GoogleCloudStorage(bucket,projectId,applicationName,accountId,keyPath);

        InputStream inputStream = googleCloudStorage.recupera(arquivo.getId());

        Assert.assertNotNull(inputStream);


    }

    @Test
    public void testConteudo() throws Exception {

    }
}