package model.entidades;

import java.util.Date;
import model.entidades.enums.CategoriaUsuario;
import model.entidades.enums.UF;
import util.DateUtilities;
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

    public Usuario(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        senha = csv[1];
        ativo = Boolean.parseBoolean(csv[2]);
        categoria = CategoriaUsuario.valueOf(csv[3]);
        pessoa = instanciarPessoa(csv);
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

    private PessoaFisica instanciarPessoa(String[] csv) {
        String nome = csv[4];
        String telefone = csv[5];
        String email = csv[6];
        String logradouro = csv[7];
        Integer numero = Utilities.tryParseToInteger(csv[8]);
        String complemento = csv[9];
        String bairro = csv[10];
        String cidade = csv[11];
        UF uf = UF.valueOf(csv[12]);
        String cep = csv[13];
        String cpf = csv[14];
        String registroGeral = csv[15];
        Date dataNascimento = DateUtilities.tryParseToDate(csv[16]);

        Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
        return new PessoaFisica(nome, telefone, email, endereco, cpf, registroGeral, dataNascimento);
    }
}
