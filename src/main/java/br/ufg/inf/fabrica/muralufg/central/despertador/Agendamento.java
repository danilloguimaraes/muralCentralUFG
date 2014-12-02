package br.ufg.inf.fabrica.muralufg.central.despertador;
import java.util.Observable;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
public class Agendamento extends Observable {

	private String id;
	private Date data;
	
	private Timer timer = new Timer();

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public Agendamento(Date data, String id) {
		this.data = data;
		
		this.id = id;			
		timer.schedule(contagem(), data);
		
	}
	public void cancelaAgendamento(){
	 timer.cancel();
	 
	}

	private TimerTask contagem() {
		
		
		return new TimerTask() {
			

			public void run() {
				
			
					setChanged();
					notifyObservers();
					cancel();
				
			}

		};
	}
}
