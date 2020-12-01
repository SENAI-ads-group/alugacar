package model.entidades;

import model.entidades.enums.Combustivel;
import model.servicos.persistencia.DAOFactory;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class Modelo {

    private Integer id;
    private String codigoFipe;
    private String descricao;
    private Marca marca;
    private Categoria categoria;
    private Integer ano;
    private Combustivel combustivel;

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Modelo() {
    }

    public Modelo(Integer id, String codigoFipe, String descricao, Marca marca, Categoria categoria, Combustivel combustivel, Integer ano) {
        this.id = id;
        this.codigoFipe = codigoFipe;
        this.descricao = descricao;
        this.marca = marca;
        this.categoria = categoria;
        this.combustivel = combustivel;
        this.ano = ano;
    }

    public Modelo(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        codigoFipe = csv[1];
        descricao = csv[2];
        Integer idMarca = Utilities.tryParseToInteger(csv[3]);
        marca = DAOFactory.createMarcaService().buscar(idMarca);
        Integer idCategoria = Utilities.tryParseToInteger(csv[4]);
        categoria = DAOFactory.createCategoriaService().buscar(idCategoria);
        ano = Utilities.tryParseToInteger(csv[5]);
        combustivel = Combustivel.valueOf(csv[6]);        
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="getters e setters">  
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
        if(marca == null) {
            throw new IllegalStateException("A marca está nula");
        }
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
    // </editor-fold>

    public String toCSV() {
        //id;codigoFipe;descricao;idMarca;idCategoria;ano;combustivel
        return "" + id + ";"
                + codigoFipe + ";"
                + descricao + ";"
                + marca.getId() + ";"
                + categoria.getId() + ";"
                + ano + ";"
                + combustivel.toCSV();
    }

}
