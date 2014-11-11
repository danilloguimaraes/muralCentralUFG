package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.ArrayList;
import java.util.List;

public class AutorizacaoServiceImpl implements AutorizacaoService {

	
	@Override
	public boolean autoriza(String usuario, String acao, String escopo) {

		if (acao.equals(AcaoEnum.ENVIAR_MENSAGEM.toString()) && escopo != null) {
			return autorizarEnvio(usuario, escopo);
		}
		
		if (acao.equals(AcaoEnum.CANCELAR_MENSAGEM.toString())){
			return autorizaCancelarMensagem(new Long(usuario));
		}
		
		if (acao.equals(AcaoEnum.GRAVA_REGISTRO.toString())){
			return autorizaGravarMensagem(new Long(usuario));
		}

		return false;
	}
	

	/**
	 * 
	 * @param idRemetente Id do usuário que pretende enviar a mensagem.
	 * @param idsDestinatariosString
	 *            String definindo os destinarios para quais a mensagem será
	 *            enviada, podendo ser id de um curso, turma, aluno, instituto, etc.
	 *            Os ids devem vir separados por virgula. (Ex.: "34,35,36").
	 * @return
	 * 
	 */
	public boolean autorizarEnvio(String idRemetenteString, String idsDestinatariosString){
		
		boolean autorizado = true;
		
		if(!verificarSeIdsForamInformados(idRemetenteString, idsDestinatariosString)){
			return false;
		}
		
		try{
		
			Long idRemetente = new Long(idRemetenteString);
			
			List<Long> idsPermitidosParaEnvioDeMensagem = getIdsPermitidosParaEnvioDeMensagem(idRemetente);
		
			List<Long> idsDestinatarios = new ArrayList<Long>();
			
			if(!idsDestinatariosString.equals("")){
				for(String idDestinatario : idsDestinatariosString.split(",")){
					idsDestinatarios.add(new Long(idDestinatario));
				}
			}
			
			autorizado = idsPermitidosParaEnvioDeMensagem.containsAll(idsDestinatarios);
		
		}catch(NumberFormatException numberFormatException){
			
			return false;
		}
		
		return autorizado;
	}


	/**
	 * Verifica se os ids do remetente e do destinatário foram informados,
	 * ou seja, se não estão nulos ou vazios.
	 * 
	 * @param idRemetente Id do remetente da mensagem.
	 * @param idsDestinatariosString Ids dos destinatarios da mensagem (String separada por virgulas).
	 * @return
	 */
	private boolean verificarSeIdsForamInformados(String idRemetente,	String idsDestinatariosString) {
		
		return idsDestinatariosString != null && !idsDestinatariosString.equals("")
				&& idRemetente != null && !idRemetente.equals("");
	}


	public boolean autorizaGravarMensagem(Long idUsuario) {
		
		boolean autorizado = false;
		
		autorizado = isUsuarioPodeGravar(idUsuario);
		
		return autorizado;
	}


	public boolean autorizaCancelarMensagem(Long idUsuario) {
		
		boolean autorizado = false;
		
		autorizado = isUsuarioPodeCancelar(idUsuario);
		
		return autorizado;
	}


	@Override
	public List<Long> getIdsPermitidosParaEnvioDeMensagem(Long idRemetente) {
		// TODO Auto-generated method stub
		return null;
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
