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

public class MainTeste {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno();
        aluno1.setMatricula("133200");
        aluno1.setNome("Eurismar");
        Aluno aluno2 = new Aluno();
        aluno2.setMatricula("323224");
        aluno2.setNome("Julliano");

        AlunoDao dao = new AlunoDao();
        dao.salvar(aluno1);
        dao.salvar(aluno2);

    }
}
