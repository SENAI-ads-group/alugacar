package model.entidades;

import java.util.Objects;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class Marca {

    private Integer id;
    private String descricao;

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Marca() {
    }

    public Marca(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Marca(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        descricao = csv[1];
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
        final Marca other = (Marca) obj;
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
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }
    // </editor-fold>

    public String toCSV() {
        return "" + id + ";"
                + descricao;
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
    }

}
