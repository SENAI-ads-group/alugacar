package model.entidades;

import model.entidades.enums.UF;

/**
 *
 * @author usuario
 */
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private UF uf;
    private String cep;

    // <editor-fold defaultstate="collapsed" desc="construtores"> 
    public Endereco() {
    }

    public Endereco(String logradouro, String numero, String bairro, String cidade, UF uf) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, UF uf, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters"> 
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    // </editor-fold>

    public String toCSV() {
        return logradouro + ";"
                + numero + ";"
                + complemento + ";"
                + bairro + ";"
                + cidade + ";"
                + uf.toString() + ";"
                + cep;
    }

}
