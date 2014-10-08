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
package br.ufg.inf.fabrica.muralufg.central.despertador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;

/**
 *
 * Classe de implementação dos metodos da classe abstrata Despertador
 */
public class ImplementaDespertador extends Despertador {

/**
 * No metodo que inicia o agendador e onde e feita a chamada do metodo
 * que verifica os agendamentos feitos 
 * @return 
 */
    @Override
    public boolean inicie() {
        boolean liberaDespertador;

        if (!ListaAgendamentos.listaAgendamentos.isEmpty()) {
            TriggerDespertador trigger = new TriggerDespertador();
            Thread t1 = new Thread(trigger);
            t1.start();
            liberaDespertador = true;
        } else {
            liberaDespertador = false;
        }
        return liberaDespertador;
    }

    @Override
    public String desperteEm(String identificador, Date instante) {
        return null;
    }

    /**
     * Para remover o agendamento e verificado na lista onde estão 
     * os agendamentos comparando o indentificador passado com o identificador
     * unico do agendamento na lista
     * @param identificador
     * @return 
     */
    @Override
    public boolean remove(String identificador) {
        int tamanhoLista = ListaAgendamentos.listaAgendamentos.size();
        boolean saida = true;

        if (identificador.equals("") || identificador == null) {
            throw new IllegalArgumentException("Identificador vazio ou null!");
        } else {
            for (int loopLista = 0; loopLista < tamanhoLista; loopLista++) {
                if (identificador == ListaAgendamentos.listaAgendamentos.get(loopLista).getId()) {
                    ListaAgendamentos.listaAgendamentos.get(loopLista) = null;
                    saida = true;
                } else {
                    saida = false;
                }
            }
        }
        return saida;
    }

    @Override
    public boolean adicionaObservador(Observer observador) {
        return true;
    }

}
