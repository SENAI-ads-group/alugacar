package entidades;

import entidades.enums.CategoriaUsuario;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Usuario {
    
    private Integer id;
    private String senha;
    private boolean ativo;
    private CategoriaUsuario categoria;
    private PessoaFisica pessoa;
    
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
    
    public Usuario(String[] csv, PessoaFisica pessoa) {
        id = Utilities.tryParseToInteger(csv[0]);
        senha = csv[1];
        ativo = Boolean.parseBoolean(csv[2]);
        categoria = CategoriaUsuario.valueOf(csv[3]);
        this.pessoa = pessoa;
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
                + senha + ";"
                + ativo + ";"
                + categoria.toString() + ";"
                + pessoa.toCSV();
    }
}
