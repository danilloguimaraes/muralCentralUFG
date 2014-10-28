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
import java.util.Random;

/**
 * Classe de implementação dos metodos da classe abstrata Despertador
 */
public class ImplementaDespertador extends Despertador {

    /**
     * No metodo que inicia o agendador e onde e feita a chamada do metodo que
     * verifica os agendamentos feitos
     *
     * @return
     */
    @Override
    public boolean inicie() {
        boolean saidaDespertador = false;

        if (!ListaAgendamentos.listaAgendamentos.isEmpty()) {
            throw new IllegalArgumentException("Lista de agendamentos vazia crontab não iniciado");
        } else {
            Crontab.agendamentoDespertador();
            System.out.println("Crontab dos agendamentos iniciado com sucesso");
            saidaDespertador = true;
        }
        return saidaDespertador;
    }

    /**
     * Metódo que recebe as informações do agendamento
     * @param identificador
     * @param instante
     * @return
     */
    @Override
    public boolean desperteEm(String identificador, Date instante) {
        boolean saidaDesperta = false;

        Agendamentos obj = new Agendamentos();
        obj.setData(instante);
        obj.setId(identificador);
        PersistenciaDespertador.inserirAgendamento(obj);

        return saidaDesperta;
    }

    /**
     * Metódo que remove o agendamento e verificado na lista onde estão os
     * agendamentos
     *
     * @param identificador
     * @return
     */
    @Override
    public boolean remove(String identificador) {
        boolean saidaRemove = false;
        ArrayList<Agendamentos> lista = PersistenciaDespertador.caregaAgendamentos();
        int tamanhoLista = lista.size();

        if (identificador.equals("")) {
            throw new IllegalArgumentException("Identificador vazio!");
        } else {
            for (int loopLista = 0; loopLista < tamanhoLista; loopLista++) {
                if (identificador.equals(lista.get(loopLista).getId())) {
                    PersistenciaDespertador.deletarAgendamento(lista.get(loopLista));
                    System.out.println("Agendamento deletado com sucesso");
                    saidaRemove = true;
                }
            }
        }
        return saidaRemove;
    }

    /**
     * Neste metodo adicionamos observer interessado em agendamentos
     * @param observador
     * @return
     */
    @Override
    public boolean adicionaObservador(Observer observador) {
        boolean saidaObserver = false;
        /**
         * Aqui peguei um id aleatorio na lista de agendamentos para que o
         * observer seja lincado com um agendamento de seu interesse, essa
         * implementação pode ser alterada aqui fiz assim para ter o agendamento
         * funcionando
         */
        Random rd = new Random();
        String id = ListaAgendamentos.listaAgendamentos.get(rd.nextInt(ListaAgendamentos.listaAgendamentos.size())).getId();

        Observadores obs = new Observadores();
        obs.setObserv(observador);
        obs.setId(id);

        if (observador == null) {
            throw new IllegalArgumentException("Observer passado e nulo");
        } else {
            ListaAgendamentos.listaObservers.add(obs);
            System.out.println("Observer adicionado com sucesso");
            saidaObserver = true;
        }
        return saidaObserver;
    }
}
