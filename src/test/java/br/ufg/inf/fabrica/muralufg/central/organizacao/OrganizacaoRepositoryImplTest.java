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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe auxiliar de teste da implementação da estrutura organizacional da UFG.
 */
public class OrganizacaoRepositoryImplTest extends OrganizacaoRepositoryImpl {

    /**
     * Obtém o JSON requerido
     * @param uri A URI do recurso
     * @return recurso no formato JSON
     */
    @Override
    public String getJsonFromURI(String uri) {
        if(uri.contains("/resources/dispositivos/turmaid/1")) {
            Set<String> dispositivos = new HashSet<String>();
            dispositivos.add("93093290239902");
            dispositivos.add("38832883823888");
            dispositivos.add("390248284320989");
            dispositivos.add("39290309239032");
            dispositivos.add("854938594398588385398583");
            return getObjectToStrJson(dispositivos);
        }
        else if(uri.contains("/resources/dispositivos_turma/turmaid/1")){
            Set<String> dispositivosAlunos = new HashSet<String>();
            dispositivosAlunos.add("32893892392393");
            dispositivosAlunos.add("32929329392923");
            dispositivosAlunos.add("84884884388348");
            return getObjectToStrJson(dispositivosAlunos);
        }
        else if(uri.contains("/resources/alunos/turmaid/")){
            Set<Aluno> alunos = new HashSet<>();
            alunos.add(new Aluno("Pedro","92932"));
            alunos.add(new Aluno("Felipe","39922"));
            return getObjectToStrJson(alunos);
        }
        else if(uri.contains("/resources/docentes/turmaid/")){
            Turma turma = new Turma("1",null,null,null);
            Set<Docente> docentes = new HashSet<>();
            Set<Turma> turmas = new HashSet<>();
            turmas.add(turma);
            Orgao orgao = new Orgao("INF",null,null,null,null);
            Docente docente1 = new Docente("1","Marcelo",turmas,orgao);
            Docente docente2 = new Docente("2","Fabio",turmas,orgao);
            docentes.add(docente1);
            docentes.add(docente2);
            return getObjectToStrJson(docentes);
        }
        else if(uri.contains("/resources/docentes/curso/")){
            Set<String> dispositivosDocentes = new HashSet<>();
            dispositivosDocentes.add("466647740");
            dispositivosDocentes.add("585859859");
            return getObjectToStrJson(dispositivosDocentes);
        }
        else if(uri.contains("/resources/docentes/orgao/")){
            Set<String> dispositivosDocentesOrgao = new HashSet<>();
            dispositivosDocentesOrgao.add("1341324");
            dispositivosDocentesOrgao.add("9984124");
            dispositivosDocentesOrgao.add("8973240");
            return getObjectToStrJson(dispositivosDocentesOrgao);
        }
        else if(uri.contains("/resources/tecnicos/orgao/")){
            Set<String> tecnicos = new HashSet<>();
            tecnicos.add("8347474");
            tecnicos.add("3333344");
            tecnicos.add("2343333");
            return getObjectToStrJson(tecnicos);
        }
        else if(uri.contains("/resources/turmas/disciplina/")){
            Set<Turma> turmas = new HashSet<>();
            Turma turma = new Turma("1",null,null,null);
            turmas.add(turma);
            return getObjectToStrJson(turmas);
        }
        else if(uri.contains("/resources/disciplinas/curso/")){
            Set<Disciplina> disciplinas = new HashSet<>();
            disciplinas.add(new Disciplina("1","Algoritmos em Grafos",null));
            disciplinas.add(new Disciplina("2","Construção de Software",null));
            return getObjectToStrJson(disciplinas);
        }
        else if(uri.contains("/resources/cursos/orgao/")){
            Set<Curso> cursos = new HashSet<>();
            cursos.add(new Curso("1","Engenharia de Software",null,null,null));
            cursos.add(new Curso("2","Sistemas de Informação",null,null,null));
            cursos.add(new Curso("3","Ciencias da Computação",null,null,null));
            return getObjectToStrJson(cursos);
        }
        else return null;
    }

    /**
     * Obtém a sequência de caracteres JSON do objeto passado como parâmetro.
     * @param object O recurso na forma de objeto
     * @return recurso no formato String JSON
     */
    public String getObjectToStrJson(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Obtém a URI definida para teste
     * @return sequência de caracteres correspondente ao URI dos recursos
     */
    @Override
    public String getURIRecursos() {
     // return super.getURIRecursos();
        return  "http://localhost:8082";
    }
}
