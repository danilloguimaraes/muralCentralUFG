package br.ufg.inf.fabrica.muralufg.central.ouvidoria;

import com.google.api.services.datastore.DatastoreV1;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

/**
 * Created by Paulo on 14/10/2014.
 */

import com.google.api.services.datastore.DatastoreV1.*;
import com.google.api.services.datastore.client.DatastoreException;

import static com.google.api.services.datastore.client.DatastoreHelper.makeKey;
import static com.google.api.services.datastore.client.DatastoreHelper.makeProperty;
import static com.google.api.services.datastore.client.DatastoreHelper.makeValue;

public class ImpOuvidoriaRepository implements OuvidoriaRepository{

    public final String CONTEUDO = "Conteudo";
    public final String DATA = "Data";
    public final String FONTE = "Fonte";
    private Datastore datastore;

    public void conectar(){


        try {
            String datasetId = "";
            datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv()
                    .dataset(datasetId).build());

        } catch (GeneralSecurityException exception) {
            System.err.println("Erro de segurança ao fazer a conexão com o banco de dados: " + exception.getMessage());
            System.exit(1);
        } catch (IOException exception) {
            System.err.println("Erro de E/S ao conectar com o banco de dados: " + exception.getMessage());
            System.exit(1);
        }

    }


    public boolean insere(Assunto assunto){
        try {
            DatastoreV1.Entity.Builder entAssunto = DatastoreV1.Entity.newBuilder();
            entAssunto.setKey(makeKey());
            entAssunto.addProperty(makeProperty(CONTEUDO, makeValue(assunto.getConteudo())));
            entAssunto.addProperty(makeProperty(DATA, makeValue(assunto.getData())));
            entAssunto.addProperty(makeProperty(FONTE, makeValue(assunto.getFonte())));

            DatastoreV1.Mutation.Builder mutation = Mutation.newBuilder();
            mutation.addInsertAutoId(entAssunto);

            CommitRequest req = CommitRequest.newBuilder()
                    .setMutation(mutation)
                    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                    .build();

        }catch (DatastoreException exception){

            System.err.println("Erro ao realizar operações no banco de dados");
            // Log the exception, the name of the method called and the error code.
            System.err.println(String.format("DatastoreException(%s): %s %s",
                    exception.getMessage(),
                    exception.getMethodName(),
                    exception.getCode()));
            System.exit(1);
        }

    }


    public List<Assunto> buscaNaoRespondidos(Date desde) {
        return null;
    }


    public List<Assunto> buscaNaoRespondidos(Date desde, int aPartirDe) {
        return null;
    }


    public List<Assunto> buscaRespondidos(Date desde) {
        return null;
    }


    public List<Assunto> buscaRespondidos(Date desde, int aPartirDe) {
        return null;
    }


    public List<Assunto> buscaPalavraChave(String palavraChave) {
        return null;
    }


}
