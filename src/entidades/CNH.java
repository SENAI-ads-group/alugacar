package entidades;

import entidades.enums.CategoriaCNH;
import java.util.Date;
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
}
