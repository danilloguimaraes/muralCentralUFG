package br.ufg.inf.fabrica.muralufg.central.frase;

import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DataStoreFraseDoDia {
    private static final String DATASET_ID = "ufg";
    
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
