package model.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author patrick-ribeiro
 */
public class Vistoria {

    private Integer id;
    private double kmVeiculo;
    private Date data;
    private List<ItemVistoria> itens = new ArrayList<>();

    public Vistoria() {
    }

    public Vistoria(Integer id, double kmVeiculo, Date data) {
        this.id = id;
        this.kmVeiculo = kmVeiculo;
        this.data = data;
    }

    public Vistoria(String[] csv) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getKmVeiculo() {
        return kmVeiculo;
    }

    public void setKmVeiculo(double kmVeiculo) {
        this.kmVeiculo = kmVeiculo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemVistoria> getItens() {
        return itens;
    }

    public void addItem(ItemVistoria item) {
        itens.add(item);
    }

    public void removeItem(ItemVistoria item) {
        itens.remove(item);
    }

    public String toCSV() {
        return null;
    }

}
