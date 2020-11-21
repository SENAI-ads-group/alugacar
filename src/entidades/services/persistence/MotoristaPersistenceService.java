package entidades.services.persistence;

import entidades.Motorista;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface MotoristaPersistenceService {

    void inserir(Motorista motorista);

    void atualizar(Motorista motorista);

    Motorista buscar(Integer id);

    List<Motorista> buscarTodos();

}
