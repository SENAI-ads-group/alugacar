package model.entidades;

import model.services.persistence.PersistenceFactory;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class Modelo {

    private Integer id;
    private String codigoFipe;
    private String descricao;
    private Marca marca;

    public Modelo(Integer id, Marca marca, String codigoFipe, String descricao) {
        this.id = id;
        this.marca = marca;
        this.codigoFipe = codigoFipe;
        this.descricao = descricao;
    }

    public Modelo(Integer id, Marca marca, String descricao) {
        this.id = id;
        this.marca = marca;
        this.descricao = descricao;
    }

    public Modelo(Marca marca, String descricao) {
        this.marca = marca;
        this.descricao = descricao;
    }

    public Modelo(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        codigoFipe = csv[1];
        descricao = csv[2];
        Integer idMarca = Utilities.tryParseToInteger(csv[3]);
        marca = PersistenceFactory.createMarcaService().buscar(idMarca);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toCSV() {
        return "" + id + ";"
                + codigoFipe + ";"
                + descricao + ";"
                + marca.getId();
    }

}
