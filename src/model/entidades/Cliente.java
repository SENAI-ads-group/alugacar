package model.entidades;

import java.util.Date;
import java.util.Objects;
import model.entidades.enums.TipoCliente;
import model.entidades.enums.UF;
import util.DateUtilities;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Cliente {

    public static final int IDADE_MINIMA = 18;

    private Integer id;
    private Pessoa pessoa;
    private boolean ativo = true;
    private final TipoCliente tipoCliente;

    public Cliente(TipoCliente tipo) {
        this.tipoCliente = tipo;
        if (tipo == TipoCliente.PESSOA_FISICA) {
            pessoa = new PessoaFisica();
        } else if (tipo == TipoCliente.PESSOA_JURIDICA) {
            pessoa = new PessoaJuridica();
        }
    }

    public Cliente(Integer id, Pessoa pessoa, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.ativo = ativo;
        if (pessoa instanceof PessoaFisica) {
            tipoCliente = TipoCliente.PESSOA_FISICA;
        } else {
            tipoCliente = TipoCliente.PESSOA_JURIDICA;
        }
    }

    public Cliente(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        ativo = Boolean.parseBoolean(csv[1]);
        tipoCliente = TipoCliente.valueOf(csv[2]);
        if (tipoCliente == TipoCliente.PESSOA_JURIDICA) {
            pessoa = instanciarPessoaJuridica(csv);
        } else {
            pessoa = instanciarPessoaFisica(csv);
        }
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

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public String toCSV() {
        return "" + id + ";"
                + ativo + ";"
                + tipoCliente.toCSV() + ";"
                + pessoa.toCSV();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + pessoa.getNome();
    }

    private PessoaJuridica instanciarPessoaJuridica(String[] csv) {
        String nome = csv[3];
        String telefone = csv[4];
        String email = csv[5];
        String logradouro = csv[6];
        Integer numeroEndereco = Utilities.tryParseToInteger(csv[7]);
        String complemento = csv[8];
        String bairro = csv[9];
        String cidade = csv[10];
        UF uf = UF.valueOf(csv[11]);
        String cep = csv[12];
        String cnpj = csv[13];
        String razaoSocial = csv[14];
        String inscricaoEstadual = csv[15];

        Endereco endereco = new Endereco(logradouro, numeroEndereco, complemento, bairro, cidade, uf, cep);
        return new PessoaJuridica(nome, telefone, email, endereco, cnpj, razaoSocial, inscricaoEstadual);
    }

    private PessoaFisica instanciarPessoaFisica(String[] csv) {
        String nome = csv[3];
        String telefone = csv[4];
        String email = csv[5];
        String logradouro = csv[6];
        Integer numeroEndereco = Utilities.tryParseToInteger(csv[7]);
        String complemento = csv[8];
        String bairro = csv[9];
        String cidade = csv[10];
        UF uf = UF.valueOf(csv[11]);
        String cep = csv[12];
        String cpf = csv[13];
        String registroGeral = csv[14];
        Date dataNascimento = DateUtilities.tryParseToDate(csv[15]);

        Endereco endereco = new Endereco(logradouro, numeroEndereco, complemento, bairro, cidade, uf, cep);
        return new PessoaFisica(nome, telefone, email, endereco, cpf, registroGeral, dataNascimento);
    }
}
