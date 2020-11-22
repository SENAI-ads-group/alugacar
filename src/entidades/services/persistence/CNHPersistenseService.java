package entidades.services.persistence;

import entidades.CNH;
import entidades.services.persistence.exceptions.PersistenceException;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CNHPersistenseService {

    void inserir(CNH cnh) throws PersistenceException;

    void atualizar(CNH cnh);

    CNH buscar(Integer id);

    List<CNH> buscarTodos();

}
