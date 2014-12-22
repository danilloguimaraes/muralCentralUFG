package br.ufg.inf.fabrica.muralufg.central.mensagem;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufg.inf.fabrica.muralufg.central.mensagem.dao.MensagemDAO;

public class MensagemTest extends TestCase {
	String idMensagem = "2e0caf70-744b-11e4-82f8-0800200c9a66";
	MensagemDAO mensagemDAO = new MensagemDAO();
	Mensagem mensagem = null;
	
	@BeforeClass
	public void testSalvarComSucesso(){
		MensagemDAO mensagemDAO = new MensagemDAO();
		Mensagem mensagem1 = new Mensagem("2e0caf70-744b-11e4-82f8-0800200c9a66", "Teste1", mensagemDAO.getDataOntem(), null);
		Mensagem mensagem2 = new Mensagem("067e6162-3b6f-4ae2-a171-2470b63dff00", "Teste2", new Date(), null);
		
		mensagemDAO.salvar(mensagem1);
		mensagemDAO.salvar(mensagem2);
		
		mensagemDAO.getPorId("2e0caf70-744b-11e4-82f8-0800200c9a66");
		mensagemDAO.getPorId("067e6162-3b6f-4ae2-a171-2470b63dff00");
		mensagemDAO.getPorPeriodo(mensagemDAO.getDataOntem(), new Date());
	}
	
	@Test
	public void testGetPorIdComSucesso(){
		mensagem = mensagemDAO.getPorId(idMensagem);
		assertNotNull(mensagem);
		assertEquals(idMensagem, mensagem.getId());
		assertNotNull(mensagem.getConteudo());
		assertNotNull(mensagem.getDataCriacao());
	}
	
	@Test
	public void testGetPorIdFalhaIdInvalido(){
		mensagem = mensagemDAO.getPorId("Id Inválido");
		assertNull(mensagem);
	}
	
	@Test
	public void testGetPorPeriodoComSucesso(){
		List<Mensagem> mensagens = mensagemDAO.getPorPeriodo(mensagemDAO.getDataOntem(), new Date());
		assertNotNull("Não foi encontrado nenhum dado", mensagens);
		assertEquals("A quantidade de mensagens encontras foi"+ mensagens.size() +", porem o esperado era 2",mensagens.size(),2);
	}
	
	@Test
	public void testGetPorPeriodoFalhaDataInicioMaiorDataFim(){
		List<Mensagem> mensagens = mensagemDAO.getPorPeriodo(new Date(), mensagemDAO.getDataOntem());
		assertNotNull(mensagens);
		assertEquals("Erro no método de busca", mensagens.size(),0);
	}
	
	@Test
	public void testGetPorPeriodoComSucessoDataDeUmaMensagem(){
		List<Mensagem> mensagens = mensagemDAO.getPorPeriodo(new Date(), new Date());
		assertEquals("Erro no método de busca", mensagens.size(),1);
		assertEquals("Foi retornado a mensagem errada." ,mensagens.get(0).getId(), "067e6162-3b6f-4ae2-a171-2470b63dff00");
	}
	
}

