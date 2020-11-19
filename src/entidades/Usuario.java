package entidades;

import entidades.enums.CategoriaUsuario;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Usuario {

    private Integer id;
    private PessoaFisica pessoa;
    private String senha;
    private CategoriaUsuario categoria;
    private boolean ativo;

    public Usuario() {
    }

    public Usuario(Integer id, PessoaFisica pessoa, CategoriaUsuario categoria, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.categoria = categoria;
        this.ativo = ativo;
    }

    public Usuario(Integer id, PessoaFisica pessoa, String senha, CategoriaUsuario categoria, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.senha = senha;
        this.categoria = categoria;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CategoriaUsuario getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaUsuario categoria) {
        this.categoria = categoria;
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
                + senha + ";"
                + categoria.toString() + ";"
                + ativo;
    }
}
