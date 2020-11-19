package entidades;

import entidades.enums.UF;

/**
 *
 * @author usuario
 */
public class Endereco {

    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private UF uf;
    private String cep;

    public Endereco(String logradouro, Integer numero, String bairro, String cidade, UF uf) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(String logradouro, Integer numero, String complemento, String bairro, String cidade, UF uf, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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
