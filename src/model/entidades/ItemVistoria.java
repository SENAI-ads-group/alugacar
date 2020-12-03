package model.entidades;

import java.util.Objects;

/**
 *
 * @author patrick-ribeiro
 */
public class ItemVistoria {

    private String descricao;
    private boolean obrigatorio;
    private boolean adequado;

    public ItemVistoria(String descricao, boolean obrigatorio) {
        this.descricao = descricao;
        this.obrigatorio = obrigatorio;
    }

    public ItemVistoria(String[] csv) {
        descricao = csv[0];
        obrigatorio = Boolean.parseBoolean(csv[1]);
        adequado = Boolean.parseBoolean(csv[2]);
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

    public String toCSV() {
        return descricao + ";"
                + obrigatorio + ";"
                + adequado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.descricao);
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
        if (this.obrigatorio != other.obrigatorio) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

}
