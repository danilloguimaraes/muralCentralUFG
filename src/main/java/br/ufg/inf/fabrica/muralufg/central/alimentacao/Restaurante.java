/*
 * ====================================================================
 * Licença da Fábrica de Software (UFG)
 *
 * Copyright (c) 2014 Fábrica de Software
 * Instituto de Informática (Universidade Federal de Goiás)
 * Todos os direitos reservados.
 *
 * Redistribuição e uso, seja dos fontes ou do formato binário
 * correspondente, com ou sem modificação, são permitidos desde que
 * as seguintes condições sejam atendidas:
 *
 * 1. Redistribuição do código fonte deve conter esta nota, em sua
 *    totalidade, ou seja, a nota de copyright acima, as condições
 *    e a observação sobre garantia abaixo.
 *
 * 2. Redistribuição no formato binário deve reproduzir o conteúdo
 *    desta nota, em sua totalidade, ou seja, o copyright acima,
 *    esta lista de condições e a observação abaixo na documentação
 *    e/ou materiais fornecidos com a distribuição.
 *
 * 3. A documentação fornecida com a redistribuição,
 *    qualquer que seja esta, deve incluir o seguinte
 *    texto, entre aspas:
 *       "Este produto inclui software desenvolvido pela Fábrica
 *       de Software do Instituto de Informática (UFG)."
 *
 * 4. Os nomes Fábrica de Software, Instituto de Informática e UFG
 *    não podem ser empregados para endoçar ou promover produtos
 *    derivados do presente software sem a explícita permissão
 *    por escrito.
 *
 * 5. Produtos derivados deste software não podem ser chamados
 *    "Fábrica de Software", "Instituto de Informática", "UFG",
 *    "Universidade Federal de Goiás" ou contê-los em seus nomes,
 *    sem permissão por escrito.
 *
 * ESTE SOFTWARE É FORNECIDO "COMO ESTÁ". NENHUMA GARANTIA É FORNECIDA,
 * EXPLÍCITA OU NÃO. NÃO SE AFIRMA QUE O PRESENTE SOFTWARE
 * É ADEQUADO PARA QUALQUER QUE SEJA O USO. DE FATO, CABE AO
 * INTERESSADO E/OU USUÁRIO DO PRESENTE SOFTWARE, IMEDIATO OU NÃO,
 * ESTA AVALIAÇÃO E A CONSEQUÊNCIA QUE O USO DELE OCASIONAR. QUALQUER
 * DANO QUE DESTE SOFTWARE DERIVAR DEVE SER ATRIBUÍDO AO INTERESSADO
 * E/OU USUÁRIO DESTE SOFTWARE.
 * ====================================================================
 *
 * Este software é resultado do trabalho de voluntários, estudantes e
 * professores, ao realizar atividades no âmbito da Fábrica de Software
 * do Instituto de Informática (UFG). Consulte <http://fs.inf.ufg.br>
 * para detalhes.
 */
package br.ufg.inf.fabrica.muralufg.central.alimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Estabelecimento destinado ao preparo, comercialização ou distribuição de
 * refeições ou alimentos, localizado próximo aos campi da UFG.
 * 
 * @author Fabrica de Software - INF/UFG
 */
public class Restaurante extends ConfiguracaoBase {

    private String id;
    private String campus;
    private String nome;
    private List<String> formaPagamento;
    private Date inicioHorario;
    private Date fimHorario;
    private List<Prato> listaPratos;

    public Restaurante() {

    }

    /**
     * Construtor da classe Restaurante.
     *
     * @param id
     * @param campus
     * @param nome
     * @param inicioHorario
     * @param fimHorario
     */
    public Restaurante(String id, String campus, String nome, Date inicioHorario, Date fimHorario) {
        this.formaPagamento = obtenhaFormasDePagamento();
        this.listaPratos = obtenhaListaDePratos();
        this.id = id;
        this.campus = campus;
        this.nome = nome;
        this.inicioHorario = inicioHorario;
        this.fimHorario = fimHorario;
    }

    /**
     * Identifica se o presente restaurante é "semelhante" àquele fornecido.
     * <p>
     * Dois restaurantes são ditos semelhantes se há semelhanças entre seus
     * membros. Por exemplo, para o membro {@code campus}, a semelhança é o
     * mesmo que igualdade. Por outro lado, {@code formaPagamento} é semelhante
     * se as formas de pagamento do presente restaurante incluem aquela(s) do
     * restaurante fornecido.</p>
     * <p>
     * Semelhança para os demais membros deverão ser definidas após análise de
     * "casos de interesse".</p>
     *
     * @param restaurante Restaurante com a qual a semelhança será verificada.
     * @return {@code true} se o presente restaurante é semelhante àquele
     * fornecido.
     */
    public boolean semelhante(Restaurante restaurante) {
        return true;
    }

    /**
     * Obtém o campus.
     *
     * @return Valor da propriedade campus.
     */
    public String getCampus() {
        return campus;
    }

    /**
     * Obtém o nome.
     *
     * @return Valor da propriedade nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome.
     *
     * @param nome Valor para que seja definida a propriedade nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o formaPagamento.
     *
     * @return Valor da propriedade formaPagamento.
     */
    public List<String> getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Define a formaPagamento.
     *
     * @param formaPagamento Valor para que seja definida a propriedade
     * formaPagamento.
     */
    public void setFormaPagamento(List<String> formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * Obtém o inicioHorario.
     *
     * @return Valor da propriedade inicioHorario.
     */
    public Date getInicioHorario() {
        return inicioHorario;
    }

    /**
     * Define o inicioHorario.
     *
     * @param inicioHorario Valor para que seja definida a propriedade
     * inicioHorario.
     */
    public void setInicioHorario(Date inicioHorario) {
        this.inicioHorario = inicioHorario;
    }

    /**
     * Obtém o fimHorario.
     *
     * @return Valor da propriedade fimHorario.
     */
    public Date getFimHorario() {
        return fimHorario;
    }

    /**
     * Define o fimHorario.
     *
     * @param fimHorario Valor para que seja definida a propriedade fimHorario.
     */
    public void setFimHorario(Date fimHorario) {
        this.fimHorario = fimHorario;
    }

    /**
     * Define o campus.
     *
     * @param campus Valor para que seja definida a propriedade campus.
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * Obtém o id.
     *
     * @return Valor da propriedade id.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o id.
     *
     * @param id Valor para que seja definida a propriedade id.
     */
    public void setId(String id) {
        this.id = id;
    }

    public List<Prato> getListaPratos() {
        return listaPratos;
    }

    public void setListaPratos(List<Prato> listaPratos) {
        this.listaPratos = listaPratos;
    }

    /**
     * Obtém as formas de pagamento disponíveis para o restaurante.
     *
     * @return
     */
    private List<String> obtenhaFormasDePagamento() {
        List<String> retorno = new ArrayList<>();

        retorno.add("CATÃO DE CRÉDITO");
        retorno.add("CARTÃO DE DEBITO");
        retorno.add("CHEQUE");
        retorno.add("DINHEIRO");

        return retorno;
    }

    /**
     * Obtém uma lista randômica de pratos.
     *
     * @return {@link ArrayList<Prato>}.
     */
    private ArrayList<Prato> obtenhaListaDePratos() {
        ArrayList<Prato> lista = new ArrayList<>();

        int indice = new Random().nextInt(2);

        for (int i = 0; i < indice; i++) {
            lista.add(ObtenhaPratoAleatorio());
        }

        return lista;
    }

    /**
     * Obtém um único prato.
     *
     * @return {@link Prato}.
     */
    protected Prato ObtenhaPratoAleatorio() {
        List<String> listaMimeType = new ArrayList<>();
        UUID imagemId;

        listaMimeType.add("image/bmp");
        listaMimeType.add("image/x-windows-bmp");
        listaMimeType.add("image/gif");
        listaMimeType.add("image/jpeg");

        Prato p = new Prato();
        p.setDescricao("Prato " + new Random().nextInt(999));
        p.setDiaEmQueEstaDisponivel(obtenhaDataAleatoria(DIAS));
        p.setMimeTypeImage(listaMimeType.get(new Random().nextInt(listaMimeType.size())));
        p.setPrecoEmReais(new Random().nextDouble());
        imagemId = UUID.randomUUID();
        p.setImagemId(imagemId.toString());

        return p;
    }
}
