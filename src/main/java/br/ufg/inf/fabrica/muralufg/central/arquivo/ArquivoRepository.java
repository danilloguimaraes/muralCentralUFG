package br.ufg.inf.fabrica.muralufg.central.arquivo;

import java.util.stream.Stream;

/**
 * Serviços para manipulação de arquivos em meio persistente.
 */
public interface ArquivoRepository {

    /**
     * Persiste o arquivo.
     * @param arquivo Detalhes do arquivo a ser persistido.
     */
    public void persiste(Arquivo arquivo);

    /**
     * Recupera metainformações sobre o arquivo cujo identificador único é
     * fornecido.
     * @param arquivoId String identificador do arquivo que se deseja obter.
     * @return Instância de {@link Arquivo} correspondente ao identificador
     * fornecido.
     */
    public Arquivo recupera(String arquivoId);

    /**
     * Obtém {@link Stream} para conteúdo do arquivo.
     * @param arquivoId O identificador único do arquivo.
     * @return {@link Stream} para o conteúdo do arquivo.
     */
    public Stream conteudo(String arquivoId);
}
