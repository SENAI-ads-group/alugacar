package model.entidades;

import java.util.Objects;
import model.entidades.enums.StatusVeiculo;
import model.servicos.persistencia.DAOFactory;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class Veiculo {

    private Integer id;
    private String placa;
    private String renavam;
    private Modelo modelo;
    private Integer anoFabricacao;
    private Double KMRodado;
    private StatusVeiculo statusVeiculo = StatusVeiculo.INDISPONIVEL;

    // <editor-fold defaultstate="collapsed" desc="construtores">
    public Veiculo() {
    }

    public Veiculo(Integer id, String placa, String renavam, Modelo modelo, Integer anoFabricacao, Double KMRodado) {
        this.id = id;
        this.placa = placa;
        this.renavam = renavam;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.KMRodado = KMRodado;
    }

    public Veiculo(String[] csv) {
        id = Integer.parseInt(csv[0]);
        placa = csv[1];
        renavam = csv[2];
        Integer idModelo = Utilities.tryParseToInteger(csv[3]);
        modelo = DAOFactory.createModeloDAO().buscar(idModelo);
        anoFabricacao = Utilities.tryParseToInteger(csv[4]);
        KMRodado = Utilities.tryParseToDouble(csv[5]);
        statusVeiculo = StatusVeiculo.valueOf(csv[6]);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters">
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

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Double getKMRodado() {
        return KMRodado;
    }

    public void setKMRodado(Double KMRodado) {
        this.KMRodado = KMRodado;
    }

    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hashCode">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

    public void addKM(double km) {
        KMRodado += km;
    }

    public String toCSV() {
        return "" + id + ";"
                + placa + ";"
                + renavam + ";"
                + modelo.getId() + ";"
                + anoFabricacao + ";"
                + KMRodado + ";"
                + statusVeiculo.toCSV();
    }

    @Override
    public String toString() {
        return modelo.getDescricao() + " " + modelo.getAno() + " " + placa;
    }

}
