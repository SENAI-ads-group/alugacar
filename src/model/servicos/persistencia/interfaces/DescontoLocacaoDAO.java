package model.servicos.persistencia.interfaces;

import model.entidades.Desconto;
import model.entidades.Locacao;

/**
 *
 * @author patrick-ribeiro
 */
public interface DescontoLocacaoDAO {

    void importar(Locacao locacao);

    void exportar(Locacao locacao, Desconto desconto);

    void atualizarLocacao(Locacao locacao);

}
