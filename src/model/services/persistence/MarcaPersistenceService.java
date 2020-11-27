package model.services.persistence;

import model.entidades.Marca;
import model.services.persistence.exceptions.PersistenceException;
import java.util.List;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface MarcaPersistenceService {

    void inserir(Marca marca) throws PersistenceException;

    void atualizar(Marca marca);

    Marca buscar(Integer id);

    List<Marca> buscarTodos();
}
