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


package br.ufg.inf.fabrica.muralufg.central.ouvidoria;

import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import com.google.protobuf.ByteString;
import org.joda.time.DateTime;


/**
 * Created by Paulo on 14/10/2014.
 */

import com.google.api.services.datastore.DatastoreV1.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static com.google.api.services.datastore.client.DatastoreHelper.makeKey;
import static com.google.api.services.datastore.client.DatastoreHelper.makeProperty;
import static com.google.api.services.datastore.client.DatastoreHelper.makeValue;
import static com.google.api.services.datastore.client.DatastoreHelper.*;


public class ImpOuvidoriaRepository implements OuvidoriaRepository{

    public final String CONTEUDO = "Conteudo";
    public final String DATA = "Data";
    public final String FONTE = "Fonte";
    private Datastore datastore;

    public ImpOuvidoriaRepository(){


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


    public void insere(Assunto assunto){
        Entity.Builder entAssunto = Entity.newBuilder();
        entAssunto.setKey(makeKey());
        entAssunto.addProperty(makeProperty(CONTEUDO, makeValue(assunto.getConteudo())));
        entAssunto.addProperty(makeProperty(DATA, makeValue(String.valueOf(assunto.getData()))));
        entAssunto.addProperty(makeProperty(FONTE, makeValue(assunto.getFonte())));

        Mutation.Builder mutation = Mutation.newBuilder();
        mutation.addInsertAutoId(entAssunto);

        CommitRequest req = CommitRequest.newBuilder()
                .setMutation(mutation)
                .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                .build();

    }


    public List<Assunto> buscaNaoRespondidos(Date desde) {

        List<Assunto> listaRetorno = new ArrayList<Assunto>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");


        try {


            //Filtro 1: pega mensagens que nao foram respondidas
            Filter filtroNaoRespondido = makeFilter(
                    "respondido", PropertyFilter.Operator.EQUAL, makeValue(false)).build();

            //filtro 2: pega mensagens que possuem a data maior que a data que foi passada
            Filter filtroDepoisDe = makeFilter(
                    "data", PropertyFilter.Operator.GREATER_THAN, makeValue(desde)).build();

            // É usado o método makeCompositeFilter() para combinar os dois filtros
            Filter filtroNaoRespondidoDepoisDe = makeFilter(filtroNaoRespondido, filtroDepoisDe).build();

            // É usado o Query.Builder para montar a query a query
            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Assunto");
            query.setFilter(filtroNaoRespondidoDepoisDe).build();

            // Montar um RunQueryRequest
            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();

            RunQueryResponse response = datastore.runQuery(request);


            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String fonte = getString(props.get("fonte"));
                String conteudo = getString(props.get("conteudo"));
                String data = getString(props.get("data"));
                DateTime dt = formatter.parseDateTime(data);


                listaRetorno.add(new Assunto(conteudo, dt, fonte));

            }

            if (response.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = response.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

            Collections.sort(listaRetorno);
            return listaRetorno;

        } catch (DatastoreException e) {
            e.printStackTrace();
        }

        return listaRetorno;
    }


    public List<Assunto> buscaNaoRespondidos(Date desde, int aPartirDe) {

        List<Assunto> listaAssuntos = new ArrayList<Assunto>();
        List<Assunto> listaRetorno = new ArrayList<Assunto>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");


        try {


            //Filtro 1: pega mensagens que nao foram respondidas
            Filter filtroNaoRespondido = makeFilter(
                    "respondido", PropertyFilter.Operator.EQUAL, makeValue(false)).build();

            //filtro 2: pega mensagens que possuem a data maior que a data que foi passada
            Filter filtroDepoisDe = makeFilter(
                    "data", PropertyFilter.Operator.GREATER_THAN, makeValue(desde)).build();

            // É usado o método makeCompositeFilter() para combinar os dois filtros
            Filter filtroNaoRespondidoDepoisDe = makeFilter(filtroNaoRespondido, filtroDepoisDe).build();

            // É usado o Query.Builder para montar a query a query
            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Assunto");
            query.setFilter(filtroNaoRespondidoDepoisDe).build();

            // Montar um RunQueryRequest
            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();

            RunQueryResponse response = datastore.runQuery(request);


            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String fonte = getString(props.get("fonte"));
                String conteudo = getString(props.get("conteudo"));
                String data = getString(props.get("data"));
                DateTime dt = formatter.parseDateTime(data);


                listaAssuntos.add(new Assunto(conteudo, dt, fonte));

            }

            if (response.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = response.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

            Collections.sort(listaAssuntos);


            for(int i=aPartirDe; i<listaAssuntos.size(); i++){
                listaRetorno.add(listaAssuntos.get(i));
            }



        } catch (DatastoreException e) {
            e.printStackTrace();
        }


        return listaRetorno;


    }


    public List<Assunto> buscaRespondidos(Date desde) {



        List<Assunto> listaRetorno = new ArrayList<Assunto>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");


        try {


            //Filtro 1: pega mensagens que nao foram respondidas
            Filter filtroNaoRespondido = makeFilter(
                    "respondido", PropertyFilter.Operator.EQUAL, makeValue(true)).build();

            //filtro 2: pega mensagens que possuem a data maior que a data que foi passada
            Filter filtroDepoisDe = makeFilter(
                    "data", PropertyFilter.Operator.GREATER_THAN, makeValue(desde)).build();

            // É usado o método makeCompositeFilter() para combinar os dois filtros
            Filter filtroNaoRespondidoDepoisDe = makeFilter(filtroNaoRespondido, filtroDepoisDe).build();

            // É usado o Query.Builder para montar a query a query
            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Assunto");
            query.setFilter(filtroNaoRespondidoDepoisDe).build();

            // Montar um RunQueryRequest
            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();

            RunQueryResponse response = datastore.runQuery(request);


            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String fonte = getString(props.get("fonte"));
                String conteudo = getString(props.get("conteudo"));
                String data = getString(props.get("data"));
                DateTime dt = formatter.parseDateTime(data);


                listaRetorno.add(new Assunto(conteudo, dt, fonte));

            }

            if (response.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = response.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

            Collections.sort(listaRetorno);
            return listaRetorno;

        } catch (DatastoreException e) {
            e.printStackTrace();
        }

        return listaRetorno;
    }


    public List<Assunto> buscaRespondidos(Date desde, int aPartirDe) {
        List<Assunto> listaAssuntos = new ArrayList<Assunto>();
        List<Assunto> listaRetorno = new ArrayList<Assunto>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");


        try {


            //Filtro 1: pega mensagens que nao foram respondidas
            Filter filtroNaoRespondido = makeFilter(
                    "respondido", PropertyFilter.Operator.EQUAL, makeValue(false)).build();

            //filtro 2: pega mensagens que possuem a data maior que a data que foi passada
            Filter filtroDepoisDe = makeFilter(
                    "data", PropertyFilter.Operator.GREATER_THAN, makeValue(desde)).build();

            // É usado o método makeCompositeFilter() para combinar os dois filtros
            Filter filtroNaoRespondidoDepoisDe = makeFilter(filtroNaoRespondido, filtroDepoisDe).build();

            // É usado o Query.Builder para montar a query a query
            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName("Assunto");
            query.setFilter(filtroNaoRespondidoDepoisDe).build();

            // Montar um RunQueryRequest
            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();

            RunQueryResponse response = datastore.runQuery(request);


            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String fonte = getString(props.get("fonte"));
                String conteudo = getString(props.get("conteudo"));
                String data = getString(props.get("data"));
                DateTime dt = formatter.parseDateTime(data);


                listaAssuntos.add(new Assunto(conteudo, dt, fonte));

            }

            if (response.getBatch().getMoreResults() == QueryResultBatch.MoreResultsType.NOT_FINISHED) {
                ByteString endCursor = response.getBatch().getEndCursor();
                query.setStartCursor(endCursor);
            }

            Collections.sort(listaAssuntos);


            for(int i=aPartirDe; i<listaAssuntos.size(); i++){
                listaRetorno.add(listaAssuntos.get(i));
            }



        } catch (DatastoreException e) {
            e.printStackTrace();
        }


        return listaRetorno;
    }




}
