package model.entidades;

import java.util.Objects;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class ItemVistoria {

    private Integer id;
    private String nome;
    private String descricao;
    private boolean obrigatorio;
    private boolean adequado;

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public ItemVistoria() {
    }

    public ItemVistoria(String descricao, boolean obrigatorio) {
        this.nome = descricao;
        this.obrigatorio = obrigatorio;
    }

    public ItemVistoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        nome = csv[1];
        descricao = csv[2];
        obrigatorio = Boolean.parseBoolean(csv[3]);
        adequado = Boolean.parseBoolean(csv[4]);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters">  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public boolean isAdequado() {
        return adequado;
    }

    public void setAdequado(boolean adequado) {
        this.adequado = adequado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hashCode">  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + (this.obrigatorio ? 1 : 0);
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
        final ItemVistoria other = (ItemVistoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

    public String toCSV() {
        return "" + id + ";"
                + nome + ";"
                + descricao + ";"
                + obrigatorio + ";"
                + adequado;
    }

    @Override
    public String toString() {
        String string = (adequado) ? "adequado" : "não adequado";
        return nome + " " + string;
    }

}
