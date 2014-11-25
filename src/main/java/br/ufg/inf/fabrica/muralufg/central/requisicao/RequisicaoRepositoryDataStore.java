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

package br.ufg.inf.fabrica.muralufg.central.requisicao;

import br.ufg.inf.fabrica.muralufg.central.mensagem.Mensagem;
import java.util.Set;
import com.google.api.services.datastore.DatastoreV1.CommitRequest;
import com.google.api.services.datastore.DatastoreV1.Entity;
import com.google.api.services.datastore.DatastoreV1.EntityResult;
import com.google.api.services.datastore.DatastoreV1.Mutation;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/*
 * Baixar o arquivo
 * http://storage.googleapis.com/gcd/tools/gcd-v1beta2-rev1-2.1.1.zip 
 * e configurá-lo para acesso ao database "banco-requisicao".
 */

/**
 * Classe que executa a persistência das requisições de divulgação/alerta no Google Datastore.
 */
public class RequisicaoRepositoryDataStore {

    private Datastore datastore;

    /**
     * Construtor da classe.
     */
    public RequisicaoRepositoryDataStore() {
        try {
            String datasetId = "";
            datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv()
                    .dataset(datasetId).build());
        } catch (GeneralSecurityException exception) {
            System.err.println("Falha de Segurança: " + exception.getMessage());
            System.exit(1);
        } catch (IOException exception) {
            System.err.println("Falha de Conexão com o Banco: " + exception.getMessage());
            System.exit(1);
        }
    }

    /**
     * Método responsável por salvar a requisição no banco.
     * 
     * @param requisicao
     * @return true para êxito, false para falha
     */
    public boolean salvar(Requisicao requisicao) {
        try {
            Entity.Builder entity = Entity.newBuilder();
            entity.setKey(makeKey());
            entity.addProperty(makeProperty("mensagem", makeValue(requisicao.getMensagem().getConteudo())));

            Mutation.Builder mutation = Mutation.newBuilder();
            mutation.addInsertAutoId(entity);

            CommitRequest req = CommitRequest.newBuilder()
                    .setMutation(mutation)
                    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                    .build();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }        
    }

    /**
     * Método responsável por listar todas as requisições de divulgação persistidas.
     * 
     * @return set de requisições
     * @throws DatastoreException 
     */
    public Set<Requisicao> listarTodasRequisicoes() throws DatastoreException {
        try {
            Set<Requisicao> requisicoes = null;

            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Requisicao");

            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse resposta = datastore.runQuery(request);

            for (EntityResult result : resposta.getBatch().getEntityResultList()) {
                Map<String, Value> propriedades = getPropertyMap(result.getEntity());
                String mensagemRequisicao = getString(propriedades.get("mensagem"));

                requisicoes.add(new Requisicao(new Mensagem(null, mensagemRequisicao, null, null), null));
            }

            if (resposta.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = resposta.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

            return requisicoes;

        } catch (DatastoreException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Método responsável por listar todas as requisições de alerta persistidas.
     * 
     * @return set de alertas
     * @throws ParseException 
     */
    public Set<Alerta> listarTodosAlertas() throws ParseException {
        try {
            Set<Alerta> alertas = null;

            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Requisicao");

            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse resposta = datastore.runQuery(request);

            for (EntityResult result : resposta.getBatch().getEntityResultList()) {
                Map<String, Value> propriedades = getPropertyMap(result.getEntity());
                String mensagemRequisicao = getString(propriedades.get("mensagem"));
                String dataAlerta = getString(propriedades.get("data_alerta"));
                
                DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                Date data = (Date)formatter.parse(dataAlerta);
                alertas.add(new Alerta(data, new Mensagem(null, mensagemRequisicao, null, null), null));
            }

            if (resposta.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = resposta.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

            return alertas;

        } catch (DatastoreException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
