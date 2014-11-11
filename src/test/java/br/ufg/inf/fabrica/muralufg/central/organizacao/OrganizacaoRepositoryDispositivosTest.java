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

        Turma turma = new Turma("1",null,null,null);
        Set<String> dispositivosResultado =   repository.dispositivos(turma);

        assertEquals(dispositivosEsperados,dispositivosResultado);
        ass

    }

    @Test
    public void testDispositivosAlunos() {
        Set<String> dispositivosEsperados = new HashSet<String>();
        dispositivosEsperados.add("93093290239902");
        dispositivosEsperados.add("38832883823888");
        dispositivosEsperados.add("390248284320989");
        dispositivosEsperados.add("39290309239032");


        Turma turma = new Turma("1",null,null,null);

        Set<String> dispositivosResultado =   repository.dispositivosAlunos(turma);

        assertEquals(dispositivosEsperados,dispositivosResultado);
    }
    @Test
    public void testAlunos() {
        Turma turma = new Turma("1",null,null,null);
        Set<Aluno> alunosEsperados = new HashSet<>();

        Aluno aluno1 = new Aluno("Alice","123456");

        Set<String> reg1 = new HashSet<String>();
        reg1.add("3293029023");
        aluno1.setRegistrationIds(reg1);


        Aluno aluno2 = new Aluno("Bob","4994393");
        Set<String> reg2 = new HashSet<String>();
        reg2.add("4499393993");
        reg2.add("69593949439");
        reg2.add("39943949999");
        aluno2.setRegistrationIds(reg2);


        alunosEsperados.add(aluno1);
        alunosEsperados.add(aluno2);

        for (Aluno a: alunosEsperados){
            System.out.println(a.getNome() + "-" + a.getMatricula() + "-" + a.getRegistrationIds());
        }

        Set<Aluno> alunosResultado = repository.alunos(turma);
        System.out.println("alunosEsperados: " + alunosEsperados);
        System.out.println("alunosResultado: " + alunosResultado);

//        assertEquals(alunosEsperados.size(), alunosResultado.size());

    }
    public void testDocentes_Turma() {
        Turma turma = new Turma("1",null,null,null);
        Set<Docente> docentesEsperados = new HashSet<>();
        Set<Turma> turmas = new HashSet<>();
        turmas.add(turma);
        Orgao orgao = new Orgao("INF",null,null);
        Docente docente1 = new Docente("Marcelo",turmas,orgao);
        Docente docente2 = new Docente("Fabio",turmas,orgao);
        docentesEsperados.add(docente1);
        docentesEsperados.add(docente2);

        Set<Docente> docentesResultado = repository.docentes(turma);
        assertEquals(docentesEsperados, docentesResultado);


    }

    public void testDocentes_Curso() {

    }

    public void testDocentes_Orgao() {

    }

    public void testTecnicos() {

    }

    public void testTurmas() {

    }

    public void testDisciplinas() {

    }

    public void testCursos() {

    }
}
