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
import static org.junit.Assert.assertNotNull;

/**
 * Representa o teste da implementação da estrutura organizacional da UFG.
 */
public class OrganizacaoRepositoryTest {
    private static OrganizacaoRepository repository = null;
    private Turma turma;
    private Orgao orgao;

    /**
     * Construtor padrão da instância de teste da estrutura
     * organizacional da UFG.
     */
    public OrganizacaoRepositoryTest() {
    }

    /**
     * Configura a classe de teste instanciando a classe auxiliar
     * de teste da implementação da estrutura organizacional da UFG.
     */
    @BeforeClass
    public static void setUpClass() {
        repository = new OrganizacaoRepositoryImplTest();
    }

    /**
     * Finaliza os testes da implementação da estrutura
     * organizacional da UFG.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finalizando os testes.");
    }

    /**
     * Configura os testes da implementação da estrutura organizacional
     * da UFG, instanciando Turma e Orgao.
     */
    @Before
    public void setUp() {
        turma = new Turma("1",null,null,null);
        orgao = new Orgao("INF",null,null,null,null);
    }

    /**
     * Testa os dispositivos (ids) dos usuários quando
     * não há uma conexão com o repositório.
     */
    @Test
    public void testDispositivosQuandoNaoHaConexaoComRepositorio(){
        Set<String> dispositivosEsperados = new HashSet<String>();
        dispositivosEsperados.add("854938594398588385398583");
        dispositivosEsperados.add("39290309239032");
        dispositivosEsperados.add("390248284320989");
        dispositivosEsperados.add("38832883823888");
        dispositivosEsperados.add("93093290239902");

        Set<String> dispositivosResultado = repository.dispositivos(turma);
        assertNotNull(dispositivosResultado);
        assertEquals(dispositivosEsperados,dispositivosResultado);
    }

    /**
     * Testa os dispositivos (ids) dos alunos de uma dada turma em questão.
     */
    @Test
    public void testDispositivosAlunos() {
        Set<String> dispositivosEsperados = new HashSet<String>();
        dispositivosEsperados.add("32893892392393");
        dispositivosEsperados.add("32929329392923");
        dispositivosEsperados.add("84884884388348");

        Set<String> dispositivosResultado = repository.dispositivosAlunos(turma);
        assertNotNull(dispositivosResultado);
        assertEquals(dispositivosEsperados,dispositivosResultado);
    }

    /**
     * Testa os alunos de uma dada turma em questão.
     */
    @Test
    public void testAlunos() {
        Set<Aluno> alunosEsperados = new HashSet<>();
        alunosEsperados.add(new Aluno("Pedro","92932"));
        alunosEsperados.add(new Aluno("Felipe","39922"));

        Set<Aluno> alunosRetornados = repository.alunos(turma);
        assertNotNull(alunosRetornados);

        assertEquals(alunosEsperados.size(),alunosRetornados.size());
    }

    /**
     * Testa os docentes de uma dada turma em questão.
     */
    @Test
    public void testDocentesTurma() {
        Set<Docente> docentesEsperados = new HashSet<>();
        Set<Turma> turmas = new HashSet<>();
        turmas.add(turma);
        Docente docente1 = new Docente("1","Marcelo",turmas,orgao);
        Docente docente2 = new Docente("2","Fabio",turmas,orgao);
        docentesEsperados.add(docente1);
        docentesEsperados.add(docente2);

        Set<Docente> docentesResultado = repository.docentes(turma);
        assertNotNull(docentesResultado);

        assertEquals(docentesEsperados.size(), docentesResultado.size());
    }

    /**
     * Testa os dispositivos (ids) dos docentes de um dado curso em questão.
     */
    @Test
    public void testDispositivosDocentesCurso() {
        Set<String> dispositivosDocentesEsperados = new HashSet<>();
        dispositivosDocentesEsperados.add("466647740");
        dispositivosDocentesEsperados.add("585859859");

        Curso curso = new Curso("1","Engenharia de Software",null,null,null);
        Set<String> dispositivosDocentesResultado = repository.docentes(curso);
        assertNotNull(dispositivosDocentesResultado);
        assertEquals(dispositivosDocentesEsperados, dispositivosDocentesResultado);
    }

    /**
     * Testa os dispositivos (ids) dos docentes de um dado órgão em questão.
     */
    @Test
    public void testDispositivosDocentesOrgao() {
        Set<String> dispositivosDocentesEsperados = new HashSet<>();
        dispositivosDocentesEsperados.add("1341324");
        dispositivosDocentesEsperados.add("9984124");
        dispositivosDocentesEsperados.add("8973240");

        Set<String> dispotivosDocentesResultado = repository.docentes(orgao);
        assertNotNull(dispotivosDocentesResultado);
        assertEquals(dispositivosDocentesEsperados, dispotivosDocentesResultado);
    }

    /**
     * Testa os dispositivos (ids) dos técnicos de um dado órgão em questão.
     */
    @Test
    public void testDispositivosTecnicos() {
        Set<String> dispositivosTecnicosEsperados = new HashSet<>();
        dispositivosTecnicosEsperados.add("8347474");
        dispositivosTecnicosEsperados.add("3333344");
        dispositivosTecnicosEsperados.add("2343333");

        Set<String> dispositivosTecnicosResultado = repository.tecnicos(orgao);
        assertNotNull(dispositivosTecnicosResultado);
        assertEquals(dispositivosTecnicosEsperados, dispositivosTecnicosResultado);
    }

    /**
     * Testa as turmas de uma dada disciplina em questão.
     */
    @Test
    public void testTurmas() {
        Set<Turma> turmasEsperadas = new HashSet<>();
        turmasEsperadas.add(turma);
        Disciplina disciplina = new Disciplina("1","Pratica em ES",turmasEsperadas);

        Set<Turma> turmasResultado = repository.turmas(disciplina);
        assertNotNull(turmasResultado);

        assertEquals(turmasEsperadas.size(),turmasResultado.size());
    }

    /**
     * Testa as disciplinas de um dado curso em questão.
     */
    @Test
    public void testDisciplinas() {
        Set<Disciplina> disciplinasEsperadas = new HashSet<>();
        disciplinasEsperadas.add(new Disciplina("1","Algoritmos em Grafos",null));
        disciplinasEsperadas.add(new Disciplina("2","Construção de Software",null));

        Curso curso = new Curso("1","Engenharia de Software",null,null,null);
        Set<Disciplina> disciplinasResultado = repository.disciplinas(curso);
        assertNotNull(disciplinasResultado);

        assertEquals(disciplinasEsperadas.size(),disciplinasResultado.size());
    }

    /**
     * Testa os cursos de um dado órgão em questão.
     */
    @Test
    public void testCursos() {
        Set<Curso> cursosEsperados = new HashSet<>();
        cursosEsperados.add(new Curso("1","Engenharia de Software",null,null,null));
        cursosEsperados.add(new Curso("2","Sistemas de Informação",null,null,null));
        cursosEsperados.add(new Curso("3","Ciencias da Computação",null,null,null));

        Set<Curso> cursosResultado = repository.cursos(orgao);
        assertNotNull(cursosResultado);
        assertEquals(cursosEsperados.size(),cursosResultado.size());
    }
}
