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

package br.ufg.inf.fabrica.muralufg.central.organizacao;

import br.ufg.inf.fabrica.muralufg.central.CentralConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Contém a implementação da manutenção das informações sobre a
 * estrutura organizacional da UFG.
 */
public class OrganizacaoRepositoryImpl implements OrganizacaoRepository {

    /**
     * Obtém o JSON via http REST da servidor
     * @param uri A URI do recurso
     * @return recurso no formato JSON
     */
    public String getJsonFromURI(String uri){
        Client c = Client.create();
        WebResource webResource = c.resource(uri);
        return webResource.get(String.class);
    }

    /**
     * Obtém a URI definida no arquivo de configuração
     * @return sequência de caracteres correspondente ao URI dos recursos
     */
    public String getURIRecursos(){
        CentralConfiguration centralConfiguration = new CentralConfiguration();
        return centralConfiguration.getRecursos();
    }

    /**
     * Obtém todos os dispositivos (ids) dos alunos e do
     * docente responsável por uma dada turma.
     * @param turma A turma da qual os ids dos dispositivos serão
     *              obtidos.
     * @return Conjunto de todos os ids disponíveis dos usuários,
     * alunos e docente responsável, da turma em questão.
     */
    @Override
    public Set<String> dispositivos(Turma turma) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/dispositivos/turmaid/" + turma.getId());
        Set<String> dispositivos = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dispositivos = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dispositivos;
    }

    /**
     * Obtém os dispositivos cadastrados (ids) dos alunos da
     * turma.
     * @param turma
     * @return Conjunto dos ids disponíveis dos alunos da turma
     * em questão.
     */
    @Override
    public Set<String> dispositivosAlunos(Turma turma) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/dispositivos_turma/turmaid/" + turma.getId());
        Set<String> dispositivos = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dispositivos = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dispositivos;
    }

    /**
     * Obtém o conjunto de alunos de uma turma.
     * @param turma
     * @return Conjunto dos alunos da turma em questão.
     */
    @Override
    public Set<Aluno> alunos(Turma turma) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/alunos/turmaid/" + turma.getId());
        Set<Aluno> alunos = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            alunos = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    /**
     * Obtém os docentes
     * responsáveis pela turma.
     * @param turma
     * @return Conjunto de docentes responsáveis
     * pela turma em questão.
     */
    @Override
    public Set<Docente> docentes(Turma turma) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/docentes/turmaid/" + turma.getId());
        Set<Docente> docentes = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            docentes = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    /**
     * Obtém os dispositivos cadastrados (ids) dos
     * docentes do curso.
     * @param curso
     * @return Conjunto dos ids dos docentes do curso em questão.
     */
    @Override
    public Set<String> docentes(Curso curso) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/docentes/curso/" + curso.getId());
        Set<String> docentes = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            docentes = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    /**
     * Obtém os dispositivos cadastrados (ids) dos docentes
     * lotados no órgão em questão.
     * @param orgao
     * @return Conjunto dos ids dos docentes do órgão em questão.
     */
    @Override
    public Set<String> docentes(Orgao orgao) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/docentes/orgao/" + orgao.getId());
        Set<String> docentes = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            docentes = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    /**
     * Obtém os dispositivos cadastrados (ids) dos técnicos
     * do órgão.
     * @param orgao
     * @return Conjunto dos ids dos técnicos do órgão em questão.
     */
    @Override
    public Set<String> tecnicos(Orgao orgao) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/tecnicos/orgao/" + orgao.getId());
        Set<String> tecnicos = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            tecnicos = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tecnicos;
    }

    /**
     * Obtém as turmas de uma dada disciplina.
     * @param disciplina
     * @return Conjunto de turmas da disciplina em questão.
     */
    @Override
    public Set<Turma> turmas(Disciplina disciplina) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/turmas/disciplina/" + disciplina.getId());
        Set<Turma> turmas = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            turmas = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return turmas;
    }

    /**
     * Obtém o conjunto de disciplinas de um dado curso.
     * @param curso
     * @return Conjunto de disciplinas do curso em questão.
     */
    @Override
    public Set<Disciplina> disciplinas(Curso curso) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/disciplinas/curso/" + curso.getId());
        Set<Disciplina> disciplinas = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            disciplinas = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    /**
     * Obtém o conjunto de cursos oferecidos por um órgão.
     * @param orgao
     * @return Conjunto de cursos oferecidos pelo órgão em questão.
     */
    @Override
    public Set<Curso> cursos(Orgao orgao) {
        String json = getJsonFromURI(getURIRecursos() + "/resources/cursos/orgao/" + orgao.getId());
        Set<Curso> cursos = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cursos = objectMapper.readValue(json,Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
