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

import java.util.Date;

/**
 * Refeição (<i>value object</i>).
 * 
 * @author Fabrica de Software - INF/UFG
 */
public class Prato {

    private String descricao;
    private double precoEmReais;
    private Date diaEmQueEstaDisponivel;
    private String imagemId;
    private String mimeTypeImage;

    /**
     * Dois pratos são considerados idênticos (iguais) se o dia e a descrição
     * correspondente coincidirem.
     *
     * @param o O objeto com o qual será feita a comparação.
     * @return {@code true} se e somente se as descrições e os dias em que estão
     * disponíveis forem idênticos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prato prato = (Prato) o;

        if (!descricao.equals(prato.descricao)) {
            return false;
        }
        if (!diaEmQueEstaDisponivel.equals(prato.diaEmQueEstaDisponivel)) {
            return false;
        }

        return true;
    }

    /**
     * Obtém o hascode do objeto Prato.
     * @return Inteiro que representa o hascode da descrição do objeto Prato.
     */
    @Override
    public int hashCode() {
        int result = descricao.hashCode();
        result = 31 * result + diaEmQueEstaDisponivel.hashCode();
        return result;
    }

    /**
     * Obtém a descrição.
     * @return Valor da propriedade descrição.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição.
     * @param descricao Valor para que seja definida a propriedade descrição.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o precoEmReais.
     * @return Valor da propriedade precoEmReais.
     */
    public double getPrecoEmReais() {
        return precoEmReais;
    }

    /**
     * Define o precoEmReais.
     * @param precoEmReais Valor para que seja definida a propriedade precoEmReais.
     */
    public void setPrecoEmReais(double precoEmReais) {
        this.precoEmReais = precoEmReais;
    }

    /**
     * Obtém o diaEmQueEstaDisponivel.
     * @return Valor da propriedade diaEmQueEstaDisponivel.
     */
    public Date getDiaEmQueEstaDisponivel() {
        return diaEmQueEstaDisponivel;
    }

    /**
     * Define o diaEmQueEstaDisponivel.
     * @param diaEmQueEstaDisponivel Valor para que seja definida a propriedade diaEmQueEstaDisponivel.
     */
    public void setDiaEmQueEstaDisponivel(Date diaEmQueEstaDisponivel) {
        this.diaEmQueEstaDisponivel = diaEmQueEstaDisponivel;
    }

    /**
     * Obtém o imagemId.
     * @return Valor da propriedade imagemId.
     */
    public String getImagemId() {
        return imagemId;
    }

    /**
     * Define o imagemId.
     * @param imagemId Valor para que seja definida a propriedade imagemId.
     */
    public void setImagemId(String imagemId) {
        this.imagemId = imagemId;
    }

    /**
     * Obtém o mimeTypeImage.
     * @return Valor da propriedade mimeTypeImage.
     */
    public String getMimeTypeImage() {
        return mimeTypeImage;
    }

    /**
     * Define o mimeTypeImage.
     * @param mimeTypeImage Valor para que seja definida a propriedade mimeTypeImage.
     */
    public void setMimeTypeImage(String mimeTypeImage) {
        this.mimeTypeImage = mimeTypeImage;
    }
}
