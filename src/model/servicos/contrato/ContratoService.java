package model.servicos.contrato;

import java.util.List;
import model.entidades.Desconto;
import model.entidades.ItemVistoria;
import model.entidades.Locacao;
import model.entidades.Taxa;

/**
 *
 * @author patrick-ribeiro
 */
public interface ContratoService {

    Locacao getLocacao();

    void setLocacao(Locacao locacao);

    void addTaxa(Taxa taxa);

    void removeTaxa(Taxa taxa);

    double getValorTaxas();

    List<Taxa> getTaxas();

    void addDesconto(Desconto desconto);

    void removeDesconto(Desconto desconto);

    double getValorDescontos();

    List<Desconto> getDescontos();

    double getValorBruto();

    default double getValorTotal() {
        checarVistorias();
        return (getValorBruto() + getValorTaxas()) - getValorDescontos();
    }

    default void checarVistorias() {
        List<ItemVistoria> itensRetirada = getLocacao().getVistoriaRetirada().getItens();
        List<ItemVistoria> itensDevolucao = getLocacao().getVistoriaDevolucao().getItens();

        for (int i = 0; i < itensRetirada.size(); i++) {
            ItemVistoria itemRetirada = itensRetirada.get(i);
            ItemVistoria itemDevolucao = itensDevolucao.get(i);

            if (itemRetirada.isAdequado()) {
                if (!itemDevolucao.isAdequado()) {
                    addTaxa(Taxa.getTaxaItemVistoria());
                }
            } else {
                addDesconto(Desconto.getDescontoItemVistoria());
            }
        }
    }
}
