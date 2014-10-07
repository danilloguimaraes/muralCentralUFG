/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DespertadorQuartz;

import Agenda.ListaAgendamentos;
import ScheduleQuartz.TriggerDespertador;
import java.util.Date;
import java.util.Observer;

/**
 *
 * @author 3db
 */
public class ImplementaDespertador extends Despertador {

    @Override
    public boolean inicie() {
        boolean liberaDespertador;

        if (!ListaAgendamentos.listaAgendamentos.isEmpty()) {
            TriggerDespertador trigger = new TriggerDespertador();
            trigger.agendamentoDespertador();
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

    @Override
    public boolean remove(String identificador) {
        return true;
    }

    @Override
    public boolean adicionaObservador(Observer observador) {
        return true;
    }

}
