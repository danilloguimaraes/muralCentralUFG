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
package br.ufg.inf.fabrica.muralufg.central.oportunidade.dao;

import br.ufg.inf.fabrica.muralufg.central.oportunidade.Oportunidade;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import com.google.protobuf.ByteString;
import org.joda.time.DateTime;
import com.google.api.services.datastore.DatastoreV1.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import static com.google.api.services.datastore.client.DatastoreHelper.*;

/*
 * Para testar o DAO usando o Google Datastore localmente
 *
 * 1 - Baixar o arquivo http://storage.googleapis.com/gcd/tools/gcd-v1beta2-rev1-2.1.1.zip
 * 2 - Descompactar o arquivo em uma pasta
 * 3 - Executar na linha de comando:
 *       gcd-v1beta2-rev1-2.1.1/gcd.exe create my-dataset
 *       gcd-v1beta2-rev1-2.1.1/gcd.sh start --port=8085 my-dataset
 * 4 - Criar duas variáveis de ambiente:
 *       DATASTORE_HOST=http://localhost:8085
 *       DATASTORE_DATASET=my-dataset

 Dados utilizados localmente:
 nome do dataset = my-dataset
 dataset-id registrado = my-dataset
 * */
/**
 *
 * @author Luiz Hernique
 * 
 * Classe responsável pela persistência dos dados relativos a
 * Oportunidade, que deve ser feita no Google Datastore
 */
public class OportunidadeDAO {

    public final String DESCRICAO = "descricao";
    public final String DATA_INICIO = "data_inicio";
    public final String DATA_FIM = "data_fim";
    private Datastore datastore;

    public OportunidadeDAO() {
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

    /**
     * Insere uma nova Oportunidade ao banco de dados
     * @param oportunidade 
     */
    public void adicionar(Oportunidade oportunidade) {
        Entity.Builder entOportunidade = Entity.newBuilder();
        entOportunidade.setKey(makeKey());
        entOportunidade.addProperty(makeProperty(DESCRICAO, makeValue(oportunidade.getDescricao())));
        entOportunidade.addProperty(makeProperty(DATA_INICIO, makeValue(String.valueOf(oportunidade.getDataInicio()))));
        entOportunidade.addProperty(makeProperty(DATA_FIM, makeValue(String.valueOf(oportunidade.getDataFim()))));

        Mutation.Builder mutation = Mutation.newBuilder();
        mutation.addInsertAutoId(entOportunidade);

        CommitRequest req = CommitRequest.newBuilder()
                .setMutation(mutation)
                .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                .build();
    }

    /**
     * Identifica, para o instante em que a chamada é realizada, o conjunto de
     * oportunidades vigentes, ou seja, cuja execução está em andamento.
     * @return um Set de Oportunidades vigentes.
     */
    public Set<Oportunidade> buscarOportunidadesVigentes() {
        Set<Oportunidade> oportunidadesVigentes = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

        try {

            //Filtro 1: recupera as oportunidades dentro do período de início
            Filter filtroDataInicioAntesQueHoje = makeFilter(
                    "data_inicio", PropertyFilter.Operator.LESS_THAN_OR_EQUAL, makeValue(new Date(System.currentTimeMillis()))).build();

            //filtro 2: recupera as oportunidades que não foram finalizadas
            Filter filtroDataFimDepoisQueHoje = makeFilter(
                    "data_fim", PropertyFilter.Operator.GREATER_THAN_OR_EQUAL, makeValue(new Date(System.currentTimeMillis()))).build();

            // É usado o método makeCompositeFilter() para combinar os dois filtros
            Filter filtroPeriodoVigente = makeFilter(filtroDataInicioAntesQueHoje, filtroDataFimDepoisQueHoje).build();
            // É usado o Query.Builder para montar a query a query
            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Oportunidade");
            query.setFilter(filtroPeriodoVigente).build();

            // Montar um RunQueryRequest
            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();

            RunQueryResponse response = datastore.runQuery(request);

            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String descricao = getString(props.get("descricao"));
                String dataInicio = getString(props.get("data_inicio"));
                String dataFim = getString(props.get("data_fim"));
                DateTime dataInicioFormatted = formatter.parseDateTime(dataInicio);
                DateTime dataFimFormatted = formatter.parseDateTime(dataFim);

                oportunidadesVigentes.add(new Oportunidade(descricao, dataInicioFormatted, dataFimFormatted));
            }

            if (response.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = response.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

        } catch (DatastoreException e) {
            e.printStackTrace();
        }

        return oportunidadesVigentes;
    }

    /*public boolean adicionar(Oportunidade oportunidade) {
     Datastore datastore = DaoHelper.getDataStore();

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
     .setKind("Oportunidade"));

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
     // Cria as propriedades de entidades
     entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
     .setName("descricao")
     .setValue(DatastoreV1.Value.newBuilder().setStringValue(oportunidade.getDescricao())));
     entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
     .setName("data_inicio")
     .setValue(DatastoreV1.Value.newBuilder().setTimestampMicrosecondsValue(oportunidade.getDataInicio().getMillis())));
     entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
     .setName("data_fim")
     .setValue(DatastoreV1.Value.newBuilder().setTimestampMicrosecondsValue(oportunidade.getDataFim().getMillis())));
     // Cria a entidade
     entity = entityBuilder.build();
     // Insere a entidade na confirmação da mutação de requisição
     creq.getMutationBuilder().addInsert(entity);
     }

     // Execute o RPC Commit de forma síncrona e ignorar a resposta.
     // Aplicar a mutação de inserção se a entidade não foi encontrado e fecha
     // A transação.
     datastore.commit(creq.build());
     return true;
     } catch (DatastoreException e) {
     e.printStackTrace();
     return false;
     }
     }*/
}
