package model.servicos.persistencia;

import model.entidades.Locacao;
import model.entidades.Taxa;

/**
 *
 * @author patrick-ribeiro
 */
public interface DescontoLocacaoDAO {

    void importar(Locacao locacao);

    void exportar(Locacao locacao, Taxa taxa);

    void atualizarLocacao(Locacao locacao);

}
