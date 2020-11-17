package entidades;

import entidades.abstracts.Pessoa;

/**
 *
 * @author usuario
 */
public class PessoaFisica extends Pessoa {

    private String cpf;
    private Integer registroGeral;

    public PessoaFisica(String nome) {
        super(nome);
    }

    public PessoaFisica(String nome, String telefone, String email, Endereco endereco) {
        super(nome, telefone, email, endereco);
    }

    public PessoaFisica(String cpf, Integer registroGeral, String nome) {
        super(nome);
        this.cpf = cpf;
        this.registroGeral = registroGeral;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getRegistroGeral() {
        return registroGeral;
    }

    public void setRegistroGeral(Integer registroGeral) {
        this.registroGeral = registroGeral;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";"
                + cpf + ";"
                + registroGeral;
    }

}
