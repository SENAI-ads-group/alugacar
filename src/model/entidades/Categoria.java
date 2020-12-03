package model.entidades;

import java.util.Objects;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class Categoria {

    private Integer id;
    private String descricao;
    private Double valorMinimoLocacao = 0.0;
    private Double valorDiaria = 0.0;
    private Double valorKM = 0.0;

    public Categoria() {
    }

    public Categoria(Integer id, String descricao, Double valorMinimoLocacao, Double valorDiaria, Double valorKM) {
        this.id = id;
        this.descricao = descricao;
        this.valorMinimoLocacao = valorMinimoLocacao;
        this.valorDiaria = valorDiaria;
        this.valorKM = valorKM;
    }

    public Categoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        descricao = csv[1];
        valorMinimoLocacao = Utilities.tryParseToDouble(csv[2]);
        valorDiaria = Utilities.tryParseToDouble(csv[3]);
        valorKM = Utilities.tryParseToDouble(csv[4]);
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

    public Double getValorKM() {
        return valorKM;
    }

    public void setValorKM(Double valorKM) {
        this.valorKM = valorKM;
    }

    public String toCSV() {
        return "" + id + ";"
                + descricao + ";"
                + valorMinimoLocacao + ";"
                + valorDiaria + ";"
                + valorKM;
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
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
        final Categoria other = (Categoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
