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
package br.ufg.inf.fabrica.muralufg.central.oportunidade.dao;

import br.ufg.inf.fabrica.muralufg.central.oportunidade.Oportunidade;
import br.ufg.inf.fabrica.muralufg.central.organizacao.Aluno;
import java.util.Date;

public class MainTeste {

    public static void main(String[] args) {
//        Oportunidade aluno1 = new Aluno("Eurismar", "133200");
        Oportunidade oportunidade1 = new Oportunidade();
        oportunidade1.setId(1);
        oportunidade1.setDescricao("Oportunidade de Estagio no LabTime.");
        oportunidade1.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidade1.setDataTermino(new Date(System.currentTimeMillis()));
        
        Oportunidade oportunidade2 = new Oportunidade();
        oportunidade2.setId(2);
        oportunidade2.setDescricao("Oportunidade de Pesquisa em Alimentos.");
        oportunidade2.setDataInicio(new Date(System.currentTimeMillis()));
        oportunidade2.setDataTermino(new Date(System.currentTimeMillis()));
        
        
        OportunidadeDAO dao = new OportunidadeDAO();
        dao.adicionar(oportunidade1);
        dao.adicionar(oportunidade2);
    }
}
