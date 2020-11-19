package entidades;

/**
 *
 * @author usuario
 */
public class Modelo {

    private Integer id;
    private Marca marca;
    private String codigoFipe;
    private String descricao;

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

    public Integer getId() {
        return id;
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
                + marca.getId() + ";"
                + codigoFipe + ";"
                + descricao;
    }

}
