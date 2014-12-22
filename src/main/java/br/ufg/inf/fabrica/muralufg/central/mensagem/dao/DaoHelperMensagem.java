package br.ufg.inf.fabrica.muralufg.central.mensagem.dao;

import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DaoHelperMensagem {
    private static final String DATASET_ID = "teste";
    /**
     * Cria uma conexão com o banco Google Data Store e retorna para o solicitante.
     * @return datastore Conexão criada com o banco.
     */
    public static Datastore getDataStore() {
        Datastore datastore = null;
        try {
            //configuração da conexão com o google data store
            datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv().dataset(DATASET_ID).build());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datastore;
    }
}
