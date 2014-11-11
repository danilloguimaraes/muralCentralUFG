package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.mensagem.MensagemRepository;

public class AutorizacaoServiceImpl implements AutorizacaoService {

	private MensagemRepository mensagemRepository;
	
	
	@Override
	public boolean autoriza(String usuario, String acao, String escopo) {

		if (acao.equals(AcaoEnum.ENVIAR_MENSAGEM) && escopo != null) {
			return autorizarEnvio(new Long(usuario), escopo);
		}
		
		if (acao.equals(AcaoEnum.CANCELAR_MENSAGEM)){
			return autorizaCancelarMensagem(new Long(usuario));
		}
		
		if (acao.equals(AcaoEnum.GRAVA_REGISTRO)){
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
	public boolean autorizarEnvio(Long idRemetente, String idsDestinatariosString) {
		
		boolean autorizado = true;
	
		List<Long> idsPermitidosParaEnvioDeMensagem = mensagemRepository.getIdsPermitidosParaEnvioDeMensagem(idRemetente);
	
		List<Long> idsDestinatarios = new ArrayList<Long>();
		
		if(!idsDestinatariosString.equals("")){
			for(String idDestinatario : idsDestinatariosString.split(",")){
				idsDestinatarios.add(new Long(idDestinatario));
			}
		}
		
		autorizado = verificarSePossuiPermissaoDeEnvioParaTodosOsIds(idsPermitidosParaEnvioDeMensagem, idsDestinatarios);
		
		return autorizado;
	}

	
	/**
	 * Verifica se o usuário possui permissão de envio para todos os destinatários que
	 * está tentando enviar.
	 * 
	 * @param idsPermitidosParaEnvioDeMensagem Todos os ids que o usuário possui permissão de envio.
	 * @param idsDestinatarios Ids que o usuário pretende enviar a mensagem.
	 * @return
	 */
	private boolean verificarSePossuiPermissaoDeEnvioParaTodosOsIds(List<Long> idsPermitidosParaEnvioDeMensagem,
			List<Long> idsDestinatarios) {
		
		if(idsPermitidosParaEnvioDeMensagem.isEmpty() || idsDestinatarios.isEmpty()){
			return false;
		}
		
		for(Long idDestinatario : idsDestinatarios){
			if(!idsPermitidosParaEnvioDeMensagem.contains(idDestinatario)){
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean autorizaGravarMensagem(Long idUsuario) {
		
		boolean autorizado = false;
		
		autorizado = mensagemRepository.isUsuarioPodeGravar(idUsuario);
		
		return autorizado;
	}
	

	public boolean autorizaCancelarMensagem(Long idUsuario) {
		
		boolean autorizado = false;
		
		autorizado = mensagemRepository.isUsuarioPodeCancelar(idUsuario);
		
		return autorizado;
	}
}
