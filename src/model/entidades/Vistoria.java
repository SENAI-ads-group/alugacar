package model.entidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.servicos.persistencia.DAOFactory;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class Vistoria {

    private Integer id;
    private List<ItemVistoria> itens = DAOFactory.createItemVistoriaDAO().buscarTodos();
    private double kmVeiculo;
    private double quantidadeCombustivel;
    private File[] imagens = new File[6];

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Vistoria() {
    }

    public Vistoria(Integer id, double kmVeiculo) {
        this.id = id;
        this.kmVeiculo = kmVeiculo;
    }

    public Vistoria(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        kmVeiculo = Utilities.tryParseToDouble(csv[1]);
        quantidadeCombustivel = Utilities.tryParseToDouble(csv[2]);
        itens = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters">  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemVistoria> getItens() {
        return itens;
    }

    public void setItens(List<ItemVistoria> itens) {
        this.itens = itens;
    }

    public ItemVistoria getItem(int index) {
        return itens.get(index);
    }

    public double getKmVeiculo() {
        return kmVeiculo;
    }

    public void setKmVeiculo(double kmVeiculo) {
        this.kmVeiculo = kmVeiculo;
    }

    public double getQuantidadeCombustivel() {
        return quantidadeCombustivel;
    }

    public void setQuantidadeCombustivel(double quantidadeCombustivel) {
        this.quantidadeCombustivel = quantidadeCombustivel;
    }

    public File[] getImagens() {
        return imagens;
    }

    public void setImagens(File[] imagens) {
        this.imagens = imagens;
    }

    public void setImagem(int index, File imagem) {
        imagens[index] = imagem;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hashCode">  
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Vistoria other = (Vistoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

    public void addItem(ItemVistoria item) {
        itens.add(item);
    }

    public void removeItem(ItemVistoria item) {
        itens.remove(item);
    }

    public boolean isAdequada() {
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
                + kmVeiculo + ";"
                + quantidadeCombustivel;
    }

}
