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

import org.junit.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;



public class OrganizacaoRepositoryDispositivosTest {
    static OrganizacaoRepository repository = null;
    private Turma turma;
    private Orgao orgao;
    public OrganizacaoRepositoryDispositivosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        repository = new OrganizacaoRepositoryImplTest();
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finalizando os testes");
    }

    @Before
    public void setUp() {
        turma = new Turma("1",null,null,null);
        orgao = new Orgao("INF",null,null);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testDispositivosQuandoNaoHaConexaoComRepositorio(){
        Set<String> dispositivosEsperados = new HashSet<String>();
        dispositivosEsperados.add("854938594398588385398583");
        dispositivosEsperados.add("39290309239032");
        dispositivosEsperados.add("390248284320989");
        dispositivosEsperados.add("38832883823888");
        dispositivosEsperados.add("93093290239902");


        Set<String> dispositivosResultado =   repository.dispositivos(turma);

        assertEquals(dispositivosEsperados,dispositivosResultado);

    }

    @Test
    public void testDispositivosAlunos() {
        Set<String> dispositivosEsperados = new HashSet<String>();
        dispositivosEsperados.add("32893892392393");
        dispositivosEsperados.add("32929329392923");
        dispositivosEsperados.add("84884884388348");
        Set<String> dispositivosResultado =   repository.dispositivosAlunos(turma);
        assertEquals(dispositivosEsperados,dispositivosResultado);
    }
    @Test
    public void testAlunos() {
      Set<Aluno> alunosEsperados = new HashSet<>();
      alunosEsperados.add(new Aluno("Pedro","92932"));
      alunosEsperados.add(new Aluno("Felipe","39922"));
      Set<Aluno> alunosRetornados = repository.alunos(turma);

      assertEquals(alunosEsperados.size(),alunosRetornados.size());
     // assertTrue(alunosEsperados.equals(alunosRetornados));
    }
    @Test
    public void testDocentes_Turma() {
        Set<Docente> docentesEsperados = new HashSet<>();
        Set<Turma> turmas = new HashSet<>();
        turmas.add(turma);
        Orgao orgao = new Orgao("INF",null,null);
        Docente docente1 = new Docente("Marcelo",turmas,orgao);
        Docente docente2 = new Docente("Fabio",turmas,orgao);
        docentesEsperados.add(docente1);
        docentesEsperados.add(docente2);

        Set<Docente> docentesResultado = repository.docentes(turma);
        assertEquals(docentesEsperados.size(), docentesResultado.size());
    }
    @Test
    public void testDocentes_Curso() {
        Set<String> docentesEsperados = new HashSet<>();

        docentesEsperados.add("Marcelo");
        docentesEsperados.add("Fabio");

       Curso curso = new Curso("Engenharia de Software",null,null);
       Set<String> docentesResultado = repository.docentes(curso);
       assertEquals(docentesEsperados, docentesResultado);
    }

    @Test
    public void testDocentes_Orgao() {
        Set<String> docentesEsperados = new HashSet<>();
        docentesEsperados.add("Marcelo");
        docentesEsperados.add("Fabio");
        docentesEsperados.add("Juliano");
        Set<String> docentesResultado = repository.docentes(orgao);
        assertEquals(docentesEsperados, docentesResultado);
    }
    @Test
    public void testTecnicos() {
        Set<String> tecnicosEsperados = new HashSet<>();
        tecnicosEsperados.add("Maria");
        tecnicosEsperados.add("Joana");
        tecnicosEsperados.add("Joaquim");
        Set<String> tecnicosResultado = repository.tecnicos(orgao);
        assertEquals(tecnicosEsperados, tecnicosResultado);

    }
    @Test
    public void testTurmas() {
        Set<Turma> turmasEsperadas = new HashSet<>();
        turmasEsperadas.add(turma);
        Disciplina disciplina = new Disciplina("Pratica em ES",turma);
        Set<Turma> turmasResultado = repository.turmas(disciplina);
        assertEquals(turmasEsperadas.size(),turmasResultado.size());
    }
    @Test
    public void testDisciplinas() {
        Set<Disciplina> disciplinasEsperadas = new HashSet<>();
        disciplinasEsperadas.add(new Disciplina("Algoritmos em Grafos",null));
        disciplinasEsperadas.add(new Disciplina("Construção de Software",null));
        Curso curso = new Curso("Engenharia de Software",null,null);
        Set<Disciplina> disciplinasResultado = repository.disciplinas(curso);
        assertEquals(disciplinasEsperadas.size(),disciplinasResultado.size());
    }
    @Test
    public void testCursos() {
        Set<Curso> cursosEsperados = new HashSet<>();
        cursosEsperados.add(new Curso("Engenharia de Software",null,null));
        cursosEsperados.add(new Curso("Sistemas de Informação",null,null));
        cursosEsperados.add(new Curso("Ciencias da Computação",null,null));
        Set<Curso> cursosResultado = repository.cursos(orgao);
        assertEquals(cursosEsperados.size(),cursosResultado.size());
    }
}
