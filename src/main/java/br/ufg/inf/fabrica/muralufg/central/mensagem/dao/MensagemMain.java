package br.ufg.inf.fabrica.muralufg.central.mensagem.dao;

import java.util.Date;

import br.ufg.inf.fabrica.muralufg.central.mensagem.Mensagem;

public class MensagemMain {
	public static void main(String []args){
		MensagemDAO mensagemDAO = new MensagemDAO();
		Mensagem mensagem1 = new Mensagem("2e0caf70-744b-11e4-82f8-0800200c9a66", "Teste1", mensagemDAO.getDataOntem(), null);
		Mensagem mensagem2 = new Mensagem("067e6162-3b6f-4ae2-a171-2470b63dff00", "Teste2", new Date(), null);
		
		mensagemDAO.salvar(mensagem1);
		mensagemDAO.salvar(mensagem2);
		
		mensagemDAO.getPorId("2e0caf70-744b-11e4-82f8-0800200c9a66");
		mensagemDAO.getPorId("067e6162-3b6f-4ae2-a171-2470b63dff00");
		mensagemDAO.getPorPeriodo(mensagemDAO.getDataOntem(), new Date());
	}
	
	
	
	
}
