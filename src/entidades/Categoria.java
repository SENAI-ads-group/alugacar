package entidades;

/**
 *
 * @author usuario
 */
public class Categoria {

    private Integer id;
    private String descricao;
    private Double valorMinimoLocacao;

    public Categoria(Integer id, String descricao, Double valorMinimoLocacao) {
        this.id = id;
        this.descricao = descricao;
        this.valorMinimoLocacao = valorMinimoLocacao;
    }

    public Categoria(String descricao, Double valorMinimoLocacao) {
        this.descricao = descricao;
        this.valorMinimoLocacao = valorMinimoLocacao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorMinimoLocacao() {
        return valorMinimoLocacao;
    }

    public void setValorMinimoLocacao(Double valorMinimoLocacao) {
        this.valorMinimoLocacao = valorMinimoLocacao;
    }

    public String toCSV() {
        return "" + id + ";"
                + descricao + ";"
                + valorMinimoLocacao;
    }

}
