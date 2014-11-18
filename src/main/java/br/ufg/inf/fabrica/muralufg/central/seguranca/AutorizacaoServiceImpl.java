package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.mensagem.Mensagem;
import br.ufg.inf.fabrica.muralufg.central.mensagem.MensagemRepository;

public class AutorizacaoServiceImpl implements AutorizacaoService {
	
	
	MensagemRepository mensagemRepository;

	
	/**
	 * 
	 * Método para a autorização de açoes.
	 * 
	 * @param usuario Id do usuário que deseja realizar a ação
	 * 
	 * @param acao String que representa uma das opçoes do AcaoEnum
	 * @param escopo Escopo da acao que deseja realizar 
	 * (ids influenciados pela acao ou que atendem uma regra da ação por exemplo)
	 * 
	 */
	@Override
	public boolean autoriza(String usuario, String acao, String escopo) {

		if (acao.equals(AcaoEnum.ENVIAR_MENSAGEM.toString()) && escopo != null) {
			return autorizarEnvio(usuario, escopo);
		}
		
		if (acao.equals(AcaoEnum.CANCELAR_MENSAGEM.toString())){
			return autorizaCancelarMensagem(new Long(usuario), escopo);
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
	private boolean autorizarEnvio(String idRemetenteString, String idsDestinatariosString){
		
		boolean autorizado = true;
		
		if(!verificarSeIdsForamInformados(idRemetenteString, idsDestinatariosString)){
			return false;
		}
		
		try{
			Long idRemetente = new Long(idRemetenteString);
			
			List<Long> idsPermitidosParaEnvioDeMensagem = getIdsPermitidosParaEnvioDeMensagem(idRemetente);
			List<Long> idsDestinatarios = getIdsDestinatariosList(idsDestinatariosString);
			
			autorizado = idsPermitidosParaEnvioDeMensagem.containsAll(idsDestinatarios);
		
		}catch(NumberFormatException numberFormatException){
			
			return false;
		}
		
		return autorizado;
	}

	
	/**
	 * Faz o split da string recebida pela requisicao (ids separados por virgula)
	 * e retorna um list com os ids.
	 * 
	 * @param idsDestinatariosString String com os ids dos destinatários separados por virgula.
	 * 
	 * @return ArrayList<Long> dos ids informados na string.
	 * 
	 */
	private List<Long> getIdsDestinatariosList(String idsDestinatariosString) throws NumberFormatException{
		
		List<Long> idsDestinatariosList = new ArrayList<Long>();
		
		if(idsDestinatariosString != null && !idsDestinatariosString.equals("")){
			for(String idDestinatario : idsDestinatariosString.split(",")){
				idsDestinatariosList.add(new Long(idDestinatario));
			}
		}
		
		return idsDestinatariosList;
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

	/**
	 * Verifica se um usuário possui permissão para cancelar uma mensagem.
	 * 
	 * @param idUsuario Identificador único do usuário que deseja cancelar a mensagem.
	 * @param idMensagem Identificador único da mensagem que o usuário deseja cancelar.
	 * 
	 */
	public boolean autorizaCancelarMensagem(Long idUsuario, String idMensagem) {
		
		boolean autorizado = false;
		
		autorizado = isUsuarioPodeCancelar(idUsuario, idMensagem);
		
		return autorizado;
	}

	/**
	 * 
	 * Busca os ids para os quais o remente possui permissão de envio,
	 * seja esses ids de um curso, turma, órgão ou alunos em específico.
	 * 
	 * @param idRemetente Identificador único do usuário que deseja enviar uma mensagem.
	 * 
	 */
	@Override
	public List<Long> getIdsPermitidosParaEnvioDeMensagem(Long idRemetente) {
		
		// TODO Aguardar implementação da camada de persistência para ser implementado.
		
		return null;
	}

	
	@Override
	public boolean isUsuarioPodeGravar(Long idUsuario) {
		
		// TODO Auto-generated method stub
		
		return false;
	}

	
	/**
	 * 
	 * Verifica se o usuário com o id informado tem permissão para cancelar uma mensagem.
	 * 
	 * @param idUsuario Id do usuário que deseja cancelar uma mensagem.
	 * @param idMensagem Identificador único da mensagem.
	 * 
	 */
	@Override
	public boolean isUsuarioPodeCancelar(Long idUsuario, String idMensagem) {
		
		boolean autorizado = false;
		
		Mensagem mensagem = getMensagemRepository().getPorId(idMensagem);
		
		//Caso o usuário seja o remetente da mensagem, permite o cancelamento.
		if(mensagem.getRemetente().getId().toString().equals(idUsuario)){
			autorizado = true;
		}
		
		// TODO Aguardar a definicão da persistência e domínio para fazer os demais casos das verificações.
		
		return autorizado;
	}


	public MensagemRepository getMensagemRepository() {
		return mensagemRepository;
	}


	public void setMensagemRepository(MensagemRepository mensagemRepository) {
		this.mensagemRepository = mensagemRepository;
	}
}
