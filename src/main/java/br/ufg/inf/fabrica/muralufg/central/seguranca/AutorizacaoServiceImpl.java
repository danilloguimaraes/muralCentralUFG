package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.mensagem.MensagemRepository;

public class AutorizacaoServiceImpl implements AutorizacaoService {

	private MensagemRepository mensagemRepository;
	
	
	@Override
	public boolean autoriza(String usuario, String acao, String escopo) {

		if (acao.equals(AcaoEnum.ENVIAR_MENSAGEM.toString()) && escopo != null) {
			return autorizarEnvio(new Long(usuario), escopo);
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
	public boolean autorizarEnvio(Long idRemetente, String idsDestinatariosString) {
		
		boolean autorizado = true;
	
		List<Long> idsPermitidosParaEnvioDeMensagem = getIdsPermitidosParaEnvioDeMensagem(idRemetente);
	
		List<Long> idsDestinatarios = new ArrayList<Long>();
		
		if(!idsDestinatariosString.equals("")){
			for(String idDestinatario : idsDestinatariosString.split(",")){
				idsDestinatarios.add(new Long(idDestinatario));
			}
		}
		
		autorizado = idsPermitidosParaEnvioDeMensagem.containsAll(idsDestinatarios);
		
		return autorizado;
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
