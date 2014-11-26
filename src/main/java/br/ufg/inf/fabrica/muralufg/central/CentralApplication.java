package br.ufg.inf.fabrica.muralufg.central;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import br.ufg.inf.fabrica.muralufg.central.identificacao.IdentificacaoHealthCheck;
import br.ufg.inf.fabrica.muralufg.central.identificacao.IdentificacaoResource;
import br.ufg.inf.fabrica.muralufg.central.notificacao.NotificaErro;
import br.ufg.inf.fabrica.muralufg.central.upload.UploadResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Ponto de Entrada da Central. O processamento executado pela Central
 * (componente) inicia-se por esta classe. A configuração é fornecida por outra
 * classe {@link br.ufg.inf.fabrica.muralufg.central.CentralConfiguration}.
 * 
 * @see br.ufg.inf.fabrica.muralufg.central.CentralConfiguration
 */

public class CentralApplication extends Application<CentralConfiguration> {

	/**
	 * Ponto de entrada da aplicação.
	 * 
	 * @param args
	 *            Argumentos fornecidos.
	 * @throws Exception
	 */
	public static void main(String[] args) {
		try {
			new CentralApplication().run(args);
		} catch (Exception e) {
			NotificaErro notificaErro = new NotificaErro();
			notificaErro
					.enviaEmailDeErro("Primeira execução falhou, tentando realizar proxima execução\n"
							+ e.getMessage());
			Queue<Thread> allThreads = new ConcurrentLinkedQueue<Thread>();
			for (Thread t; (t = allThreads.poll()) != null;)
				try {
					t.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			try {
				new CentralApplication().run(args);
			} catch (Exception e1) {
				notificaErro
						.enviaEmailDeErro("Segunda execução falhou, tentando realizar proxima execução\n"
								+ e1.getMessage());
				Queue<Thread> allThreads2 = new ConcurrentLinkedQueue<Thread>();
				for (Thread t; (t = allThreads2.poll()) != null;)
					try {
						t.join();
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
				try {
					new CentralApplication().run(args);
				} catch (Exception e2) {
					notificaErro
							.enviaEmailDeErro("Terceira execução falhou, desligando o sistema.\n"
									+ e2.getMessage());
					Queue<Thread> allThreads3 = new ConcurrentLinkedQueue<Thread>();
					for (Thread t; (t = allThreads3.poll()) != null;)
						try {
							t.join();
						} catch (InterruptedException e3) {
							e1.printStackTrace();
						}
				}
			}

		}
	}

	@Override
	public String getName() {
		return "Central UFG";
	}

	@Override
	public void initialize(Bootstrap<CentralConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(CentralConfiguration configuracao, Environment environment) {

		final IdentificacaoResource versaoResource = new IdentificacaoResource(

				configuracao.getNome(), configuracao.getVersao(),configuracao.getRecursos());




		environment.jersey().register(versaoResource);

		final UploadResource ur = new UploadResource();
		environment.jersey().register(ur);

		final IdentificacaoHealthCheck healthCheck = new IdentificacaoHealthCheck(
				configuracao);
		environment.healthChecks().register("identificacao", healthCheck);
	}
}
