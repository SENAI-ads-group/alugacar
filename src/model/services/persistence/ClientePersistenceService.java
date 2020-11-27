package model.services.persistence;

import model.entidades.Cliente;

import java.util.List;
import model.services.persistence.exceptions.PersistenceException;

/**
 *
 * @author Alexsander
 */
public interface ClientePersistenceService {

    void inserir(Cliente cliente) throws PersistenceException;

    void atualizar(Cliente cliente);

    Cliente buscar(Integer id);

    List<Cliente> buscarTodos();

}
