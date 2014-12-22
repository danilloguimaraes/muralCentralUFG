package br.ufg.inf.fabrica.muralufg.central.mensagem.dao;

import static com.google.api.services.datastore.client.DatastoreHelper.getPropertyMap;
import static com.google.api.services.datastore.client.DatastoreHelper.getString;
import static com.google.api.services.datastore.client.DatastoreHelper.getTimestamp;
import static com.google.api.services.datastore.client.DatastoreHelper.makeFilter;
import static com.google.api.services.datastore.client.DatastoreHelper.makeKey;
import static com.google.api.services.datastore.client.DatastoreHelper.makeProperty;
import static com.google.api.services.datastore.client.DatastoreHelper.makeValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.ufg.inf.fabrica.muralufg.central.arquivo.Arquivo;
import br.ufg.inf.fabrica.muralufg.central.arquivo.ArquivoRepository;
import br.ufg.inf.fabrica.muralufg.central.mensagem.Mensagem;
import br.ufg.inf.fabrica.muralufg.central.mensagem.MensagemRepository;

import com.google.api.services.datastore.DatastoreV1.CommitRequest;
import com.google.api.services.datastore.DatastoreV1.Entity;
import com.google.api.services.datastore.DatastoreV1.EntityResult;
import com.google.api.services.datastore.DatastoreV1.Filter;
import com.google.api.services.datastore.DatastoreV1.Mutation;
import com.google.api.services.datastore.DatastoreV1.PropertyFilter;
import com.google.api.services.datastore.DatastoreV1.Query;
import com.google.api.services.datastore.DatastoreV1.RunQueryRequest;
import com.google.api.services.datastore.DatastoreV1.RunQueryResponse;
import com.google.api.services.datastore.DatastoreV1.Value;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;

public class MensagemDAO implements MensagemRepository {
	private ArquivoRepository arquivoRepository;
	/**
	 * Salva a mensagem no banco de dados.
	 * @param mensagem Mensagem a ser salva no banco de dados.
	 */
	@Override
	public void salvar(Mensagem mensagem){
		Datastore datastore = DaoHelperMensagem.getDataStore();
		
 		Entity.Builder employee = Entity.newBuilder()
		    .setKey(makeKey("Mensagem"))
		    .addProperty(makeProperty("id_mensagem", makeValue(mensagem.getId())))
		    .addProperty(makeProperty("conteudo", makeValue(mensagem.getConteudo())))
		    .addProperty(makeProperty("data_criacao", makeValue(formataDataSemHora(mensagem.getDataCriacao()))));
		    
		    if(!mensagem.getArquivos().isEmpty() && verificaArquivosNoBanco(mensagem.getArquivos())){
				 for (Arquivo arquivo : mensagem.getArquivos()) {
					 employee.addProperty(makeProperty("lista_id_arquivo", makeValue(arquivo.getId())));
				 }
		    }else{
		    	employee.addProperty(makeProperty("lista_id_arquivo", makeValue("None")));
		    }

		CommitRequest commitRequest = CommitRequest.newBuilder()
		    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
		    .setMutation(Mutation.newBuilder().addInsertAutoId(employee))
		    .build();
		try {
			datastore.commit(commitRequest);
		} catch (DatastoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	Retorna a mensagem consutada no banco.
	 * @param mensagemId String com id da mensagem a ser consultada.
	 * @return mensagem Mensagem consutalda no banco.
	 */
	@Override
	public Mensagem getPorId(String mensagemId) {
		Datastore datastore = DaoHelperMensagem.getDataStore();
		
		Query.Builder q = Query.newBuilder();
		q.addKindBuilder().setName("Mensagem");
		Filter idMensagem = makeFilter("id_mensagem", PropertyFilter.Operator.EQUAL, makeValue(mensagemId)).build();
		q.setFilter(idMensagem).build();
		
		RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(q).build();
		RunQueryResponse response = null;
		
		try {
			response = datastore.runQuery(request);
		} catch (DatastoreException e) {
			e.printStackTrace();
		}
		
		List<Mensagem> mensagens = populaMensagemComDadosRequisicao(response);
		Mensagem mensagem = !mensagens.isEmpty() ? mensagens.get(0) : null;
		
		return mensagem;
	
	}
	/**
	 * Realiza a busca no banco com o periodo criado pelas datas aPartirDe e fim
	 * @param aPartirDe Data inicial do periodo da busca.
	 * @param fim Data final do período de busca.
	 * 
	 * @return mensagens Lista de mensagens consultadas no banco.
	 */
	@Override
	public List<Mensagem> getPorPeriodo(Date aPartirDe, Date fim) {
		Datastore datastore = DaoHelperMensagem.getDataStore();
		//Cria filtro com a data inicial
		Filter dataInicialFilter = makeFilter("data_criacao", PropertyFilter.Operator.GREATER_THAN_OR_EQUAL, makeValue(formataDataSemHora(aPartirDe))).build();
		
		//Cria filtro com a data final
		Filter dataFinalFilter = makeFilter( "data_criacao", PropertyFilter.Operator.LESS_THAN_OR_EQUAL, makeValue(formataDataSemHora(fim))).build();

		// Combina os filtros de data inicaial e final criando um periodo de busca.
		Filter periodoDeBuscaFilter = makeFilter(dataInicialFilter, dataFinalFilter).build();

		
		Query.Builder consultaMensagem = Query.newBuilder();
		consultaMensagem.addKindBuilder().setName("Mensagem");
		consultaMensagem.setFilter(periodoDeBuscaFilter).build();

		// Cria a requisição de busca
		RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(consultaMensagem).build();
		RunQueryResponse response = null;
		try {
			response = datastore.runQuery(request);
		} catch (DatastoreException e) {
			e.printStackTrace();
		}

		List<Mensagem> mensagens = populaMensagemComDadosRequisicao(response);
	
		return mensagens;
	}
	/**
	 * Formata a data recebida atribuindo zero aos campos de relacionados a hora.
	 * @param data Data que será formatada.
	 * @return data Data formatada sem horas.
	 */
	private Date formataDataSemHora(Date data){
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	/**
	 * Converte a data no formato LongTimes para o tipo Darte.
	 * @param timesLong Data no formatado de Long a ser convertida
	 * @return data Data formatada.
	 */
	private Date converteLongTimesParaDate(Long timesLong){
		Date data = new Date(timesLong/1000);
		
		return data;
	}

	/**
	 * Retorna a data atual menos um dia
	 * @return data Data formatada 
	 */
	public static Date getDataOntem(){
		final long DAY_MILIS = 24 * 3600 * 1000;
	    return new Date(new Date().getTime() - 1 * DAY_MILIS);
	}
	
	/**
	 * 
	 * Pega as mensagens que estão na response e  adiciona na lista de mensagens para retorno.
	 * @param response RunQueryResponse.
	 * @return mensagens Lista de mensagens encontradas na response
	 */
	private List<Mensagem> populaMensagemComDadosRequisicao(RunQueryResponse response){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem mensagem = null;
		for (EntityResult result : response.getBatch().getEntityResultList()) {
		  Map<String, Value> props = getPropertyMap(result.getEntity());
		  String id = getString(props.get("id_mensagem"));
		  Long timesLong = getTimestamp(props.get("data_criacao"));
		  String conteudo = getString(props.get("conteudo"));
		  
		  mensagem = new Mensagem(id, conteudo, converteLongTimesParaDate(timesLong), null);
		  mensagens.add(mensagem);
		}
		return mensagens;
	}
	
	private boolean verificaArquivosNoBanco(List<Arquivo> arquivos){
		arquivoRepository = new ArquivoRepositoryGoogleStore();
		for (Arquivo arquivo : arquivos) {
			if(arquivoRepository.recupera(arquivo.getId()) == null){
				return false;
			}
		}
		return true;
	}
	
	private Mensagem populaMensagemComArquivos(Mensagem mensagem){
		arquivoRepository = new ArquivoRepositoryGoogleStore();
		List<Arquivo>arquivosDoBanco = new ArrayList<>();
		
		for (Arquivo arquivo : mensagem.getArquivos()) {
			Arquivo arquivoCompleto = new Arquivo(arquivo.getId(), arquivoRepository.recupera(arquivo.getId()));
			arquivosDoBanco.add(arquivoCompleto);
		}
		mensagem.getArquivos().clear();
		mensagem.getArquivos().addAll(arquivosDoBanco);
		
		return mensagem;
	}
}
