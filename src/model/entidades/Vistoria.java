package model.entidades;

import application.Configuracoes;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class Vistoria {

    private Integer id;
    private List<ItemVistoria> itens;
    private double kmVeiculo;

    public Vistoria(double kmVeiculo) {
        this.kmVeiculo = kmVeiculo;
        itens = Configuracoes.loadItensVistoria();
    }

    public Vistoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        kmVeiculo = Utilities.tryParseToDouble(csv[1]);
        itens = new ArrayList<>();
    }

    public void addItem(ItemVistoria item) {
        itens.add(item);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemVistoria> getItens() {
        return itens;
    }

    public double getKmVeiculo() {
        return kmVeiculo;
    }

    public void setKmVeiculo(double kmVeiculo) {
        this.kmVeiculo = kmVeiculo;
    }

    public boolean isVeiculoAdequado() {
        boolean adequado = true;
        for (ItemVistoria item : itens) {
            if (!item.isAdequado() && item.isObrigatorio()) {
                adequado = false;
                break;
            }
        }
        return adequado;
    }

    public String toCSV() {
        return "" + id + ";"
                + kmVeiculo;
    }

}
