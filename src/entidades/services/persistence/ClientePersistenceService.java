package entidades.services.persistence;

import entidades.Cliente;
import entidades.services.persistence.exceptions.PersistenceException;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface ClientePersistenceService  {

    void inserir(Cliente cliente) throws PersistenceException;

    void atualizar(Cliente cliente);

    Cliente buscar(Integer id);

    List<Cliente> buscarTodos();

}
