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

		return false;
	}

	/**
	 * 
	 * @param usuario
	 * @param acao
	 * @param escopo
	 *            String definindo os destinarios para quais a mensagem será
	 *            enviada, seguindo a seguinte regra TIPO_ESCOPO_ENUM:ID_ESCOPO,
	 *            onde o TIPO_ESCOPO_ENUM representa um enumerado
	 *            TipoEscopoEnum, e o ID_ESCOPO o id do respectivo escopo que o
	 *            enum representa.
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
		
		verificarSePossuiPermissaoDeEnvioParaTodosOsIds(idsPermitidosParaEnvioDeMensagem, idsDestinatarios);
		
		return autorizado;
	}

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
}
