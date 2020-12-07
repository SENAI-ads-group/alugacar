package model.servicos.persistencia;

import java.util.List;
import model.entidades.ItemVistoria;

/**
 *
 * @author patrick-ribeiro
 */
public interface ItemVistoriaDAO {

    void inserir(ItemVistoria item);

    void atualizar(ItemVistoria item);

    void excluir(Integer id);

    ItemVistoria buscar(Integer id);

    List<ItemVistoria> buscarTodos();
}
