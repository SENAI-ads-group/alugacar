package model.services.persistence;

import model.entidades.Motorista;
import model.services.persistence.exceptions.PersistenceException;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface MotoristaPersistenceService {

    void inserir(Motorista motorista) throws PersistenceException;

    void atualizar(Motorista motorista);

    Motorista buscar(Integer id);

    List<Motorista> buscarTodos();

}
