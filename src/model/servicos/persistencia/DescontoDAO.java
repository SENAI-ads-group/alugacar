package model.servicos.persistencia;

import java.util.List;
import model.entidades.Taxa;

/**
 *
 * @author patrick-ribeiro
 */
public interface DescontoDAO {

    void inserir(Taxa taxa);

    void atualizar(Taxa taxa);

    void excluir(Integer id);

    Taxa buscar(Integer id);

    List<Taxa> buscarTodos();
}
