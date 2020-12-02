package model.entidades;

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
                + obrigatorio
                + adequado;
    }

}
