package model.entidades;

/**
 *
 * @author Patrick-Ribeiro
 */
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    // <editor-fold defaultstate="collapsed" desc="construtores">
    public PessoaJuridica() {
    }

    public PessoaJuridica(String nome) {
        super(nome);
    }

    public PessoaJuridica(String nome, String telefone, String email, Endereco endereco) {
        super(nome, telefone, email, endereco);
    }

    public PessoaJuridica(String cnpj, String razaoSocial, String inscricaoEstadual, String nome) {
        super(nome);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public PessoaJuridica(String nome, String telefone, String email, Endereco endereco, String cnpj, String razaoSocial, String inscricaoEstadual) {
        super(nome, telefone, email, endereco);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters">
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

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
    // </editor-fold>

    @Override
    public String toCSV() {
        return super.toCSV() + ";"
                + cnpj + ";"
                + razaoSocial + ";"
                + inscricaoEstadual;
    }

}
