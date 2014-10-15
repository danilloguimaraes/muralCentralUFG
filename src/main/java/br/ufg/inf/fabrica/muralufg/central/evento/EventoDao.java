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

import java.util.List;
import br.ufg.inf.fabrica.muralufg.central.evento.Evento;
import com.google.api.services.datastore.DatastoreV1;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.protobuf.ByteString;

public class EventoDao {

	public void salvar(Evento evento) {
		Datastore datastore = DaoHelper.getDataStore();
		DatastoreV1.BeginTransactionRequest.Builder treq = DatastoreV1.BeginTransactionRequest
				.newBuilder();
		try {
			DatastoreV1.BeginTransactionResponse tres = datastore
					.beginTransaction(treq.build());
			ByteString tx = tres.getTransaction();
			DatastoreV1.LookupRequest.Builder lreq = DatastoreV1.LookupRequest
					.newBuilder();
			DatastoreV1.Key.Builder key = DatastoreV1.Key.newBuilder()
					.addPathElement(
							DatastoreV1.Key.PathElement.newBuilder()
									.setKind("Evento").setId(evento.getId()));
			lreq.addKey(key);
			lreq.getReadOptionsBuilder().setTransaction(tx);
			DatastoreV1.LookupResponse lresp = datastore.lookup(lreq.build());
			DatastoreV1.CommitRequest.Builder creq = DatastoreV1.CommitRequest
					.newBuilder();
			creq.setTransaction(tx);
			DatastoreV1.Entity entity;
			if (lresp.getFoundCount() > 0) {
				entity = lresp.getFound(0).getEntity();
			} else {
				DatastoreV1.Entity.Builder entityBuilder = DatastoreV1.Entity
						.newBuilder();
				entityBuilder.setKey(key);
				entityBuilder.addProperty(DatastoreV1.Property
						.newBuilder()
						.setName("id")
						.setValue(DatastoreV1.Value.newBuilder().setIntegerValue(
										evento.getId())));
				entityBuilder.addProperty(DatastoreV1.Property
						.newBuilder()
						.setName("nome")
						.setValue(DatastoreV1.Value.newBuilder().setStringValue(
										evento.getNome())));
				entityBuilder.addProperty(DatastoreV1.Property
						.newBuilder()
						.setName("dataInicio")
						.setValue(DatastoreV1.Value.newBuilder().setStringValue(
										evento.getDataInicio().toString())));
				entityBuilder.addProperty(DatastoreV1.Property
						.newBuilder()
						.setName("dataFim")
						.setValue(DatastoreV1.Value.newBuilder().setStringValue(
										evento.getDataFim().toString())));
				entityBuilder.addProperty(DatastoreV1.Property
						.newBuilder()
						.setName("horaEvento")
						.setValue(DatastoreV1.Value.newBuilder().setStringValue(
										evento.getHoraEvento().toString())));
				entity = entityBuilder.build();
				creq.getMutationBuilder().addInsert(entity);
			}
			datastore.commit(creq.build());
		} catch (DatastoreException e) {
			e.printStackTrace();
		}
	}

	public List<Evento> filtraEventoPorRaio(int raioEmDias) {
		return null;
	}

	public List<Evento> filtraEventoPorDataERaio(int raioEmDias) {
		return null;
	}

}
