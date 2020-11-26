package model.entidades;

import util.Utilities;

/**
 *
 * @author usuario
 */
public class Categoria {

    private Integer id;
    private String descricao;
    private Double valorMinimoLocacao;

    public Categoria(Integer id, String descricao, Double valorMinimoLocacao) {
        this.id = id;
        this.descricao = descricao;
        this.valorMinimoLocacao = valorMinimoLocacao;
    }

    public Categoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        descricao = csv[1];
        valorMinimoLocacao = Utilities.tryParseToDouble(csv[2]);
    }

    public Categoria(String descricao, Double valorMinimoLocacao) {
        this.descricao = descricao;
        this.valorMinimoLocacao = valorMinimoLocacao;
    }

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

    public Double getValorMinimoLocacao() {
        return valorMinimoLocacao;
    }

    public void setValorMinimoLocacao(Double valorMinimoLocacao) {
        this.valorMinimoLocacao = valorMinimoLocacao;
    }

    public String toCSV() {
        return "" + id + ";"
                + descricao + ";"
                + valorMinimoLocacao;
    }

}
