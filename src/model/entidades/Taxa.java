package model.entidades;

import application.Configuracoes;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class Taxa {

    private String descricao;
    private double valor;

    public Taxa() {
    }

    public Taxa(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static Taxa getTaxaItemVistoria() {
        Double valorTaxa = Utilities.tryParseToDouble(Configuracoes.getProperties().getProperty("taxa.item-vistoria"));
        return new Taxa("Taxa de item da vistoria", valorTaxa);
    }

}
