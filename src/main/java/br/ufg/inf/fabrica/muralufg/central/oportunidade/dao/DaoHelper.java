package br.ufg.inf.fabrica.muralufg.central.oportunidade.dao;

import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;

import java.io.IOException;
import java.security.GeneralSecurityException;
/**
 * 
 * @author Luiz
 * 
 * Essa classe é utilitária para configurações do Google Datastore
 */
public class DaoHelper {
    //Nome do dataset escolhido no momento da configuração local
    private static final String DATASET_ID = "dataset-id";
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
