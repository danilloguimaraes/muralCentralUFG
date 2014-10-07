package ScheduleQuartz;

import DespertadorQuartz.ImplementaDespertador;
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
