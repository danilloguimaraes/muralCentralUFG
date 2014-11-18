package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.mensagem.Mensagem;

/**
 * Classe utilizada para simular o comportamento de retorno de ids para 
 * os testes de autorização.
 * 
 * @author italogustavomirandamelo
 *
 */
public class AutorizacaoServiceImplMock extends AutorizacaoServiceImpl{
	
	List<Long> idsTurmasPermitidos;
	List<Long> idsCursosPermitidos;
	List<Long> idsInstitutos;
	
	//Mock utilizado no teste de autorização de cancelamento.
	Mensagem mensagemCancelar;
	
	Usuario usuarioGravacao;
	
	/**
	 * Cria dados para testes, que serão usados na classe AutorizacaoTest.
	 */
	public AutorizacaoServiceImplMock(){
		
		idsTurmasPermitidos = new ArrayList<Long>();
		idsCursosPermitidos = new ArrayList<Long>();
		idsInstitutos = new ArrayList<Long>();
		
		idsTurmasPermitidos.add(10l);
		idsTurmasPermitidos.add(11l);
		idsTurmasPermitidos.add(12l);
		
		idsCursosPermitidos.add(21l);
		idsCursosPermitidos.add(22l);
		idsCursosPermitidos.add(23l);
		
		idsInstitutos.add(31l);
		idsInstitutos.add(32l);
		idsInstitutos.add(33l);
		
		Usuario usuarioRemetenteMensagemCancelar = new Usuario(1L);
		
		mensagemCancelar = new Mensagem();
		mensagemCancelar.setRemetente(usuarioRemetenteMensagemCancelar);
		
		usuarioGravacao = new Usuario(3L);
	}
	
	
	/**
	 * Retorna ids para serem usados nos testes de unidade de autorização.
	 */
	@Override
	public List<Long> getIdsPermitidosParaEnvioDeMensagem(Long idRemetente) {
		
		List<Long> idsPermitidos = new ArrayList<Long>();
		
		idsPermitidos.addAll(idsCursosPermitidos);
		idsPermitidos.addAll(idsInstitutos);
		idsPermitidos.addAll(idsTurmasPermitidos);
		
		return idsPermitidos;
	}
	
	/**
	 * Retorna true ou false a partir do id do usuário passado tem permissão de alteração ou gravação do item
	 */
	@Override
	public boolean isUsuarioPodeGravar(Long idUsuario) {
		if(usuarioGravacao.getId()!= null && usuarioGravacao.getId() == idUsuario){
			return true;
		}		
		return false;
	}


	public List<Long> getIdsTurmasPermitidos() {
		return idsTurmasPermitidos;
	}


	public void setIdsTurmasPermitidos(List<Long> idsTurmasPermitidos) {
		this.idsTurmasPermitidos = idsTurmasPermitidos;
	}


	public List<Long> getIdsCursosPermitidos() {
		return idsCursosPermitidos;
	}


	public void setIdsCursosPermitidos(List<Long> idsCursosPermitidos) {
		this.idsCursosPermitidos = idsCursosPermitidos;
	}


	public List<Long> getIdsInstitutos() {
		return idsInstitutos;
	}


	public void setIdsInstitutos(List<Long> idsInstitutos) {
		this.idsInstitutos = idsInstitutos;
	}


	public Mensagem getMensagemCancelar() {
		return mensagemCancelar;
	}


	public void setMensagemCancelar(Mensagem mensagemCancelar) {
		this.mensagemCancelar = mensagemCancelar;
	}
}
