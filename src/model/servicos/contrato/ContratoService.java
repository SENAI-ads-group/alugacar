package model.servicos.contrato;

import java.util.List;
import model.entidades.Desconto;
import model.entidades.Locacao;
import model.entidades.Taxa;
import model.entidades.enums.StatusLocacao;

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
        if (getLocacao().getStatus() != StatusLocacao.FINALIZADA) {
            throw new IllegalStateException("A locação deve ser finalizada para o processamento");
        }
        if (getLocacao().getVistoriaEntrega().isAdequada()) {
            if (getLocacao().getVistoriaDevolucao().isAdequada()) {
                addTaxa(Taxa.getTaxaItemVistoria());
            }
        } else {
            addDesconto(Desconto.getDescontoItemVistoria());
        }
    }
}
