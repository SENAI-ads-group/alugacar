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
    private Double valorDiaria = 0.0;
    private Double valorKM = 0.0;

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Categoria() {
    }

    public Categoria(Integer id, String descricao, Double valorDiaria, Double valorKM) {
        this.id = id;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.valorKM = valorKM;
    }

    public Categoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        descricao = csv[1];
        valorDiaria = Utilities.tryParseToDouble(csv[2]);
        valorKM = Utilities.tryParseToDouble(csv[3]);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hasCode">  
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
        if (Objects.equals(this.descricao.toLowerCase(), other.descricao.toLowerCase())) {
            return true;
        }
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
    // </editor-fold>
    
    public String toCSV() {
        return "" + id + ";"
                + descricao + ";"
                + valorDiaria + ";"
                + valorKM;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
