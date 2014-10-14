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


    public void insere(Assunto assunto){
        Entity.Builder entAssunto = Entity.newBuilder();
        entAssunto.setKey(makeKey());
        entAssunto.addProperty(makeProperty(CONTEUDO, makeValue(assunto.getConteudo())));
        entAssunto.addProperty(makeProperty(DATA, makeValue(assunto.getData())));
        entAssunto.addProperty(makeProperty(FONTE, makeValue(assunto.getFonte())));

        Mutation.Builder mutation = Mutation.newBuilder();
        mutation.addInsertAutoId(entAssunto);

        CommitRequest req = CommitRequest.newBuilder()
                .setMutation(mutation)
                .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                .build();

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
