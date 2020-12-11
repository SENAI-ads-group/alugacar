package model.entidades;

import model.entidades.enums.UF;
import model.servicos.persistencia.DAOFactory;
import java.util.Date;
import java.util.Objects;
import util.DateUtilities;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Motorista {

    public static final int IDADE_MINIMA = 18;

    private Integer id;
    private boolean ativo;
    private PessoaFisica pessoa;
    private CNH cnh;

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Motorista() {
        pessoa = new PessoaFisica();
    }

    public Motorista(Integer id, PessoaFisica pessoa, CNH cnh, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.cnh = cnh;
        this.ativo = ativo;
    }

    public Motorista(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        ativo = Boolean.parseBoolean(csv[1]);
        cnh = DAOFactory.createCnhDAO().buscar(csv[2]);
        pessoa = instanciarPessoa(csv);
    }

    private PessoaFisica instanciarPessoa(String[] csv) {
        String nome = csv[3];
        String telefone = csv[4];
        String email = csv[5];
        String logradouro = csv[6];
        String numero = csv[7];
        String complemento = csv[8];
        String bairro = csv[9];
        String cidade = csv[10];
        UF uf = UF.valueOf(csv[11]);
        String cep = csv[12];
        String cpf = csv[13];
        String registroGeral = csv[14];
        Date dataNascimento = DateUtilities.tryParseToDate(csv[15]);

        Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
        return new PessoaFisica(nome, telefone, email, endereco, cpf, registroGeral, dataNascimento);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters">  
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hashCode">  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Motorista other = (Motorista) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

    public String toCSV() {
        return "" + id + ";"
                + ativo + ";"
                + cnh.getNumeroRegistro() + ";"
                + pessoa.toCSV();
    }

    @Override
    public String toString() {
        return id + " - " + pessoa.getNome();
    }
}
