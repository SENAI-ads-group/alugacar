package model.entidades;

import application.Configuracoes;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Desconto {

    private String descricao;
    private double valor;

    // <editor-fold defaultstate="collapsed" desc="construtores"> 
    public Desconto() {
    }

    public Desconto(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters"> 
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
    // </editor-fold>

    public static Desconto getDescontoItemVistoria() {
        Double valorDesconto = Utilities.tryParseToDouble(Configuracoes.getProperties().getProperty("desconto.item-vistoria"));
        return new Desconto("Desconto de item da vistoria", valorDesconto);
    }
}
