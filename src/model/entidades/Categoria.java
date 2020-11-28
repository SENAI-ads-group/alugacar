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
    private Double valorDiaria;

    public Categoria() {
    }

    public Categoria(Integer id, String descricao, Double valorMinimoLocacao, Double valorDiaria) {
        this.id = id;
        this.descricao = descricao;
        this.valorMinimoLocacao = valorMinimoLocacao;
        this.valorDiaria = valorDiaria;
    }

    public Categoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        descricao = csv[1];
        valorMinimoLocacao = Utilities.tryParseToDouble(csv[2]);
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

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String toCSV() {
        return "" + id + ";"
                + descricao + ";"
                + valorMinimoLocacao + ";"
                + valorDiaria;
    }

}
