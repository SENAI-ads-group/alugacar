package model.entidades;

/**
 *
 * @author patrick-ribeiro
 */
public class ItemVistoria {

    private String descricao;
    private boolean adequado;

    public ItemVistoria() {
    }

    public ItemVistoria(String descricao, boolean adequado) {
        this.descricao = descricao;
        this.adequado = adequado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAdequado() {
        return adequado;
    }

    public void setAdequado(boolean adequado) {
        this.adequado = adequado;
    }

}
