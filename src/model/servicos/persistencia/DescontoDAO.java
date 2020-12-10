package model.servicos.persistencia;

import java.util.List;
import model.entidades.Desconto;

/**
 *
 * @author patrick-ribeiro
 */
public interface DescontoDAO {

    void inserir(Desconto desconto);

    void atualizar(Desconto desconto);

    Desconto buscar(Integer id);

    List<Desconto> buscarTodos();
}
