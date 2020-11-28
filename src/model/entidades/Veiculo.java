package model.entidades;

import model.entidades.enums.StatusVeiculo;
import model.services.persistence.PersistenceFactory;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class Veiculo {

    private Integer id;
    private String placa;
    private String renavam;
    private Double precoCompra;
    private Integer anoFabricacao;
    private Integer anoModelo;
    private Double quilometragemRodada;
    private StatusVeiculo statusVeiculo = StatusVeiculo.INDISPONIVEL;
    private Modelo modelo;

    public Veiculo() {
    }

    public Veiculo(String placa, String renavam, Modelo modelo, int anoFabricacao, int anoModelo, Double quilometragemRodada) {
        this.placa = placa;
        this.renavam = renavam;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.quilometragemRodada = quilometragemRodada;
    }

    public Veiculo(Integer id, String placa, String renavam, Modelo modelo, int anoFabricacao, int anoModelo, Double quilometragemRodada) {
        this.id = id;
        this.placa = placa;
        this.renavam = renavam;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.quilometragemRodada = quilometragemRodada;
    }

    public Veiculo(String[] csv) {
        id = Integer.parseInt(csv[0]);
        placa = csv[1];
        renavam = csv[2];
        precoCompra = Utilities.tryParseToDouble(csv[3]);
        anoFabricacao = Utilities.tryParseToInteger(csv[4]);
        anoModelo = Utilities.tryParseToInteger(csv[5]);
        quilometragemRodada = Utilities.tryParseToDouble(csv[6]);
        statusVeiculo = StatusVeiculo.valueOf(csv[7]);
        Integer idModelo = Utilities.tryParseToInteger(csv[8]);
        modelo = PersistenceFactory.createModeloService().buscar(idModelo);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public Double getQuilometragemRodada() {
        return quilometragemRodada;
    }

    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public void adicionarQuilometragem(double quilometragem) {
        quilometragemRodada += quilometragem;
    }

    public String toCSV() {
        return "" + id + ";"
                + placa + ";"
                + renavam + ";"
                + precoCompra + ";"
                + anoFabricacao + ";"
                + anoModelo + ";"
                + quilometragemRodada + ";"
                + statusVeiculo.toString() + ";"
                + modelo.getId();
    }

}
