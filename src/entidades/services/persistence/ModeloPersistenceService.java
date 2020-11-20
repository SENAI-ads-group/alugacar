package entidades.services.persistence;

import entidades.Marca;
import entidades.Modelo;
import java.util.List;

/**
 *
 * @author patrick-ribeiro
 */
public interface ModeloPersistenceService {

    void inserir(Modelo modelo);

    void atualizar(Modelo modelo);

    Modelo buscar(Integer id);

    List<Modelo> buscar(Marca marca);

    List<Modelo> buscarTodos();
}
