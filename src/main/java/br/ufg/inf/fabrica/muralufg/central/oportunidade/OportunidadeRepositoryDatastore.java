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
package br.ufg.inf.fabrica.muralufg.central.oportunidade;

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
 * Classe responsável pela implementação da persistência dos dados
 * relativos a Oportunidade, feita utilizando o Google Datastore
 */
public class OportunidadeRepositoryDatastore implements OportunidadeRepository {

    public final String DESCRICAO = "descricao";
    public final String DATA_INICIO = "data_inicio";
    public final String DATA_FIM = "data_fim";
    private Datastore datastore;

    /**
     * Contrutor da classe, o qual quando instanciado cria a conexão com o Banco
     * de Dados informado a partir do 'datasetId'
     *
     * @throws br.ufg.inf.fabrica.muralufg.central.oportunidade.CentralException
     * - Lança a exceção caso a operação de conexão com o banco de dados não
     * seja realizada com sucesso
     */
    public OportunidadeRepositoryDatastore() throws CentralException {
        try {
            String datasetId = "";
            datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv()
                    .dataset(datasetId).build());
        } catch (GeneralSecurityException exception) {
            throw new CentralException("Erro de segurança ao fazer a conexão com o banco de dados: " + exception.getMessage());
        } catch (IOException exception) {
            throw new CentralException("Erro de E/S ao conectar com o banco de dados: " + exception.getMessage());
        }
    }

    /**
     * Insere uma nova Oportunidade ao banco de dados
     *
     * @param oportunidade - Oportunidade a ser inserida no banco de dados
     */
    @Override
    public long adicionar(Oportunidade oportunidade) throws CentralException {
        try {
            Entity.Builder entOportunidade = Entity.newBuilder();
            entOportunidade.setKey(makeKey());
            entOportunidade.addProperty(makeProperty(DESCRICAO, makeValue(oportunidade.getDescricao())));
            entOportunidade.addProperty(makeProperty(DATA_INICIO, makeValue(String.valueOf(oportunidade.getDataInicio()))));
            entOportunidade.addProperty(makeProperty(DATA_FIM, makeValue(String.valueOf(oportunidade.getDataFim()))));

            Mutation.Builder mutation = Mutation.newBuilder();
            mutation.addInsertAutoId(entOportunidade);

            CommitRequest request = CommitRequest.newBuilder()
                    .setMutation(mutation)
                    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                    .build();
        } catch (Exception e) {
            throw new CentralException("Erro ao adicionar a oportunidade no banco de dados. Detalhes: "+ e.getMessage());
        }
        return oportunidade.getId();
    }

    /**
     * Identifica, para o instante em que a chamada é realizada, o conjunto de
     * oportunidades vigentes, ou seja, cuja execução está em andamento.
     *
     * @return O conjunto de oportunidades vigentes. Se nenhuma oportunidade
     * estiver vigente, então o conjunto retornado não possui nenhuma
     * oportunidade.
     */
    @Override
    public Set<Oportunidade> vigentes() throws CentralException {
        Set<Oportunidade> oportunidadesVigentes = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

        try {
            //Filtro 1: recupera as oportunidades dentro do período de início
            Filter filtroDataInicioAntesQueHoje = makeFilter(
                    "data_inicio", PropertyFilter.Operator.LESS_THAN_OR_EQUAL, makeValue(new Date(System.currentTimeMillis()))).build();

            //Filtro 2: recupera as oportunidades que não foram finalizadas
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

                oportunidadesVigentes.add(new Oportunidade(descricao, new Date(dataInicioFormatted.getMillis()), new Date(dataFimFormatted.getMillis())));
            }

            if (response.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = response.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }
        } catch (DatastoreException e) {
            throw new CentralException("Erro ao buscar as oportunidades vigentes no banco de dados. Detalhes: " + e.getMessage());
        }

        return oportunidadesVigentes;
    }
}
