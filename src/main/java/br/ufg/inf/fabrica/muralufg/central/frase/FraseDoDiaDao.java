package br.ufg.inf.fabrica.muralufg.central.frase;

import java.util.List;

import com.google.api.services.datastore.DatastoreV1;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.protobuf.ByteString;

public class FraseDoDiaDao {

    public void salvar(FraseDoDia fraseDoDia) {
        Datastore datastore = DataStoreFraseDoDia.getDataStore();

        // Cria uma requisição RPC para iniciar uma nova transação
        DatastoreV1.BeginTransactionRequest.Builder treq = DatastoreV1.BeginTransactionRequest.newBuilder();

        try {

            //executa o RPC de forma síncrona
            DatastoreV1.BeginTransactionResponse tres = datastore.beginTransaction(treq.build());
            //obtem o identificador da transação através da resposta
            ByteString tx = tres.getTransaction();
            // Cria uma solicitação de RPC request para pegar entidades por chave.
            DatastoreV1.LookupRequest.Builder lreq = DatastoreV1.LookupRequest.newBuilder();
            // Defina a chave de entidade com apenas um ` path_element` : nenhum pai .
            DatastoreV1.Key.Builder key = DatastoreV1.Key.newBuilder().addPathElement(
                    DatastoreV1.Key.PathElement.newBuilder()
                            .setKind("FraseDoDia")
                            .setId(fraseDoDia.getId()));

            //Adicionar uma chave para a solicitação de pesquisa .
            lreq.addKey(key);
            // Definir a transação , por isso, ter uma cópia consistente da
            // Entidade no momento que a operação começou .
            lreq.getReadOptionsBuilder().setTransaction(tx);

            // Executa o RPC e obtem a resposta.
            DatastoreV1.LookupResponse lresp = datastore.lookup(lreq.build());
            //Criar uma solicitação de RPC para confirmar a transação .
            DatastoreV1.CommitRequest.Builder creq = DatastoreV1.CommitRequest.newBuilder();
            //confirma a transação
            creq.setTransaction(tx);


            DatastoreV1.Entity entity;
            if (lresp.getFoundCount() > 0) {
                entity = lresp.getFound(0).getEntity();
            } else {
                // Se nenhuma entidade foi encontrada, cria uma nova.
                DatastoreV1.Entity.Builder entityBuilder = DatastoreV1.Entity.newBuilder();
                // Define a chave de entidade
                entityBuilder.setKey(key);
                // adiciona duas propriedades de entidades
                entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                        .setName("frase")
                        .setValue(DatastoreV1.Value.newBuilder().setStringValue(fraseDoDia.getFrase())));
                entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                        .setName("autor")
                        .setValue(DatastoreV1.Value.newBuilder().setStringValue(fraseDoDia.getAutor())));
                entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                        .setName("data")
                        .setValue(DatastoreV1.Value.newBuilder().setTimestampMicrosecondsValue(fraseDoDia.getData().getTime())));
                // Cria a entidade
                entity = entityBuilder.build();
                // Insere a entidade na confirmação da mutação de requisição
                creq.getMutationBuilder().addInsert(entity);
            }

            // Execute o RPC Commit de forma síncrona e ignorar a resposta.
            // Aplicar a mutação de inserção se a entidade não foi encontrado e fecha
            // A transação.
            datastore.commit(creq.build());

        } catch (DatastoreException e) {
            e.printStackTrace();
        }


    }



    public void alterar(FraseDoDia fraseDoDia) {

    }

    public void delete(FraseDoDia fraseDoDia){

    }
    public List<FraseDoDia> lista(){
        return null;
    }

}
