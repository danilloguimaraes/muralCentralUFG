package ScheduleQuartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class TriggerDespertador implements Runnable{
    
    public void agendamentoDespertador(){
        try{        
            //Obtem a referencia do scheduler
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();
            
            //Criar um jobdetail
            JobDetail job = newJob(TarefaDespertador.class).withIdentity("Despertador", "grupo").build();
            
            //Criar uma crontrigger - de 10 em 10 segundos
            CronTrigger trigger = newTrigger().withIdentity("agendamentoDespertador", "grupo")
                .withSchedule(cronSchedule("0/10 * * * * ?")).build();
            
            //Adicionar o job e cron
            sched.scheduleJob(job, trigger);
            
            //Iniciar o scheduler
            sched.start();
            
        }catch(Exception x){
            System.out.println("Erro ao iniciar crontab: " + x.getMessage());
        }
    }

    @Override
    public void run() {
        agendamentoDespertador();
    }
}