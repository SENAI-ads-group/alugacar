package model.entidades;

import model.entidades.enums.CategoriaCNH;
import java.util.Date;
import java.util.Objects;
import util.DateUtilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class CNH {

    private Integer numeroRegistro;
    private CategoriaCNH categoria;
    private Date dataValidade;

    public CNH() {
    }

    public CNH(String[] csv) {
        numeroRegistro = Integer.parseInt(csv[0]);
        categoria = CategoriaCNH.valueOf(csv[1]);
        dataValidade = DateUtilities.tryParseToDate(csv[2]);
    }

    public CNH(Integer numeroRegistro, CategoriaCNH categoria, Date dataValidade) {
        this.numeroRegistro = numeroRegistro;
        this.categoria = categoria;
        this.dataValidade = dataValidade;
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public CategoriaCNH getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCNH categoria) {
        this.categoria = categoria;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String toCSV() {
        return "" + numeroRegistro + ";"
                + categoria.toString() + ";"
                + DateUtilities.formatData(dataValidade);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.numeroRegistro);
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
        final CNH other = (CNH) obj;
        if (!Objects.equals(this.numeroRegistro, other.numeroRegistro)) {
            return false;
        }
        return true;
    }

}
