package entidades;

import entidades.abstracts.Pessoa;

/**
 *
 * @author usuario
 */
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private Integer inscricaoEstadual;

    public PessoaJuridica(String nome) {
        super(nome);
    }

    public PessoaJuridica(String nome, String telefone, String email, Endereco endereco) {
        super(nome, telefone, email, endereco);
    }

    public PessoaJuridica(String cnpj, String razaoSocial, Integer inscricaoEstadual, String nome) {
        super(nome);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public PessoaJuridica(String cnpj, String razaoSocial, String nomeFantasia, String nome) {
        super(nome);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Integer getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(Integer inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String toCSV() {
        return super.toCSV() + ";"
                + cnpj + ";"
                + nomeFantasia + ";"
                + razaoSocial + ";"
                + inscricaoEstadual;
    }

}
