package br.ufg.inf.fabrica.muralufg.central.configuracao.dao;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;

public class UrlBibliotecaDaoHelper {
	private static final String DATASET_ID = "my-dataset";
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
