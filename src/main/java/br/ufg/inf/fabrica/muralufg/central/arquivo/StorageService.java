package br.ufg.inf.fabrica.muralufg.central.arquivo;

/**
 * Created by Vinicius on 14/10/2014.
 * Classe que encapsula os serviços de nuvem para armazenamento e recuperação de arquivos. *
 */
public class StorageService {

    private String bucketName;

    /**
     * Construtor primario.
     * @param bucketName nome do bucket que a instancia irá tratar.
     */
    public StorageService(String bucketName)
    {
        this.bucketName = bucketName;

    }

    /**
     * Envia arquivo ao Google Cloud Storage
     * @param arquivo arquivo a ser enviado para o google.
     */
    public void salvaArquivo(Arquivo arquivo)
    {

    }

    /**
     * Recupera um arquivo armazenado no google cloud storage, a partir do seu id.
     * @param id String identificador do arquivo que se deseja obter.
     * @return
     */
    public Arquivo recuperaArquivo(String id)
    {
        return null;
    }



    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }



}
