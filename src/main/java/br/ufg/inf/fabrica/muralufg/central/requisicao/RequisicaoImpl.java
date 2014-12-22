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

import com.google.api.services.datastore.client.DatastoreException;
import java.text.ParseException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * Classe que implementa a interface RequisicaoRepository.
 */
public class RequisicaoImpl implements RequisicaoRepository {

    RequisicaoRepositoryDataStore repositoryDataStore = new RequisicaoRepositoryDataStore();

    /**
     * Método responsável por adicionar uma nova requisição de divulgação.
     * 
     * @param requisicao
     * @return true para êxito, false para falha
     */
    @POST
    @Override
    public boolean adicionarRequisicao(Requisicao requisicao) {
        try {
            repositoryDataStore.salvar(requisicao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Método responsável por adicionar uma nova requisição de alerta.
     * 
     * @param alerta 
     * @return true para êxito, false para falha
     */
    @POST
    @Override
    public boolean adicionarAlerta(Alerta alerta) {
        try {
            repositoryDataStore.salvar(alerta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Método responsável por listar todas as requisições de divulgação.
     * 
     * @return set de requisicoes
     */
    @GET
    @Produces("application/json")
    @Override
    public Set<Requisicao> listarTodasRequisicoes() {
        try {
            return repositoryDataStore.listarTodasRequisicoes();
        } catch (DatastoreException ex) {
            Logger.getLogger(RequisicaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Método responsável por listar todas as requisições de alerta.
     * 
     * @return set de alerta
     */
    @GET
    @Produces("application/json")
    @Override
    public Set<Alerta> listarTodosAlertas() {
        try {
            return repositoryDataStore.listarTodosAlertas();
        } catch (ParseException ex) {
            Logger.getLogger(RequisicaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
