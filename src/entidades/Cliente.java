package entidades;

import entidades.abstracts.Pessoa;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Cliente {

    private Integer id;
    private Pessoa pessoa;
    private boolean ativo;

    public Cliente() {
    }

    public Cliente(Integer id, Pessoa pessoa, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String toCSV() {
        return "" + id + ";"
                + pessoa.toCSV() + ";"
                + ativo;
    }
}
