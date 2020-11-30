package model.entidades;

/**
 *
 * @author patrick-ribeiro
 */
public class Vistoria {

    private final double kmVeiculo;
    private final boolean veiculoAdequado;

    public Vistoria(double kmVeiculo, boolean veiculoAdequado) {
        this.kmVeiculo = kmVeiculo;
        this.veiculoAdequado = veiculoAdequado;
    }

    public double getKmVeiculo() {
        return kmVeiculo;
    }

    public boolean isVeiculoAdequado() {
        return veiculoAdequado;
    }

    public String toCSV() {
        return kmVeiculo + ";"
                + veiculoAdequado;
    }

}
