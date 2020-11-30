package model.servicos.contrato;

import java.util.ArrayList;
import java.util.List;
import model.entidades.Desconto;
import model.entidades.Locacao;
import model.entidades.Taxa;

/**
 *
 * @author patrick-ribeiro
 */
public class ContratoKM implements ContratoService {

    private Locacao locacao;
    private final List<Taxa> taxasList = new ArrayList<>();
    private final List<Desconto> descontosList = new ArrayList<>();

    @Override
    public Locacao getLocacao() {
        if (this.locacao == null) {
            throw new IllegalStateException("A locação está nula");
        }
        return locacao;
    }

    @Override
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public void addTaxa(Taxa taxa) {
        taxasList.add(taxa);
    }

    @Override
    public void removeTaxa(Taxa taxa) {
        taxasList.remove(taxa);
    }

    @Override
    public double getValorTaxas() {
        double valorTotal = 0;
        for (Taxa taxa : taxasList) {
            valorTotal += taxa.getValor();
        }
        return valorTotal;
    }

    @Override
    public List<Taxa> getTaxas() {
        return taxasList;
    }

    @Override
    public void addDesconto(Desconto desconto) {
        descontosList.add(desconto);
    }

    @Override
    public void removeDesconto(Desconto desconto) {
        descontosList.remove(desconto);
    }

    @Override
    public double getValorDescontos() {
        double valorTotal = 0;
        for (Desconto desconto : descontosList) {
            valorTotal += desconto.getValor();
        }
        return valorTotal;
    }

    @Override
    public List<Desconto> getDescontos() {
        return descontosList;
    }

    @Override
    public double getValorBruto() {
        double kmRodado = locacao.getVistoriaDevolucao().getKmVeiculo() - locacao.getVistoriaEntrega().getKmVeiculo();
        double valorKM = locacao.getVeiculo().getModelo().getCategoria().getValorKM();
        return kmRodado * valorKM;
    }

}
