package model.entidades;

import model.entidades.enums.UF;
import model.services.persistence.PersistenceFactory;
import java.io.File;
import java.util.Date;
import java.util.Objects;
import util.DateUtilities;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Motorista {

    private Integer id;
    private boolean ativo;
    private PessoaFisica pessoa;
    private File foto;
    private CNH cnh;

    public static final int IDADE_MINIMA = 18;

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
        foto = new File(csv[2]);
        Integer numeroRegistroCNH = Utilities.tryParseToInteger(csv[3]);
        cnh = PersistenceFactory.createCNHService().buscar(numeroRegistroCNH);
        pessoa = instanciarPessoa(csv);
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
        String caminhoFoto = null;
        if (foto != null) {
            caminhoFoto = foto.getPath();
        }
        return "" + id + ";"
                + ativo + ";"
                + caminhoFoto + ";"
                + cnh.getNumeroRegistro() + ";"
                + pessoa.toCSV();
    }

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
