package br.ufg.inf.fabrica.muralufg.central.arquivo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Vinicius on 14/10/2014.
 * Classe responsavel por criar e gerenciar tokens para envio de arquivos.
 */
public class TokenUtil {


    /**
     * Gera um token unico, baseado em UUID.
     * @return Uma String que representa o token gerado.
     */
    public String generateToken()
    {
        String token = UUID.randomUUID().toString();

        return token;
    }

    /**
     * Busca todos os arquivos que possuem uma token em comum.
     * @param token String que representa um token ligado aos arquivos.
     * @return Lista de Arquivos que compartilham o mesmo token e fazem portanto, parte da mesma publicação.
     */
    public List<Arquivo> getArquivosPorToken(String token)
    {
        List<Arquivo> arquivos = new ArrayList<Arquivo>();


        return arquivos;
    }

}
