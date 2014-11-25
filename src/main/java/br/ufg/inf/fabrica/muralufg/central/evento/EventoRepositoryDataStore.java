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
package br.ufg.inf.fabrica.muralufg.central.evento;

import com.google.api.services.datastore.DatastoreV1.CommitRequest;
import com.google.api.services.datastore.DatastoreV1.Entity;
import com.google.api.services.datastore.DatastoreV1.EntityResult;
import com.google.api.services.datastore.DatastoreV1.Filter;
import com.google.api.services.datastore.DatastoreV1.Mutation;
import com.google.api.services.datastore.DatastoreV1.PropertyFilter;
import com.google.api.services.datastore.DatastoreV1.Query;
import com.google.api.services.datastore.DatastoreV1.QueryResultBatch;
import com.google.api.services.datastore.DatastoreV1.RunQueryRequest;
import com.google.api.services.datastore.DatastoreV1.RunQueryResponse;
import com.google.api.services.datastore.DatastoreV1.Value;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;
import static com.google.api.services.datastore.client.DatastoreHelper.*;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Para testar o DAO usando o Google Datastore localmente: 1 - Baixar o arquivo
 * http://storage.googleapis.com/gcd/tools/gcd-v1beta2-rev1-2.1.1.zip 2 -
 * Descompactar o arquivo em uma pasta 3 - Executar na linha de comando:
 * gcd-v1beta2-rev1-2.1.1/gcd.exe create my-dataset
 * gcd-v1beta2-rev1-2.1.1/gcd.sh start --port=8085 my-dataset 4 - Criar duas
 * variáveis de ambiente: DATASTORE_HOST=http://localhost:8085
 * DATASTORE_DATASET=my-dataset Dados utilizados localmente: nome do dataset =
 * my-dataset dataset-id registrado = my-dataset
 */
/**
 * Classe responsável pela persistência dos dados relativos a Evento, que deve
 * ser feita no Google Datastore
 */
public class EventoRepositoryDataStore {

    private final String DATA_INICIO = "data_inicio";
    private final String DATA_FIM = "data_fim";
    private final String DESCRICAO = "descricao";
    private final String NOME_EVENTO = "nome_evento";
    private final String HORA_EVENTO = "hora_evento";
    private Datastore datastore;

    public EventoRepositoryDataStore() {
        try {
            String datasetId = "";
            datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv()
                    .dataset(datasetId).build());
        } catch (GeneralSecurityException exception) {
            System.err.println("Erro de segurança ao conectar com o banco de dados: " + exception.getMessage());
            System.exit(1);
        } catch (IOException exception) {
            System.err.println("Erro ao conectar com o banco de dados: " + exception.getMessage());
            System.exit(1);
        }
    }

    /**
     * Adiciona o evento ao repositório.
     *
     * @param evento O evento a ser adicionado.
     * @return {@code true} se e somente se o evento foi adicionado de forma
     * satisfatória.
     */
    public boolean adiciona(Evento evento) {
        try{
        Entity.Builder entidadeEvento = Entity.newBuilder();
        entidadeEvento.setKey(makeKey());
        entidadeEvento.addProperty(makeProperty(NOME_EVENTO, makeValue(evento.getNomeEvento())));
        entidadeEvento.addProperty(makeProperty(HORA_EVENTO, makeValue(evento.getHoraEvento())));
        entidadeEvento.addProperty(makeProperty(DESCRICAO, makeValue(evento.getDescricao())));
        entidadeEvento.addProperty(makeProperty(DESCRICAO, makeValue(evento.getDescricao())));
        entidadeEvento.addProperty(makeProperty(DATA_INICIO, makeValue(String.valueOf(evento.getDataInicio()))));
        entidadeEvento.addProperty(makeProperty(DATA_FIM, makeValue(String.valueOf(evento.getDataFim()))));

        Mutation.Builder mutation = Mutation.newBuilder();
        mutation.addInsertAutoId(entidadeEvento);

        CommitRequest req = CommitRequest.newBuilder()
                .setMutation(mutation)
                .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                .build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Identifica eventos do repositório cuja realização encontra-se no "raio"
     * de tempo, em dias, com base no dia corrente.
     *
     * @param raioEmDias Número de dias passados e futuros perfazendo um período
     * no qual qualquer realização de evento que há interseção com ele será
     * fornecido na resposta.
     * @return Eventos, em ordem cronológica, cuja realização coincide com o
     * período determinado pela data corrente e o "raio" fornecidos.
     *
     * @see #proximos(java.util.Date, int)
     */
    public List<Evento> filtraEventoPorRaio(int raioEmDias) {
        List<Evento> listaEventosPorRaio = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        
        try{
        //Data corrente do sistema
        Date dataAtual = new Date(System.currentTimeMillis());

        //Filtro para data inicial como data atual do sistema
        Filter dataInicio = makeFilter(
                "data_inicio", PropertyFilter.Operator.LESS_THAN_OR_EQUAL, makeValue(dataAtual)).build();

        //Combinação da data com o raio especificado
        Date dataComRaio = new Date();
        dataComRaio.setDate(dataAtual.getDate() + raioEmDias);

        //Filtro para data dentro do raio especificado
        Filter dataFim = makeFilter("data_fim", PropertyFilter.Operator.GREATER_THAN_OR_EQUAL, makeValue(dataComRaio)).build();

        //Combinação de filtros
        Filter filtroPeriodo = makeFilter(dataInicio, dataFim).build();

        // É usado o Query.Builder para montar a query a query
        Query.Builder query = Query.newBuilder();
        query.addKindBuilder().setName("Evento");
        query.setFilter(filtroPeriodo).build();
        
        // Montar um RunQueryRequest
            RunQueryRequest requisicao = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse resposta = datastore.runQuery(requisicao);
            
            for (EntityResult result : resposta.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String nomeEvento = getString(props.get("nome_evento"));
                String horaEvento = getString(props.get("hora_evento"));
                String descricao = getString(props.get("descricao"));
                String dataInicial = getString(props.get("data_inicio"));
                String dataFinal = getString(props.get("data_fim"));
                DateTime dataInicialFormatted = formatter.parseDateTime(dataInicial);
                DateTime dataFinalFormatted = formatter.parseDateTime(dataFinal);

                listaEventosPorRaio.add(new Evento(nomeEvento, horaEvento, descricao, dataInicialFormatted, dataFinalFormatted));
            }

            if (resposta.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = resposta.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }
            
        }catch (DatastoreException e){
            e.printStackTrace();
        }
        return listaEventosPorRaio;
    }

    /**
     * Identifica eventos cuja realização coincide com o período definido pela
     * data e os dias indicados, tanto anteriores quanto posteriores à data.
     *
     * @param data Data que define período relevante para a busca de eventos.
     * @param raioEmDias Número de dias, tanto anteriores quanto posteriores à
     * data indicada.
     * @return Eventos cuja realização coincide com o período definido pela data
     * e dias anteriores/posteriores fornecidos.
     *
     * @see #adiciona(Evento)
     */
    public List<Evento> filtraEventoPorDataERaio(Date data, int raioEmDias) {
        List<Evento> listaEventosPorDataRaio = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        
        try{
        //Filtro para data inicial como data atual do sistema
        Filter dataInicio = makeFilter(
                "data_inicio", PropertyFilter.Operator.LESS_THAN_OR_EQUAL, makeValue(data)).build();

        //Combinação da data com o raio especificado
        Date dataComRaio = new Date();
        dataComRaio.setDate(data.getDate() + raioEmDias);

        //Filtro para data dentro do raio especificado
        Filter dataFim = makeFilter("data_fim", PropertyFilter.Operator.GREATER_THAN_OR_EQUAL, makeValue(dataComRaio)).build();

        //Combinação de filtros
        Filter filtroPeriodo = makeFilter(dataInicio, dataFim).build();

        // É usado o Query.Builder para montar a query a query
        Query.Builder query = Query.newBuilder();
        query.addKindBuilder().setName("Evento");
        query.setFilter(filtroPeriodo).build();
        
        // Montar um RunQueryRequest
            RunQueryRequest requisicao = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse resposta = datastore.runQuery(requisicao);
            
            for (EntityResult result : resposta.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String nomeEvento = getString(props.get("nome_evento"));
                String horaEvento = getString(props.get("hora_evento"));
                String descricao = getString(props.get("descricao"));
                String dataInicial = getString(props.get("data_inicio"));
                String dataFinal = getString(props.get("data_fim"));
                DateTime dataInicialFormatted = formatter.parseDateTime(dataInicial);
                DateTime dataFinalFormatted = formatter.parseDateTime(dataFinal);

                listaEventosPorDataRaio.add(new Evento(nomeEvento, horaEvento, descricao, dataInicialFormatted, dataFinalFormatted));
            }

            if (resposta.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = resposta.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }
            
        }catch (DatastoreException e){
            e.printStackTrace();
        }
        return listaEventosPorDataRaio;
    }
}
