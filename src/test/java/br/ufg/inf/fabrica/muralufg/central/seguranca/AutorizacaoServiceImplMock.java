package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.ArrayList;
import java.util.List;

public class AutorizacaoServiceImplMock extends AutorizacaoServiceImpl{
	
	List<Long> idsTurmasPermitidos;
	List<Long> idsCursosPermitidos;
	List<Long> idsInstitutos;
	
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
	}
	

	@Override
	public List<Long> getIdsPermitidosParaEnvioDeMensagem(Long idRemetente) {
		
		List<Long> idsPermitidos = new ArrayList<Long>();
		
		idsPermitidos.addAll(idsCursosPermitidos);
		idsPermitidos.addAll(idsInstitutos);
		idsPermitidos.addAll(idsTurmasPermitidos);
		
		return idsPermitidos;
	}

	@Override
	public boolean isUsuarioPodeGravar(Long idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsuarioPodeCancelar(Long idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

}