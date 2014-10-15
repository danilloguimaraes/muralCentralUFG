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
package br.ufg.inf.fabrica.muralufg.central.biblioteca;

import java.nio.file.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

/**
 * Envio de notificação para a Central.
 *
 * <p>
 * Para submeter uma notificação para a central é necessário ser um usuário
 * autorizado e enviar os dados da requisição dentro do padrão. </p>
 */
@Path("/biblioteca")
@Produces(MediaType.APPLICATION_JSON)
public class BibliotecaResource {

    /**
     * @param Login Login do usuário.
     * @param Password Hash da senha do usuário.
     * @return Retorna em caso de erro o código 550 caso o usuário não seja 
     * autenticado ou não seja a Biblioteca ou retorna 401 caso ele não tenha 
     * autorização para realizar tal ação.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response logarUsuarioBiblioteca(@QueryParam("id1") String Login, @QueryParam("id2") String Password) {
        Response.setnotificacao(new autenticaUsuarioController().autenticarBiblioteca(Login, Password));
        return Response.status();
    }

    /**
     * @param obj Dados da requisição.
     * @return Retorna em caso de erro o código 400, caso a requisição não esteja
     * no padrão definido, ou 503, caso ocorrer um erro ao persistir ou enviar a 
     * requisição.
     */
    @POST
    @Path("/requisicaoNotificacao")
    @Consumes(MediaType.APPLICATION_JSON + "; charset=iso-8859-1")
    @Produces(MediaType.APPLICATION_JSON + "; charset=iso-8859-1")
    public Response persisteRequisicaoNotificacao(requisicao obj) {
        Response.setnotificacao(new enviaRequisicaoController().persisteRequisicaoBiblioteca(obj));
        return Response.status();
    }
}
