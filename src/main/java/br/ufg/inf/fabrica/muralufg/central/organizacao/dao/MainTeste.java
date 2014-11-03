/*
* Para testar o DAO usando o Google Datastore localmente
*
* 1 - Baixar o arquivo http://storage.googleapis.com/gcd/tools/gcd-v1beta2-rev1-2.1.1.zip
* 2 - Descompactar o arquivo em uma pasta
* 3 - Executar na linha de comando:
*       gcd-v1beta2-rev1-2.1.1/gcd.exe create my-dataset
*       gcd-v1beta2-rev1-2.1.1/gcd.sh start my-dataset
* 4 - Criar duas variáveis de ambiente:
*       DATASTORE_HOST=http://localhost:8080
*       DATASTORE_DATASET=my-dataset
* */

package br.ufg.inf.fabrica.muralufg.central.organizacao.dao;

import br.ufg.inf.fabrica.muralufg.central.organizacao.Aluno;
import br.ufg.inf.fabrica.muralufg.central.organizacao.OrganizacaoRepository;
import br.ufg.inf.fabrica.muralufg.central.organizacao.OrganizacaoRepositoryImpl;
import br.ufg.inf.fabrica.muralufg.central.organizacao.Turma;

import java.util.Set;

public class MainTeste {
    public static void main(String[] args) {
     /*   Aluno aluno1 = new Aluno("Eurismar", "133200");
        Aluno aluno2 = new Aluno("Julliano", "323224");

        AlunoDao dao = new AlunoDao();
        dao.salvar(aluno1);
        dao.salvar(aluno2);*/

        System.out.println("Dispositivos da turma 1");
        Turma turma = new Turma("1",null,null,null);
        OrganizacaoRepository repository = new OrganizacaoRepositoryImpl();
        Set<String> dispositivos =   repository.dispositivosAlunos(turma);
        for (String d: dispositivos) {
            System.out.println(d);
        }

        Turma turma2 = new Turma("2",null,null,null);
        System.out.println("Dispositivos da turma 2");
        dispositivos =   repository.dispositivosAlunos(turma2);
        for (String d: dispositivos) {
            System.out.println(d);
        }

    }
}
