package br.ufg.inf.fabrica.muralufg.central.contato;

import br.ufg.inf.fabrica.muralufg.central.despertador.ImplementaDespertador;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TarefaDespertador implements Job{
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        ImplementaDespertador despertador = new ImplementaDespertador();
        despertador.desperteEm(null, null);
    }
}
