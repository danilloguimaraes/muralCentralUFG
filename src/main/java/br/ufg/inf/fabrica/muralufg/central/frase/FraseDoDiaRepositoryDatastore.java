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

package br.ufg.inf.fabrica.muralufg.central.frase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.api.services.datastore.DatastoreV1;
import com.google.api.services.datastore.DatastoreV1.EntityResult;
import com.google.api.services.datastore.DatastoreV1.Filter;
import com.google.api.services.datastore.DatastoreV1.PropertyFilter;
import com.google.api.services.datastore.DatastoreV1.Query;
import com.google.api.services.datastore.DatastoreV1.RunQueryRequest;
import com.google.api.services.datastore.DatastoreV1.RunQueryResponse;
import com.google.api.services.datastore.DatastoreV1.Value;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.protobuf.ByteString;

import static com.google.api.services.datastore.client.DatastoreHelper.*;

public class FraseDoDiaRepositoryDatastore implements FraseDoDiaRepository {

	@Override
	public boolean adiciona(Date data, String frase) {
		return inserirRegistro(data, frase);
	}

	private boolean inserirRegistro(Date data, String frase) {
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
                            //.setId(new Random().nextInt()));
                            .setName(frase));

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
                        .setValue(DatastoreV1.Value.newBuilder().setStringValue(frase)));
                /*entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                        .setName("autor")
                        .setValue(DatastoreV1.Value.newBuilder().setStringValue(fraseDoDia.getAutor())));*/
                if(data != null){
                	entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                            .setName("data")
                            .setValue(DatastoreV1.Value.newBuilder().setTimestampMicrosecondsValue(formataDataSemHora(data).getTime())));
                }else{
                	entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                            .setName("data")
                    		.setValue(DatastoreV1.Value.newBuilder().setTimestampMicrosecondsValue(0)));
                }
                
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
        }
        
        return false;
	}

	@Override
	public boolean adiciona(String frase) {
		return inserirRegistro(null, frase);
	}

	@Override
	public String recupera(Date data) {
		return recuperarFrase(data);
	}

	@Override
	public String recupera() {
		return recuperarFrase(null);
	}

	private String recuperarFrase(Date data) {
		Datastore datastore = DataStoreFraseDoDia.getDataStore();
		
		Query.Builder q = Query.newBuilder();
		q.addKindBuilder().setName("FraseDoDia");
		
		if(data != null){
			Filter idMensagem = makeFilter("data", PropertyFilter.Operator.EQUAL, makeValue(formataDataSemHora(data))).build();
			q.setFilter(idMensagem).build();
		}
		
		RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(q).build();
		RunQueryResponse response = null;
		try {
			response = datastore.runQuery(request);
		} catch (DatastoreException e) {
			e.printStackTrace();
		}
		
		List<String> mensagens = populaLista(response);
		// mensagem = !mensagens.isEmpty() ? mensagens.get(0) : null;
		
		return mensagens.get(0);
	}
	
	private List<String> populaLista(RunQueryResponse response){
		List<String> frases = new ArrayList<>();
		
		for (EntityResult result : response.getBatch().getEntityResultList()) {
		  Map<String, Value> props = getPropertyMap(result.getEntity());
		  //String id = getString(props.get("id_mensagem"));
		  //Long timesLong = getTimestamp(props.get("data_criacao"));
		  String conteudo = getString(props.get("frase"));
		  //mensagem = new Mensagem(id, conteudo, converteLongTimesParaDate(timesLong), null);
		  frases.add(conteudo);
		}
		return frases;
	}
	
	/**
	 * Formata a data recebida retirando a hora.
	 * @param data
	 * @return data Data formatada sem horas.
	 */
	private Date formataDataSemHora(Date data){
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

}