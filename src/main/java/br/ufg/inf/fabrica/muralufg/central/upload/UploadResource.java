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

package br.ufg.inf.fabrica.muralufg.central.upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Envio de arquivos para a Central.
 *
 * <p>Para enviar um arquivo para a Central é necessário
 * criar uma "sessão". Esta sessão permite agrupar uma ou
 * mais submissões de arquivos, enviadas independentemente,
 * como se fosse uma única submissão.</p>
 *
 * <p>Caso a sessão não seja fechada, então após transcorrido um
 * período de horas configurável, os arquivos associados à sessão
 * serão removidos.</p>
 *
 * <p>Para submeter um arquivo para a Central é obrigatório
 * obter a sessão (identificador) correspondente. </p>
 */
@Path("/upload")
@Produces(MediaType.APPLICATION_JSON)
public class UploadResource {

    //TODO salvar os ids noutro serviço
    private Set<String> ids = new HashSet<String>();

    /**
     * Cria uma nova "sessão", representada por
     * identificador único.
     *
     * @return Identificador único da sessão a ser
     * empregado para enviar um ou mais arquivos.
     */
    @Path("/iniciar")
    @POST
    public String novaSessao() {
        String novaSessao = UUID.randomUUID().toString();
        ids.add(novaSessao);

        return novaSessao;
    }

    /**
     * Indica que sessão não receberá mais uploads de
     * arquivos.
     */
    @Path("/fechar/{id}")
    @POST
    public Response fechaSessao(@PathParam("id") String sessaoId) {
        if (ids.contains(sessaoId)) {
            ids.remove(sessaoId);
            return Response.ok().build();
        } else {
            return Response.status(404).build();
        }
    }

    /**
     * Submete conteúdo de arquivo associado à sessão.
     *
     * <p>Para enviar via linha de comandos, execute:</p>
     * <p>curl -v -include --form file=@ARQUIVO-AQUI http://localhost:8080/upload</p>
     * @param sessaoId O identificador único da sessão associado ao presente
     *                 <i>upload</i>.
     * @return Indicação se o <i>upload</i> foi realizado
     * de forma satisfatória, ou não.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("sessaoId") String sessaoId,
                           @FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {

        // TODO O diretório fixo abaixo deverá ser obtido da configuração
        // TODO o nome do arquivo não pode ser o nome da sessão.
        java.nio.file.Path outputPath = FileSystems.getDefault().getPath("c:/tmp", sessaoId);
        Files.copy(uploadedInputStream, outputPath);
        return Response.ok().build();
    }
}
