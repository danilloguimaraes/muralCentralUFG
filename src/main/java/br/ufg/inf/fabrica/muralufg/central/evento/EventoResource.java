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

import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Implementação da interface EventoRepository
 */
@Path(value = "/evento")
public class EventoResource implements EventoRepository {

    EventoRepositoryDataStore eventoRepositoryDataStore = new EventoRepositoryDataStore();

    @POST
    @Override
    public boolean adiciona(Evento evento) {
        eventoRepositoryDataStore.adiciona(evento);
        return true;
    }

    /*
     Implementação da listagem de eventos da interface EventoRepository.
     @param raioEmDias Número de dias passados e futuros
     perfazendo um período no qual qualquer
     realização de evento que há interseção
     com ele será fornecido na resposta.
     @return Eventos, em ordem cronológica, cuja realização
     coincide com o período determinado pela data corrente e
     o "raio" fornecidos.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Evento> proximos(@PathParam("raio") int raioEmDias) {
        return eventoRepositoryDataStore.filtraEventoPorRaio(raioEmDias);
    }

    /*
     Implementação da listagem de eventos com data e evento a partir da interface EventoRepository.
     @param data Data que define período relevante para a busca de
     eventos.
     @param raioEmDias Número de dias, tanto anteriores quanto
     posteriores à data indicada.
     @return Eventos cuja realização coincide com o período definido
     pela data e dias anteriores/posteriores fornecidos.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Evento> proximos(@PathParam("data") Date data, @PathParam("raio") int raioEmDias) {
        return eventoRepositoryDataStore.filtraEventoPorDataERaio(data, raioEmDias);
    }

}
