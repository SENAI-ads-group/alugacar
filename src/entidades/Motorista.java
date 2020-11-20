package entidades;

import java.io.File;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Motorista {

    private Integer id;
    private PessoaFisica pessoa;
    private File foto;
    private CNH cnh;
    private boolean ativo;

    public Motorista() {
    }

    public Motorista(Integer id, PessoaFisica pessoa, CNH cnh, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.cnh = cnh;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public CNH getCnh() {
        return cnh;
    }

    public void setCnh(CNH cnh) {
        this.cnh = cnh;
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
                + foto.getAbsolutePath() + ";"
                + cnh.toCSV() + ";"
                + ativo;
    }
}
