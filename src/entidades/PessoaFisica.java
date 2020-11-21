package entidades;

import java.util.Date;
import util.DateUtilities;

/**
 *
 * @author usuario
 */
public class PessoaFisica extends Pessoa {

    private String cpf;
    private Integer registroGeral;
    private Date dataNascimento;

    public PessoaFisica(String nome) {
        super(nome);
    }

    public PessoaFisica(String nome, String telefone, String email, Endereco endereco) {
        super(nome, telefone, email, endereco);
    }

    public PessoaFisica(String nome, String telefone, String email, Endereco endereco, String cpf, Integer registroGeral, Date dataNascimento) {
        super(nome, telefone, email, endereco);
        this.cpf = cpf;
        this.registroGeral = registroGeral;
        this.dataNascimento = dataNascimento;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";"
                + cpf + ";"
                + DateUtilities.formatData(dataNascimento) + ";"
                + registroGeral;
    }

}
