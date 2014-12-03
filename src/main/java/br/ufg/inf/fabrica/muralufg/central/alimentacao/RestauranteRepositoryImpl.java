package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementação da interface {@link RestauranteRepository}.
 *
 * @author Iasmim Ribeiro
 */
public class RestauranteRepositoryImpl implements RestauranteRepository {

    // <editor-fold defaultstate="collapsed" desc="VARIÁVEIS">
    /**
     * Constante com valor 0.
     */
    private final int ZERO = 0;

    /**
     * Constante com valor "" (string.Empty).
     */
    private final String VAZIO = "";

    /**
     * Repositório de restaurantes.
     */
    private ArrayList<Restaurante> repoRestaurantes;

    /**
     * Repositório de cardápios.
     */
    private HashMap<Restaurante, ArrayList<Prato>> repoCardapio;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="CONSTRUTOR">
    /**
     * Constutor da classe
     * {@link com.principal.implementacoes.RestauranteRepositoryImpl}.
     */
    public RestauranteRepositoryImpl() {
        repoRestaurantes = new ArrayList<>();
        repoCardapio = new HashMap<>();
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
     * @return
     */
    @Override
    public List<Restaurante> obtem(final Restaurante filtro) {
        List<Restaurante> listaRetorno = new ArrayList<>();
        
        if (filtro != null) {            
            for (Restaurante elemento : repoRestaurantes) {
                if(elemento.semelhante(filtro)){
                    listaRetorno.add(elemento);
                }
            }
            
            return listaRetorno;
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

        if (restaurante == null || restaurante.getNome().trim().length() == ZERO) {
            throw new IllegalArgumentException();
        }

        repoRestaurantes.add(restaurante);
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

        if (restaurante == null) {
            return false;
        }

        return repoRestaurantes.remove(restaurante);
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
            repoRestaurantes.add(repoRestaurantes.indexOf(getEqual(restaurante)),
                    restaurante);
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

        List<Prato> lista = getListaPratos(restaurante);

        if (lista.size() != ZERO) {
            lista.add(prato);
            return true;
        }

        return false;
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

        List<Prato> pratosDoDia = getListaPratos(restaurante);
        List<Prato> listaRetorno = new ArrayList<>();

        if (pratosDoDia.size() != ZERO) {            
            for (Prato elemento : pratosDoDia) {
                if(elemento.getDiaEmQueEstaDisponivel().equals(dia)){
                    listaRetorno.add(elemento);
                }
            }
            
            return listaRetorno;
        }

        return pratosDoDia;
    }

    /**
     * Obtém a imagem.
     *
     * @param imagemId Identificador único da imagem.
     * @return Vetor de bytes que é o conteúdo da imagem.
     */
    @Override
    public byte[] getImagem(String imagemId) {

        for (Map.Entry<Restaurante, ArrayList<Prato>> entrySet : repoCardapio.entrySet()) {
            for (Prato elemento : entrySet.getValue()) {
                if(elemento.getImagemId().equals(imagemId)){
                    return new byte[0];
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

        for (Map.Entry<Restaurante, ArrayList<Prato>> elemento : repoCardapio.entrySet()) {
            for (Prato prato : elemento.getValue()) {
                if (prato.getImagemId().equals(imagemId)) {
                    return prato.getMimeTypeImage();
                }   
            }
        }
        
        return VAZIO;
    }

    public ArrayList<Restaurante> getRepoRestaurantes() {
        return repoRestaurantes;
    }

    public HashMap<Restaurante, ArrayList<Prato>> getRepoCardapio() {
        return repoCardapio;
    }

    public void setRepoRestaurantes(ArrayList<Restaurante> repoRestaurantes) {
        this.repoRestaurantes = repoRestaurantes;
    }

    public void setRepoCardapio(HashMap<Restaurante, ArrayList<Prato>> repoCardapio) {
        this.repoCardapio = repoCardapio;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÉTODOS PRIVADOS">
    /**
     * Obtém o primeiro restaurante do repositorio que satisfaça o filtro
     * informado.
     *
     * @param filtro Para busca do resturante desejado.
     * @return {@code null} Caso não encontre e {@link Restaurante} caso seja
     * encontrado.
     */
    private Restaurante getEqual(final Restaurante filtro) {

        if (filtro == null || filtro.getNome().trim().length() == ZERO) {
            throw new IllegalArgumentException();
        }
        
        for (Restaurante elemento : repoRestaurantes) {
            if(elemento.equals(filtro)){
                return elemento;
            }
        }
        
        return null;
    }

    /**
     * Obtém a lista de pratos de um retaurante informado.
     *
     * @param key Chave para busca.
     * @return Lista de pratos do restaurante ou uma lista vazia caso não seja
     * encontrado.
     */
    public List<Prato> getListaPratos(Restaurante key) {
        for (Map.Entry<Restaurante, ArrayList<Prato>> entrySet : repoCardapio.entrySet()) {
            if (entrySet.getKey().equals(key)) {
                return entrySet.getValue();
            }
        }

        return new ArrayList<>();
    }

    // </editor-fold>
}
