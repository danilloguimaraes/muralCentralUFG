package br.ufg.inf.fabrica.muralufg.central.organizacao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alunoinf on 11/11/14.
 */
public class OrganizacaoRepositoryImplTest extends OrganizacaoRepositoryImpl {
    @Override
    public String getJsonFromURI(String Uri) {
      //  throw new NotImplementedException();

        if(Uri.contains("/resources/dispositivos/turmaid/1")) {
            Set<String> dispositivos = new HashSet<String>();
            dispositivos.add("93093290239902");
            dispositivos.add("38832883823888");
            dispositivos.add("390248284320989");
            dispositivos.add("39290309239032");
            dispositivos.add("854938594398588385398583");
            return getObjectToStrJson(dispositivos);
        }
        else if(Uri.contains("/resources/dispositivos_turma/turmaid/1")){
            Set<String> dispositivosAlunos = new HashSet<String>();
            dispositivosAlunos.add("32893892392393");
            dispositivosAlunos.add("32929329392923");
            dispositivosAlunos.add("84884884388348");
            return getObjectToStrJson(dispositivosAlunos);
        }
        else if(Uri.contains("/resources/alunos/turmaid/")){
            Set<Aluno> alunos = new HashSet<>();
            alunos.add(new Aluno("Pedro","92932"));
            alunos.add(new Aluno("Felipe","39922"));
            return getObjectToStrJson(alunos);
        }
        else if(Uri.contains("/resources/docentes/turmaid/")){
            Turma turma = new Turma("1",null,null,null);
            Set<Docente> docentes = new HashSet<>();
            Set<Turma> turmas = new HashSet<>();
            turmas.add(turma);
            Orgao orgao = new Orgao("INF",null,null);
            Docente docente1 = new Docente("Marcelo",turmas,orgao);
            Docente docente2 = new Docente("Fabio",turmas,orgao);
            docentes.add(docente1);
            docentes.add(docente2);
            return getObjectToStrJson(docentes);
        }
        else if(Uri.contains("/resources/docentes/curso/")){
            Set<String> docentes = new HashSet<>();
            docentes.add("Marcelo");
            docentes.add("Fabio");
            return getObjectToStrJson(docentes);
        }
        else if(Uri.contains("/resources/docentes/orgao/")){
            Set<String> docentes = new HashSet<>();
            docentes.add("Marcelo");
            docentes.add("Fabio");
            docentes.add("Juliano");
            return getObjectToStrJson(docentes);
        }
        else if(Uri.contains("/resources/tecnicos/orgao/")){
            Set<String> tecnicos = new HashSet<>();
            tecnicos.add("Maria");
            tecnicos.add("Joana");
            tecnicos.add("Joaquim");
            return getObjectToStrJson(tecnicos);
        }
        else if(Uri.contains("/resources/turmas/disciplina/")){
            Set<Turma> turmas = new HashSet<>();
            Turma turma = new Turma("1",null,null,null);
            Disciplina disciplina = new Disciplina("Pratica em ES",turma);
            turmas.add(turma);
            return getObjectToStrJson(turmas);
        }
        else if(Uri.contains("/resources/disciplinas/curso/")){
            Set<Disciplina> disciplinas = new HashSet<>();
            disciplinas.add(new Disciplina("Algoritmos em Grafos",null));
            disciplinas.add(new Disciplina("Construção de Software",null));
            return getObjectToStrJson(disciplinas);
        }
        else if(Uri.contains("/resources/cursos/orgao/")){
            Set<Curso> cursos = new HashSet<>();
            cursos.add(new Curso("Engenharia de Software",null,null));
            cursos.add(new Curso("Sistemas de Informação",null,null));
            cursos.add(new Curso("Ciencias da Computação",null,null));
            return getObjectToStrJson(cursos);
        }
        else return null;

    }
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
}
