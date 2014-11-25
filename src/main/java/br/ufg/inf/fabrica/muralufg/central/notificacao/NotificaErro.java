package br.ufg.inf.fabrica.muralufg.central.notificacao;

import br.ufg.inf.fabrica.muralufg.central.CentralConfiguration;
import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.SimpleEmail; 

public class NotificaErro {

	public void enviaEmailDeErro(String mensagem){
		CentralConfiguration configuracao = new CentralConfiguration();
		SimpleEmail email = new SimpleEmail(); 
		email.setHostName(configuracao.getSmtp()); // o servidor SMTP para envio do e-mail 
		try {
			email.addTo(configuracao.getEmailTo());//destinatário 
			email.setFrom(configuracao.getEmailFrom()); // remetente 
			email.setSubject("Erro ao inicializar a thread inicial da centralApplication"); // assunto do e-mail 
			email.setMsg(mensagem); //conteudo do e-mail 
			email.send(); //envia o e-mail
		} catch (EmailException e) {
			e.printStackTrace();
		} 
		
	}
}
