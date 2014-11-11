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

package br.ufg.inf.fabrica.muralufg.central.frase;

import java.util.Date;

public class TestesFraseDoDia {
    
	public static void main(String[] args) {
        FraseDoDia frase1 = new FraseDoDia(
        		1,
        		new Date(), 
        		"E assista depois capítulo inédito de Vale a Pena Ver de Novo!", 
        		"Galvão Bueno");
        
        FraseDoDia frase2 = new FraseDoDia(
        		2,
        		new Date(), 
        		"Estou louca para ir a Nova Yorke. Eu sempre quis conhecer a Europa!", 
        		"Carla Perez");

        FraseDoDiaDao dao = new FraseDoDiaDao();
        dao.salvar(frase1);
        dao.salvar(frase2);

    }
}
