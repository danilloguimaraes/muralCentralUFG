package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.api.services.datastore.DatastoreV1.CommitRequest;
import com.google.api.services.datastore.DatastoreV1.CommitResponse;
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
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;
import static com.google.api.services.datastore.client.DatastoreHelper.getPropertyMap;
import static com.google.api.services.datastore.client.DatastoreHelper.getString;
import static com.google.api.services.datastore.client.DatastoreHelper.getTimestamp;
import static com.google.api.services.datastore.client.DatastoreHelper.makeFilter;
import static com.google.api.services.datastore.client.DatastoreHelper.makeProperty;
import static com.google.api.services.datastore.client.DatastoreHelper.makeKey;
import static com.google.api.services.datastore.client.DatastoreHelper.makeValue;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Implementação da interface {@link RestauranteRepository}.
 *
 * @author Fabrica de Software - INF/UFG
 */
@RunWith(MockitoJUnitRunner.class)
public final class RestauranteRepositoryImpl extends ConfiguracaoBase implements RestauranteRepository {

    // <editor-fold defaultstate="collapsed" desc="VARIÁVEIS">
    /**
     * Constante com valor 0.
     */
    private final int ZERO = 0;

    private Datastore datastore;
    /**
     * Constante com valor "" (string.Empty).
     */
    private final String VAZIO = "";

    /**
     * Data em que o prato está diponível.
     */
    protected Date dataDisponivel;

    // </editor-fold>
    
    final String ID = "id";
    final String CAMPUS = "campus";
    final String NOME = "nome";
    final String INICIO_HORARIO = "inicioHorario";
    final String FIM_HORARIO = "fimHorario";
    final String DATA_SET_NAME = "my-dataset";
    final String KEY = "Restaurante";

    // <editor-fold defaultstate="collapsed" desc="CONSTRUTOR">
    /**
     * Constutor da classe
     * {@link com.principal.implementacoes.RestauranteRepositoryImpl}.
     */
    public RestauranteRepositoryImpl() {
        inicializeDataset();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÉTODOS PÚBLICOS">
    /**
     * Recupera lista de restaurantes que satisfazem os valores do filtro
     * fornecido.
     *
     * @param filtro Restaurante cujos membros definem semelhanças ou não com
     * outros restaurantes. Restaurantes semelhantes são identificados pelo
     * método
     * @return Lista de restaurantes que satisfazem o filtro.
     */
    @Override
      public List<Restaurante> obtem(final Restaurante filtro) {
        List<Restaurante> listaRetorno = new ArrayList<>();
        List<Filter> filtros = new ArrayList<>();

        try {
            filtros.add(makeFilter(
                    ID, PropertyFilter.Operator.GREATER_THAN_OR_EQUAL, makeValue(filtro.getId())).build());

            Filter enconteIgual = makeFilter(filtros).build();

            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName(KEY);
            query.setFilter(enconteIgual).build();

            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse response = datastore.runQuery(request);

            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String id = getString(props.get(ID));
                String campus = getString(props.get(CAMPUS));
                String nome = getString(props.get(NOME));
                Date inicioHorario = new Date(getTimestamp(props.get(INICIO_HORARIO)));
                Date fimHorario = new Date(getTimestamp(props.get(FIM_HORARIO)));

                listaRetorno.add(new Restaurante(
                        id,
                        campus,
                        nome,
                        inicioHorario,
                        fimHorario));
            }

            return listaRetorno;

        } catch (DatastoreException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;

    }

    /**
     * Adiciona um restaurante ao repositório. O identificador único do
     * restaurante é atualizado neste processo.
     *
     * @param restaurante O restaurante a ser adicionado.
     * @return {@code true} se e somente se o restaurante foi adicionado de
     * forma satisfatória.
     * @throws IllegalArgumentException Se o argumento fornecido é {@code null},
     * ou o {@link com.principal.classes.Restaurante#nome} é {@code null} ou
     * vazio.
     */
    @Override
    public boolean adiciona(Restaurante restaurante) {

        try {
            if (restaurante == null || restaurante.getNome().trim().length() == ZERO) {
                throw new IllegalArgumentException();
            }
            
            Entity.Builder entRestaurante = Entity.newBuilder();
            entRestaurante.setKey(makeKey(KEY));
            entRestaurante.addProperty(makeProperty(ID, makeValue(restaurante.getId())));
            entRestaurante.addProperty(makeProperty(CAMPUS, makeValue(restaurante.getId())));
            entRestaurante.addProperty(makeProperty(NOME, makeValue(restaurante.getId())));
            entRestaurante.addProperty(makeProperty(INICIO_HORARIO, makeValue(restaurante.getInicioHorario())));
            entRestaurante.addProperty(makeProperty(FIM_HORARIO, makeValue(restaurante.getFimHorario())));
            
            Mutation.Builder mutation = Mutation.newBuilder();
            mutation.addInsertAutoId(entRestaurante);
            
            CommitRequest comit = CommitRequest.newBuilder()
                    .setMutation(mutation)
                    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                    .build();
            CommitResponse resp = datastore.commit(comit);
        } catch (DatastoreException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    /**
     * Remove o restaurante do repositório.
     *
     * @param restaurante O restaurante a ser removido, juntamente com o
     * cardápio correspondente.
     * @return {@code true} se e somente se o restaurante foi removido de forma
     * satisfatória.
     */
    @Override
    public boolean remover(Restaurante restaurante) {

        try {
            if (restaurante == null) {
                return false;
            }
            
            Entity employee = Entity.newBuilder()
                                    .setKey(makeKey(KEY, restaurante.getNome()))
                                    .build();
            
            CommitRequest request = CommitRequest.newBuilder()
                                                 .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                                                 .setMutation(Mutation.newBuilder().addDelete(employee.getKey()))
                                                 .build();
            datastore.commit(request);
            
            return true;
        } catch (DatastoreException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
           Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    /**
     * Atualiza as informações associadas a um restaurante existente.
     *
     * @param restaurante O restaurante cujas informações serão atualizadas.
     * @return {@code true} se e somente se as informações pertinentes ao
     * restaurante foram atualizadas de forma satisfatória.
     */
    @Override
    public boolean atualizar(Restaurante restaurante) {

        if (restaurante != null) {
            Entity.Builder entAssunto = Entity.newBuilder();
            entAssunto.setKey(makeKey());
            entAssunto.addProperty(makeProperty(ID, makeValue(restaurante.getId())));
            entAssunto.addProperty(makeProperty(CAMPUS, makeValue(restaurante.getId())));
            entAssunto.addProperty(makeProperty(NOME, makeValue(restaurante.getId())));
            entAssunto.addProperty(makeProperty(INICIO_HORARIO, makeValue(restaurante.getInicioHorario())));
            entAssunto.addProperty(makeProperty(FIM_HORARIO, makeValue(restaurante.getFimHorario())));

            Mutation.Builder mutation = Mutation.newBuilder();
            mutation.addUpsert(entAssunto);

            CommitRequest.newBuilder()
                    .setMutation(mutation)
                    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                    .build();

            return true;
        }

        return false;
    }

    /**
     * Adiciona o prato ao restaurante.
     *
     * @param prato O prato a ser adicionado.
     * @param restaurante
     * @return {@code true} se e somente se o prato é adicionado de forma
     * satisfatória ao restaurante.
     */
    @Override
    public boolean adicionaPrato(Prato prato, Restaurante restaurante) {
        try {
            Restaurante restauranteEncontrado = obtem(restaurante).get(ZERO);
            restauranteEncontrado.getListaPratos().add(prato);

            return atualizar(restauranteEncontrado);
        } catch (Exception ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Obtém os pratos disponíveis no restaurante no dia indicado.
     *
     * @param restaurante O restaurante no qual os pratos são servidos.
     * @param dia O dia em que os pratos estão disponíveis.
     * @return A lista de pratos disponíveis no resutarante no dia indicado.
     * Retorna uma lista vazia, sem elementos, caso neste dia o restaurante não
     * sirva nenhum prato.
     */
    @Override
    public List<Prato> obtemPrato(Restaurante restaurante, Date dia) {
        return obtem(restaurante).get(ZERO).getListaPratos();
    }

    /**
     * Obtém a imagem.
     *
     * @param imagemId Identificador único da imagem.
     * @return Vetor de bytes que é o conteúdo da imagem.
     */
    @Override
    public byte[] getImagem(String imagemId) {

        for (Restaurante repoRestaurante : obtem()) {
            for (Prato prato : repoRestaurante.getListaPratos()) {
                if (prato.getImagemId() == null ? imagemId == null : prato.getImagemId().equals(imagemId)) {
                    return new byte[ZERO];
                }
            }
        }

        return null;
    }

    /**
     * Obtém o mime-type correspondente ao formato da imagem, por exemplo,
     * {@code image/png} para imagem no formato PNG.
     *
     * @param imagemId O identificador único da imagem.
     * @return O mime-type que identifica o formato da imagem.
     */
    @Override
    public String getMimeType(String imagemId) {

        for (Restaurante repoRestaurante : obtem()) {
            for (Prato prato : repoRestaurante.getListaPratos()) {
                if (prato.getImagemId() == null ? imagemId == null : prato.getImagemId().equals(imagemId)) {
                    return prato.getMimeTypeImage();
                }
            }
        }

        return VAZIO;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÉTODOS PRIVADOS">   
    /**
     * Obtém uma lista de restaurantes.
     *
     * @return {@link ArrayList<>} do tipo {@link Restaurante}.
     */
    protected final ArrayList<Restaurante> obtenhaListaDeRestaurante() {
        ArrayList<Restaurante> lista = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Restaurante r1 = new Restaurante();
            r1.setCampus("Campus " + i);
            r1.setFimHorario(obtenhaDataAleatoria(HORAS));
            r1.setId(String.valueOf(new Random().nextInt(999)));
            r1.setInicioHorario(obtenhaDataAleatoria(HORAS));
            r1.setNome("Restaurante " + i);

            lista.add(r1);
        }

        return lista;
    }

    /**
     * Inicializa o dataset para uso posterior.
     */
    private void inicializeDataset() {
        try {
            if(datastore == null){
                String datasetId = "my-dataset";
                datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv()
                        .dataset(datasetId).build());
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtém todos os restaurantes cadastrados, sem filtro.
     *
     * @return Lista de restaurantes.
     */
    private List<Restaurante> obtem() {
        List<Restaurante> listaRetorno = new ArrayList<>();

        try {
            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName(KEY);

            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse response = datastore.runQuery(request);

            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String id = getString(props.get(ID));
                String campus = getString(props.get(CAMPUS));
                String nome = getString(props.get(NOME));
                Date inicioHorario = new Date(getTimestamp(props.get(INICIO_HORARIO)));
                Date fimHorario = new Date(getTimestamp(props.get(FIM_HORARIO)));

                listaRetorno.add(new Restaurante(
                        id,
                        campus,
                        nome,
                        inicioHorario,
                        fimHorario));
            }

            return listaRetorno;

        } catch (DatastoreException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Obtém apenas um único restaurante.
     *
     * @param filtro Filtro para busca.
     * @return Restaurante encontrado ou null em caso de não encontrar.
     */
    private Restaurante obtemRestaurante(Restaurante filtro) {
        Restaurante retorno = new Restaurante();
        List<Filter> filtros = new ArrayList<>();

        try {
            filtros.add(makeFilter(
                    ID, PropertyFilter.Operator.EQUAL, makeValue(filtro.getId())).build());

            filtros.add(makeFilter(
                    CAMPUS, PropertyFilter.Operator.EQUAL, makeValue(filtro.getCampus())).build());

            filtros.add(makeFilter(
                    NOME, PropertyFilter.Operator.EQUAL, makeValue(filtro.getNome())).build());

            filtros.add(makeFilter(
                    FIM_HORARIO, PropertyFilter.Operator.EQUAL, makeValue(filtro.getFimHorario())).build());

            filtros.add(makeFilter(
                    INICIO_HORARIO, PropertyFilter.Operator.EQUAL, makeValue(filtro.getInicioHorario())).build());

            Filter enconteIgual = makeFilter(filtros).build();

            Query.Builder query = Query.newBuilder();
            query.addKindBuilder().setName(DATA_SET_NAME);
            query.setFilter(enconteIgual).build();

            RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(query).build();
            RunQueryResponse response = datastore.runQuery(request);

            for (EntityResult result : response.getBatch().getEntityResultList()) {
                Map<String, Value> props = getPropertyMap(result.getEntity());
                String id = getString(props.get(ID));
                String campus = getString(props.get(CAMPUS));
                String nome = getString(props.get(NOME));
                Date inicioHorario = new Date(getString(props.get(INICIO_HORARIO)));
                Date fimHorario = new Date(getString(props.get(FIM_HORARIO)));

                retorno = new Restaurante(
                        id,
                        campus,
                        nome,
                        inicioHorario,
                        fimHorario);
                break;
            }
            return retorno;
        } catch (DatastoreException ex) {
            Logger.getLogger(RestauranteRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    // </editor-fold>
}
