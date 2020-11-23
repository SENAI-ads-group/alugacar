package entidades;

import entidades.services.persistence.PersistenceFactory;
import java.io.File;
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

    public Motorista() {
        pessoa = new PessoaFisica();
    }

    public Motorista(Integer id, PessoaFisica pessoa, CNH cnh, boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.cnh = cnh;
        this.ativo = ativo;
    }

    public Motorista(String[] csv, PessoaFisica pessoa) {
        id = Utilities.tryParseToInteger(csv[0]);
        ativo = Boolean.parseBoolean(csv[1]);
        foto = new File(csv[2]);
        Integer numeroRegistroCNH = Utilities.tryParseToInteger(csv[3]);
        cnh = PersistenceFactory.createCNHService().buscar(numeroRegistroCNH);
        this.pessoa = pessoa;
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
                + ativo + ";"
                + foto.getAbsolutePath() + ";"
                + cnh.getNumeroRegistro() + ";"
                + pessoa.toCSV();
    }
}
