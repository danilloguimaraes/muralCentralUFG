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

package br.ufg.inf.fabrica.muralufg.central.configuracao;

import br.ufg.inf.fabrica.muralufg.central.configuracao.dao.UrlBibliotecaDaoHelper;
import com.google.api.services.datastore.DatastoreV1;
import com.google.api.services.datastore.DatastoreV1.*;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.protobuf.ByteString;

public class ConfiguracaoResourceUrlBiblioteca implements ConfiguracaoRepository {

	/**
	 * Salva a URL da Biblioteca no banco de dados local da Google Cloud
	 * @param String chave, String valor
	 * @return void
	 */
	public void define(String chave, String valor) {
		Datastore datastore = UrlBibliotecaDaoHelper.getDataStore();

		// Cria uma requisição RPC para iniciar uma nova transação
        BeginTransactionRequest.Builder treq = DatastoreV1.BeginTransactionRequest.newBuilder();

        try {
            //executa o RPC de forma síncrona
            BeginTransactionResponse tres = datastore.beginTransaction(treq.build());
            //obtem o identificador da transação através da resposta
            ByteString tx = tres.getTransaction();
            // Cria uma solicitação de RPC request para pegar entidades por chave.
            LookupRequest.Builder lreq = DatastoreV1.LookupRequest.newBuilder();
            // Defina a chave de entidade com apenas um ` path_element` : nenhum pai .
            Key.Builder key = DatastoreV1.Key.newBuilder().addPathElement(
                    DatastoreV1.Key.PathElement.newBuilder()
                            .setKind("URL")
                            .setName(chave));

            //Adicionar uma chave para a solicitação de pesquisa .
            lreq.addKey(key);
            // Definir a transação , por isso, ter uma cópia consistente da
            // Entidade no momento que a operação começou .
            lreq.getReadOptionsBuilder().setTransaction(tx);

            // Executa o RPC e obtem a resposta.
            LookupResponse lresp = datastore.lookup(lreq.build());
            //Criar uma solicitação de RPC para confirmar a transação .
            CommitRequest.Builder creq = DatastoreV1.CommitRequest.newBuilder();
            //confirma a transação
            creq.setTransaction(tx);

            DatastoreV1.Entity entity;
            if (lresp.getFoundCount() > 0) {
                entity = lresp.getFound(0).getEntity();
            } else {
                // Se nenhuma entidade foi encontrada, cria uma nova.
                Entity.Builder entityBuilder = DatastoreV1.Entity.newBuilder();
                // Define a chave de entidade
                entityBuilder.setKey(key);
                // adiciona duas propriedades de entidades
                entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                        .setName("chave")
                        .setValue(DatastoreV1.Value.newBuilder().setStringValue(chave)));
                entityBuilder.addProperty(DatastoreV1.Property.newBuilder()
                		.setName("valor")
                		.setValue(DatastoreV1.Value.newBuilder().setStringValue(valor)));
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

	/**
	 * Recupera a URL da Biblioteca no banco de dados
	 * @param String chave
	 * @return String  
	 */
	@Override
	public String valor(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

}
