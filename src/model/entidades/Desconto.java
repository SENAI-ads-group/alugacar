package model.entidades;

import aplicacao.Configuracoes;
import java.util.Objects;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Desconto {

    private Integer id;
    private String descricao;
    private double valor;

    // <editor-fold defaultstate="collapsed" desc="construtores"> 
    public Desconto() {
    }

    public Desconto(Integer id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Desconto(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Desconto(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        descricao = csv[1];
        valor = Utilities.tryParseToDouble(csv[2]);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getters e setters"> 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hashcode">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Desconto other = (Desconto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

    public String toCSV() {
        return id + ";"
                + descricao + ";"
                + valor;
    }

    @Override
    public String toString() {
        return descricao + " - R$" + valor;
    }

    public static Desconto getDescontoItemVistoria() {
        Double valorDesconto = Utilities.tryParseToDouble(Configuracoes.getProperties().getProperty("desconto.item-vistoria"));
        return new Desconto("Desconto de item da vistoria", valorDesconto);
    }
}
