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

package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.io.IOException;


/**
 * Serviço de manutenção e verificação de credenciais.
 * <p>Por meio deste serviço é possível armazenar credenciais,
 * ou seja, pares (usuário, senha), de forma segura.
 * </p>
 * <p>O fluxo básico exige que uma credencial seja inicialmente
 * criada, e armazenada, por meio de {@link #insere(String, String)}.
 * </p>
 * <p>Posteriormente, por meio de</p>
 */
public interface CredencialService {
	
    /**
     * Persiste a credencial fornecida.
     * <p>Convém destacar que a estratégia deve assegurar que,
     * mesmo que o armazenamento empregado para persistir a credencial
     * torne-se acessível a quem não deveria, nem a indicação de quais
     * usuários a base se refere, nem tampouco as senhas, poderão ser
     * recuperadas.</p>
     * @param usuario Nome da conta associada à credencial.
     * @param senha Senha da credencial.
     * @return {@code true} se a credencial foi persistida de forma
     * satisfatória ou {@code false}, caso contrário.
     * @throws IOException 
     * @see #autenticar(String, String)
     * @throws java.lang.IllegalArgumentException Caso
     */
    boolean insere(String usuario, String senha) throws IOException;

    /**
     * Autentica a credencial fornecida, ou seja, retorna {@code true}
     * se e somente se o usuário e a senha fornecidos coincide com
     * credencial previamente inserida.
     * @param usuario O nome da credencial.
     * @param senha A senha da credencial.
     * @return {@code true} se e somente se a credencial fornecida
     * coincide com credencial anteriormente inserida por meio do
     * método {@link #insere(String, String)}.
     * @see #insere(String, String)
     */
    boolean autenticar(String usuario, String senha);

    /**
     * Torna inválida a credencial cujo nome é fornecido.
     * @param usuario O nome da credencial que se tornará inválido
     *                após a presente chamada.
     * @see #insere(String, String)
     */
    void invalidar(String usuario);
}
