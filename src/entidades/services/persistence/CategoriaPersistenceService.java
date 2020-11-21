package entidades.services.persistence;

import entidades.Categoria;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CategoriaPersistenceService {

    void inserir(Categoria categoria);

    void atualizar(Categoria categoria);

    Categoria buscar(Integer id);

    List<Categoria> buscarTodos();

}
